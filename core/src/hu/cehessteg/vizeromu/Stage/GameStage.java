package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.Actor.Hegy;
import hu.cehessteg.vizeromu.Actor.Kacsa;
import hu.cehessteg.vizeromu.Actor.Viz;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.Vizeromu;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.Screen.MenuScreen;

import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.fuggvenyekNull;
import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.vizcseppek;
import static hu.cehessteg.vizeromu.GlobalClasses.Fuggvenyek.worldThread;

public class GameStage extends MyStage {
    World esoWorld;
    World kifolyoWorld;
    public static Matek matek;
    private static boolean isGamePaused;
    private boolean still = false;//Ez akkor lesz igaz, ha a GameStage a menü mögött van egy állóképként elhelyezve
    Gat gat;
    Viz viz;
    Viz patak;
    Hegy hegy1;
    Hegy hegy2;
    Hegy hegy3;
    Kacsa kacsa;

    Gat.gatListenes elsoNyilas;
    Gat.gatListenes masodikNyilas;
    Gat.gatListenes harmadikNyilas;
    Gat.gatListenes negyedikNyilas;
    Gat.gatListenes otodikNyilas;

    public static Music gameMusic;
    private int simulationSpeed = 60;

    public GameStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        isGamePaused = false;
        assignment();
        addActors();
        fuggvenyekNull();
        addBackButtonListener(new BackButtonListener() {
            @Override
            public void backKeyDown() {
                setGamePaused(true);
            }
        });
    }

    void assignment()
    {
        esoWorld = new World(new Vector2(0,-1), false);
        kifolyoWorld = new World(new Vector2(0,-1), false);
        matek = new Matek();
        matek.addDemoTime(MenuScreen.demoElapsed);
        gat = new Gat();
        viz = new Viz()
        {
            @Override
            public void act(float delta) {
                super.act(delta);
                if(!still) {
                    setHeight((matek.getVizmennyiseg() / matek.getMaxviz()) * 20);
                    setWidth((matek.getVizmennyiseg() / matek.getMaxviz()) * 75);
                }
                else {
                    if(Vizeromu.gameSave.getFloat("vizmennyiseg") == 0) {
                        viz.setHeight((1000000 / 1100000.0f) * 20);
                        viz.setWidth((1000000 / 1100000.0f) * 75);
                    }
                    else
                    {
                        viz.setHeight((Vizeromu.gameSave.getFloat("vizmennyiseg") / 1100000.0f) * 20);
                        viz.setWidth((Vizeromu.gameSave.getFloat("vizmennyiseg") / 1100000.0f) * 75);
                    }
                }
                setX(17-(getWidth()/2));
                setY(16.46f-(getHeight()/2));
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

        elsoNyilas = new Gat.gatListenes((byte) 1);
        masodikNyilas = new Gat.gatListenes((byte) 2);
        harmadikNyilas = new Gat.gatListenes((byte) 3);
        negyedikNyilas = new Gat.gatListenes((byte) 4);
        otodikNyilas = new Gat.gatListenes((byte) 5);

        kacsa = new Kacsa();
        kacsa.setSize(kacsa.getWidth()/30,kacsa.getHeight()/30);
        kacsa.setPosition(patak.getX() + patak.getWidth()/2 - kacsa.getWidth()/2, patak.getY() + patak.getHeight()/1.6f - kacsa.getHeight()/2);

        gameMusic = Assets.manager.get(Assets.GAME_MUSIC);
    }

    void addActors()
    {
        addActor(viz);
        addActor(gat);
        addActor(patak);
        addActor(kacsa);
        addActor(hegy1);
        addActor(hegy2);
        addActor(hegy3);
        if(!still) {
            addActor(elsoNyilas);
            addActor(masodikNyilas);
            addActor(harmadikNyilas);
            addActor(negyedikNyilas);
            addActor(otodikNyilas);
        }
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
        if (!still) {
            if(matek.still == true) matek.still = false;
            worldThread(delta*(simulationSpeed/60.0f), kifolyoWorld, esoWorld, simulationSpeed);
            matek.step(delta * 36*simulationSpeed, simulationSpeed);
            vizcseppek(esoWorld, kifolyoWorld, this, matek, elapsedTime, viz, patak);
            kacsa.move(patak, simulationSpeed);
            if(alpha < 1) setAlphaForGatListeners();
        } else {
            if(matek.still == false) matek.still = true;
            matek.step(delta * 36*simulationSpeed, simulationSpeed);
            MenuScreen.demoElapsed += delta * 36*simulationSpeed;
            Fuggvenyek.rainSound.stop();
        }
    }

    //delta*36     1mp a játékban = 1mp a valóságban
    //delta*36*6   6mp a játékban = 1mp a valóságban ==> 1 nap a játékban = 10p a valóságban

    public void setStill(boolean still) {
        this.still = still;
    }

    public static boolean isGamePaused() {
        return isGamePaused;
    }

    public static void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }

    public int getSimulationSpeed() {
        return simulationSpeed;
    }

    public void setSimulationSpeed(int simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
    }

    float alpha = 0;

    void setAlphaForGatListeners()
    {
        if(alpha < 0.985) alpha += 0.0125;
        else alpha = 1;
        elsoNyilas.setColor(1,1,1,alpha);
        masodikNyilas.setColor(1,1,1,alpha);
        harmadikNyilas.setColor(1,1,1,alpha);
        negyedikNyilas.setColor(1,1,1,alpha);
        otodikNyilas.setColor(1,1,1,alpha);
    }
}
