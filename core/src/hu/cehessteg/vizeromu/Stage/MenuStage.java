package hu.cehessteg.vizeromu.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.cehessteg.vizeromu.Actor.Ajto;
import hu.cehessteg.vizeromu.Actor.CautionSign;
import hu.cehessteg.vizeromu.GlobalClasses.Styles;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyStage;
import hu.cehessteg.vizeromu.ParentClasses.UI.MyButton;
import hu.cehessteg.vizeromu.Screen.GameScreen;

public class MenuStage extends MyStage {
    public CautionSign felsoSign;
    CautionSign alsoSign;
    public Ajto ajto;
    MyButton start;
    MyButton exit;
    MyButton info;
    MyButton options;

    boolean drawGame = false;
    boolean drawInfo = false;
    boolean drawOptions = false;

    boolean mehetVissza = false;

    boolean ajtoKifogMenni = false;
    boolean willExit = false;

    public MenuStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        assignment();
        setPositions();
        addListeners();
        addActors();
    }

    void assignment()
    {
        felsoSign = new CautionSign((byte)2,getViewport());
        alsoSign = new CautionSign((byte)1,getViewport());
        ajto = new Ajto(getViewport());
        start = new MyButton("Start", Styles.getTextButtonStyle());
        exit = new MyButton("Kilépés", Styles.getTextButtonStyle());
        info = new MyButton("A játékról", Styles.getTextButtonStyle());
        options = new MyButton("Beállítások", Styles.getTextButtonStyle());
        ajto.setMove(true);
        ajto.setMoveIn(true);
        ajto.setMoveOut(true);
        felsoSign.setMove(true);
        alsoSign.setMove(true);
        felsoSign.setMoveDown(true);
        alsoSign.setMoveDown(true);
        felsoSign.setMoveUp(false);
        alsoSign.setMoveUp(false);
    }

    void setPositions()
    {
        start.setPosition(getViewport().getWorldWidth()*0.18f,getViewport().getWorldHeight()*0.66f);
        info.setPosition(start.getX(),start.getY()-100);
        options.setPosition(start.getX(),info.getY()-100);
        exit.setPosition(start.getX(),options.getY()-100);
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

        ajto.setZIndex(0);
        felsoSign.setZIndex(1);
        alsoSign.setZIndex(1);
        start.setZIndex(2);
        options.setZIndex(2);
        exit.setZIndex(2);
        info.setZIndex(2);

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

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isMehetVissza())
        {
            ajto.setMove(true);
            ajto.setMoveIn(true);
            ajto.setMoveOut(true);
            felsoSign.setMove(true);
            alsoSign.setMove(true);
            felsoSign.setMoveDown(true);
            alsoSign.setMoveDown(true);
            felsoSign.setMoveUp(false);
            alsoSign.setMoveUp(false);
            alpha = 0;
            start.setColor(1,1,1,alpha);
            options.setColor(1,1,1,alpha);
            exit.setColor(1,1,1,alpha);
            info.setColor(1,1,1,alpha);
            moveBack = false;
            setPositions();
            setMehetVissza(false);
        }

        if(ajto.getX() > -50)
        {
            if(alpha < 0.99) alpha += 0.02;
            else alpha = 1;

            start.setColor(1,1,1,alpha);
            options.setColor(1,1,1,alpha);
            exit.setColor(1,1,1,alpha);
            info.setColor(1,1,1,alpha);
        }

        if(moveBack)
        {
            for (Actor actor : getActors())
            {
                if(!(actor instanceof CautionSign) && !(actor instanceof Ajto)) if(actor.getX()>-actor.getWidth()-1)actor.setX(actor.getX()-20);
            }
        }

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
}
