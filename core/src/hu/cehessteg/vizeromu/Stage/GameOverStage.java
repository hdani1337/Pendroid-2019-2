package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.Actor.Gomb;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;
import hu.cehessteg.vizeromu.Screen.MenuScreen;

import static hu.cehessteg.vizeromu.Vizeromu.gameSave;

public class GameOverStage extends MyStage {
    MyLabel gameOver;
    Gomb restart;
    Gomb menu;
    OneSpriteStaticActor background;
    CautionSign felso;
    CautionSign also;
    Ajto ajto;
    MyLabel napok;
    int napokInt = 0;

    public GameOverStage(Viewport viewport, final MyGame game) {
        super(viewport, game);
        assignment();
        setPositions();
        addActors();
        addListeners();
    }

    void assignment()
    {
        gameOver = new MyLabel("Vége a játéknak!", Styles.getCalibriLabelStyle());
        restart = new Gomb("Újraindítás", this);
        menu = new Gomb("Kilépés",this);
        napok = new MyLabel("",Styles.getCalibriLabelStyle());
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BLUE_TEXTURE));
        background.setColor(0,0,0,0.5f);
        background.setDebug(false);
        felso = new CautionSign((byte)2,getViewport());
        also = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
    }

    void setPositions()
    {
        gameOver.setAlignment(0);
        gameOver.setPosition(getViewport().getWorldWidth()/2-gameOver.getWidth()/2,getViewport().getWorldHeight()/2+gameOver.getHeight()*2.5f);
        restart.setPosition(getViewport().getWorldWidth()/2-restart.getWidth()/2,getViewport().getWorldHeight()/2);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,restart.getY()-120);
        ajto.setX(-ajto.getWidth()*1.25f);
        background.setPosition(0,0);
        background.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    void addActors()
    {
        addActor(background);
        addActor(gameOver);
        addActor(restart);
        addActor(menu);
        addActor(ajto);
        addActor(felso);
        addActor(also);
        addActor(napok);

        menu.myLabel.setPosition(menu.getX()+menu.getWidth()/2-menu.myLabel.getWidth()/2,menu.getY()+menu.getHeight()/2-menu.myLabel.getHeight()/2);
        menu.myLabel.setZIndex(menu.getZIndex()+1);

        restart.myLabel.setPosition(restart.getX()+restart.getWidth()/2-restart.myLabel.getWidth()/2,restart.getY()+restart.getHeight()/2-restart.myLabel.getHeight()/2);
        restart.myLabel.setZIndex(restart.getZIndex()+1);

        napok.setAlignment(0);
        napok.setPosition(getViewport().getWorldWidth()/2-napok.getWidth()/2,getViewport().getWorldHeight()*0.2f);
    }

    void addListeners()
    {
        restart.addListener(new ClickListener(){
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
        });
    }

    @Override
    public void init() {

    }

    int lastRecord = gameSave.getInteger("rekordNapok");

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
                if (alpha > 0.0275) alpha -= 0.0275;
                else {
                    alpha = 0;
                    game.setScreen(new GameScreen(game));
                    isContinueGame = false;
                }
            }
        }
        if(ajto.getX() >= getViewport().getWorldWidth()-ajto.getWidth()) {
            MenuScreen menuScreen = new MenuScreen(game);
            menuScreen.setJojjonCaution(false);
            game.setScreen(menuScreen);
        }

        if(!napok.getText().equals(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben.")) {
            napok.setText(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben.");
        }

        if(napokInt > gameSave.getInteger("rekordNapok")) {
            if(!napok.getText().equals(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben,\nezzel megdöntve a rekordodat, ami " + lastRecord + " volt.")) {
                napok.setText(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben,\nezzel megdöntve a rekordodat, ami " + lastRecord + " volt.");
                gameSave.putInteger("rekordNapok", napokInt);
                gameSave.flush();
            }
        }
    }

    public void setNapokInt(int napokInt) {
        this.napokInt = napokInt;
    }

    public int getNapokInt() {
        return napokInt;
    }
}
