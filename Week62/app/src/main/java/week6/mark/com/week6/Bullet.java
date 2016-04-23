package week6.mark.com.week6;

import android.graphics.Bitmap;

import week6.mark.com.week6.Collision.AABB;

/**
 * Created by mark on 4/21/2016.
 */
public class Bullet extends Entity
{
    Entity owner;
    int damage;
    World world;
    public Bullet(AABB bb, Bitmap p, int damage, Entity owner, World w)
    {
        super(bb, p);
        this.damage = damage;
        this.owner = owner;
        world = w;
    }
    @Override
    public void Update(float deltaTime)
    {
        super.Update(deltaTime);
        if(bounds.lower.x > world.screenSize.x)
            hp--;
        else if(bounds.upper.x < 0)
            hp--;
        else if(bounds.upper.y < 0)
            hp--;
        else if(bounds.lower.y > world.screenSize.y)
            hp--;
    }

    @Override
    public void onHit(Entity other, AABB overlap)
    {
        if(other != null && other != owner && other != this && other.getClass() != Bullet.class)
        {
            other.takeDamage(owner, damage);
            hp--;
        }
    }

    @Override
    public void takeDamage(Entity other, int damage)
    {
        hp -= damage;
    }

    @Override
    public int getMaxHP() {
        return 1;
    }
}
