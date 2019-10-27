package hu.cehessteg.vizeromu.ParentClasses.UI;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import hu.cehessteg.vizeromu.ParentClasses.Game.InitableInterface;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MyButton extends TextButton implements InitableInterface {

    public MyButton(String text, TextButtonStyle style) {
        super(text, style);
        init();
    }

    public void init() {
    }
}
