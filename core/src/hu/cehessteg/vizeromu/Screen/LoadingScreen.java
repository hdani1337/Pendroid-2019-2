package hu.cehessteg.vizeromu.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hu.cehessteg.vizeromu.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class LoadingScreen extends MyScreen {
    public LoadingScreen(MyGame game) {
        super(game);
    }
    Texture csany = new Texture("logos/csany.png");
    Texture logo = new Texture("logos/csapatlogo.png");
    Texture pendroid = new Texture("logos/pendroid.png");

    OneSpriteStaticActor csanyActor = new OneSpriteStaticActor(csany);
    OneSpriteStaticActor logoActor = new OneSpriteStaticActor(logo);
    OneSpriteStaticActor pendroidActor = new OneSpriteStaticActor(pendroid);

    @Override
    public void show() {
        Assets.manager.finishLoading();
        Assets.load();
    }

    float alpha0 = 0;
    boolean alpha0nov = true;
    float alpha1 = 0;
    boolean alpha1nov = true;
    float alpha2 = 0;
    boolean alpha2nov = true;
    float pElapsedTime = 0;

    @Override
    public void render(float delta) {
        super.render(delta);
        logoActor.setPosition(Gdx.graphics.getWidth()/2-logoActor.getWidth()/2,Gdx.graphics.getHeight()/2-logoActor.getHeight()/2);
        csanyActor.setPosition(Gdx.graphics.getWidth()/2-csanyActor.getWidth()/2,Gdx.graphics.getHeight()/2-csanyActor.getHeight()/2);
        pendroidActor.setPosition(Gdx.graphics.getWidth()/2-pendroidActor.getWidth()/2,Gdx.graphics.getHeight()/2-pendroidActor.getHeight()/2);

        game.getSpriteBatch().begin();

        logoActor.sprite.setAlpha(0);
        csanyActor.sprite.setAlpha(0);
        pendroidActor.sprite.setAlpha(0);

        pElapsedTime += Gdx.graphics.getDeltaTime();

        if(pElapsedTime < 2) {
            logoActor.sprite.setAlpha(alpha0);
            if(alpha0nov && alpha0 < 0.99) alpha0 += 0.02;
            else if(!alpha0nov && alpha0 >= 0.04) alpha0 -= 0.04;
            if(!alpha0nov && alpha0 < 0.04) alpha0 = 0;
            if(pElapsedTime > 1.5) alpha0nov = false;
        }

        if(pElapsedTime >= 2) {
            csanyActor.sprite.setAlpha(alpha1);
            if(alpha1nov && alpha1 < 0.99) alpha1 += 0.02;
            else if(!alpha1nov && alpha1 > 0.04) alpha1 -= 0.04;
            if(!alpha1nov && alpha1 < 0.04) alpha1 = 0;
            if(pElapsedTime > 3.5) alpha1nov = false;
        }

        if(pElapsedTime >= 4) {
            pendroidActor.sprite.setAlpha(alpha2);
            if(alpha2nov && alpha2 < 0.99) alpha2 += 0.02;
            else if(!alpha2nov && alpha2 > 0.04) alpha2 -= 0.04;
            if(!alpha2nov && alpha2 < 0.04) alpha2 = 0;
            if(pElapsedTime > 5.5) alpha2nov = false;
        }

        if(pElapsedTime > 6 && Assets.manager.update()) game.setScreen(new MenuScreen(game));

        logoActor.draw(game.getSpriteBatch(),1);
        csanyActor.draw(game.getSpriteBatch(),1);
        pendroidActor.draw(game.getSpriteBatch(),1);

        game.getSpriteBatch().end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void init() {
        setBackGroundColor(0f, 0f, 0f);
    }
}
