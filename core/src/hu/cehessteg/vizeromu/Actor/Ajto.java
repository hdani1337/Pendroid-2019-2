package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Ajto extends OneSpriteStaticActor {
    private boolean move = false;
    private boolean isFinished = false;

    public Ajto(Viewport viewport) {
        super(Assets.manager.get(Assets.AJTO));
        setPosition(-getWidth()*1.5f,0);
        setDebug(false);
        if(viewport.getWorldWidth() > 1400) setWidth(viewport.getWorldWidth());
    }

    public void setMove(boolean move)
    {
        this.move = move;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(move && !isFinished)
        {
            if(getX() < -39) setX(getX()+40);
            else isFinished = true;
        }
    }
}
