package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.graphics.Texture;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Kacsa extends OneSpriteStaticActor {
    public Kacsa() {
        super(Assets.manager.get(Assets.KACSA));
        setDebug(false);
    }
}
