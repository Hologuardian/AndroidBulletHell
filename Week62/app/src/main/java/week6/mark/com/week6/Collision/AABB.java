package week6.mark.com.week6.Collision;

import android.graphics.drawable.VectorDrawable;

/**
 * Created by mark on 4/20/2016.
 */
public class AABB
{
    public enum BoxType
    {
        Collidable,
        Kinamatic,
        Overlap;
    }
    BoxType type;
    public Vec2d lower;
    public Vec2d upper;
    public Vec2d size;

    public AABB(Vec2d p, Vec2d s)
    {
        lower = p;
        upper = p.Sum(s);
        size = s;
    }

    public AABB(Vec2d p, Vec2d s, BoxType t)
    {
        lower = p;
        upper = p.Sum(s);
        size = s;
        type = t;
    }

    public void Move(Vec2d dist)
    {
        lower.Add(dist);
        upper.Add(dist);
    }

    public AABB CheckCollision(AABB other)
    {
        if(lower.LessEqual(other.upper) && upper.GreaterEqual(other.lower))
        {
            Vec2d l = lower.Max(other.lower);
            Vec2d u =  upper.Min(other.upper);
            return new AABB(l,u.Diff(l));
        }
        if(upper.LessEqual(other.lower) && lower.GreaterEqual(other.upper))
        {
            Vec2d l = lower.Min(other.lower);
            Vec2d u =  upper.Max(other.upper);
            return new AABB(l,u.Diff(l), BoxType.Overlap);
        }
        return null;
    }
}
