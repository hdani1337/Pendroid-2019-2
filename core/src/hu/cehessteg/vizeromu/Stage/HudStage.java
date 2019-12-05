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
    float alpha;

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
        alpha = 0;
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

        speeddown.setSize(pause.getWidth(),pause.getHeight());
        speedup.setSize(pause.getWidth(),pause.getHeight());

        speedup.setPosition(getViewport().getWorldWidth()-speedup.getWidth(),getViewport().getWorldHeight()/2+25);
        speeddown.setPosition(speedup.getX(),speedup.getY()-speeddown.getHeight()-10);

        speedup.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(simulationSpeed < 360) simulationSpeed += 12;
            }
        });

        speeddown.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(simulationSpeed > 12) simulationSpeed -= 12;
                else simulationSpeed = 6;
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
        vizmero.sprite.setAlpha(0);
        arammero.sprite.setAlpha(0);
        ora.sprite.setAlpha(0);
        kismutato.sprite.setAlpha(0);
        nagymutato.sprite.setAlpha(0);
        pause.sprite.setAlpha(0);
        speeddown.sprite.setAlpha(0);
        speedup.sprite.setAlpha(0);
        penz.sprite.setAlpha(0);

        addActor(pause);
        addActor(ora);
        addActor(kismutato);
        addActor(nagymutato);
        addActor(arammero);
        addActor(vizmero);
        addActor(penz);
        addActor(speeddown);
        addActor(speedup);
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

    void hudFadeIn()
    {
        if(alpha < 0.99) alpha += 0.02;
        else alpha = 1;

        vizmero.sprite.setAlpha(alpha);
        arammero.sprite.setAlpha(alpha);
        ora.sprite.setAlpha(alpha);
        kismutato.sprite.setAlpha(alpha);
        nagymutato.sprite.setAlpha(alpha);
        pause.sprite.setAlpha(alpha);
        speeddown.sprite.setAlpha(alpha);
        speedup.sprite.setAlpha(alpha);
        penz.sprite.setAlpha(alpha);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        hudFadeIn();
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
