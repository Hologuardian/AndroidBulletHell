package week6.mark.com.week6;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import week6.mark.com.week6.Collision.AABB;
import week6.mark.com.week6.Collision.Vec2d;

/**
 * Created by mark on 4/21/2016.
 */
public class Player extends Entity
{
    Bitmap bullet;
    World world;
    public Player(AABB bb, Bitmap p, Bitmap bullet, World w)
    {
        super(bb, p);
        this.bullet = bullet;
        world = w;
    }

    @Override
    public void Update(float deltaTime) {
        super.Update(deltaTime);
        velocity.Scale(0.5f);
        if (velocity.GetLength() < 0.1f)
            velocity.Set(0, 0);
        if (bounds.lower.x < 0)
            bounds.Move(new Vec2d(bounds.lower.x, 0));
        if (bounds.upper.x > world.screenSize.x)
            bounds.Move(new Vec2d(0, world.screenSize.x - bounds.upper.x));
        if (bounds.lower.y < 0)
            bounds.Move(new Vec2d(0, bounds.lower.y));
        if (bounds.upper.y > world.screenSize.y)
            bounds.Move(new Vec2d(0, world.screenSize.y - bounds.upper.y));
    }



    public void Attack()
    {
        Bullet en = new Bullet(new AABB(this.bounds.lower.Sum(new Vec2d(0, -bullet.getHeight())), new Vec2d(bullet.getWidth(), bullet.getHeight())), bullet, 1, this);
        en.AddVelocity(new Vec2d(0, -15));
        world.SpawnEntity(en);
    }
    @Override
    public void Draw(Canvas g)
    {
        super.Draw(g);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(20);
        g.drawText(String.valueOf(hp), 50, 50, paint);
    }
    @Override
    public void onHit(Entity other, AABB overlap)
    {
        //
    }
    @Override
    public void takeDamage(Entity other, int damage)
    {
        hp -= damage;
    }
    @Override
    public int getMaxHP()
    {
        return 15;
    }
}
