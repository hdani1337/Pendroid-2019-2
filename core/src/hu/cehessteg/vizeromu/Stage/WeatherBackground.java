package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MultiSpriteActor;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyActor;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OffsetSprite;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class WeatherBackground extends WeatherAbstract {
    private static AssetManager manager = null;

    private static Random random = new Random();

    private static final AssetDescriptor<Texture> SKYCLEAR_TEXTURE = new AssetDescriptor<Texture>("weather/skyclear.png", Texture.class);
    private static final AssetDescriptor<Texture> SKYCLOUDY_TEXTURE = new AssetDescriptor<Texture>("weather/skycloudy.png", Texture.class);
    private static final AssetDescriptor<Texture> CLOUD4_TEXTURE = new AssetDescriptor<Texture>("weather/cloud4.png", Texture.class);
    private static final AssetDescriptor<Texture> CLOUD3_TEXTURE = new AssetDescriptor<Texture>("weather/cloud3.png", Texture.class);
    private static final AssetDescriptor<Texture> CLOUD2_TEXTURE = new AssetDescriptor<Texture>("weather/cloud2.png", Texture.class);
    private static final AssetDescriptor<Texture> CLOUD1_TEXTURE = new AssetDescriptor<Texture>("weather/cloud1.png", Texture.class);
    private static final AssetDescriptor<Texture> SUNDOWN_TEXTURE = new AssetDescriptor<Texture>("weather/sundown.png", Texture.class);
    private static final AssetDescriptor<Texture> SUNDAYLIGHT_TEXTURE = new AssetDescriptor<Texture>("weather/sundaylight.png", Texture.class);

    private class SkyActor extends MultiSpriteActor{
        OffsetSprite clear;
        OffsetSprite cloudy;

        public SkyActor(float width, float height) {
            super(width, height);
        }

        @Override
        public void init() {
            super.init();
            addSprite(clear = new  OffsetSprite(WeatherBackground.manager.get(SKYCLEAR_TEXTURE),0,0,getWidth(),getHeight()));
            addSprite(cloudy = new  OffsetSprite(WeatherBackground.manager.get(SKYCLOUDY_TEXTURE),0,0,getWidth(),getHeight()));
            setRain(0);
        }

        public void setRain(float rain){
            cloudy.setAlpha(rain * 3 > 1 ? 1 : rain * 3);
        }

    };


    private class Cloud extends OneSpriteStaticActor{
        float speed;
        float alpha = 0;
        float ontime;

        public void setDelete(boolean delete) {
            this.delete = delete;
        }

        boolean delete = false;
        public Cloud() {
            super(WeatherBackground.manager.get(CLOUD1_TEXTURE));
            setDebug(false);
            switch (random.nextInt(4)){
                case 0:
                    sprite.setTexture(WeatherBackground.manager.get(CLOUD2_TEXTURE));
                    break;
                case 1:
                    sprite.setTexture(WeatherBackground.manager.get(CLOUD3_TEXTURE));
                    break;
                case 2:
                    sprite.setTexture(WeatherBackground.manager.get(CLOUD4_TEXTURE));
                    break;
            }
            sprite.setAlpha(0f);
            ontime = random.nextInt(10) + 2;
            speed = random.nextFloat() * 4f + 1f;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            alpha+=delta / (float)ontime;
            sprite.setAlpha(alpha < 0.7f ? alpha : 0.7f);
            setX(getX() + speed * delta);
            if (!isInFrustum(1.2f)){
                if (delete){
                    getStage().getActors().removeValue(this,true);
                }else {
                    setX(-getWidth());
                }
            }
        }
    }

    SkyActor skyActor;


    public WeatherBackground(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        skyActor = new SkyActor(getWidth(), getWidth());
        addActor(skyActor);
    }

    @Override
    public void init() {

    }


    public static void load(AssetManager manager){
        WeatherBackground.manager = manager;
        WeatherBackground.manager.load(SKYCLEAR_TEXTURE);
        WeatherBackground.manager.load(SKYCLOUDY_TEXTURE);
        WeatherBackground.manager.load(CLOUD1_TEXTURE);
        WeatherBackground.manager.load(CLOUD3_TEXTURE);
        WeatherBackground.manager.load(CLOUD2_TEXTURE);
        WeatherBackground.manager.load(CLOUD4_TEXTURE);
        WeatherBackground.manager.load(SUNDAYLIGHT_TEXTURE);
        WeatherBackground.manager.load(SUNDOWN_TEXTURE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime+=delta;
        float light = getLight(time);
        skyActor.setY(-(skyActor.getHeight() - getHeight()) + light * skyActor.getHeight() * 0.8f);
        skyActor.setRain(rain);

        int cofc = 0;
        float rainfactor = rain*50;

        for (Actor a: getActors()) {
            if (a instanceof Cloud){
                cofc++;
                if (cofc > (int)rainfactor){
                    ((Cloud) a).setDelete(true);
                }
            }

        }
        if (cofc < rainfactor) {
            Cloud c;
            addActor(c = new Cloud());
            c.setPosition(random.nextInt((int)getWidth() * 2) - (int)getWidth(), getHeight() *0.85f - random.nextInt((int)(getHeight() / 8f)));
            c.setWidth(getWidth() / 2);
            c.setHeight(getHeight() / 3);
            //c.setY(-(c.getHeight() - getHeight()));

        }

    }
}