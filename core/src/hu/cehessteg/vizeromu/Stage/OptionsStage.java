package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;

public class OptionsStage extends MyStage {
    MyButton menu;
    MyLabel myLabel;
    boolean mehetVissza;
    public static boolean muted = false;

    public OptionsStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addActors();
        addListeners();
    }

    void assignment()
    {
        menu = new MyButton("Vissza a menübe", Styles.getTextButtonStyle());
        myLabel = new MyLabel("Ide jönnek majd a beállítások\nValószínűleg csak a zene és a hangok némítása lesz", Styles.getCalibriLabelStyle());
    }

    void setPositions()
    {
        menu.setPosition(getViewport().getWorldWidth()*0.66f,getViewport().getWorldHeight()*0.1f);
        myLabel.setAlignment(0);
        myLabel.setPosition(getViewport().getWorldWidth()/2-myLabel.getWidth()/2,getViewport().getWorldHeight()/2+myLabel.getHeight()/4);
    }

    void addActors()
    {
        addActor(menu);
        addActor(myLabel);
    }

    void addListeners()
    {
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setMehetVissza(true);
            }
        });
    }

    @Override
    public void init() {

    }

    public boolean isMehetVissza() {
        return mehetVissza;
    }

    public void setMehetVissza(boolean mehetVissza) {
        this.mehetVissza = mehetVissza;
    }

    public static boolean isMuted() {
        return muted;
    }

    public static void setMuted(boolean muted) {
        OptionsStage.muted = muted;
    }
}
