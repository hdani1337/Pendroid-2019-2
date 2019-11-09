package hu.cehessteg.vizeromu;

import com.badlogic.gdx.Gdx;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.Screen.LoadingScreen;

public class Vizeromu extends MyGame {
	static boolean multitasking;

	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	public static float keparanySzelesvaszonra()
	{
		float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f);
		int egyArany = 80;//egy arányra eső szélesség 720-as magasságnál ((720/9)*x)
		int x = 1;//szélességi arány
		while (keparany > (x/9.0f)) x++;

		if((int)keparany*(x*egyArany) != Gdx.graphics.getWidth()) return (int)(720.0/Gdx.graphics.getHeight() * Gdx.graphics.getWidth());
		//Ha nem pontos a képarány számítása, akkor a világ szélessége legyen a telefon kijelzőjének szélessége 720 pixelhez viszonyítva

		return x * egyArany;
	}

	public static void setMultitasking(boolean value)
	{
		multitasking = value;
	}

	public static boolean getMultitasking()
	{
		return multitasking;
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}
}
