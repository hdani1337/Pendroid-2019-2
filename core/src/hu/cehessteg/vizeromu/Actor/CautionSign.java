package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class CautionSign extends OneSpriteStaticActor {
    byte id;
    private boolean isFinished = false;

    public CautionSign(byte id, Viewport viewport) {
        super(Assets.manager.get(Assets.CAUTION));
        setDebug(false);
        if(id == 1) setY(-getHeight());
        else if (id == 2) setY(viewport.getWorldHeight());
        this.id = id;
        if(viewport.getWorldWidth() > 1400) setWidth(viewport.getWorldWidth());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(id == 1 && getY() < -1) setY(getY()+10);
        else isFinished = true;
        if(id == 2 && getY() > getStage().getViewport().getWorldHeight()-getHeight()+1) setY(getY()-10);
        else isFinished = true;
    }

    public boolean isFinished()
    {
        return this.isFinished;
    }
}
