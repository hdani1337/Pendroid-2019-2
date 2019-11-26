package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.OptionsStage;

public class Ajto extends OneSpriteStaticActor {
    private boolean move;
    private boolean moveIn;
    private boolean moveOut;
    private Viewport viewport;
    private Sound nyikorgas;
    private boolean nyikorgasPlayed;
    private boolean nyikorgasSettedBack;

    public Ajto(Viewport viewport) {
        super(Assets.manager.get(Assets.AJTO));
        setPosition(-getWidth()*2,0);
        setDebug(false);
        if(viewport.getWorldWidth() > getWidth()) setWidth(viewport.getWorldWidth());
        this.viewport = viewport;
        nyikorgas = Assets.manager.get(Assets.NYIKORGAS);
        nyikorgasPlayed = true;
        nyikorgasSettedBack = false;

        addListener(new ClickListener());//Ez igazából csak azért kell, hogy a mögötte lévő stagen ne lehessen rákattintani a vissza gombra, amikor előtte van az ajtó
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
            if(!OptionsStage.isMuted() && !nyikorgasPlayed)
            {
                nyikorgas.play(1);
                nyikorgasPlayed = true;
            }

            if(moveIn) {
                if(nyikorgasPlayed && !nyikorgasSettedBack) {
                    nyikorgasPlayed = false;
                    nyikorgasSettedBack = true;
                }
                if (getX() < viewport.getWorldWidth()-this.getWidth()-15) setX(getX() + 30);
                else {
                    setX(viewport.getWorldWidth() - this.getWidth() + 15);
                    nyikorgasSettedBack = false;
                    moveIn = false;
                }
            }
            if(moveOut)
            {
                if(nyikorgasPlayed && !nyikorgasSettedBack) {
                    nyikorgasPlayed = false;
                    nyikorgasSettedBack = true;
                }
                if (getX() > -getWidth()*1.75) setX(getX() - 30);
                else {
                    setX(-getWidth() * 1.75f);
                    nyikorgasSettedBack = false;
                    moveOut = false;
                }
            }
        }

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
