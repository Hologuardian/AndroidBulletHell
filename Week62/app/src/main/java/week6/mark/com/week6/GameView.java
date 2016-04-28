package week6.mark.com.week6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import week6.mark.com.week6.Collision.Vec2d;

public class GameView extends SurfaceView implements SensorEventListener
{
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    World world;
    private SensorManager mSensorManager;
    private Sensor sense;

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        mSensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        sense = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, sense,
                SensorManager.SENSOR_DELAY_GAME);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                world = new World(getResources(), new Vec2d(holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height()));
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }

        });
    }@Override
     public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                world.player.Attack();
                break;
        }

        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        switch (e.sensor.getType()) {
            case Sensor.TYPE_GYROSCOPE:
                world.player.AddVelocity(new Vec2d(e.values[0], e.values[1]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        float[] out = new float[]{0, 0, 0};

        world.player.AddVelocity(new Vec2d(out[0], out[2]));
        world.update(1);
        world.updateDraw(canvas);

    }
}


