package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Arammero;
import hu.cehessteg.vizeromu.Actor.Penz;
import hu.cehessteg.vizeromu.Actor.Vizmero;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.Pause;

public class HudStage extends MyStage {
    Pause pause;
    OneSpriteStaticActor ora;
    OneSpriteStaticActor kismutato;
    OneSpriteStaticActor nagymutato;

    OneSpriteStaticActor speedup;
    OneSpriteStaticActor speeddown;

    Penz penz;
    Arammero arammero;
    Vizmero vizmero;

    int napok = 0;
    private boolean napokNov = true;
    private int simulationSpeed = 60;

    public HudStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        assignment();
        clock();
        speed();
        setPositions();
        addActors();
    }

    void assignment()
    {
        pause = new Pause(getViewport());
        arammero = new Arammero(this);
        vizmero = new Vizmero(this);
        penz = new Penz(this);
    }

    void speed()
    {
        speedup = new OneSpriteStaticActor(Assets.manager.get(Assets.SPEEDU));
        speedup.setDebug(false);

        speeddown = new OneSpriteStaticActor(Assets.manager.get(Assets.SPEEDD));
        speeddown.setDebug(false);

        speedup.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(simulationSpeed < 120) simulationSpeed += 1;
            }
        });

        speeddown.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(simulationSpeed > 1) simulationSpeed -= 1;
            }
        });
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

    void setPositions()
    {
        arammero.setPosition(pause.getX()-arammero.getWidth(),getViewport().getWorldHeight()-arammero.getHeight()-11);
        vizmero.setScale(0.85f);
        vizmero.setPosition(15,getViewport().getWorldHeight()-vizmero.getHeight()-15);
    }

    void addActors()
    {
        addActor(pause);
        addActor(ora);
        addActor(kismutato);
        addActor(nagymutato);
        addActor(arammero);
        addActor(vizmero);
        addActor(penz);
        /*addActor(speeddown);
        addActor(speedup);*/
    }

    void napszamlalo()
    {
        if(GameStage.matek.getH() % 24 == 0 && napokNov)
        {
            napok++;
            napokNov = false;
        }

        else if(GameStage.matek.getH() % 24 != 0 && !napokNov) napokNov = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        nagymutato.setRotation(-(360/60)*GameStage.matek.getM());
        kismutato.setRotation(-(360/12)*GameStage.matek.getH()-(360/12/60.0f)*GameStage.matek.getM());
        napszamlalo();
    }

    public int getNapok() {
        return napok;
    }

    public int getSimulationSpeed() {
        return simulationSpeed;
    }
}
