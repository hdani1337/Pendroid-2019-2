package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class Ajto extends OneSpriteStaticActor {
    private boolean move;
    private boolean moveIn;
    private boolean moveOut;
    private Viewport viewport;

    public Ajto(Viewport viewport) {
        super(Assets.manager.get(Assets.AJTO));
        setPosition(-getWidth()*2,0);
        setDebug(false);
        if(viewport.getWorldWidth() > getWidth()) setWidth(viewport.getWorldWidth());
        this.viewport = viewport;
    }

    public void setMove(boolean move)
    {
        this.move = move;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(move)
        {
            if(moveIn) {
                if (getX() < viewport.getWorldWidth()-this.getWidth()-20) setX(getX() + 40);
                else setX(viewport.getWorldWidth()-this.getWidth()+20);
            }
            if(moveOut)
            {
                if (getX() > -getWidth()*1.25) setX(getX() - 20);
                else setX(-getWidth()*1.25f);
            }
        }
        System.out.println(getX());
    }

    public boolean isMoveIn() {
        return moveIn;
    }

    public void setMoveIn(boolean moveIn) {
        this.moveIn = moveIn;
    }

    public boolean isMoveOut() {
        return moveOut;
    }

    public void setMoveOut(boolean moveOut) {
        this.moveOut = moveOut;
    }
}
