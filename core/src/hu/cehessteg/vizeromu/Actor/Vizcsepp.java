package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;

public class Vizcsepp extends WorldActorGroup {
    public Vizcsepp(World world) {
        super(world, ShapeType.Circle, BodyDef.BodyType.DynamicBody, new FixtureDef());
        getFixtureDef().shape = new CircleShape();
        getFixtureDef().density = 2000.0f; // A vízcsepp tömege
        getFixtureDef().friction = 0.0f; // Súrlódás
        getFixtureDef().restitution = 0.0f; // Pattanás

        OneSpriteStaticActor water = new OneSpriteStaticActor(Assets.manager.get(Assets.VIZ_TEXTURE));
        water.setSize(0.4f,0.4f);
        water.setDebug(false);

        addActor(water);
        setSize(0.4f,0.4f);
        setColor(0,0,255,1);
    }
}