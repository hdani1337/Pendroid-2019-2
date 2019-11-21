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
import hu.cehessteg.vizeromu.Screen.InfoScreen;
import hu.cehessteg.vizeromu.Screen.OptionsScreen;

public class MenuStage extends MyStage {
    CautionSign felsoSign;
    CautionSign alsoSign;
    Ajto ajto;
    MyButton start;
    MyButton exit;
    MyButton info;
    MyButton options;

    boolean drawGame = false;
    boolean drawInfo = false;
    boolean drawOptions = false;

    boolean isFinished = false;

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
                drawGame = true;
            }
        });

        info.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                moveBack = true;
                drawInfo = true;
            }
        });

        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                moveBack = true;
                drawOptions = true;
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
                System.exit(0);
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

        if(felsoSign.isFinished() && alsoSign.isFinished()) ajto.setMove(true);

        if(ajto.isFinished())
        {
            if(alpha < 0.99)
            {
                alpha += 0.02;
                start.setColor(1,1,1,alpha);
                options.setColor(1,1,1,alpha);
                exit.setColor(1,1,1,alpha);
                info.setColor(1,1,1,alpha);
            }
            else alpha = 1;
        }

        if(moveBack)
        {
            for (Actor actor : getActors())
            {
                if(actor.getX()>-actor.getWidth()-1)actor.setX(actor.getX()-20);
            }

            if(ajto.getX() <= -ajto.getWidth()) isFinished = true;
        }

        if(isFinished)
        {
            if(drawGame) game.setScreen(new GameScreen(game));
            else if(drawInfo) game.setScreen(new InfoScreen(game));
            else if(drawOptions) game.setScreen(new OptionsScreen(game));
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

    public boolean isFinished() {
        return isFinished;
    }
}
