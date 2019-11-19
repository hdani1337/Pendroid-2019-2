package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MultiSpriteActor;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;

public abstract class WeatherAbstract extends MyStage {
    private final static int sunset = 6*60;
    private final static int sundown = 21*60;
    private final static float dayNigthGradientSec = 6400/36.0f;

    public WeatherAbstract(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
    }

    //0 és 1 közt ad egy értéket a nap fényerejével arányosan.
    public float getLight(float time){
        if (time < sunset){
            return 0f;
        }
        if (time >=sunset && time<=sunset + dayNigthGradientSec){
            return ((time - sunset)) / dayNigthGradientSec;
        }

        if (time>sunset + dayNigthGradientSec && time <= sundown){
            return 1f;
        }
        if (time> sundown && time <= sundown + dayNigthGradientSec){
            return 1f - ((time - sundown)) / dayNigthGradientSec;
        }
        if (time > sundown + dayNigthGradientSec){
            return 0f;
        }
        return 0f;
    }

    abstract public void setTime(float time);
}
