package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.GameStage;

public class Arammero extends OneSpriteStaticActor {
    private OneSpriteStaticActor bg;

    public Arammero(MyStage stage) {
        super(Assets.manager.get(Assets.ARAMMERO));
        setDebug(false);
        bgStuff(stage);
    }

    private void bgStuff(MyStage stage)
    {
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.SLIDER_BG_GR));
        bg.setDebug(false);
        bg.setPosition(this.getX() + this.getWidth()*0.15f,this.getY());
        bg.setSize(this.getWidth()*0.85f,this.getHeight());
        stage.addActor(bg);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //bg.setWidth(this.getWidth()*0.85f * (GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz()*1.0f));
    }
}
