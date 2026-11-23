package io.github.com;

import com.badlogic.gdx.math.Rectangle;

public class Player {
    float x;
    float y;
    float width;
    float height;
    float velocityY;

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

    public void updatePosition(float delta) {
        if (!isOnGround) {
            velocityY -= 1200 * delta;
        } else {
            velocityY = 0;
        }

        y += velocityY * delta;

        bodyHitbox.setPosition((int) x, (int) (y + (height / 10)));
        feetHitbox.setPosition((int) x, (int) (y));
    }
}
