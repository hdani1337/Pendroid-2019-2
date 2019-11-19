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
    private final static int sunset = 6 * 3600;
    private final static int sundown = 21 * 3600;
    private final static int dayNigthGradientSec = 6400;

    public WeatherAbstract(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
    }

    //0 és 1 közt ad egy értéket a nap fényerejével arányosan.
    public float getLight(float time){
        int daysec = (int)time % 86400;
        if (daysec < sunset){
            return 0f;
        }
        if (daysec >=sunset && daysec<=sunset + dayNigthGradientSec){
            return ((float)(daysec - sunset)) / (float)dayNigthGradientSec;
        }

        if (daysec>sunset + dayNigthGradientSec && daysec <= sundown){
            return 1f;
        }
        if (daysec> sundown && daysec <= sundown + dayNigthGradientSec){
            return 1f - ((float)(daysec - sundown)) / (float)dayNigthGradientSec;
        }
        if (daysec > sundown + dayNigthGradientSec){
            return 0f;
        }
        return 0f;
    }

    abstract public void setTime(float time);
}
