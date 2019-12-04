package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.InfoStage;
import hu.cehessteg.vizeromu.Stage.MenuStage;
import hu.cehessteg.vizeromu.Stage.OptionsStage;
import hu.cehessteg.vizeromu.Stage.ShopStage;
import hu.cehessteg.vizeromu.Stage.WeatherBackground;

import static hu.cehessteg.vizeromu.Stage.GameStage.matek;
import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;
    GameStage gameStage;
    InfoStage infoStage;
    OptionsStage optionsStage;
    ShopStage shopStage;
    WeatherBackground weatherBackground;

    public static float demoElapsed = 0;

    private boolean jojjonCaution = true;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        infoStage = new InfoStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        optionsStage = new OptionsStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        shopStage = new ShopStage(new FitViewport(keparanySzelesvaszonra(),720),game);

        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20),game);
        weatherBackground = new WeatherBackground(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),game);

        gameStage.gameMusic.stop();

        menuStage.menuMusic.setLooping(true);
        menuStage.menuMusic.setVolume(0.5f);
        if(!OptionsStage.isMuted()) {
            menuStage.menuMusic.play();
            gameStage.gameMusic.stop();
        }
        else menuStage.menuMusic.stop();
        menuStage.addBackButtonScreenBackByStackPopListener();
    }

    @Override
    public void show() {
        super.show();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menuStage);
        inputMultiplexer.addProcessor(infoStage);
        inputMultiplexer.addProcessor(optionsStage);
        inputMultiplexer.addProcessor(shopStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        whatToDraw(delta);
        backToMenuFromAnotherStage();
        menuStage.act(delta);
        menuStage.draw();
    }

    void backToMenuFromAnotherStage()
    {
        if(infoStage.isMehetvissza()) {
            menuStage.setMehetVissza(true);
            menuStage.setJojjonCaution(true);
            infoStage.setMehetvissza(false);
        }

        if(optionsStage.isMehetVissza()) {
            menuStage.setMehetVissza(true);
            menuStage.setJojjonCaution(true);
            optionsStage.setMehetVissza(false);
        }

        if(shopStage.isMehetVissza()) {
            menuStage.setMehetVissza(true);
            menuStage.setJojjonCaution(true);
            shopStage.setMehetVissza(false);
        }
    }

    void whatToDraw(float delta)
    {
        if(menuStage.isDrawGame() && menuStage.felsoSign.getY() < menuStage.getViewport().getWorldHeight()) drawDemoGame(delta);
        else if(menuStage.isDrawInfo()) {
            drawDemoGame(delta);
            infoStage.draw();
        }
        else if(menuStage.isDrawOptions()) {
            drawDemoGame(delta);
            optionsStage.act(delta);
            optionsStage.draw();
        }
        else if(menuStage.isDrawShop()) {
            drawDemoGame(delta);
            shopStage.act(delta);
            shopStage.draw();
        }
    }

    void drawDemoGame(float delta)
    {
        gameStage.act(delta);

        weatherBackground.setTime(matek.getTime());
        weatherBackground.setRain(matek.getRain());
        weatherBackground.act(delta);

        weatherBackground.draw();
        gameStage.draw();

        gameStage.setStill(true);
    }

    public boolean isJojjonCaution() {
        return jojjonCaution;
    }

    public void setJojjonCaution(boolean jojjonCaution) {
        this.jojjonCaution = jojjonCaution;
        menuStage.setJojjonCaution(this.jojjonCaution);
    }
}
