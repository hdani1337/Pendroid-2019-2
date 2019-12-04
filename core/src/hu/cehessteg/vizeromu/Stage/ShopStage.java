package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import hu.cehessteg.vizeromu.Actor.Gomb;
import hu.cehessteg.vizeromu.Actor.Penz;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Matek;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.cehessteg.vizeromu.Vizeromu.gameSave;

public class ShopStage extends MyStage {
    boolean mehetVissza = false;

    Gomb menu;
    MyLabel myLabel;
    OneSpriteStaticActor background;
    Penz penz;

    ArrayList<OneSpriteStaticActor> gatKepek = new ArrayList<OneSpriteStaticActor>();
    ArrayList<OneSpriteStaticActor> deszkak = new ArrayList<OneSpriteStaticActor>();
    ArrayList<MyLabel> szintekLabel = new ArrayList<MyLabel>();
    ArrayList<Gomb> gombok = new ArrayList<Gomb>();

    public ShopStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        assignment();
        setPositions();
        addActors();
        addListeners();
    }

    void assignment()
    {
        menu = new Gomb("Vissza",this);
        myLabel = new MyLabel("Turbinák fejlesztése", Styles.getCalibriLabelStyle());
        bgStuff();
        penz = new Penz(this);

        for (int i = 0; i < 5; i++) {
            gatKepek.add(new OneSpriteStaticActor(Assets.manager.get(Assets.LYUK)));
            gatKepek.get(i).setDebug(false);

            deszkak.add(new OneSpriteStaticActor(Assets.manager.get(Assets.DESZKA)));
            deszkak.get(i).setDebug(false);
            deszkak.get(i).setSize(gatKepek.get(i).getWidth()+30, gatKepek.get(i).getHeight()+30);

            szintekLabel.add(new MyLabel("", Styles.getCalibriLabelStyle()));
            szintekLabel.get(i).setAlignment(0);

            gombok.add(new Gomb("",this));
        }
    }

    void bgStuff()
    {
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BLUE_TEXTURE));
        background.setColor(0,0,0,0.85f);
        addActor(background);
    }

    void setPositions()
    {
        menu.setPosition(getViewport().getWorldWidth()-menu.getWidth()-getViewport().getWorldHeight()*0.045f,getViewport().getWorldHeight()*0.045f);
        myLabel.setAlignment(0);
        myLabel.setPosition(getViewport().getWorldWidth()/2-myLabel.getWidth()/2,getViewport().getWorldHeight()*0.75f);
        background.setPosition(0,0);
        background.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());

        penz.setY(menu.getY()*0.85f);
        penz.setX(getViewport().getWorldWidth()*0.005f);

        gatKepek.get(0).setPosition(getViewport().getWorldWidth()/2 - 5*(gatKepek.get(0).getWidth() + 100)/2 ,getViewport().getWorldHeight()/1.825f);
        szintekLabel.get(0).setPosition(gatKepek.get(0).getX()+gatKepek.get(0).getWidth()/2-szintekLabel.get(0).getWidth()/2, gatKepek.get(0).getY() + gatKepek.get(0).getHeight()/2-szintekLabel.get(0).getHeight()/2);
        gombok.get(0).setPosition(gatKepek.get(0).getX() + gatKepek.get(0).getWidth()/2 - gombok.get(0).getWidth()/2, gatKepek.get(0).getY() - gombok.get(0).getHeight()*1.3f);

        for (int i = 1; i < 5; i++) {
            gatKepek.get(i).setPosition(gatKepek.get(i-1).getX() + gatKepek.get(i-1).getWidth() + 125 ,getViewport().getWorldHeight()/1.825f);
            deszkak.get(i).setPosition(gatKepek.get(i).getX()-10, gatKepek.get(i).getY()-20);
            gombok.get(i).setPosition(gatKepek.get(i).getX() + gatKepek.get(i).getWidth()/2 - gombok.get(i).getWidth()/2, gatKepek.get(i).getY() - gombok.get(i).getHeight()*1.3f);
            szintekLabel.get(i).setPosition(gatKepek.get(i).getX()+gatKepek.get(i).getWidth()/2-szintekLabel.get(i).getWidth()/2, gatKepek.get(i).getY() + gatKepek.get(i).getHeight()/2-szintekLabel.get(i).getHeight()/2);
        }
    }

    void addActors()
    {
        background.setDebug(false);

        addActor(menu);
        addActor(myLabel);
        addActor(penz);

        for (int i = 0; i < 5; i++) {
           addActor(gatKepek.get(i));
           addActor(gombok.get(i));
        }

        szintekLabel.get(0).setText(gameSave.getInteger("csoLevel1") + "");
        szintekLabel.get(1).setText(gameSave.getInteger("csoLevel2") + "");
        szintekLabel.get(2).setText(gameSave.getInteger("csoLevel3") + "");
        szintekLabel.get(3).setText(gameSave.getInteger("csoLevel4") + "");
        szintekLabel.get(4).setText(gameSave.getInteger("csoLevel5") + "");

        if(gameSave.getInteger("csoLevel1") == 0) addActor(deszkak.get(0));
        else addActor(szintekLabel.get(0));
        if(gameSave.getInteger("csoLevel2") == 0) addActor(deszkak.get(1));
        else addActor(szintekLabel.get(1));
        if(gameSave.getInteger("csoLevel3") == 0) addActor(deszkak.get(2));
        else addActor(szintekLabel.get(2));
        if(gameSave.getInteger("csoLevel4") == 0) addActor(deszkak.get(3));
        else addActor(szintekLabel.get(3));
        if(gameSave.getInteger("csoLevel5") == 0) addActor(deszkak.get(4));
        else addActor(szintekLabel.get(4));

        menu.myLabel.setPosition(menu.getX()+menu.getWidth()/2-menu.myLabel.getWidth()/2,menu.getY()+menu.getHeight()/2-menu.myLabel.getHeight()/2);
        menu.myLabel.setZIndex(30);
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

        gombok.get(0).addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(Matek.coins >= GameStage.matek.getNyilasok()[0].lvl * 200 + 1000)
                {
                    if(GameStage.matek.getNyilasok()[0].lvl == 0) {
                        deszkak.get(0).remove();
                        addActor(szintekLabel.get(0));
                    }
                    if(GameStage.matek.getNyilasok()[0].lvl < 25) {
                        GameStage.matek.lvlupcso(0);
                        GameStage.matek.buysometing(GameStage.matek.getNyilasok()[0].lvl * 200 + 1000);
                        gameSave.putInteger("csoLevel1", gameSave.getInteger("csoLevel1") + 1);
                        gameSave.flush();
                    }
                }
            }
        });

        gombok.get(1).addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(Matek.coins >= GameStage.matek.getNyilasok()[1].lvl * 500 + 2000)
                {
                    if(GameStage.matek.getNyilasok()[1].lvl == 0) {
                        deszkak.get(1).remove();
                        addActor(szintekLabel.get(1));
                    }
                    if(GameStage.matek.getNyilasok()[1].lvl < 25) {
                        GameStage.matek.lvlupcso(1);
                        GameStage.matek.buysometing(GameStage.matek.getNyilasok()[1].lvl * 500 + 2000);
                        gameSave.putInteger("csoLevel2", gameSave.getInteger("csoLevel2") + 1);
                        gameSave.flush();
                    }
                }
            }
        });

        gombok.get(2).addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(Matek.coins >= GameStage.matek.getNyilasok()[2].lvl * 500 + 2000)
                {
                    if(GameStage.matek.getNyilasok()[2].lvl == 0) {
                        deszkak.get(2).remove();
                        addActor(szintekLabel.get(2));
                    }
                    if(GameStage.matek.getNyilasok()[2].lvl < 25) {
                        GameStage.matek.lvlupcso(2);
                        GameStage.matek.buysometing(GameStage.matek.getNyilasok()[2].lvl * 500 + 2000);
                        gameSave.putInteger("csoLevel3", gameSave.getInteger("csoLevel3") + 1);
                        gameSave.flush();
                    }
                }
            }
        });

        gombok.get(3).addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(Matek.coins >= GameStage.matek.getNyilasok()[3].lvl * 500 + 2000)
                {
                    if(GameStage.matek.getNyilasok()[3].lvl == 0) {
                        deszkak.get(3).remove();
                        addActor(szintekLabel.get(3));
                    }
                    if(GameStage.matek.getNyilasok()[3].lvl < 25) {
                    GameStage.matek.lvlupcso(3);
                    GameStage.matek.buysometing(GameStage.matek.getNyilasok()[3].lvl * 500 + 2000);
                    gameSave.putInteger("csoLevel4", gameSave.getInteger("csoLevel4") + 1);
                    gameSave.flush();
                    }
                }
            }
        });

        gombok.get(4).addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(Matek.coins >= GameStage.matek.getNyilasok()[4].lvl * 500 + 2000)
                {
                    if(GameStage.matek.getNyilasok()[4].lvl == 0) {
                        deszkak.get(4).remove();
                        addActor(szintekLabel.get(4));
                    }
                    if(GameStage.matek.getNyilasok()[4].lvl < 25) {
                        GameStage.matek.lvlupcso(4);
                        GameStage.matek.buysometing(GameStage.matek.getNyilasok()[4].lvl * 500 + 2000);
                        gameSave.putInteger("csoLevel5", gameSave.getInteger("csoLevel5") + 1);
                        gameSave.flush();
                    }
                }
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

    @Override
    public void act(float delta) {
        super.act(delta);
        for (int i = 0; i < gombok.size(); i++)
        {
            gombok.get(i).setZIndex(100);
            gombok.get(i).myLabel.setZIndex(1000);
            gombok.get(i).myLabel.setText(GameStage.matek.getNyilasok()[i].lvl * 500 + 2000 + "");
            gombok.get(i).myLabel.setPosition( gombok.get(i).getX()+gombok.get(i).getWidth()/2-gombok.get(i).myLabel.getWidth()/2, gombok.get(i).getY()+gombok.get(i).getHeight()/2-gombok.get(i).myLabel.getHeight()/2);
        }
        for (int i = 0; i < szintekLabel.size(); i++)
        {
            szintekLabel.get(i).setText(GameStage.matek.getNyilasok()[i].lvl + "");
        }
    }
}
