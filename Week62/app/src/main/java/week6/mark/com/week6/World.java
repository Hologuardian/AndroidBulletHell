package week6.mark.com.week6;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

import week6.mark.com.week6.Collision.AABB;
import week6.mark.com.week6.Collision.Vec2d;

public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;

    public boolean gameOver = false;;
    public int score = 0;
    Random random = new Random();

    float timer = 0.0f;
    float timeAlive = 0.0f;

    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> dead = new ArrayList<Entity>();
    public ArrayList<Entity> spawn = new ArrayList<Entity>();

    public Player player;

    Resources res;
    Vec2d screenSize;
    Bitmap bullet;
    Bitmap pbullet;

    public World(Resources res, Vec2d screenSize)
    {
        bullet = BitmapFactory.decodeResource(res, R.drawable.enemybullet);
        pbullet = BitmapFactory.decodeResource(res, R.drawable.middleshot);
        this.res = res;
        this.screenSize = screenSize;
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.player);
        player = new Player(new AABB(new Vec2d(screenSize.x / 2 - bmp.getWidth(), screenSize.y - 100 - bmp.getHeight()), new Vec2d(bmp.getWidth(), bmp.getHeight())), bmp, pbullet, this);
        entities.add(player);
    }

    public void update(float deltaTime)
    {
        timeAlive += deltaTime;
        timer += deltaTime;
        if(timer > 100.0f)
        {
            Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.enemy1);
            Enemy en = new Enemy(new AABB(new Vec2d(random.nextInt((int)screenSize.x - bmp.getWidth()), random.nextInt(100)), new Vec2d(bmp.getWidth(), bmp.getHeight())), bmp, bullet, this);
            en.AddVelocity(new Vec2d((random.nextFloat() - 0.5f) / 5.0f, 2));
            spawn.add(en);
            timer = 0;
        }
        for (Entity e : spawn)
        {
            entities.add(e);
        }
        spawn.clear();
        int i = 1;
        for (Entity e : entities)
        {
            e.Update(deltaTime);
            for(int j = i; j < entities.size(); j++)
            {
                AABB coll = e.bounds.CheckCollision(entities.get(j).bounds);
                if(coll != null)
                {
                    e.onHit(entities.get(j), coll);
                    e.onHit(e, coll);
                }
            }
            if(e.isDead())
                dead.add(e);
        }
        for (Entity e : dead)
        {
            entities.remove(e);
        }
        dead.clear();
    }

    public void updateDraw(Canvas g)
    {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(20);
        g.drawText(String.valueOf(timeAlive), screenSize.x - 50 - String.valueOf(timeAlive).length() * 10, 50, paint);
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
