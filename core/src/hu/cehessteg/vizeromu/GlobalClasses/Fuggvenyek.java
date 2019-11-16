package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
                vizcsepp2.setPosition((float)(Math.random() * stage.getViewport().getWorldWidth()),stage.getViewport().getWorldHeight()+1);
                stage.addActor(vizcsepp2);
                vizcsepp2.setZIndex(5);
                pElapsedTime = elapsedTime;

            }
        }

        private static void removeVizcsepp(MyStage stage)
        {
            for (Actor actor : stage.getActors()) {
                if (actor instanceof Vizcsepp) {
                    if(actor.getY() < -actor.getHeight())//Ne egyből tűnjön el, legyen egy kis átmenet
                    {
                        if(actor == null) return;
                        ((WorldActorGroup) actor).remove();
                    }
                }
            }
        }
}
