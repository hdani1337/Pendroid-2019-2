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
}
