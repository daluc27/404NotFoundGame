package io.github.com;

import com.badlogic.gdx.math.Rectangle;


public class Block {
    float x;
    float y;
    float width;
    float height;
    Rectangle hitbox;

    public Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        hitbox = new Rectangle(x, y, width, height);
    }

    public void updatePosition() {
        hitbox.setPosition(x, y);
    }
}
