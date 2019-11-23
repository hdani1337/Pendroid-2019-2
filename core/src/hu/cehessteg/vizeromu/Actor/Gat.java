package hu.cehessteg.vizeromu.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.GameStage;

public class Gat extends OneSpriteStaticActor {

    public Gat() {
        super(Assets.manager.get(Assets.GAT));
        setPosition(-1.5f,-2);
        setSize(getWidth()/20,getHeight()/20);
        setDebug(false);
    }

    public static class gatListenes extends OneSpriteStaticActor
    {
        byte id;

        public gatListenes(final byte id) {
            super(Assets.manager.get(Assets.BLANK));
            this.id = id;
            this.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if(id == 1) GameStage.matek.getNyilasok()[0].setOpen(!GameStage.matek.getNyilasok()[0].isOpen);
                    else if(id == 2) GameStage.matek.getNyilasok()[1].setOpen(!GameStage.matek.getNyilasok()[1].isOpen);
                    else if(id == 3) GameStage.matek.getNyilasok()[2].setOpen(!GameStage.matek.getNyilasok()[2].isOpen);
                    else if(id == 4) GameStage.matek.getNyilasok()[3].setOpen(!GameStage.matek.getNyilasok()[3].isOpen);
                    else if(id == 5) GameStage.matek.getNyilasok()[4].setOpen(!GameStage.matek.getNyilasok()[4].isOpen);
                }
            });

            setZIndex(5000);

            if(id == 1)
            {
                setSize(3f,4.5f);
                setPosition(10.5f,8);
            }
            else if(id == 2)
            {
                setSize(3.4f,4.5f);
                setPosition(15.6f,9.2f);
            }
            else if(id == 3)
            {
                setSize(3.2f,4.4f);
                setPosition(21f,10.3f);
            }
            else if(id == 4)
            {
                setSize(3f,4.5f);
                setPosition(26.5f,11);
            }
            else if(id == 5)
            {
                setSize(3f,4.5f);
                setPosition(32,11);
            }
        }
    }
}
