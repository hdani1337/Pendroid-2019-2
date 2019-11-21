package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.InfoStage;

import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class InfoScreen extends MyScreen {
    InfoStage infoStage;
    public InfoScreen(MyGame game) {
        super(game);
        infoStage = new InfoStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(infoStage);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        infoStage.act(delta);
        infoStage.draw();
    }
}
