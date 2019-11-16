package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Random;

import hu.cehessteg.vizeromu.Actor.Vizcsepp;
import hu.cehessteg.vizeromu.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.Vizeromu;

public class Fuggvenyek {
    static float pElapsedTime = 0;

    public static void vizcseppThread(final World world, final MyStage stage, final float elapsedTime, final Matek matek)
    {
        if (Vizeromu.getMultitasking()) {
            new Thread(new Runnable() {
                public void run() {
                    addVizcsepp(world, stage, elapsedTime, matek);
                    removeVizcsepp(stage);
                }
            }).start();
        }
        else
        {
            addVizcsepp(world, stage, elapsedTime, matek);
            removeVizcsepp(stage);
        }
    }

        private static void addVizcsepp(World world, MyStage stage, float elapsedTime, Matek matek)
        {
            if (elapsedTime > pElapsedTime && matek.isVolteso()) {
                WorldActorGroup vizcsepp2 = new Vizcsepp(world);
                if(vizcsepp2 == null) return;
                vizcsepp2.addToWorld();
                vizcsepp2.setPosition((float) (Math.random()*10.3f),stage.getViewport().getWorldHeight()+1);
                stage.addActor(vizcsepp2);
                vizcsepp2.setZIndex(2);
                pElapsedTime = elapsedTime;

            }
        }

        private static void removeVizcsepp(MyStage stage)
        {
            try {
                for (Actor actor : stage.getActors()) {
                    if (actor == null) return;
                    else {
                        if (actor instanceof Vizcsepp) {
                            if (actor.getY() < 3.6)
                            {
                                actor.remove();
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
}
