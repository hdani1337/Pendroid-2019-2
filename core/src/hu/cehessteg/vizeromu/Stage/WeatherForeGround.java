package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class WeatherForeGround extends WeatherAbstract {
    private static AssetManager manager = null;

    private static Random random = new Random();

    private static final AssetDescriptor<Texture> NIGHT_TEXTURE = new AssetDescriptor<Texture>("weather/night.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG3_TEXTURE = new AssetDescriptor<Texture>("weather/fog3.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG2_TEXTURE = new AssetDescriptor<Texture>("weather/fog2.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG1_TEXTURE = new AssetDescriptor<Texture>("weather/fog1.png", Texture.class);
    private static final AssetDescriptor<Texture> MOON_TEXTURE = new AssetDescriptor<Texture>("weather/moon.png", Texture.class);

    private OneSpriteStaticActor night;
    private OneSpriteStaticActor moonActor;

    private final static float nightAlpha = 0.85f;

    private float elapsedTime = 0;
    private float lastFogTime = 0;


    public WeatherForeGround(Viewport viewport, MyGame game) {
        super(viewport, game);
        addActor(night = new OneSpriteStaticActor(WeatherForeGround.manager.get(NIGHT_TEXTURE)));
        night.setSize(getWidth(), getHeight());
        night.setDebug(false);
        night.setTouchable(null);

        moonActor = new OneSpriteStaticActor(WeatherForeGround.manager.get(MOON_TEXTURE));
        moonActor.setSize(getWidth() / 4, (getWidth() / 16 * 9) / 4);
        moonActor.setX(720 / 2 - moonActor.getWidth() / 2);//Átírtam 720-ra, mert ha szélesebb a vievport, akkor elcsúszik a Hold a hegy elé
        moonActor.setAlpha(11);
        moonActor.setDebug(false);
        moonActor.setTouchable(null);

        addActor(moonActor);

    }

    @Override
    public void init() {

    }

    private class Fog extends OneSpriteStaticActor{
        float speed;
        public Fog() {
            super(WeatherForeGround.manager.get(FOG1_TEXTURE));
            setDebug(false);
            switch (random.nextInt(3)){
                case 0:
                    sprite.setTexture(WeatherForeGround.manager.get(FOG3_TEXTURE));
                    break;
                case 1:
                    sprite.setTexture(WeatherForeGround.manager.get(FOG2_TEXTURE));
                    break;
            }
            sprite.setAlpha(random.nextFloat() / 3f);
            speed = random.nextFloat() * 10f + 3f;
            setDebug(false);
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            setX(getX() + speed * delta);
            if (!isInFrustum(1.2f)){
                getStage().getActors().removeValue(this, true);
            }
        }
    }






    public static void load(AssetManager manager){
        WeatherForeGround.manager = manager;
        WeatherForeGround.manager.load(NIGHT_TEXTURE);
        WeatherForeGround.manager.load(FOG1_TEXTURE);
        WeatherForeGround.manager.load(FOG2_TEXTURE);
        WeatherForeGround.manager.load(FOG3_TEXTURE);
        WeatherForeGround.manager.load(MOON_TEXTURE);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
        float light = getLight(time);
        night.setAlpha(nightAlpha * (1f - light));
        if (rain >= 0.5f) {
            if (elapsedTime > lastFogTime + (1 - rain) * 2 + 1) {
                Fog fog;
                addActor(fog = new Fog());
                fog.setTouchable(null);
                fog.setZIndex(night.getZIndex() - 1);
                fog.setPosition(-getWidth(), 0);
                fog.setWidth(getWidth());
                fog.setHeight(getHeight());
                lastFogTime = elapsedTime;
            }
        }


        float a = ((1f - Math.abs(getMoonPosition(time))) * 5f > 1f ? 1f : (1f - Math.abs(getMoonPosition(time))) * 5f);

        moonActor.setAlpha(0.4f * a * ((1f - rain * 5f) < 0f ? 0f : (1f - rain * 5f)));
        moonActor.setY(getHeight() * 0.95f - moonActor.getHeight() / 2 + getHeight() * getMoonPosition(time) / 18f);

    }
}
