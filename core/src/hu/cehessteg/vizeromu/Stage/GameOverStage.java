package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;
import hu.cehessteg.vizeromu.Screen.MenuScreen;

public class GameOverStage extends MyStage {
    MyLabel gameOver;
    MyButton restart;
    MyButton menu;
    OneSpriteStaticActor background;
    CautionSign felso;
    CautionSign also;
    Ajto ajto;

    public GameOverStage(Viewport viewport, Batch batch, final MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addActors();
        addListeners();
    }

    void assignment()
    {
        gameOver = new MyLabel("Vége a játéknak!", Styles.getCalibriLabelStyle());
        restart = new MyButton("Újra!", Styles.getTextButtonStyle());
        menu = new MyButton("Főmenü", Styles.getTextButtonStyle());
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
        gameOver.setPosition(getViewport().getWorldWidth()/2-gameOver.getWidth()/2,getViewport().getWorldHeight()/2+gameOver.getHeight()/2);
        restart.setPosition(getViewport().getWorldWidth()/2-restart.getWidth()/2,gameOver.getY()-75);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,restart.getY()-75);
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
    }

    void addListeners()
    {
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
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
