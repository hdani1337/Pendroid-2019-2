package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.GameOverStage;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.HudStage;
import hu.cehessteg.vizeromu.Stage.MenuStage;
import hu.cehessteg.vizeromu.Stage.OptionsStage;
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
        gameStage = new GameStage(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f), game);
        weatherForeGround = new WeatherForeGround(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),game);
        weatherBackground = new WeatherBackground(new FitViewport(keparanySzelesvaszonra()/20.0f,720/20.0f),game);
        gameOverStage = new GameOverStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        pauseStage = new PauseStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        hudStage = new HudStage(new FitViewport(keparanySzelesvaszonra(),720),game);
        gameStage.setStill(false);

        gameStage.gameMusic.setLooping(true);
        gameStage.gameMusic.setVolume(0.5f);
        if(!OptionsStage.isMuted()) {
            MenuStage.menuMusic.stop();
            gameStage.gameMusic.play();
        }
        else gameStage.gameMusic.stop();
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

        ifGameOver(delta);
        ifPaused(delta);
        simulation();
    }

    void ifGameOver(float delta)
    {
        if (gameStage.matek.isGameover()) {
            if(!inputMultiplexer.getProcessors().contains(gameOverStage,true)) {
                inputMultiplexer.removeProcessor(gameStage);
                inputMultiplexer.removeProcessor(hudStage);
                inputMultiplexer.addProcessor(gameOverStage);
            }
            if(!OptionsStage.isMuted()) gameStage.gameMusic.pause();
            if(gameOverStage.getNapokInt() != hudStage.getNapok()) gameOverStage.setNapokInt(hudStage.getNapok());
            Fuggvenyek.rainSound.setVolume(0);
            Fuggvenyek.rainSound.stop();
            gameOverStage.act(delta);
            gameOverStage.draw();
        }
    }

    void ifPaused(float delta)
    {
        if (gameStage.isGamePaused()) {
            if(!inputMultiplexer.getProcessors().contains(pauseStage,true)) {
                inputMultiplexer.addProcessor(pauseStage);
                inputMultiplexer.removeProcessor(hudStage);
                inputMultiplexer.removeProcessor(gameStage);
                pauseStage.setAlpha(0);
            }
            if(!OptionsStage.isMuted()) {
                gameStage.gameMusic.pause();
                Fuggvenyek.rainSound.pause();
            }
            Fuggvenyek.rainSound.setVolume(0);
            Fuggvenyek.rainSound.stop();
            pauseStage.act(delta);
            pauseStage.draw();
        }
        else if(!gameStage.isGamePaused() && !gameStage.matek.isGameover()){
            if(!inputMultiplexer.getProcessors().contains(hudStage, true))
                inputMultiplexer.addProcessor(hudStage);
            if(!inputMultiplexer.getProcessors().contains(gameStage, true))
                inputMultiplexer.addProcessor(gameStage);
            if(inputMultiplexer.getProcessors().contains(pauseStage,true))
                inputMultiplexer.removeProcessor(pauseStage);
        }
    }

    void simulation()
    {
        if(gameStage.getSimulationSpeed() != hudStage.getSimulationSpeed()) gameStage.setSimulationSpeed(hudStage.getSimulationSpeed());
    }
}
