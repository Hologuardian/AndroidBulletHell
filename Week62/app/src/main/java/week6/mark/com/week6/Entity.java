package week6.mark.com.week6;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import week6.mark.com.week6.Collision.AABB;
import week6.mark.com.week6.Collision.Vec2d;

/**
 * Created by mark on 4/21/2016.
 */
public abstract class Entity
{
    protected Vec2d velocity;
    protected AABB bounds;
    Bitmap image;
    protected int hp;

    public Entity(AABB bb, Bitmap p)
    {
        bounds = bb;
        image = p;
        hp = getMaxHP();
        velocity = new Vec2d(0, 0);
    }

    public void Update(float deltaTime)
    {
        bounds.Move(velocity.Times(deltaTime));
    }

    public void AddVelocity(Vec2d d)
    {
        velocity.Add(d);
    }

    public void Draw(Canvas g)
    {
        g.drawBitmap(image, (int) bounds.lower.x, (int) bounds.lower.y, null);
    }

    public boolean isDead()
    {
        return hp <= 0;
    }

    public abstract void onHit(Entity other, AABB overlap);
    public abstract void takeDamage(Entity other, int damage);
    public abstract int getMaxHP();
}
