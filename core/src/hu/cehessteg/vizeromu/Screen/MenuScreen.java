package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.Stage.MenuStage;

import static hu.cehessteg.vizeromu.Vizeromu.keparanySzelesvaszonra;

public class MenuScreen extends MyScreen {
    MenuStage menuStage;

    public MenuScreen(MyGame game) {
        super(game);
        menuStage = new MenuStage(new FitViewport(keparanySzelesvaszonra(),720),spriteBatch,game);
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
        menuStage.act(delta);
        menuStage.draw();
    }
}
