package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.Vector;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameOverStage;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.HudStage;
import hu.cehessteg.vizeromu.Stage.PauseStage;
import hu.cehessteg.vizeromu.Stage.WeatherBackground;
import hu.cehessteg.vizeromu.Stage.WeatherForeGround;

import static hu.cehessteg.vizeromu.Stage.GameStage.matek;
import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class GameScreen extends MyScreen {
    GameStage gameStage;
    GameOverStage gameOverStage;
    WeatherForeGround weatherForeGround;
    WeatherBackground weatherBackground;
    PauseStage pauseStage;
    HudStage hudStage;

    InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public GameScreen(MyGame game) {
        super(game);
        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
        weatherForeGround = new WeatherForeGround(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
        weatherBackground = new WeatherBackground(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
        gameOverStage = new GameOverStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        pauseStage = new PauseStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        hudStage = new HudStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        gameStage.setStill(false);
    }

    @Override
    public void show() {
        super.show();
        inputMultiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(!gameStage.matek.isGameover() && !gameStage.isGamePaused()) {
            gameStage.act(delta);
            hudStage.act(delta);
        }

        weatherBackground.setTime(matek.getTime());
        weatherBackground.setRain(matek.getRain());
        weatherBackground.act(delta);

        weatherForeGround.setTime(matek.getTime());
        weatherForeGround.setRain(matek.getRain());
        weatherForeGround.act(delta);

        weatherBackground.draw();
        gameStage.draw();
        weatherForeGround.draw();
        hudStage.draw();

        if (gameStage.matek.isGameover()) {
            if(!inputMultiplexer.getProcessors().contains(gameOverStage,true)) {
                inputMultiplexer.addProcessor(gameOverStage);
                inputMultiplexer.removeProcessor(gameStage);
                inputMultiplexer.removeProcessor(hudStage);
            }
            gameOverStage.draw();
        }

        if (gameStage.isGamePaused()) {
            if(!inputMultiplexer.getProcessors().contains(pauseStage,true)) {
                inputMultiplexer.addProcessor(pauseStage);
                inputMultiplexer.removeProcessor(hudStage);
                inputMultiplexer.removeProcessor(gameStage);
            }
            pauseStage.draw();
        }
        else {
            if(!inputMultiplexer.getProcessors().contains(hudStage, true))
                inputMultiplexer.addProcessor(hudStage);
            if(!inputMultiplexer.getProcessors().contains(gameStage, true))
                inputMultiplexer.addProcessor(gameStage);
            if(inputMultiplexer.getProcessors().contains(pauseStage,true))
                inputMultiplexer.removeProcessor(pauseStage);
        }

    }
}
