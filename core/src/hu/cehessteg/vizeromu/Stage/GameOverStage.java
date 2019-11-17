package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;

public class GameOverStage extends MyStage {
    MyLabel gameOver;
    MyButton restart;

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
    }

    void setPositions()
    {
        gameOver.setAlignment(0);
        gameOver.setPosition(getViewport().getWorldWidth()/2-gameOver.getWidth()/2,getViewport().getWorldHeight()/2-gameOver.getHeight()/2);
        restart.setPosition(getViewport().getWorldWidth()/2-restart.getWidth()/2,gameOver.getY()-75);
    }

    void addActors()
    {
        addActor(gameOver);
        addActor(restart);
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
    }

    @Override
    public void init() {

    }
}
