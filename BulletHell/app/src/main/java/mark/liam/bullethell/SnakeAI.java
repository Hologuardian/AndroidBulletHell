package mark.liam.bullethell;

/**
 * Created by mark on 4/7/2016.
 */
public class SnakeAI extends Snake
{
    World w;
    public SnakeAI(World world, int dir, int startx, int starty)
    {
        super(dir, startx, starty);
        w = world;
    }

    public void updateAI()
    {
        int moves = checkValidMoves();
        boolean canUp, canLeft, canDown, canRight;
        canUp = moves % 2 == 1;
        canLeft = (moves >> 1) % 2 == 1;
        canDown = (moves >> 2) % 2 == 1;
        canRight = (moves >> 3) % 2 == 1;

        boolean wantUp, wantLeft, wantDown, wantRight;
        wantUp = parts.get(0).y > w.stain.y;
        wantLeft = parts.get(0).x > w.stain.x;
        wantDown = parts.get(0).y < w.stain.y;
        wantRight = parts.get(0).x < w.stain.x;

        if(wantUp && canUp)
            direction = UP;
        else if(wantLeft && canLeft)
            direction = LEFT;
        else if(wantDown && canDown)
            direction = DOWN;
        else if(wantRight && canRight)
            direction = RIGHT;


    }

    public int checkValidMoves()
    {
        int ret = 0;

        if(checkBite(parts.get(0).x, parts.get(0).y - 1))
            ret += UP;
        if(checkBite(parts.get(0).x, parts.get(0).y + 1))
            ret += DOWN;
        if(checkBite(parts.get(0).x - 1, parts.get(0).y))
            ret += LEFT;
        if(checkBite(parts.get(0).x + 1, parts.get(0).y))
            ret += RIGHT;

        return ret;
    }

    public boolean checkBite(int x, int y)
    {
        int len = parts.size();
        for(int i = 0; i < len; i++) {
            SnakePart part = parts.get(i);
            if(part.x == x && part.y == y)
                return false;
        }
        len = w.snake.parts.size();
        for(int i = 0; i < len; i++) {
            SnakePart part = w.snake.parts.get(i);
            if(part.x == x && part.y == y)
                return false;
        }
        if(w.stain.x == x && w.stain.y == x)
        {
            return false;
        }

        return true;
    }
}
