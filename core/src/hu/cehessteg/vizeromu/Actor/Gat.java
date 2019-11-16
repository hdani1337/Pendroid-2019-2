package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Gat extends OneSpriteStaticActor {
    public Gat() {
        super(Assets.manager.get(Assets.GAT));
        setPosition(0,0);
        setSize(getWidth()/80,getHeight()/80);
        setDebug(false);
    }
}
