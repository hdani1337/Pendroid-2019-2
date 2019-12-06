package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.Actor.Gomb;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.MenuScreen;

import static hu.cehessteg.vizeromu.Vizeromu.gameSave;

public class PauseStage extends MyStage {
    MyLabel myLabel;
    Gomb continueGame;
    Gomb menu;
    OneSpriteStaticActor background;
    CautionSign felso;
    CautionSign also;
    OneSpriteStaticActor frozenClock;
    Ajto ajto;

    public PauseStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        assignment();
        setPositions();
        addListeners();
        addActors();
        addBackButtonListener(new BackButtonListener() {
            @Override
            public void backKeyDown() {
                leaveGame();
            }
        });
    }

    void assignment()
    {
        frozenClock = new OneSpriteStaticActor(Assets.manager.get(Assets.FAGYOTTORA));
        frozenClock.setDebug(false);
        myLabel = new MyLabel("Megállítva", Styles.getCalibriLabelStyle());
        continueGame = new Gomb("Folytatás", this);
        menu = new Gomb("Kilépés", this);
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BLUE_TEXTURE));
        background.setColor(0,0,0,0.5f);
        background.setDebug(false);
        felso = new CautionSign((byte)2,getViewport());
        also = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
    }

    void setPositions()
    {
        frozenClock.setSize(frozenClock.getWidth()/2,frozenClock.getHeight()/2);
        frozenClock.setPosition(getViewport().getWorldWidth()-frozenClock.getWidth(),0);
        myLabel.setAlignment(0);
        myLabel.setPosition(getViewport().getWorldWidth()/2-myLabel.getWidth()/2,getViewport().getWorldHeight()/1.7f+myLabel.getHeight());
        continueGame.setPosition(getViewport().getWorldWidth()/2-continueGame.getWidth()/2,myLabel.getY()-120);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,continueGame.getY()-120);
        ajto.setX(-ajto.getWidth()*1.25f);
        background.setPosition(0,0);
        background.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    void addActors()
    {
        addActor(frozenClock);
        addActor(background);
        addActor(myLabel);
        addActor(continueGame);
        addActor(menu);
        addActor(ajto);
        addActor(felso);
        addActor(also);

        menu.myLabel.setPosition(menu.getX()+menu.getWidth()/2-menu.myLabel.getWidth()/2,menu.getY()+menu.getHeight()/2-menu.myLabel.getHeight()/2);
        menu.myLabel.setZIndex(ajto.getZIndex()-2);

        continueGame.myLabel.setPosition(continueGame.getX()+continueGame.getWidth()/2-continueGame.myLabel.getWidth()/2,continueGame.getY()+continueGame.getHeight()/2-continueGame.myLabel.getHeight()/2);
        continueGame.myLabel.setZIndex(ajto.getZIndex()-2);
    }


    void leaveGame(){
        for (int i = 0; i < GameStage.matek.getNyilasok().length; i++)
        {
            gameSave.putBoolean("csoOpen" + (i+1), GameStage.matek.getNyilasok()[i].isOpen);
        }
        gameSave.putFloat("vizmennyiseg", GameStage.matek.getVizmennyiseg());
        gameSave.putInteger("coins", GameStage.matek.coins);
        gameSave.flush();
        ajto.setMove(true);
        ajto.setMoveIn(true);
        ajto.setMoveOut(false);
        felso.setMove(true);
        also.setMove(true);
        felso.setMoveDown(true);
        also.setMoveDown(true);
        felso.setMoveUp(false);
        also.setMoveUp(false);
    }

    void addListeners()
    {
        continueGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                isContinueGame = true;
            }
        });

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                leaveGame();
            }
        });
    }

    @Override
    public void init() {

    }

    float alpha = 0;
    boolean isContinueGame = false;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(ajto.isMoveIn())
        {
            for (Actor actor : this.getActors())
            {
                if(actor instanceof Gomb || actor instanceof MyLabel)
                    actor.setColor(1,1,1,alpha);
            }
            if(alpha >= 0.0275) alpha -= 0.0275;
            else  alpha = 0;
        }
        else
        {
            if(!isContinueGame) {
                for (Actor actor : this.getActors()) {
                    if (actor instanceof Gomb || actor instanceof MyLabel)
                        actor.setColor(1, 1, 1, alpha);
                    if (actor.getWidth() == getViewport().getWorldWidth() && actor.getHeight() == getViewport().getWorldHeight())
                        actor.setColor(0, 0, 0, alpha / 2);
                }
                frozenClock.setColor(1,1,1,alpha);
                if (alpha < 1 - 0.0275) alpha += 0.0275;
                else alpha = 1;
            }
            else
            {
                for (Actor actor : this.getActors()) {
                    if (actor instanceof Gomb || actor instanceof MyLabel)
                        actor.setColor(1, 1, 1, alpha);
                    if (actor.getWidth() == getViewport().getWorldWidth() && actor.getHeight() == getViewport().getWorldHeight())
                        actor.setColor(0, 0, 0, alpha / 2);
                }
                frozenClock.setColor(1,1,1,alpha);
                if (alpha > 0.0275) alpha -= 0.0275;
                else {
                    alpha = 0;
                    GameStage.setGamePaused(false);
                    if(!OptionsStage.isMuted()) {
                        GameStage.gameMusic.play();
                        Fuggvenyek.rainSound.play();
                    }
                    isContinueGame = false;
                }
            }
        }

        if(ajto.getX() >= getViewport().getWorldWidth()-ajto.getWidth()) {
            final MenuScreen menuScreen = new MenuScreen(game);
            //menuScreen.setJojjonCaution(false);
            //game.setScreen(menuScreen);
            try {
                game.setScreenBackByStackPop(new MyGame.ScreenInit() {
                    @Override
                    public void init(MyScreen scr) {
                        if (scr instanceof MenuScreen) {
                            ((MenuScreen) scr).setJojjonCaution(false);
                        }
                        else
                        {
                            menuScreen.setJojjonCaution(false);
                            game.setScreen(menuScreen);
                        }
                    }
                });
            }
            catch (Exception e)
            {
                e.printStackTrace();
                menuScreen.setJojjonCaution(false);
                game.setScreen(menuScreen);
            }
        }
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
