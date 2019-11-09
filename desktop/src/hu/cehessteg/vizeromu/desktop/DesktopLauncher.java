package hu.cehessteg.vizeromu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hu.cehessteg.vizeromu.Vizeromu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		configStuff();
		Vizeromu.setMultitasking(false);
	}

	static void configStuff()
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Vizeromu(), config);
		config.width = 1280;
		config.height = 360;
		config.vSyncEnabled = true;
		config.fullscreen = false;
	}
}
