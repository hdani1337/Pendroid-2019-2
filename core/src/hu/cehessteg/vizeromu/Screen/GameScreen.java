package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.Vector;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.WeatherForeGround;

import static hu.cehessteg.vizeromu.Stage.GameStage.matek;
import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class GameScreen extends MyScreen {
    GameStage gameStage;
    WeatherForeGround weatherForeGround;

    public GameScreen(MyGame game) {
        super(game);
        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
        weatherForeGround = new WeatherForeGround(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
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
        weatherForeGround.setTime(matek.getH() * 60 + matek.getM());
        weatherForeGround.setRain(matek.getRain());
        weatherForeGround.act(delta);
        gameStage.draw();
        weatherForeGround.draw();
    }
}
