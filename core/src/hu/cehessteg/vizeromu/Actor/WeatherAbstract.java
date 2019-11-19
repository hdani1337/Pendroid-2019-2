package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MultiSpriteActor;

public abstract class WeatherAbstract extends MultiSpriteActor {
    private final static int sunset = 6 * 3600;
    private final static int sundown = 21 * 3600;
    private final static int dayNigthGradientSec = 6400;


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

    public WeatherAbstract(float width, float height) {
        super(width, height);
    }
}
