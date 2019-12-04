package hu.csanyzeg.master.MyBaseClasses.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.Stage.GameStage;

public class Pause extends OneSpriteStaticActor {
    public Pause(Viewport viewport) {
        super(Assets.manager.get(Assets.PAUSE));
        setSize(5*20,5*20);
        setDebug(false);
        setPosition(viewport.getWorldWidth()-this.getWidth(),viewport.getWorldHeight()-this.getHeight());
        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(GameStage.isGamePaused())  GameStage.setGamePaused(false);
                else GameStage.setGamePaused(true);
            }
        });
    }
}
