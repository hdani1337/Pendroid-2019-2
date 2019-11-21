package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.Actor.Hegy;
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
    private boolean still = false;//Ez akkor lesz igaz, ha a GameStage a menü mögött van egy állóképként elhelyezve
    Gat gat;
    Viz viz;
    Viz patak;
    Hegy hegy1;
    Hegy hegy2;
    Hegy hegy3;

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
        viz = new Viz()
        {
            @Override
            public void act(float delta) {
                super.act(delta);
                setHeight((matek.getVizmennyiseg()/matek.getMaxviz())*25);
                setWidth((matek.getVizmennyiseg()/matek.getMaxviz())*75);
                setX(12-(getWidth()/2));
                setY(17.5f-(getHeight()/2));
            }
        };
        patak = new Viz()
        {
            @Override
            public void act(float delta) {
                super.act(delta);
                if(!still) {
                    setHeight((float) (matek.getPatakVizmennyiseg() * 0.0001));
                    setWidth((float) (matek.getPatakVizmennyiseg() * 0.0004));
                }//Csak a gát mögötti víz kapjon méretet, ha a menü mögött van a stage
                setX(36-(getWidth()/2));
                setY(-(getHeight()/2));
            }
        };
        patak.sprite.setTexture(Assets.manager.get(Assets.VIZ2));
        hegy1 = new Hegy((byte) 1);
        hegy2 = new Hegy((byte) 2);
        hegy3 = new Hegy((byte) 3);
    }

    void addActors()
    {
        addActor(viz);
        addActor(gat);
        addActor(patak);
        addActor(hegy1);
        addActor(hegy2);
        addActor(hegy3);
        gat.setZIndex(4);
        viz.setZIndex(0);
        patak.setZIndex(-2);
        hegy1.setZIndex(1);
        hegy2.setZIndex(2);
        hegy3.setZIndex(-1);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!still) {
            worldThread(delta, kifolyoWorld, esoWorld);
            matek.step(delta * 3600);
            vizcseppek(esoWorld, kifolyoWorld, this, matek, elapsedTime, viz, patak);
            if (matek.isGameover()) game.setScreen(new GameOverScreen(game));
        }
        else matek.step(delta*3600);
    }

    public void setStill(boolean still) {
        this.still = still;
    }
}
