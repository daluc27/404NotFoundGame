package io.github.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.tools.javac.util.MatchingUtils;

import java.awt.*;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer shapeRenderer;
    private Player player;
    //float playerWidth = 25;
    //float playerHeight = 50;

    @Override
    public void create() {
        player = new Player(0, 0, 25, 50);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        //Going left
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.xLocation -= 5;
        }

        //Going right
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.xLocation += 5;
        }

        //Bending
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.height= 25;
        }else {
            player.height = 50;
        }

        //Jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.yLocation = 200;
        }

        //Landing
        if (!(player.yLocation == 0)) {
            player.yLocation -= 3;
        }

        player.xLocation = MathUtils.clamp(player.xLocation,0,Gdx.graphics.getWidth() - player.width);
        player.yLocation = MathUtils.clamp(player.yLocation,0,Gdx.graphics.getHeight() - player.height);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(player.xLocation, player.yLocation, player.width, player.height);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
