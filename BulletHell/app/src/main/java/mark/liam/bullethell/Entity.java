package mark.liam.bullethell;

import badlogic.androidgames.framework.Graphics;
import badlogic.androidgames.framework.Pixmap;
import mark.liam.bullethell.Collision.AABB;
import mark.liam.bullethell.Collision.Vec2d;

/**
 * Created by mark on 4/21/2016.
 */
public abstract class Entity
{
    Vec2d velocity;
    AABB bounds;
    Pixmap image;
    int hp;

    public Entity(AABB bb, Pixmap p)
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

    public void Draw(Graphics g)
    {
        g.drawPixmap(image, (int) bounds.lower.x, (int) bounds.lower.y);
    }

    public boolean isDead()
    {
        return hp <= 0;
    }

    public abstract void onHit(Entity other, AABB overlap);
    public abstract void takeDamage(Entity other, int damage);
    public abstract int getMaxHP();
}
