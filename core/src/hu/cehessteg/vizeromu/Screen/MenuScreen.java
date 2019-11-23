package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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

    public static float demoElapsed = 0;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        infoStage = new InfoStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
        optionsStage = new OptionsStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);

        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20),spriteBatch,game);
        weatherBackground = new WeatherBackground(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menuStage);
        inputMultiplexer.addProcessor(infoStage);
        inputMultiplexer.addProcessor(optionsStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(menuStage.isDrawGame() && menuStage.felsoSign.getY() < menuStage.getViewport().getWorldHeight()) {
            gameStage.act(delta);

            weatherBackground.setTime(matek.getTime());
            weatherBackground.setRain(matek.getRain());
            weatherBackground.act(delta);

            weatherBackground.draw();
            gameStage.draw();

            gameStage.setStill(true);
        }
        else if(menuStage.isDrawInfo()) infoStage.draw();
        else if(menuStage.isDrawOptions()) optionsStage.draw();
        if(infoStage.isMehetvissza()) {
            menuStage.setMehetVissza(true);
            infoStage.setMehetvissza(false);
        }
        if(optionsStage.isMehetVissza()) {
            menuStage.setMehetVissza(true);
            optionsStage.setMehetVissza(false);
        }
        menuStage.act(delta);
        menuStage.draw();
    }
}
