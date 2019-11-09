package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameStage;

import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class GameScreen extends MyScreen {
    GameStage gameStage;

    public GameScreen(MyGame game) {
        super(game);
        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/80.0f,720/80.0f),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameStage.act(delta);
        gameStage.draw();
    }
}
