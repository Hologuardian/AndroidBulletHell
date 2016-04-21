package mark.liam.bullethell;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import badlogic.androidgames.framework.Graphics;

public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;

    public boolean gameOver = false;;
    public int score = 0;
    Random random = new Random();

    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> dead = new ArrayList<Entity>();
    public ArrayList<Entity> spawn = new ArrayList<Entity>();

    public World()
    {
    }

    public void update(float deltaTime)
    {
        for (Entity e : spawn)
        {
            entities.add(e);
        }
        spawn.clear();
        for (Entity e : entities)
        {
            e.Update(deltaTime);
            if(e.isDead())
                dead.add(e);
        }
        for (Entity e : dead)
        {
            entities.remove(e);
        }
        dead.clear();
    }

    public void updateDraw(Graphics g)
    {
        for(Entity e : entities)
        {
            e.Draw(g);
        }
    }

    public void SpawnEntity(Entity e)
    {
        spawn.add(e);
    }

    public void KillEntity(Entity e)
    {
        dead.add(e);
    }
}
