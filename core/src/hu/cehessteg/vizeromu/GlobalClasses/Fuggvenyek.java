package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Random;

import hu.cehessteg.vizeromu.Actor.KifeleVizcsepp;
import hu.cehessteg.vizeromu.Actor.Viz;
import hu.cehessteg.vizeromu.Actor.Vizcsepp;
import hu.cehessteg.vizeromu.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Stage.OptionsStage;
import hu.cehessteg.vizeromu.Vizeromu;

import static hu.cehessteg.vizeromu.Stage.GameStage.isGamePaused;
import static hu.cehessteg.vizeromu.Stage.GameStage.matek;

public class Fuggvenyek {
    public static void fuggvenyekNull()
    {
        pElapsedEso = 0;
        pElapsed0 = 0;
        pElapsed1 = 0;
        pElapsed2 = 0;
        pElapsed3 = 0;
        pElapsed4 = 0;
    }

    static float deltaTime;
    public static Music rainSound;

    public static void worldThread(float delta, final World kifolyoWorld, final World esoWorld) {
        deltaTime = delta;
        rainSound = Assets.manager.get(Assets.ESO);
        rainSound.setLooping(true);
        Runnable kifolyas = new Runnable() {
            @Override
            public void run() {
                kifolyoWorld.step(deltaTime, 10, 10);
            }
        };
        Runnable eso = new Runnable() {
            @Override
            public void run() {
                esoWorld.step(deltaTime,10,10);
            }
        };
        new Thread(eso).run();
        new Thread(kifolyas).run();
    }

    public static void vizcseppek(final World esoWorld, final World kifolyoWorld, final MyStage stage, final Matek matek, final float elapsedTime, final Viz viz, final Viz viz2)
    {
        addVizcsepp(esoWorld, stage, matek, elapsedTime, viz);
        addKifeleVizcsepp(kifolyoWorld, stage, matek, elapsedTime);
        removeVizcsepp(stage,viz);
        removeKifeleVizcsepp(stage,viz2);
    }

        static float pElapsedEso = 0;

        private static synchronized void addVizcsepp(World world, MyStage stage, Matek matek, float elapsedTime, Viz viz)
        {
            if (matek.getRain() > 0.05) {
                if (elapsedTime > pElapsedEso + (1 - matek.getRain()) /* && matek.isVolteso()*/) {
                    for (int i = 0; i < 1 + matek.getRain() * 3; i++) {
                        WorldActorGroup eso = new Vizcsepp(world);
                        if (eso == null) return;
                        eso.addToWorld();
                        eso.setPosition((float) (Math.random() * (viz.getX() + viz.getWidth() - 6) - 5.25), stage.getViewport().getWorldHeight() + 2f);
                        eso.getBody().applyForceToCenter(new Vector2(64000, -180000), false);
                        stage.addActor(eso);
                        eso.setZIndex(5);
                        pElapsedEso = elapsedTime;
                    }
                }
                if(!rainSound.isPlaying() && !OptionsStage.isMuted()) rainSound.play();
            }
            else if(rainSound.isPlaying()) rainSound.stop();
        }

        private static synchronized void removeVizcsepp(MyStage stage, Viz viz)
        {
            try {
                for (Actor esocsepp : stage.getActors()) {
                    if (esocsepp == null) return;
                    else {
                        if (esocsepp instanceof Vizcsepp) {
                            if (esocsepp.getY() < viz.getY() + Math.random()*(viz.getHeight()*0.7)) {
                                esocsepp.remove();
                            }
                        }
                    }
                }
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        private static synchronized void removeKifeleVizcsepp(MyStage stage, Viz viz2)
        {
            try {
                for (Actor kiesoVizcsepp : stage.getActors()) {
                    if (kiesoVizcsepp == null) return;
                    else {
                        if (kiesoVizcsepp instanceof KifeleVizcsepp) {
                            if(kiesoVizcsepp.getX() >= viz2.getX()) {
                                if (kiesoVizcsepp.getY() < viz2.getY() + Math.random()*(viz2.getHeight()*0.8)) {
                                    kiesoVizcsepp.remove();
                                }
                            }
                            else
                            {
                                if (kiesoVizcsepp.getY() < -kiesoVizcsepp.getHeight()) {
                                    kiesoVizcsepp.remove();
                                }
                            }
                        }
                    }
                }
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        static float pElapsed0 = 0;
        static float pElapsed1 = 0;
        static float pElapsed2 = 0;
        static float pElapsed3 = 0;
        static float pElapsed4 = 0;

        private static synchronized void addKifeleVizcsepp(World world, MyStage stage, Matek matek, float elapsedTime)
        {
           if (matek.getNyilasok()[0].isOpen && elapsedTime > pElapsed0 + 0.05f) {
                WorldActorGroup kifeleViz0 = new KifeleVizcsepp(world);
                if(kifeleViz0 == null) return;
                kifeleViz0.addToWorld();
                kifeleViz0.setPosition((float) (12.5 - Math.random()*2), (float) (10 + Math.random()*1.7));
                kifeleViz0.getBody().applyForceToCenter(new Vector2((GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz())*120000,-125000),false);
                stage.addActor(kifeleViz0);
                kifeleViz0.setZIndex(800);
                pElapsed0 = elapsedTime;
           }
           if (matek.getNyilasok()[1].isOpen && elapsedTime > pElapsed1 + 0.05f) {
                WorldActorGroup kifeleViz1 = new KifeleVizcsepp(world);
                if(kifeleViz1 == null) return;
                kifeleViz1.addToWorld();
                kifeleViz1.setPosition((float) (17.5 - Math.random()*2), (float) (11 + Math.random()*2));
                kifeleViz1.getBody().applyForceToCenter(new Vector2((GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz())*120000,-125000),false);
                stage.addActor(kifeleViz1);
                kifeleViz1.setZIndex(800);
                pElapsed1 = elapsedTime;
           }
           if (matek.getNyilasok()[2].isOpen && elapsedTime > pElapsed2 + 0.05f) {
                WorldActorGroup kifeleViz2 = new KifeleVizcsepp(world);
                if(kifeleViz2 == null) return;
                kifeleViz2.addToWorld();
                kifeleViz2.setPosition((float) (23.2 - Math.random()*2), (float) (12.1f + Math.random()*2));
                kifeleViz2.getBody().applyForceToCenter(new Vector2((GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz())*120000,-125000),false);
                stage.addActor(kifeleViz2);
                kifeleViz2.setZIndex(800);
                pElapsed2 = elapsedTime;
            }
            if (matek.getNyilasok()[3].isOpen && elapsedTime > pElapsed3 + 0.05f) {
                WorldActorGroup kifeleViz3 = new KifeleVizcsepp(world);
                if(kifeleViz3 == null) return;
                kifeleViz3.addToWorld();
                kifeleViz3.setPosition((float) (28.3 - Math.random()*2), (float) (12.7f + Math.random()*2));
                kifeleViz3.getBody().applyForceToCenter(new Vector2((GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz())*120000,-125000),false);
                stage.addActor(kifeleViz3);
                kifeleViz3.setZIndex(800);
                pElapsed3 = elapsedTime;
            }
            if (matek.getNyilasok()[4].isOpen && elapsedTime > pElapsed4 + 0.05f) {
                WorldActorGroup kifeleViz4 = new KifeleVizcsepp(world);
                if(kifeleViz4 == null) return;
                kifeleViz4.addToWorld();
                kifeleViz4.setPosition((float) (33.7 - Math.random()*2), (float) (13f + Math.random()*2));
                kifeleViz4.getBody().applyForceToCenter(new Vector2((GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz())*120000,-125000),false);
                stage.addActor(kifeleViz4);
                kifeleViz4.setZIndex(800);
                pElapsed4 = elapsedTime;
            }
        }
}
