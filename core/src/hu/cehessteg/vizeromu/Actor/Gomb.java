package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class Gomb extends OneSpriteStaticActor {
    public MyLabel myLabel;

    public Gomb(String text, MyStage myStage) {
        super(Assets.manager.get(Assets.GOMB));
        setDebug(false);
        setSize(this.getWidth()/2.5f,this.getHeight()/2.5f);
        myLabel = new MyLabel(text, Styles.getCalibriLabelStyle());
        myLabel.setAlignment(0);
        //myLabel.setPosition(this.getWidth()/2-myLabel.getWidth()/2,this.getHeight()/2-myLabel.getHeight()/2);
        myLabel.setFontScale(0.75f);
        myLabel.setTouchable(null);
        myStage.addActor(myLabel);
        //myLabel.setZIndex(this.getZIndex()+1);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        myLabel.setColor(r, g, b, a);
    }
}
