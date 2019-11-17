package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteActor;

public class MenuStage extends MyStage {
    ArrayList<OneSpriteActor> myActors = new ArrayList<OneSpriteActor>();

    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        //Egy fade-del jön majd be a menü a logok után
    }

    @Override
    public void init() {

    }

    public ArrayList<OneSpriteActor> getMyActors()
    {
        return myActors;
    }
}
