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

public class Fuggvenyek {
    public static void vizcseppThread(final World world, final MyStage stage, final Matek matek, final float elapsedTime)
    {
        Runnable eso = new Runnable() {
            @Override
            public void run() {
                addVizcsepp(world, stage, matek, elapsedTime);
                removeVizcsepp(stage);
            }
        };

        Runnable kifolyas = new Runnable() {
            @Override
            public void run() {
                addKifeleVizcsepp(world, stage, matek, elapsedTime);
                removeKifeleVizcsepp(stage);
            }
        };

        if (Vizeromu.getMultitasking()) {
            try {
                kifolyas.run();
                eso.run();

            }catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            addVizcsepp(world, stage, matek, elapsedTime);
            addKifeleVizcsepp(world, stage, matek, elapsedTime);
            removeVizcsepp(stage);
            removeKifeleVizcsepp(stage);
        }
    }

        static float pElapsedEso = 0;

        private static void addVizcsepp(World world, MyStage stage, Matek matek, float elapsedTime)
        {
            if (elapsedTime > pElapsedEso + 0.065 && matek.isVolteso()) {
                WorldActorGroup eso = new Vizcsepp(world);
                if(eso == null) return;
                eso.addToWorld();
                eso.setPosition((float) (Math.random()*10.3f)-3f,stage.getViewport().getWorldHeight()+0.5f);
                eso.getBody().applyForceToCenter(new Vector2(200,-150),false);
                stage.addActor(eso);
                eso.setZIndex(2);
                pElapsedEso = elapsedTime;
            }
        }

        private static void removeVizcsepp(MyStage stage)
        {
            try {
                for (Actor esocsepp : stage.getActors()) {
                    if (esocsepp == null) return;
                    else {
                        if (esocsepp instanceof Vizcsepp) {
                            if (esocsepp.getY() <= 2.65f)
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

        private static void removeKifeleVizcsepp(MyStage stage)
        {
            try {
                for (Actor kiesoVizcsepp : stage.getActors()) {
                    if (kiesoVizcsepp == null) return;
                    else {
                        if (kiesoVizcsepp instanceof KifeleVizcsepp) {
                            if (kiesoVizcsepp.getY() < -kiesoVizcsepp.getHeight())
                            {
                                kiesoVizcsepp.remove();
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

        private static void addKifeleVizcsepp(World world, MyStage stage, Matek matek, float elapsedTime)
        {
           if (matek.getNyilasok()[0].isOpen && elapsedTime > pElapsed0 + 0.05f) {
                //1. adag víz
                WorldActorGroup kifeleViz0 = new KifeleVizcsepp(world);
                if(kifeleViz0 == null) return;
                kifeleViz0.addToWorld();
                kifeleViz0.setPosition((float) (3.4f - Math.random()*0.5f), (float) (2.7f + Math.random()*0.5f));
                kifeleViz0.getBody().applyForceToCenter(new Vector2(600,-450),false);
                stage.addActor(kifeleViz0);
                kifeleViz0.setZIndex(400);
                pElapsed0 = elapsedTime;
           }
           if (matek.getNyilasok()[1].isOpen && elapsedTime > pElapsed1 + 0.05f) {
                WorldActorGroup kifeleViz1 = new KifeleVizcsepp(world);
                if(kifeleViz1 == null) return;
                kifeleViz1.addToWorld();
                kifeleViz1.setPosition((float) (4.6 - Math.random()*0.5f), (float) (3 + Math.random()*0.5f));
                kifeleViz1.getBody().applyForceToCenter(new Vector2(600,-450),false);
                stage.addActor(kifeleViz1);
                kifeleViz1.setZIndex(400);
                pElapsed1 = elapsedTime;
           }
           if (matek.getNyilasok()[2].isOpen && elapsedTime > pElapsed2 + 0.05f) {
                WorldActorGroup kifeleViz2 = new KifeleVizcsepp(world);
                if(kifeleViz2 == null) return;
                kifeleViz2.addToWorld();
                kifeleViz2.setPosition((float) (5.8 - Math.random()*0.5f), (float) (3.2f + Math.random()*0.5f));
                kifeleViz2.getBody().applyForceToCenter(new Vector2(600,-450),false);
                stage.addActor(kifeleViz2);
                kifeleViz2.setZIndex(400);
                pElapsed2 = elapsedTime;
            }
            if (matek.getNyilasok()[3].isOpen && elapsedTime > pElapsed3 + 0.05f) {
                WorldActorGroup kifeleViz3 = new KifeleVizcsepp(world);
                if(kifeleViz3 == null) return;
                kifeleViz3.addToWorld();
                kifeleViz3.setPosition((float) (7.1 - Math.random()*0.5f), (float) (3.35f + Math.random()*0.5f));
                kifeleViz3.getBody().applyForceToCenter(new Vector2(600,-450),false);
                stage.addActor(kifeleViz3);
                kifeleViz3.setZIndex(400);
                pElapsed3 = elapsedTime;
            }
            if (matek.getNyilasok()[4].isOpen && elapsedTime > pElapsed4 + 0.05f) {
                WorldActorGroup kifeleViz4 = new KifeleVizcsepp(world);
                if(kifeleViz4 == null) return;
                kifeleViz4.addToWorld();
                kifeleViz4.setPosition((float) (8.3 - Math.random()*0.5f), (float) (3.45f + Math.random()*0.5f));
                kifeleViz4.getBody().applyForceToCenter(new Vector2(600,-450),false);
                stage.addActor(kifeleViz4);
                kifeleViz4.setZIndex(400);
                pElapsed4 = elapsedTime;
            }
        }
}
