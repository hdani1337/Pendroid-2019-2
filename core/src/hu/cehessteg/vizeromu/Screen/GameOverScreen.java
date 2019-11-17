package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameOverStage;

import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class GameOverScreen extends MyScreen {
    GameOverStage gameOverStage;

    public GameOverScreen(MyGame game) {
        super(game);
        gameOverStage = new GameOverStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameOverStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameOverStage.draw();
        gameOverStage.act(delta);
    }
}
