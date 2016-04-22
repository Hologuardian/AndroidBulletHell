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
    public Bullet(AABB bb, Bitmap p, int damage, Entity owner)
    {
        super(bb, p);
        this.damage = damage;
        this.owner = owner;
    }
    @Override
    public void onHit(Entity other, AABB overlap)
    {
        if(other != null && other != owner && other != this)
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
