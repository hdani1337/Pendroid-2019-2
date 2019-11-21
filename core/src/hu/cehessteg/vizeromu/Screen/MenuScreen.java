package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteActor;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.InfoStage;
import hu.cehessteg.vizeromu.Stage.MenuStage;
import hu.cehessteg.vizeromu.Stage.OptionsStage;
import hu.cehessteg.vizeromu.Stage.WeatherBackground;
import hu.cehessteg.vizeromu.Stage.WeatherForeGround;

import static hu.cehessteg.vizeromu.Stage.GameStage.matek;
import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;
    GameStage gameStage;
    InfoStage infoStage;
    OptionsStage optionsStage;
    WeatherBackground weatherBackground;
    WeatherForeGround weatherForeGround;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        infoStage = new InfoStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        optionsStage = new OptionsStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);

        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20),spriteBatch,game);
        weatherForeGround = new WeatherForeGround(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
        weatherBackground = new WeatherBackground(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(menuStage.isDrawGame() && !menuStage.isFinished()) {
            gameStage.act(delta);

            weatherBackground.setTime(matek.getTime());
            weatherBackground.setRain(matek.getRain());
            weatherBackground.act(delta);

            weatherForeGround.setTime(matek.getTime());
            weatherForeGround.setRain(matek.getRain());
            weatherForeGround.act(delta);

            weatherBackground.draw();
            gameStage.draw();
            weatherForeGround.draw();

            gameStage.setStill(true);
        }
        else if(menuStage.isDrawInfo() && !menuStage.isFinished()) infoStage.draw();
        else if(menuStage.isDrawOptions() && !menuStage.isFinished()) optionsStage.draw();
        menuStage.act(delta);
        menuStage.draw();
    }
}
