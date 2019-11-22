package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class CautionSign extends OneSpriteStaticActor {
    byte id;
    private  boolean move;
    private boolean moveDown;
    private boolean moveUp;

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
        if(move) {
            if (moveDown) {
                if(id == 1) {
                    if (getY() < 0) setY(getY() + 5);
                    else {
                        setY(0);
                        move = false;
                    }
                }

                else if(id == 2) {
                    if (getY() > getStage().getViewport().getWorldHeight() - this.getHeight())
                        setY(getY() - 5);
                    else {
                        setY(getStage().getViewport().getWorldHeight() - this.getHeight());
                        move = false;
                    }
                }
            }
            if (moveUp) {
                if(id == 1) {
                    if (getY() > -this.getHeight()) setY(getY() - 1);
                    else {
                        setY(-this.getHeight());
                        move = false;
                    }
                }

                else if(id == 2) {
                    if (getY() < getStage().getViewport().getWorldHeight())
                        setY(getY() + 1);
                    else {
                        setY(getStage().getViewport().getWorldHeight());
                        move = false;
                    }
                }
            }
        }
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
