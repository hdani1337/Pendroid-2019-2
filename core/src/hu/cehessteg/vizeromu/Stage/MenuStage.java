package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyLabel;
import hu.cehessteg.vizeromu.Screen.GameScreen;

public class MenuStage extends MyStage {
    public CautionSign felsoSign;
    CautionSign alsoSign;
    public Ajto ajto;
    Gomb start;
    Gomb exit;
    Gomb info;
    Gomb options;
    MyLabel title;

    boolean drawGame = false;
    boolean drawInfo = false;
    boolean drawOptions = false;

    boolean mehetVissza = false;

    boolean ajtoKifogMenni = false;
    boolean willExit = false;

    private boolean jojjonCaution = true;

    public static Music menuMusic;

    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addListeners();
        addActors();
    }

    void assignment()
    {
        menuMusic = Assets.manager.get(Assets.MENU_MUSIC);
        felsoSign = new CautionSign((byte)2,getViewport());
        alsoSign = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
        title = new MyLabel("Vízerőmű",Styles.getCalibriLabelStyle());
        title.setFontScale(1.75f);
        title.setAlignment(0);
        start = new Gomb("Indítás",this);
        exit = new Gomb("Kilépés",this);
        info = new Gomb("Információ",this);
        options = new Gomb("Beállítások",this);
        ajto.setMove(true);
        ajto.setMoveIn(true);
        ajto.setMoveOut(false);
        felsoSign.setMove(true);
        alsoSign.setMove(true);
        felsoSign.setMoveDown(true);
        alsoSign.setMoveDown(true);
        felsoSign.setMoveUp(false);
        alsoSign.setMoveUp(false);
    }

    void setPositions()
    {
        start.setPosition(getViewport().getWorldWidth()*0.065f,getViewport().getWorldHeight()*0.66f);
        info.setPosition(start.getX(),start.getY()-120);
        options.setPosition(start.getX(),info.getY()-120);
        exit.setPosition(start.getX(),options.getY()-120);

        start.myLabel.setPosition(start.getX()+start.getWidth()/2-start.myLabel.getWidth()/2,start.getY()+start.getHeight()/2-start.myLabel.getHeight()/2);
        info.myLabel.setPosition(info.getX()+info.getWidth()/2-info.myLabel.getWidth()/2,info.getY()+info.getHeight()/2-info.myLabel.getHeight()/2);
        options.myLabel.setPosition(options.getX()+options.getWidth()/2-options.myLabel.getWidth()/2,options.getY()+options.getHeight()/2-options.myLabel.getHeight()/2);
        exit.myLabel.setPosition(exit.getX()+exit.getWidth()/2-exit.myLabel.getWidth()/2,exit.getY()+exit.getHeight()/2-exit.myLabel.getHeight()/2);
    }

    void addListeners()
    {
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                moveBack = true;
                ajto.setMove(true);
                ajto.setMoveOut(true);
                ajto.setMoveIn(false);
                ajtoKifogMenni = true;
                drawGame = true;
                drawInfo = false;
                drawOptions = false;
            }
        });

        info.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ajto.setMove(true);
                ajto.setMoveOut(true);
                ajto.setMoveIn(false);
                ajtoKifogMenni = true;
                moveBack = true;
                drawInfo = true;
                drawOptions = false;
                drawGame = false;
            }
        });

        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ajto.setMove(true);
                ajto.setMoveOut(true);
                ajto.setMoveIn(false);
                ajtoKifogMenni = true;
                moveBack = true;
                drawOptions = true;
                drawGame = false;
                drawInfo = false;
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ajto.setMove(true);
                ajto.setMoveOut(true);
                ajto.setMoveIn(false);
                ajtoKifogMenni = true;
                moveBack = true;
                drawOptions = false;
                drawGame = false;
                drawInfo = false;
                willExit = true;
            }
        });

    }

    void addActors()
    {
        addActor(felsoSign);
        addActor(alsoSign);
        addActor(ajto);
        addActor(start);
        addActor(options);
        addActor(exit);
        addActor(info);
        //addActor(title);

        ajto.setZIndex(0);
        felsoSign.setZIndex(1);
        alsoSign.setZIndex(1);
        start.setZIndex(2);
        options.setZIndex(2);
        exit.setZIndex(2);
        info.setZIndex(2);

        start.myLabel.setZIndex(30);
        options.myLabel.setZIndex(30);
        info.myLabel.setZIndex(30);
        exit.myLabel.setZIndex(30);

        title.setColor(1,1,1,0f);
        start.setColor(1,1,1,0f);
        options.setColor(1,1,1,0f);
        exit.setColor(1,1,1,0f);
        info.setColor(1,1,1,0f);
    }

    @Override
    public void init() {

    }

    private float alpha = 0;
    private boolean moveBack = false;

    private float movingButtons = 0.05f;

    @Override
    public void act(float delta) {
        super.act(delta);
        ajtoVissza();//Ajtó vissza bejön
        buttonsFadeIn();//Gombok megjelennek
        ajtoKimegy();//Ajtó kimegy
        mindenKimegy();//Minden actor kimegy
        buttonsAnimation();//Gombok animációja (kísérleti jelleggel)
        exitOrGame();//Kilépés vagy váltás GameScreenre
    }

    void ajtoVissza()
    {
        if(isMehetVissza())
        {
            ajto.setMove(true);
            ajto.setMoveIn(true);
            ajto.setMoveOut(false);
            felsoSign.setMove(true);
            alsoSign.setMove(true);
            felsoSign.setMoveDown(true);
            alsoSign.setMoveDown(true);
            felsoSign.setMoveUp(false);
            alsoSign.setMoveUp(false);
            alpha = 0;
            title.setColor(1,1,1,alpha);
            start.setColor(1,1,1,alpha);
            options.setColor(1,1,1,alpha);
            exit.setColor(1,1,1,alpha);
            info.setColor(1,1,1,alpha);
            moveBack = false;
            setPositions();
            setMehetVissza(false);
        }
    }

    void ajtoKimegy()
    {
        if(ajtoKifogMenni)
        {
            if(ajto.getX() < -ajto.getWidth()*0.8) {
                felsoSign.setMove(true);
                alsoSign.setMove(true);
                felsoSign.setMoveDown(false);
                alsoSign.setMoveDown(false);
                felsoSign.setMoveUp(true);
                alsoSign.setMoveUp(true);
                ajtoKifogMenni = false;
            }
        }
    }

    void mindenKimegy()
    {
        if(moveBack)
        {
            for (Actor actor : getActors())
            {
                if(!(actor instanceof CautionSign) && !(actor instanceof Ajto)) if(actor.getX()>-actor.getWidth()*2) actor.setX(actor.getX()-40);
            }
        }
    }

    void buttonsFadeIn()
    {
        if(ajto.getX() > getViewport().getWorldWidth()-ajto.getWidth()-50)
        {
            if(alpha < 0.99) alpha += 0.02;
            else alpha = 1;

            title.setColor(1,1,1,alpha);
            start.setColor(1,1,1,alpha);
            options.setColor(1,1,1,alpha);
            exit.setColor(1,1,1,alpha);
            info.setColor(1,1,1,alpha);
        }
    }

    void buttonsAnimation()
    {
        start.setRotation(start.getRotation() + movingButtons);
        if(start.getRotation() < -4 || start.getRotation() > 4) movingButtons *= -1;
    }

    void exitOrGame()
    {
        if(felsoSign.getY() >= getViewport().getWorldHeight()) {
            if (drawGame) {
                game.setScreen(new GameScreen(game));
                drawGame = false;
            }
            else if (willExit)
            {
                Gdx.app.exit();
                System.exit(0);
            }
        }
    }

    public boolean isDrawGame() {
        return drawGame;
    }

    public boolean isDrawInfo() {
        return drawInfo;
    }

    public boolean isDrawOptions() {
        return drawOptions;
    }

    public boolean isMehetVissza() {
        return mehetVissza;
    }

    public void setMehetVissza(boolean mehetVissza) {
        this.mehetVissza = mehetVissza;
    }

    public boolean isJojjonCaution() {
        return jojjonCaution;
    }

    public void setJojjonCaution(boolean jojjonCaution) {
        this.jojjonCaution = jojjonCaution;
        ajto.setMove(jojjonCaution);
        felsoSign.setMove(jojjonCaution);
        alsoSign.setMove(jojjonCaution);
        felsoSign.setMoveDown(jojjonCaution);
        alsoSign.setMoveDown(jojjonCaution);
        if(jojjonCaution == false) {
            alsoSign.setY(0);
            felsoSign.setY(getViewport().getWorldHeight() - felsoSign.getHeight());
            ajto.setX(getViewport().getWorldWidth() - ajto.getWidth() + 40);
        }
    }
}
