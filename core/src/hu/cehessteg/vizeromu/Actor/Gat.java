package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Gat extends OneSpriteStaticActor {
    public Gat() {
        super(Assets.manager.get(Assets.GAT));
        setPosition(-1.5f,-2);
        setSize(getWidth()/20,getHeight()/20);
        setDebug(false);
    }
}
