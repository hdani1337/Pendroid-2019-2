package hu.cehessteg.vizeromu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hu.cehessteg.vizeromu.Vizeromu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Vizeromu(), config);
		config.width = 405;
		config.height = 720;
		config.vSyncEnabled = true;
		config.fullscreen = false;
	}
}
