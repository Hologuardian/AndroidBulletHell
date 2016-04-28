package week6.mark.com.week6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import week6.mark.com.week6.Collision.AABB;
import week6.mark.com.week6.Collision.Vec2d;

/**
 * Created by mark on 4/21/2016.
 */
public class Enemy extends Entity
{
    static final int timerMax = 20;
    float timer = 0;
    Bitmap bullet;
    World world;
    public Enemy(AABB bb, Bitmap p, Bitmap bullet, World w)
    {
        super(bb, p);
        this.bullet = bullet;
        world = w;
        Random r = new Random();
        timer = r.nextInt(timerMax);
    }

    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);
        timer += deltaTime;
        if(timer > timerMax)
        {
            Bullet en = new Bullet(new AABB(this.bounds.lower.Sum(new Vec2d(image.getWidth()/2 - bullet.getWidth()/2, -bullet.getHeight() + image.getHeight()/2)), new Vec2d(bullet.getWidth(), bullet.getHeight())), bullet, 1, this, world);
            en.AddVelocity(new Vec2d(0, 15).Add(velocity.Clone()));
            world.SpawnEntity(en);
            timer = 0;
        }
    }
    @Override
    public void onHit(Entity other, AABB overlap)
    {
    }

    @Override
    public void takeDamage(Entity other, int damage)
    {
        hp -= damage;
    }

    @Override
    public int getMaxHP()
    {
        return 3;
    }
}
