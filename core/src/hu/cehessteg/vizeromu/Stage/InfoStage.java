package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Gomb;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;

public class InfoStage extends MyStage {
    OneSpriteStaticActor zoli;
    OneSpriteStaticActor bence;
    OneSpriteStaticActor dani;
    OneSpriteStaticActor david;

    MyLabel zoliLabel;
    MyLabel benceLabel;
    MyLabel daniLabel;
    MyLabel davidLabel;

    MyLabel zoliLabelTitle;
    MyLabel benceLabelTitle;
    MyLabel daniLabelTitle;
    MyLabel davidLabelTitle;

    MyLabel myLabel;

    Gomb back;

    boolean mehetvissza;

    public InfoStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        labelStuff();
        setPositions();
        addListeners();
        addActors();
        mehetvissza = false;
    }

    void assignment()
    {
        zoli = new OneSpriteStaticActor(Assets.manager.get(Assets.ZOLI));
        bence = new OneSpriteStaticActor(Assets.manager.get(Assets.BENCE));
        dani = new OneSpriteStaticActor(Assets.manager.get(Assets.DANI));
        david = new OneSpriteStaticActor(Assets.manager.get(Assets.DAVID));

        zoliLabel = new MyLabel("Miklós Zoltán", Styles.getCalibriLabelStyle());
        benceLabel = new MyLabel("Kutai Bence", Styles.getCalibriLabelStyle());
        daniLabel = new MyLabel("Horváth Dániel", Styles.getCalibriLabelStyle());
        davidLabel = new MyLabel("Halász Dávid", Styles.getCalibriLabelStyle());

        zoliLabelTitle = new MyLabel("Szoftverfejlesztő", Styles.getCalibriLabelStyle());
        benceLabelTitle = new MyLabel("Grafikus", Styles.getCalibriLabelStyle());
        daniLabelTitle = new MyLabel("Szoftverfejlesztő", Styles.getCalibriLabelStyle());
        davidLabelTitle = new MyLabel("Szoftverfejlesztő", Styles.getCalibriLabelStyle());

        back = new Gomb("Vissza",this);

        myLabel = new MyLabel("Ide jön majd valami leírás a játékról", Styles.getCalibriLabelStyle());
    }

    void labelStuff()
    {
        zoliLabelTitle.setFontScale(0.55f);
        benceLabelTitle.setFontScale(0.55f);
        daniLabelTitle.setFontScale(0.55f);
        davidLabelTitle.setFontScale(0.55f);

        zoliLabel.setAlignment(0);
        benceLabel.setAlignment(0);
        daniLabel.setAlignment(0);
        davidLabel.setAlignment(0);

        zoliLabelTitle.setAlignment(0);
        benceLabelTitle.setAlignment(0);
        daniLabelTitle.setAlignment(0);
        davidLabelTitle.setAlignment(0);

        myLabel.setAlignment(0);
    }

    void setPositions()
    {
        zoli.setPosition(getViewport().getWorldWidth()/2-(zoli.getWidth()+bence.getWidth()+dani.getWidth()+david.getWidth()+300)/2,getViewport().getWorldHeight()/1.25f-zoli.getHeight()/2);
        bence.setPosition(zoli.getX()+zoli.getWidth()+100,zoli.getY());
        dani.setPosition(bence.getX()+bence.getWidth()+100,zoli.getY());
        david.setPosition(dani.getX()+dani.getWidth()+100,zoli.getY());

        zoliLabel.setPosition(zoli.getX()+zoli.getWidth()/2-zoliLabel.getWidth()/2,zoli.getY()-60);
        benceLabel.setPosition(bence.getX()+bence.getWidth()/2-benceLabel.getWidth()/2,bence.getY()-60);
        daniLabel.setPosition(dani.getX()+dani.getWidth()/2-daniLabel.getWidth()/2,dani.getY()-60);
        davidLabel.setPosition(david.getX()+david.getWidth()/2-davidLabel.getWidth()/2,david.getY()-60);

        zoliLabelTitle.setPosition((zoliLabel.getX()+zoliLabel.getWidth()/2)-zoliLabelTitle.getWidth()/2,zoliLabel.getY()-35);
        benceLabelTitle.setPosition((benceLabel.getX()+benceLabel.getWidth()/2)-benceLabelTitle.getWidth()/2,benceLabel.getY()-35);
        daniLabelTitle.setPosition((daniLabel.getX()+daniLabel.getWidth()/2)-daniLabelTitle.getWidth()/2,daniLabel.getY()-35);
        davidLabelTitle.setPosition((davidLabel.getX()+davidLabel.getWidth()/2)-davidLabelTitle.getWidth()/2,davidLabel.getY()-35);

        back.setPosition(getViewport().getWorldWidth()*0.66f + back.getWidth()/2,getViewport().getWorldHeight()*0.1f);
        back.myLabel.setPosition(back.getX()+back.getWidth()/2-back.myLabel.getWidth()/2,back.getY()+back.getHeight()/2-back.myLabel.getHeight()/2);

        myLabel.setPosition(getViewport().getWorldWidth()/2-myLabel.getWidth()/2,getViewport().getWorldHeight()/2-myLabel.getHeight()*2.25f);
    }

    void addListeners()
    {
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setMehetvissza(true);

            }
        });
    }

    void addActors()
    {
        zoli.setDebug(false);
        bence.setDebug(false);
        dani.setDebug(false);
        david.setDebug(false);

        addActor(zoli);
        addActor(bence);
        addActor(dani);
        addActor(david);

        addActor(zoliLabel);
        addActor(benceLabel);
        addActor(daniLabel);
        addActor(davidLabel);

        addActor(zoliLabelTitle);
        addActor(benceLabelTitle);
        addActor(daniLabelTitle);
        addActor(davidLabelTitle);

        addActor(back);
        back.myLabel.setZIndex(300);

        addActor(myLabel);
    }

    @Override
    public void init() {

    }

    public boolean isMehetvissza() {
        return mehetvissza;
    }

    public void setMehetvissza(boolean mehetvissza) {
        this.mehetvissza = mehetvissza;
    }
}
