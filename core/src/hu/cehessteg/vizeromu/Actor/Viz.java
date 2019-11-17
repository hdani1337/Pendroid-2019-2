package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import static hu.cehessteg.vizeromu.Stage.GameStage.matek;

public class Viz extends OneSpriteStaticActor {
    public Viz() {
        super(Assets.manager.get(Assets.VIZ));
        setWidth(Assets.manager.get(Assets.GAT).getWidth()/20.0f);
        setDebug(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(getHeight() != (24.0f/matek.getMaxviz())*matek.getVizmennyiseg()) setHeight((24.0f/matek.getMaxviz())*matek.getVizmennyiseg());
    }
}
