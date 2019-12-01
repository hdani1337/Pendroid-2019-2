package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.GameStage;

public class Vizmero extends OneSpriteStaticActor {
    private OneSpriteStaticActor bg;
    private OneSpriteStaticActor bgFade;

    public Vizmero(MyStage stage) {
        super(Assets.manager.get(Assets.VIZMERO));
        setDebug(false);
        bgStuff(stage);
        bgFadeStuff(stage);
    }

    private void bgStuff(MyStage stage)
    {
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BLANK));
        bg.setColor(255,255,0,1);
        bg.setDebug(false);
        bg.setPosition(this.getX(),this.getY());
        bg.setSize(this.getWidth(),this.getHeight()*0.85f);
        stage.addActor(bg);
    }

    private void bgFadeStuff(MyStage stage)
    {
        bgFade = new OneSpriteStaticActor(Assets.manager.get(Assets.BLANK));
        bgFade.setDebug(false);
        bgFade.setPosition(this.getX(),this.getY());
        bgFade.setSize(this.getWidth(),this.getHeight()*0.85f);
        bgFade.setColor(0,0,0,1);
        bgFade.setRotation(-180);
        stage.addActor(bgFade);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        bgFade.setHeight(this.getHeight()*0.85f-(this.getHeight()*0.85f * (GameStage.matek.getVizmennyiseg()/GameStage.matek.getMaxviz()*1.0f)));
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        bg.setPosition(this.getX(),this.getY());
        bgFade.setPosition(this.getX(),this.getY());
    }

    @Override
    public void setScale(float scale) {
        this.setSize(this.getWidth()*scale,this.getHeight()*scale);
        bg.setSize(bg.getWidth()*scale,bg.getHeight()*scale);
        bgFade.setSize(bgFade.getWidth()*scale,bgFade.getHeight()*scale);
    }
}
