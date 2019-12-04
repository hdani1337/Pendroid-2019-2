package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Viz extends OneSpriteStaticActor {
    public Viz() {
        super(Assets.manager.get(Assets.VIZ));
        setWidth(Assets.manager.get(Assets.GAT).getWidth()/20.0f);
        setDebug(false);
    }
}
