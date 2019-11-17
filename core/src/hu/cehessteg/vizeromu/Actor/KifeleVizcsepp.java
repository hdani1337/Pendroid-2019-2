package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Box2dWorld.WorldActorGroup;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.ShapeType;

public class KifeleVizcsepp extends WorldActorGroup {
    public KifeleVizcsepp(World world) {
        super(world, ShapeType.Circle, BodyDef.BodyType.DynamicBody, new FixtureDef());
        getFixtureDef().shape = new CircleShape();
        getFixtureDef().density = 3000.0f; // A vízcsepp tömege
        getFixtureDef().friction = 0.0f; // Súrlódás
        getFixtureDef().restitution = 0f; // Pattanás

        OneSpriteStaticActor water = new OneSpriteStaticActor(Assets.manager.get(Assets.VIZ_TEXTURE));
        water.setSize(0.6f,0.6f);
        water.setDebug(false);

        addActor(water);
        setSize(0.6f,0.6f);
        setColor(0,0,255,1);
    }
}
