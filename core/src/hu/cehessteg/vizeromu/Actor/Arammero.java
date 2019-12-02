package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.GameStage;

public class Arammero extends OneSpriteStaticActor {
    private OneSpriteStaticActor bg;
    private OneSpriteStaticActor bgFade;

    public Arammero(MyStage stage) {
        super(Assets.manager.get(Assets.ARAMMERO));
        setDebug(false);
        bgStuff(stage);
        bgFadeStuff(stage);
    }

    private void bgStuff(MyStage stage)
    {
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.SLIDER_BG_GR));
        bg.setDebug(false);
        bg.setPosition(this.getX() + this.getWidth()*0.15f,this.getY());
        bg.setSize(this.getWidth()*0.85f,this.getHeight());
        bg.setRotation(-180);
        stage.addActor(bg);
    }

    private void bgFadeStuff(MyStage stage)
    {
        bgFade = new OneSpriteStaticActor(Assets.manager.get(Assets.BLANK));
        bgFade.setDebug(false);
        bgFade.setPosition(this.getX() + this.getWidth()*0.15f,this.getY());
        bgFade.setSize(this.getWidth()*0.85f,this.getHeight());
        bgFade.setColor(0,0,0,1);
        bgFade.setRotation(-180);
        stage.addActor(bgFade);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //bgFade.setWidth(this.getWidth()*0.85f-(this.getWidth()*0.85f * (GameStage.matek.aramPercent())));
        if(bgFade.getWidth() > (this.getWidth()*0.85f-(this.getWidth()*0.85f * (GameStage.matek.aramPercent())))+2) bgFade.setWidth(bgFade.getWidth()-4);
        else if(bgFade.getWidth() < (this.getWidth()*0.85f-(this.getWidth()*0.85f * (GameStage.matek.aramPercent())))-2) bgFade.setWidth(bgFade.getWidth()+4);
        else bgFade.setWidth((this.getWidth()*0.85f-(this.getWidth()*0.85f * (GameStage.matek.aramPercent()))));
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        bg.setPosition(this.getX() + this.getWidth()*0.15f,this.getY());
        bgFade.setPosition(this.getX() + this.getWidth()*0.15f,this.getY());
    }
}
