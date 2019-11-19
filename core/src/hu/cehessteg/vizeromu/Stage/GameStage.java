package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.Actor.GatAlja;
import hu.cehessteg.vizeromu.Actor.Viz;
import hu.cehessteg.vizeromu.Actor.WeatherForeGround;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
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
    WeatherForeGround weatherForeGround;

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
        viz = new Viz();
        weatherForeGround = new WeatherForeGround(getWidth(), getHeight());
    }

    void addActors()
    {
        addActor(viz);
        addActor(gat);
        addActor(gatAlja);
        gat.setZIndex(1);
        viz.setZIndex(0);
        gatAlja.setZIndex(3);
        addActor(weatherForeGround);
        weatherForeGround.setZIndex(Integer.MAX_VALUE);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        worldThread(delta,kifolyoWorld,esoWorld);
        matek.step(delta*3600);
        vizcseppek(esoWorld,kifolyoWorld,this,matek,elapsedTime);
        if(matek.isGameover()) game.setScreen(new GameOverScreen(game));
        //A MyLabellel gondok vannak a túl kicsi viewport miatt
        weatherForeGround.setTime(matek.getTime());
        System.out.println(matek.getTimeToString());
    }
}
