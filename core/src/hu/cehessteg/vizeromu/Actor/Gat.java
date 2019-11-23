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
            super(Assets.manager.get(Assets.RED_CIRC));
            setDebug(false);
            this.id = id;
            if(id == 1) setRotation(5);
            else if(id == 2) setRotation(2.5f);
            this.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    setZIndex(5000);
                    if(id == 1) {
                        if(GameStage.matek.getNyilasok()[0].isOpen) {
                            GameStage.matek.getNyilasok()[0].setOpen(false);
                            sprite.setTexture(Assets.manager.get(Assets.RED_CIRC));
                        }
                        else
                        {
                            GameStage.matek.getNyilasok()[0].setOpen(true);
                            sprite.setTexture(Assets.manager.get(Assets.GREEN_CIRC));
                        }

                    }
                    else if(id == 2) {
                        if(GameStage.matek.getNyilasok()[1].isOpen) {
                            GameStage.matek.getNyilasok()[1].setOpen(false);
                            sprite.setTexture(Assets.manager.get(Assets.RED_CIRC));
                        }
                        else
                        {
                            GameStage.matek.getNyilasok()[1].setOpen(true);
                            sprite.setTexture(Assets.manager.get(Assets.GREEN_CIRC));
                        }

                    }
                    else if(id == 3) {
                        if(GameStage.matek.getNyilasok()[2].isOpen) {
                            GameStage.matek.getNyilasok()[2].setOpen(false);
                            sprite.setTexture(Assets.manager.get(Assets.RED_CIRC));
                        }
                        else
                        {
                            GameStage.matek.getNyilasok()[2].setOpen(true);
                            sprite.setTexture(Assets.manager.get(Assets.GREEN_CIRC));
                        }

                    }
                    else if(id == 4) {
                        if(GameStage.matek.getNyilasok()[3].isOpen) {
                            GameStage.matek.getNyilasok()[3].setOpen(false);
                            sprite.setTexture(Assets.manager.get(Assets.RED_CIRC));
                        }
                        else
                        {
                            GameStage.matek.getNyilasok()[3].setOpen(true);
                            sprite.setTexture(Assets.manager.get(Assets.GREEN_CIRC));
                        }

                    }
                    else if(id == 5) {
                        if(GameStage.matek.getNyilasok()[4].isOpen) {
                            GameStage.matek.getNyilasok()[4].setOpen(false);
                            sprite.setTexture(Assets.manager.get(Assets.RED_CIRC));
                        }
                        else
                        {
                            GameStage.matek.getNyilasok()[4].setOpen(true);
                            sprite.setTexture(Assets.manager.get(Assets.GREEN_CIRC));
                        }

                    }
                }
            });

            setZIndex(5000);

            if(id == 1)
            {
                setSize(3.34f,4.5f);
                setPosition(10.37f,8);
            }
            else if(id == 2)
            {
                setSize(3.485f,4.45f);
                setPosition(15.49f,9.25f);
            }
            else if(id == 3)
            {
                setSize(3.53f,4.4f);
                setPosition(20.83f,10.3f);
            }
            else if(id == 4)
            {
                setSize(3.525f,4.4f);
                setPosition(26.225f,11);
            }
            else if(id == 5)
            {
                setSize(3.57f,4.5f);
                setPosition(31.63f,11);
            }
        }
    }
}
