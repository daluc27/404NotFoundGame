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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer shapeRenderer;
    private Player player;
    private Block block;
    private Block ground;
    ArrayList<Block> blocks;
    //float playerWidth = 25;
    //float playerHeight = 50;

    @Override
    public void create() {
        player = new Player(0, 0, 25, 50);
        ground = new Block(0, 0, Gdx.graphics.getWidth(), 1);
        blocks = new ArrayList<>();
        shapeRenderer = new ShapeRenderer();

        readBlockLocations();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        player.isOnGround = false;

        block.updatePosition();
        player.updatePosition();

        if (player.feetHitbox.overlaps(ground.hitbox) || player.feetHitbox.overlaps(block.hitbox)) {
            player.isOnGround = true;
        }

        //Going left
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x -= 5;

            block.updatePosition();
            player.updatePosition();

            if (player.bodyHitbox.overlaps(block.hitbox)) {
                player.x += 5;
            }
        }

        //Going right
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x += 5;

            block.updatePosition();
            player.updatePosition();

            if (player.bodyHitbox.overlaps(block.hitbox)) {
                player.x -= 5;
            }
        }

        //Bending
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.height = 25;
        } else {
            player.height = 50;
        }

        //Jumping
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.isOnGround) {
            player.y = 200;
        }

        //Landing
        if (!player.isOnGround) {
            player.y -= 3;
        }


        player.x = MathUtils.clamp(player.x, 0, Gdx.graphics.getWidth() - player.width);
        player.y = MathUtils.clamp(player.y, 0, Gdx.graphics.getHeight() - player.height);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(player.x, player.y, player.width, player.height);
        shapeRenderer.setColor(Color.RED);
        for (Block b : blocks) {
            shapeRenderer.rect(b.x,b.y,b.width,b.height);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        shapeRenderer.dispose();
    }

    public void readBlockLocations() {
        Scanner reader = new Scanner(Gdx.files.internal("files/blockLocations.csv").read());

        reader.nextLine();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            String[] lines = line.split(",");

            block = new Block(Float.parseFloat(lines[0]),Float.parseFloat(lines[1]),Float.parseFloat(lines[2]),Float.parseFloat(lines[3]));
            blocks.add(block);
        }

    }
}
