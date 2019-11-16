package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class GatAlja extends OneSpriteStaticActor {
    public GatAlja() {
        super(Assets.manager.get(Assets.GAT_ALJA));
        setDebug(false);
        setSize(getWidth()/80.0f,getHeight()/80.0f);
        //Ő azért kell, hogy a gát mögött és a hegyek előtt legyenek a vízcseppek
    }
}
