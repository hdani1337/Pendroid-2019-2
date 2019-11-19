package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MultiSpriteActor;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OffsetSprite;

public class WeatherForeGround extends WeatherAbstract {
    private static final AssetDescriptor<Texture> NIGHT_TEXTURE = new AssetDescriptor<Texture>("weather/night.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG3_TEXTURE = new AssetDescriptor<Texture>("weather/fog3.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG2_TEXTURE = new AssetDescriptor<Texture>("weather/fog2.png", Texture.class);
    private static final AssetDescriptor<Texture> FOG1_TEXTURE = new AssetDescriptor<Texture>("weather/fog1.png", Texture.class);
    private OffsetSprite night;
    private static AssetManager manager = null;

    private final static float nightAlpha = 0.8f;

    public WeatherForeGround(float width, float height) {
        super(width, height);
        addSprite(night = new OffsetSprite(WeatherForeGround.manager.get(NIGHT_TEXTURE), 0,0 , width, height));
        setTouchable(Touchable.disabled);
    }

    public void setTime(float time){
        float light = getLight(time);
        night.setAlpha(nightAlpha * (1f - light));
        System.out.println(light);
    }

    public static void load(AssetManager manager){
        WeatherForeGround.manager = manager;
        WeatherForeGround.manager.load(NIGHT_TEXTURE);
        WeatherForeGround.manager.load(FOG1_TEXTURE);
        WeatherForeGround.manager.load(FOG2_TEXTURE);
        WeatherForeGround.manager.load(FOG3_TEXTURE);
    }


}
