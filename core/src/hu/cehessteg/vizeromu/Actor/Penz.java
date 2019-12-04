package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class Penz extends OneSpriteStaticActor {
    MyLabel coinLabel;

    public Penz(MyStage stage) {
        super(Assets.manager.get(Assets.COIN));
        setDebug(false);
        setSize(96,96);
        labelStuff(stage);
    }

    void labelStuff(MyStage stage)
    {
        coinLabel = new MyLabel("0", Styles.getCalibriLabelStyle());
        coinLabel.setAlignment(0);
        coinLabel.setOrigin(0,0);
        coinLabel.setPosition(this.getX() + this.getWidth(), this.getY() + this.getHeight()/2 - coinLabel.getHeight()/2);
        stage.addActor(coinLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        coinLabel.setText(Matek.coins + "");
        coinLabel.setPosition(this.getX() + this.getWidth() + (coinLabel.getText().length)*9, this.getY() + this.getHeight()/2 - coinLabel.getHeight()/2);
    }
}
