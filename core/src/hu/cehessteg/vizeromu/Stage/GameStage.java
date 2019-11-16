package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;

import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.vizcseppThread;

public class GameStage extends MyStage {
    World world;
    Matek matek;
    //WorldBodyEditorLoader loader;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
    }

    void assignment()
    {
        System.out.println("World Width: " + getViewport().getWorldWidth());
        System.out.println("World Height: " + getViewport().getWorldHeight());
        world = new World(new Vector2(0,-1), false);
        matek = new Matek();
        //loader = new WorldBodyEditorLoader(Gdx.files.internal("fizika"));
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta,10,10);
        matek.step();
        vizcseppThread(world,this,elapsedTime,matek);
    }
}
