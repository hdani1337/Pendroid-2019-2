package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.Actor.Gomb;
import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;
import hu.cehessteg.vizeromu.Screen.MenuScreen;
import hu.cehessteg.vizeromu.Vizeromu;

public class GameOverStage extends MyStage {
    MyLabel gameOver;
    Gomb restart;
    Gomb menu;
    OneSpriteStaticActor background;
    CautionSign felso;
    CautionSign also;
    Ajto ajto;
    MyLabel napok;
    int napokInt = 0;

    public GameOverStage(Viewport viewport, Batch batch, final MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addActors();
        addListeners();
    }

    void assignment()
    {
        gameOver = new MyLabel("Vége a játéknak!", Styles.getCalibriLabelStyle());
        restart = new Gomb("Újraindítás", this);
        menu = new Gomb("Kilépés",this);
        napok = new MyLabel("",Styles.getCalibriLabelStyle());
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BLUE_TEXTURE));
        background.setColor(0,0,0,0.5f);
        background.setDebug(false);
        felso = new CautionSign((byte)2,getViewport());
        also = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
    }

    void setPositions()
    {
        gameOver.setAlignment(0);
        gameOver.setPosition(getViewport().getWorldWidth()/2-gameOver.getWidth()/2,getViewport().getWorldHeight()/2+gameOver.getHeight()*2.5f);
        restart.setPosition(getViewport().getWorldWidth()/2-restart.getWidth()/2,getViewport().getWorldHeight()/2);
        menu.setPosition(getViewport().getWorldWidth()/2-menu.getWidth()/2,restart.getY()-120);
        ajto.setX(-ajto.getWidth()*1.25f);
        background.setPosition(0,0);
        background.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    void addActors()
    {
        addActor(background);
        addActor(gameOver);
        addActor(restart);
        addActor(menu);
        addActor(ajto);
        addActor(felso);
        addActor(also);
        addActor(napok);

        menu.myLabel.setPosition(menu.getX()+menu.getWidth()/2-menu.myLabel.getWidth()/2,menu.getY()+menu.getHeight()/2-menu.myLabel.getHeight()/2);
        menu.myLabel.setZIndex(menu.getZIndex()+1);

        restart.myLabel.setPosition(restart.getX()+restart.getWidth()/2-restart.myLabel.getWidth()/2,restart.getY()+restart.getHeight()/2-restart.myLabel.getHeight()/2);
        restart.myLabel.setZIndex(restart.getZIndex()+1);

        napok.setAlignment(0);
        napok.setPosition(getViewport().getWorldWidth()/2-napok.getWidth()/2,getViewport().getWorldHeight()*0.2f);
    }

    void addListeners()
    {
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });

        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ajto.setMove(true);
                ajto.setMoveIn(true);
                ajto.setMoveOut(false);
                felso.setMove(true);
                also.setMove(true);
                felso.setMoveDown(true);
                also.setMoveDown(true);
                felso.setMoveUp(false);
                also.setMoveUp(false);
            }
        });
    }

    @Override
    public void init() {

    }

    int lastRecord = Vizeromu.gameSave.getInteger("rekordNapok");

    @Override
    public void act(float delta) {
        super.act(delta);
        if(ajto.getX() >= getViewport().getWorldWidth()-ajto.getWidth()) {
            MenuScreen menuScreen = new MenuScreen(game);
            menuScreen.setJojjonCaution(false);
            game.setScreen(menuScreen);
        }

        if(!napok.getText().equals(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben.")) {
            napok.setText(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben.");
        }

        if(napokInt > Vizeromu.gameSave.getInteger("rekordNapok")) {
            if(!napok.getText().equals(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben,\nezzel megdöntve a rekordodat, ami " + lastRecord + " volt.")) {
                napok.setText(napokInt + " napot dolgoztál le sikeresen ebben a játékmenetben,\nezzel megdöntve a rekordodat, ami " + lastRecord + " volt.");
                Vizeromu.gameSave.putInteger("rekordNapok", napokInt);
                Vizeromu.gameSave.flush();
            }
        }
    }

    public void setNapokInt(int napokInt) {
        this.napokInt = napokInt;
    }

    public int getNapokInt() {
        return napokInt;
    }
}
