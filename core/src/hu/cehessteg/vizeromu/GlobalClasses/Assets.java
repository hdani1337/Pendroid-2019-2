//https://github.com/tuskeb/mester
package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import hu.cehessteg.vizeromu.Stage.WeatherBackground;
import hu.cehessteg.vizeromu.Stage.WeatherForeGround;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "fonts/calibri.ttf";
		fontParameter.fontParameters.size = 50;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> CALIBRI_FONT = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
    static {
        fontParameter2.fontFileName = "fonts/consolas.ttf";
        fontParameter2.fontParameters.size = 50;
        fontParameter2.fontParameters.characters = CHARS;
        fontParameter2.fontParameters.color = Color.WHITE;
    }
    public static final AssetDescriptor<BitmapFont> CONSOLAS_FONT = new AssetDescriptor<BitmapFont>(fontParameter2.fontFileName, BitmapFont.class, fontParameter2);

    public static final AssetDescriptor<Texture> BLUE_TEXTURE = new AssetDescriptor<Texture>("colors/blue.png", Texture.class);
    public static final AssetDescriptor<Texture> GREEN_TEXTURE = new AssetDescriptor<Texture>("colors/green.png", Texture.class);
    public static final AssetDescriptor<Texture> YELLOW_TEXTURE = new AssetDescriptor<Texture>("colors/yellow.png", Texture.class);
    public static final AssetDescriptor<Texture> WALLPAPER_TEXTURE = new AssetDescriptor<Texture>("colors/hatter.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ_TEXTURE = new AssetDescriptor<Texture>("colors/water.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ = new AssetDescriptor<Texture>("colors/waterBlue.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZ2 = new AssetDescriptor<Texture>("colors/waterBlue2.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZSZINT_TEXTURE = new AssetDescriptor<Texture>("colors/waterLevel.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_GR = new AssetDescriptor<Texture>("colors/greenRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_BR = new AssetDescriptor<Texture>("colors/blueRed.png", Texture.class);
    public static final AssetDescriptor<Texture> SLIDER_BG_RAINBOW = new AssetDescriptor<Texture>("colors/rainbow.png", Texture.class);
    public static final AssetDescriptor<Texture> CSANY = new AssetDescriptor<Texture>("logos/csany.png", Texture.class);
    public static final AssetDescriptor<Texture> PENDROID = new AssetDescriptor<Texture>("logos/pendroid.png", Texture.class);
    public static final AssetDescriptor<Texture> CSAPATLOGO = new AssetDescriptor<Texture>("logos/csapatlogo.png", Texture.class);
    public static final AssetDescriptor<Texture> MINVIZ = new AssetDescriptor<Texture>("colors/minViz.png", Texture.class);
    public static final AssetDescriptor<Texture> MAXVIZ = new AssetDescriptor<Texture>("colors/maxViz.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB_HATTER = new AssetDescriptor<Texture>("colors/gombhatter1.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB_HATTER2 = new AssetDescriptor<Texture>("colors/gombhatter2.png", Texture.class);
    public static final AssetDescriptor<Texture> GAT = new AssetDescriptor<Texture>("gat.png", Texture.class);
    public static final AssetDescriptor<Texture> GAT_ALJA = new AssetDescriptor<Texture>("gat_alja.png", Texture.class);

    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(WALLPAPER_TEXTURE);

		manager.load(CALIBRI_FONT);
		manager.load(CONSOLAS_FONT);

        manager.load(BLUE_TEXTURE);
        manager.load(GREEN_TEXTURE);
        manager.load(YELLOW_TEXTURE);
        manager.load(VIZ_TEXTURE);
        manager.load(VIZ);
        manager.load(VIZ2);
        manager.load(VIZSZINT_TEXTURE);
        manager.load(SLIDER_BG_GR);
        manager.load(SLIDER_BG_BR);
        manager.load(SLIDER_BG_RAINBOW);
        manager.load(CSANY);
        manager.load(PENDROID);
        manager.load(CSAPATLOGO);
        manager.load(MINVIZ);
        manager.load(MAXVIZ);
        manager.load(GOMB_HATTER);
        manager.load(GOMB_HATTER2);
        manager.load(GAT);
        manager.load(GAT_ALJA);

        WeatherForeGround.load(manager);
        WeatherBackground.load(manager);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
