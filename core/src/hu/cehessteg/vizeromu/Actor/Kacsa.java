package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Kacsa extends OneSpriteStaticActor {
    public Kacsa() {
        super(Assets.manager.get(Assets.KACSA));
        setDebug(false);
    }
}
