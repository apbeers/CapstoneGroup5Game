package com.capstonegroup5.flappywash.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.TextArea;
import java.util.Vector;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;

/**
 * Created by Natalia Baker on 2/1/2018.
 */

public class Bird {
    private static final int GRAVITY = -11;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;

    public Bird(int x, int y){
      position = new Vector3(x, y, 0);
      velocity = new Vector3(0, 0,0 );
      texture = new Texture("birdanimation.png");
      birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
      bounds = new Rectangle(x,y, texture.getWidth() / 3, texture.getHeight());
      flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y,0);
        position.add(0, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
       velocity.y = 225;
       flap.play(0.5f);
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}