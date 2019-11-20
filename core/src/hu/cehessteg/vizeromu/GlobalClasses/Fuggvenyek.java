package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Random;

import hu.cehessteg.vizeromu.Actor.KifeleVizcsepp;
import hu.cehessteg.vizeromu.Actor.Vizcsepp;
import hu.cehessteg.vizeromu.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.Vizeromu;

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

    public static void worldThread(float delta, final World kifolyoWorld, final World esoWorld) {
        deltaTime = delta;
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
        new Thread(kifolyas).run();
        new Thread(eso).run();
    }

    public static void vizcseppek(final World esoWorld, final World kifolyoWorld, final MyStage stage, final Matek matek, final float elapsedTime)
    {
        addVizcsepp(esoWorld, stage, matek, elapsedTime);
        addKifeleVizcsepp(kifolyoWorld, stage, matek, elapsedTime);
        removeVizcsepp(stage,matek);
        removeKifeleVizcsepp(stage);
    }

        static float pElapsedEso = 0;

        private static synchronized void addVizcsepp(World world, MyStage stage, Matek matek, float elapsedTime)
        {
            if (matek.getRain() > 0.05) {
                if (elapsedTime > pElapsedEso + (1 - matek.getRain()) / 3 /* && matek.isVolteso()*/) {
                    for(int i = 0; i < 1 + matek.getRain() * 3; i++) {
                        WorldActorGroup eso = new Vizcsepp(world);
                        if (eso == null) return;
                        eso.addToWorld();
                        eso.setPosition((float) (Math.random() * 41.2f) - 6.2f, stage.getViewport().getWorldHeight() + 2f);
                        eso.getBody().applyForceToCenter(new Vector2(51200, -180000), false);
                        stage.addActor(eso);
                        eso.setZIndex(2);
                        pElapsedEso = elapsedTime;
                    }
                }
            }
        }

        private static synchronized void removeVizcsepp(MyStage stage, Matek matek)
        {
            try {
                for (Actor esocsepp : stage.getActors()) {
                    if (esocsepp == null) return;
                    else {
                        if (esocsepp instanceof Vizcsepp) {
                            if (esocsepp.getY() < (24.0f/matek.getMaxviz())*matek.getVizmennyiseg()-esocsepp.getHeight()*2)
                            {
                                esocsepp.setVisible(false);
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

        private static synchronized void removeKifeleVizcsepp(MyStage stage)
        {
            try {
                for (Actor kiesoVizcsepp : stage.getActors()) {
                    if (kiesoVizcsepp == null) return;
                    else {
                        if (kiesoVizcsepp instanceof KifeleVizcsepp) {
                            if(((24.0f/matek.getMaxviz())*matek.getVizmennyiseg()) > kiesoVizcsepp.getX()*0.265f) {
                                if (kiesoVizcsepp.getY() < kiesoVizcsepp.getX() * 0.265f) {
                                    kiesoVizcsepp.remove();
                                }
                            }
                            else
                            {
                                if (kiesoVizcsepp.getY() < ((24.0f/matek.getMaxviz())*matek.getVizmennyiseg()) - kiesoVizcsepp.getHeight()*1.5f) {
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
                kifeleViz0.setPosition((float) (13.6f - Math.random()*2), (float) (10.8f + Math.random()*2));
                kifeleViz0.getBody().applyForceToCenter(new Vector2(90000,-125000),false);
                stage.addActor(kifeleViz0);
                kifeleViz0.setZIndex(400);
                pElapsed0 = elapsedTime;
           }
           if (matek.getNyilasok()[1].isOpen && elapsedTime > pElapsed1 + 0.05f) {
                WorldActorGroup kifeleViz1 = new KifeleVizcsepp(world);
                if(kifeleViz1 == null) return;
                kifeleViz1.addToWorld();
                kifeleViz1.setPosition((float) (18.4 - Math.random()*2), (float) (12 + Math.random()*2));
                kifeleViz1.getBody().applyForceToCenter(new Vector2(90000,-125000),false);
                stage.addActor(kifeleViz1);
                kifeleViz1.setZIndex(400);
                pElapsed1 = elapsedTime;
           }
           if (matek.getNyilasok()[2].isOpen && elapsedTime > pElapsed2 + 0.05f) {
                WorldActorGroup kifeleViz2 = new KifeleVizcsepp(world);
                if(kifeleViz2 == null) return;
                kifeleViz2.addToWorld();
                kifeleViz2.setPosition((float) (23.2 - Math.random()*2), (float) (12.8f + Math.random()*2));
                kifeleViz2.getBody().applyForceToCenter(new Vector2(90000,-125000),false);
                stage.addActor(kifeleViz2);
                kifeleViz2.setZIndex(400);
                pElapsed2 = elapsedTime;
            }
            if (matek.getNyilasok()[3].isOpen && elapsedTime > pElapsed3 + 0.05f) {
                WorldActorGroup kifeleViz3 = new KifeleVizcsepp(world);
                if(kifeleViz3 == null) return;
                kifeleViz3.addToWorld();
                kifeleViz3.setPosition((float) (28.6 - Math.random()*2), (float) (13.4f + Math.random()*2));
                kifeleViz3.getBody().applyForceToCenter(new Vector2(90000,-125000),false);
                stage.addActor(kifeleViz3);
                kifeleViz3.setZIndex(400);
                pElapsed3 = elapsedTime;
            }
            if (matek.getNyilasok()[4].isOpen && elapsedTime > pElapsed4 + 0.05f) {
                WorldActorGroup kifeleViz4 = new KifeleVizcsepp(world);
                if(kifeleViz4 == null) return;
                kifeleViz4.addToWorld();
                kifeleViz4.setPosition((float) (33.2 - Math.random()*2), (float) (13.8f + Math.random()*2));
                kifeleViz4.getBody().applyForceToCenter(new Vector2(90000,-125000),false);
                stage.addActor(kifeleViz4);
                kifeleViz4.setZIndex(400);
                pElapsed4 = elapsedTime;
            }
        }
}
