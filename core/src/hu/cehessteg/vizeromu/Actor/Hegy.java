package hu.cehessteg.vizeromu.Actor;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Hegy extends OneSpriteStaticActor {
    public Hegy(byte id) {
        super(Assets.manager.get(Assets.HEGY1));
        setDebug(false);

        if(id==1)
        {
            setSize(getWidth()/40,getHeight()/40);
            setPosition(0,37-getHeight());
        }
        else if(id==2) {
            sprite.setTexture(Assets.manager.get(Assets.HEGY2));
            setSize(getWidth()/30,getHeight()/30);
            setPosition(24,36-getHeight());
        }
        else if(id==3) {
            sprite.setTexture(Assets.manager.get(Assets.HEGY2));
            setSize(getWidth()/37,getHeight()/37);
            setPosition(38.5f,-1.5f);
        }
    }
}
