package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.Stage.OptionsStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Kacsa extends OneSpriteStaticActor {
    private float rotation = 0.07f;

    public Kacsa() {
        super(Assets.manager.get(Assets.KACSA));
        setDebug(false);

        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!OptionsStage.muted) Assets.manager.get(Assets.KACSASOUND).play();
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(getRotation() + rotation);
        if(getRotation() > 5 || getRotation() < -5) rotation *= -1;
    }

    float kacsaX = 0.02f;

    public void move(Viz patak, int simulationSpeed)
    {
        if(sprite.getTexture() != Assets.manager.get(Assets.KACSASIMA)) sprite.setTexture(Assets.manager.get(Assets.KACSASIMA));
        setY(patak.getY() + patak.getHeight()/1.6f - getHeight()/2);
        if(patak.getX() < getX() && patak.getX() + patak.getWidth() > getX() + getWidth()) setX(getX() + kacsaX * (simulationSpeed/60.0f));
        if(getX() < 7.5 || getX() > 56) kacsaX *= -1;
    }
}
