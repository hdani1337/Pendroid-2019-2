package hu.cehessteg.vizeromu.GlobalClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Styles {

    public static Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
        style.fontColor = Color.WHITE;
        return style;
    }

    public static TextButton.TextButtonStyle getTextButtonStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Assets.manager.get(Assets.ALEGREYAREGULAR_FONT);
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.GOMB_HATTER)));
        textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.GOMB_HATTER2)));
        return textButtonStyle;
    }

}
