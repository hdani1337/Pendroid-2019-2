package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.cehessteg.vizeromu.ParentClasses.Game.MyGame;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.MyScreen;
import hu.cehessteg.vizeromu.ParentClasses.Scene2D.OneSpriteStaticActor;

public class LoadingScreen extends MyScreen {
    public LoadingScreen(MyGame game) {
        super(game);
    }
    BitmapFont bitmapFont = new BitmapFont();
    Texture viz = new Texture("colors/waterBlue.png");
    Texture bg = new Texture("colors/hatter.png");
    OneSpriteStaticActor vizActor = new OneSpriteStaticActor(viz);
    {
        vizActor.setPosition(Gdx.graphics.getWidth()/2-vizActor.getWidth()/2,Gdx.graphics.getHeight()/2 - vizActor.getHeight()*1.3f);
    }
    OneSpriteStaticActor bgActor = new OneSpriteStaticActor(bg);
    {
        bgActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        Assets.manager.finishLoading();
        Assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        bgActor.draw(spriteBatch,1f);
        vizActor.draw(spriteBatch,1f);
        vizActor.setPosition(0,0);
        vizActor.setWidth(Gdx.graphics.getWidth());
        vizActor.setHeight(Gdx.graphics.getHeight()*Assets.manager.getProgress() + 30);
        bitmapFont.draw(spriteBatch,"Betöltés: " + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", Gdx.graphics.getWidth()/2-40,Gdx.graphics.getHeight()/2+15);
        spriteBatch.end();
        if (Assets.manager.update()) {
            Assets.afterLoaded();
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void init() {
        setBackGroundColor(0f, 0f, 0f);
    }
}
