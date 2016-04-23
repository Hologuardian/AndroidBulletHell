package week6.mark.com.week6.Collision;

/**
 * Created by mark on 4/20/2016.
 */
public class Vec2d
{
    public float x, y;
    public Vec2d(float xCoord, float yCoord)
    {
        x = xCoord;
        y = yCoord;
    }
    public Vec2d Set(float x, float y)
    {
        this.x = x;
        this.y = y;
        return this;
    }
    public Vec2d Set(Vec2d n)
    {
        x = n.x;
        y = n.y;
        return this;
    }
    public float GetLength(){return (float)Math.sqrt(x * x + y * y);}
    //Returns new vector that is the difference of this object and another.
    public Vec2d Sum(Vec2d other){return new Vec2d(x + other.x, y + other.y);}
    //Returns new vector that is the difference of this object and another.
    public Vec2d Diff(Vec2d other){return new Vec2d(x - other.x, y - other.y);}

    //Returns instance of this class for easy chaining
    //This method DOES edit the vector
    public Vec2d Times(float scale) {return new Vec2d(x * scale, y * scale);}

    //Returns instance of this class for easy chaining
    //This method DOES edit the vector
    public Vec2d Scale(float scale)
    {
        x *= scale;
        y *= scale;
        return this;
    }
    //Returns instance of this class for easy chaining
    //This method DOES edit the vector
    public Vec2d Add(Vec2d other)
    {
        x += other.x;
        y += other.y;
        return this;
    }
    //Returns instance of this class for easy chaining
    //This method DOES edit the vector
    public Vec2d Sub(Vec2d other)
    {
        x -= other.x;
        y -= other.y;
        return this;
    }

    public boolean Greater(Vec2d other)
    {
        return x > other.x && y > other.y;
    }

    public boolean GreaterEqual(Vec2d other)
    {
        return x >= other.x && y >= other.y;
    }

    public boolean Less(Vec2d other)
    {
        return x < other.x && y < other.y;
    }

    public boolean LessEqual(Vec2d other)
    {
        return x <= other.x && y <= other.y;
    }

    public Vec2d Max(Vec2d other)
    {
        if(GreaterEqual(other))
            return this;
        return other;
    }

    public Vec2d Min(Vec2d other)
    {
        if(LessEqual(other))
            return this;
        return other;
    }

    public Vec2d Clone()
    {
        return new Vec2d(x, y);
    }
}
