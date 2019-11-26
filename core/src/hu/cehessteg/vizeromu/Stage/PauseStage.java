package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Menu;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;
import hu.cehessteg.vizeromu.Screen.MenuScreen;

public class PauseStage extends MyStage {
    MyLabel myLabel;
    MyButton continueGame;
    MyButton menu;
    OneSpriteStaticActor background;
    CautionSign felso;
    CautionSign also;
    Ajto ajto;

    public PauseStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addListeners();
        addActors();
    }

    void assignment()
    {
        myLabel = new MyLabel("Megállítva", Styles.getCalibriLabelStyle());
        continueGame = new MyButton("A játék folytatása", Styles.getTextButtonStyle());
        menu = new MyButton("Kilépés a menübe", Styles.getTextButtonStyle());
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BLUE_TEXTURE));
        background.setColor(0,0,0,0.5f);
        background.setDebug(false);
        felso = new CautionSign((byte)2,getViewport());
        also = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
    }

    void setPositions()
    {
        myLabel.setAlignment(0);
        myLabel.setPosition(getViewport().getWorldWidth()/2-myLabel.getWidth()/2,getViewport().getWorldHeight()/2+myLabel.getHeight());
        continueGame.setPosition(getViewport().getWorldWidth()/2-continueGame.getWidth()/2,myLabel.getY()-75);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,continueGame.getY()-100);
        ajto.setX(-ajto.getWidth()*1.25f);
        background.setPosition(0,0);
        background.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    void addActors()
    {
        addActor(background);
        addActor(myLabel);
        addActor(continueGame);
        addActor(menu);
        addActor(ajto);
        addActor(felso);
        addActor(also);
    }

    void addListeners()
    {
        continueGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameStage.setGamePaused(false);
                if(!OptionsStage.isMuted()) {
                    GameStage.gameMusic.play();
                    Fuggvenyek.rainSound.play();
                }
            }
        });

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
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

    @Override
    public void act(float delta) {
        super.act(delta);
        if(ajto.getX() >= getViewport().getWorldWidth()-ajto.getWidth()) {
            MenuScreen menuScreen = new MenuScreen(game);
            menuScreen.setJojjonCaution(false);
            game.setScreen(menuScreen);
        }
    }
}
