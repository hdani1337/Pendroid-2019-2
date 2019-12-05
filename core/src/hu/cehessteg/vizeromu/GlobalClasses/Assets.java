//https://github.com/tuskeb/mester
package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public static final AssetDescriptor<Texture> GAT = new AssetDescriptor<Texture>("Images/gat.png", Texture.class);
    public static final AssetDescriptor<Texture> HEGY1 = new AssetDescriptor<Texture>("Images/hegy1.png", Texture.class);
    public static final AssetDescriptor<Texture> HEGY2 = new AssetDescriptor<Texture>("Images/hegy2.png", Texture.class);
    public static final AssetDescriptor<Texture> CAUTION = new AssetDescriptor<Texture>("Images/caution.png", Texture.class);
    public static final AssetDescriptor<Texture> AJTO = new AssetDescriptor<Texture>("Images/ajto.png", Texture.class);
    public static final AssetDescriptor<Texture> FOLD = new AssetDescriptor<Texture>("Images/fold.png", Texture.class);
    public static final AssetDescriptor<Texture> ZOLI = new AssetDescriptor<Texture>("portraits/zoli.png", Texture.class);
    public static final AssetDescriptor<Texture> BENCE = new AssetDescriptor<Texture>("portraits/bence.png", Texture.class);
    public static final AssetDescriptor<Texture> DANI = new AssetDescriptor<Texture>("portraits/dani.png", Texture.class);
    public static final AssetDescriptor<Texture> DAVID = new AssetDescriptor<Texture>("portraits/david.png", Texture.class);
    public static final AssetDescriptor<Texture> RED_CIRC = new AssetDescriptor<Texture>("colors/redCircle.png", Texture.class);
    public static final AssetDescriptor<Texture> GREEN_CIRC = new AssetDescriptor<Texture>("colors/greenCircle.png", Texture.class);
    public static final AssetDescriptor<Texture> PAUSE = new AssetDescriptor<Texture>("icons/pause.png", Texture.class);
    public static final AssetDescriptor<Texture> SPEEDD = new AssetDescriptor<Texture>("icons/speeddown.jpg", Texture.class);
    public static final AssetDescriptor<Texture> SPEEDU = new AssetDescriptor<Texture>("icons/speedup.jpg", Texture.class);
    public static final AssetDescriptor<Texture> FAGYOTTORA = new AssetDescriptor<Texture>("Images/frozenclock.png", Texture.class);
    public static final AssetDescriptor<Texture> GOMB = new AssetDescriptor<Texture>("icons/gomb.png", Texture.class);
    public static final AssetDescriptor<Texture> ORA = new AssetDescriptor<Texture>("Images/clock.png", Texture.class);
    public static final AssetDescriptor<Texture> MUTATO = new AssetDescriptor<Texture>("Images/mutato.png", Texture.class);
    public static final AssetDescriptor<Texture> KACSA = new AssetDescriptor<Texture>("Images/kacsa.png", Texture.class);
    public static final AssetDescriptor<Texture> KACSASIMA = new AssetDescriptor<Texture>("Images/kacsaSima.png", Texture.class);
    public static final AssetDescriptor<Texture> ARAMMERO = new AssetDescriptor<Texture>("icons/arammero.png", Texture.class);
    public static final AssetDescriptor<Texture> VIZMERO = new AssetDescriptor<Texture>("icons/vizmero.png", Texture.class);
    public static final AssetDescriptor<Texture> BLANK = new AssetDescriptor<Texture>("colors/blank.png", Texture.class);
    public static final AssetDescriptor<Texture> COIN = new AssetDescriptor<Texture>("icons/coin.png", Texture.class);
    public static final AssetDescriptor<Texture> DESZKA = new AssetDescriptor<Texture>("Images/deszka.png", Texture.class);
    public static final AssetDescriptor<Texture> LYUK = new AssetDescriptor<Texture>("icons/lyuk.png", Texture.class);

    public static final AssetDescriptor<Sound> AJTO_NYIT = new AssetDescriptor<Sound>("Hangok/AjtoNyito.mp3",Sound.class);
    public static final AssetDescriptor<Sound> AJTO_ZAR = new AssetDescriptor<Sound>("Hangok/AjtoZaro.mp3",Sound.class);
    public static final AssetDescriptor<Sound> NYIKORGAS = new AssetDescriptor<Sound>("Hangok/Nyikorgas.mp3",Sound.class);
    public static final AssetDescriptor<Sound> KACSASOUND = new AssetDescriptor<Sound>("Hangok/kacsa.mp3",Sound.class);

    public static final AssetDescriptor<Music> ESO = new AssetDescriptor<Music>("Hangok/RainDrops.mp3",Music.class);
    public static final AssetDescriptor<Music> GAME_MUSIC = new AssetDescriptor<Music>("Hangok/GameMusic.mp3",Music.class);
    public static final AssetDescriptor<Music> MENU_MUSIC = new AssetDescriptor<Music>("Hangok/MenuMusic.mp3",Music.class);

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
        manager.load(HEGY1);
        manager.load(HEGY2);
        manager.load(CAUTION);
        manager.load(AJTO);
        manager.load(ZOLI);
        manager.load(BENCE);
        manager.load(DANI);
        manager.load(DAVID);
        manager.load(RED_CIRC);
        manager.load(GREEN_CIRC);
        manager.load(PAUSE);
        manager.load(FOLD);
        manager.load(GOMB);
        manager.load(ORA);
        manager.load(MUTATO);
        manager.load(KACSA);
        manager.load(KACSASIMA);
        manager.load(ARAMMERO);
        manager.load(VIZMERO);
        manager.load(BLANK);
        manager.load(COIN);
        manager.load(DESZKA);
        manager.load(LYUK);
        manager.load(SPEEDD);
        manager.load(SPEEDU);
        manager.load(FAGYOTTORA);

        manager.load(AJTO_NYIT);
        manager.load(AJTO_ZAR);
        manager.load(NYIKORGAS);
        manager.load(KACSASOUND);
        manager.load(ESO);

        manager.load(GAME_MUSIC);
        manager.load(MENU_MUSIC);

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
