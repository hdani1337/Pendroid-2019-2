package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.ParentClasses.UI.Pause;

public class HudStage extends MyStage {
    MyLabel coins;
    Pause pause;
    OneSpriteStaticActor ora;
    OneSpriteStaticActor kismutato;
    OneSpriteStaticActor nagymutato;

    public HudStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        clock();
        addActors();
    }

    void assignment()
    {
        coins = new MyLabel("Pénz: 0", Styles.getCalibriLabelStyle()){
            @Override
            public void act(float delta) {
                super.act(delta);
                setText("Pénz: " + GameStage.matek.coins);
            }
        };
        coins.setPosition(0,getViewport().getWorldHeight()-coins.getHeight());
        pause = new Pause(getViewport());
    }

    void clock()
    {
        ora = new OneSpriteStaticActor(Assets.manager.get(Assets.ORA));
        kismutato = new OneSpriteStaticActor(Assets.manager.get(Assets.MUTATO));
        nagymutato = new OneSpriteStaticActor(Assets.manager.get(Assets.MUTATO));

        ora.setSize(ora.getWidth()/2,ora.getHeight()/2);
        ora.setPosition(getViewport().getWorldWidth()-ora.getWidth(),0);

        ora.setDebug(false);
        kismutato.setDebug(false);
        nagymutato.setDebug(false);

        kismutato.setSize(kismutato.getWidth()/15,kismutato.getHeight()/15);
        nagymutato.setSize(nagymutato.getWidth()/10,nagymutato.getHeight()/10);

        kismutato.sprite.setOrigin(kismutato.getWidth()/2,0);
        nagymutato.sprite.setOrigin(nagymutato.getWidth()/2,0);

        kismutato.setPosition(ora.getX()+ora.getWidth()/2-2,ora.getY()+ora.getHeight()/2-kismutato.getWidth()/2+2);
        nagymutato.setPosition(ora.getX()+ora.getWidth()/2-2,ora.getY()+ora.getHeight()/2-nagymutato.getWidth()/2+2);
    }

    void addActors()
    {
        addActor(pause);
        //addActor(coins);
        addActor(ora);
        addActor(kismutato);
        addActor(nagymutato);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        nagymutato.setRotation(-(360/60)*GameStage.matek.getM());
        kismutato.setRotation(-(360/12)*GameStage.matek.getH());
    }
}
