package mark.liam.bullethell;

import badlogic.androidgames.framework.Pixmap;
import mark.liam.bullethell.Collision.AABB;

/**
 * Created by mark on 4/21/2016.
 */
public class Player extends Entity
{
    public Player(AABB bb, Pixmap p)
    {
        super(bb, p);
    }

    public void Attack()
    {

    }

    public void onHit(Entity other, AABB overlap)
    {

    }
    public void takeDamage(Entity other, int damage)
    {

    }

    public int getMaxHP()
    {
        return 15;
    }
}
