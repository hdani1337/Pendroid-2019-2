package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.Actor.GatAlja;
import hu.cehessteg.vizeromu.Actor.Viz;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameOverScreen;

import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.fuggvenyekNull;
import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.vizcseppek;
import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.worldThread;

public class GameStage extends MyStage {
    World esoWorld;
    World kifolyoWorld;
    public static Matek matek;
    Gat gat;
    GatAlja gatAlja;
    Viz viz;
    Viz patak;

    public GameStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        addActors();
        fuggvenyekNull();
    }

    void assignment()
    {
        esoWorld = new World(new Vector2(0,-1), false);
        kifolyoWorld = new World(new Vector2(0,-1), false);
        matek = new Matek();
        gat = new Gat();
        gatAlja = new GatAlja();
        viz = new Viz()
        {
            @Override
            public void act(float delta) {
                super.act(delta);
                setHeight((matek.getVizmennyiseg()/matek.getMaxviz())*25);
                setWidth((matek.getVizmennyiseg()/matek.getMaxviz())*75);
                setX(12-(getWidth()/2));
                setY(17-(getHeight()/2));
            }
        };
        patak = new Viz()
        {
            @Override
            public void act(float delta) {
                super.act(delta);
                setHeight((float)(matek.getPatakVizmennyiseg()*0.0001));
                setWidth((float)(matek.getPatakVizmennyiseg()*0.0004));
                setX(36-(getWidth()/2));
                setY(-(getHeight()/2));
                System.out.println(matek.getPatakVizmennyiseg());
            }
        };
        patak.sprite.setTexture(Assets.manager.get(Assets.VIZ2));
    }

    void addActors()
    {
        addActor(viz);
        addActor(gat);
        addActor(gatAlja);
        addActor(patak);
        gat.setZIndex(1);
        viz.setZIndex(0);
        patak.setZIndex(0);
        gatAlja.setZIndex(3);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        worldThread(delta,kifolyoWorld,esoWorld);
        matek.step(delta * 36);
        vizcseppek(esoWorld,kifolyoWorld,this,matek,elapsedTime,viz,patak);
        if(matek.isGameover()) game.setScreen(new GameOverScreen(game));
    }
}
