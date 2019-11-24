package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gat;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.ParentClasses.UI.Pause;

public class HudStage extends MyStage {
    MyLabel coins;
    Pause pause;

    public HudStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
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

    void addActors()
    {
        addActor(pause);
        //addActor(coins);
    }

    @Override
    public void init() {

    }
}
