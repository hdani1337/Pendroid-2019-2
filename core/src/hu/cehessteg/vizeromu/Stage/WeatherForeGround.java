package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class WeatherForeGround extends WeatherAbstract {
    private static Random random = new Random();

    private static final AssetDescriptor<Texture> NIGHT_TEXTURE = new AssetDescriptor<Texture>("weather/night.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG3_TEXTURE = new AssetDescriptor<Texture>("weather/fog3.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG2_TEXTURE = new AssetDescriptor<Texture>("weather/fog2.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG1_TEXTURE = new AssetDescriptor<Texture>("weather/fog1.png", Texture.class);
    private OneSpriteStaticActor night;
    private static AssetManager manager = null;
    private final static float nightAlpha = 0.90f;
    private float rain = 1f;
    private float time = 1;
    private float elapsedTime = 0;
    private float lastFogTime = 0;


    public WeatherForeGround(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        addActor(night = new OneSpriteStaticActor(WeatherForeGround.manager.get(NIGHT_TEXTURE)));
        night.setSize(getWidth(), getHeight());
        night.setZIndex(100);
    }

    @Override
    public void init() {

    }

    private class Fog extends OneSpriteStaticActor{
        float speed;
        public Fog() {
            super(WeatherForeGround.manager.get(NIGHT_TEXTURE));
            setDebug(false);
            switch (random.nextInt(3)){
                case 0:
                    sprite.setTexture(WeatherForeGround.manager.get(FOG3_TEXTURE));
                    break;
                case 1:
                    sprite.setTexture(WeatherForeGround.manager.get(FOG2_TEXTURE));
                    break;
                case 2:
                    sprite.setTexture(WeatherForeGround.manager.get(FOG1_TEXTURE));
                    break;
            }
            sprite.setAlpha(random.nextFloat() * 3f / 2f);
            speed = random.nextFloat() * 10f + 3f;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            setX(getX() + speed * delta);
        /*    if (!isInFrustum(1.2f)){
                getStage().getActors().removeValue(this, true);
            }*/
        }
    }


    /**
     * Bemenete az idő másodpercben
     * @param time
     */
    public void setTime(float time){
        this.time = time;
    }

    /**
     * Bemenete 0 és 1 között az eső mennyisége
     * @param rain
     */
    public void setRain(float rain){
        this.rain = rain;
    }



    public static void load(AssetManager manager){
        WeatherForeGround.manager = manager;
        WeatherForeGround.manager.load(NIGHT_TEXTURE);
        WeatherForeGround.manager.load(FOG1_TEXTURE);
        WeatherForeGround.manager.load(FOG2_TEXTURE);
        WeatherForeGround.manager.load(FOG3_TEXTURE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime+=delta;
        float light = getLight(time);
        night.setAlpha(nightAlpha * (1f - light));
        if (rain >= 0.5f) {
            if (elapsedTime > lastFogTime + (1 - rain) * 3 + 1) {
                Fog fog;
                addActor(fog = new Fog());
                fog.setZIndex(night.getZIndex() - 1);
                fog.setPosition(-getWidth(), 0);
                fog.setWidth(getWidth());
                fog.setHeight(getHeight());
                //System.out.println("Köd");
                System.out.println(getWidth());
                System.out.println(getHeight());
                lastFogTime = elapsedTime;
            }
        }

    }
}
