package mark.liam.bullethell;


import java.util.ArrayList;
import java.util.List;

public class Snake {
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 4;
    public static final int RIGHT = 8;
    
    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;    
    
    public Snake(int dir, int startx, int starty) {
        direction = dir;
        parts.add(new SnakePart(startx, starty));
        advance();
        parts.add(new SnakePart(startx, starty));
        advance();
        parts.add(new SnakePart(startx, starty));
    }
    
    public void turnLeft() {
        direction *= 2;
        if(direction > RIGHT)
            direction = UP;
    }
    
    public void turnRight() {
        direction /= 2;
        if(direction < UP)
            direction = RIGHT;
    }
    
    public void eat() {
        SnakePart end = parts.get(parts.size()-1); 
        parts.add(new SnakePart(end.x, end.y));
    }
    
    public void advance() {
        SnakePart head = parts.get(0);               
        
        int len = parts.size() - 1;
        for(int i = len; i > 0; i--) {
            SnakePart before = parts.get(i-1);
            SnakePart part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(direction == UP)
            head.y -= 1;
        if(direction == LEFT)
            head.x -= 1;
        if(direction == DOWN)
            head.y += 1;
        if(direction == RIGHT)
            head.x += 1;
        
        if(head.x < 0)
            head.x = 9;
        if(head.x > 9)
            head.x = 0;
        if(head.y < 0)
            head.y = 12;
        if(head.y > 12)
            head.y = 0;
    }
    
    public boolean checkBitten(Snake ai) {
        int len = parts.size();
        SnakePart head = parts.get(0);
        for(int i = 1; i < len; i++) {
            SnakePart part = parts.get(i);
            if(part.x == head.x && part.y == head.y)
                return true;
        }
        len = ai.parts.size();
        for(int i = 1; i < len; i++) {
            SnakePart part = ai.parts.get(i);
            if(part.x == head.x && part.y == head.y)
                return true;
        }
        return false;
    }      
}