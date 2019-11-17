package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.Actor.GatAlja;
import hu.cehessteg.vizeromu.Actor.Viz;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;

import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.vizcseppThread;

public class GameStage extends MyStage {
    World world;
    public static Matek matek;
    Gat gat;
    GatAlja gatAlja;
    Viz viz;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        addActors();
    }

    void assignment()
    {
        world = new World(new Vector2(0,-1), false);
        matek = new Matek();
        gat = new Gat();
        gatAlja = new GatAlja();
        viz = new Viz();
    }

    void addActors()
    {
        addActor(viz);
        addActor(gat);
        addActor(gatAlja);
        gat.setZIndex(1);
        gatAlja.setZIndex(3);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta,10,10);
        matek.step();
        vizcseppThread(world,this,matek,elapsedTime);
    }
}
