package io.github.com;

import com.badlogic.gdx.math.Rectangle;

public class Player {
    float x;
    float y;
    float width;
    float height;

    Rectangle bodyHitbox;
    Rectangle feetHitbox;

    boolean isOnGround;

    public Player(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bodyHitbox = new Rectangle(x, (y + (height / 10)), width, (height - (height / 10)));
        feetHitbox = new Rectangle(x, (y), width, (height / 10));
    }

    public void updatePosition() {
        bodyHitbox.setPosition((int) x, (int) (y + (height / 10)));
        feetHitbox.setPosition((int) x, (int) (y));
    }
}
