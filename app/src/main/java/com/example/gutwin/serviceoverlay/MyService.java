package com.example.gutwin.serviceoverlay;

/**
 * Created by gutwin on 1/25/2015.
 *
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

public class MyService extends Service {
    PopupView mView;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getBaseContext(),"onCreate", Toast.LENGTH_SHORT).show();
        mView = new PopupView(this);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                //WindowManager.LayoutParams.TYPE_PHONE,
                0,
//              WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                      | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        params.setTitle("Test Popup");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(),"onDestroy", Toast.LENGTH_LONG).show();
        if(mView != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView = null;
        }
    }
}

class PopupView extends ViewGroup {
    private Paint mLoadPaint;
    private float touchX = 200;
    private float touchY = 200;

    public PopupView(Context context) {
        super(context);
        Toast.makeText(getContext(),"PopupView", Toast.LENGTH_LONG).show();

        mLoadPaint = new Paint();
        mLoadPaint.setAntiAlias(true);
        mLoadPaint.setTextSize(48);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Toast.makeText(getContext(),"onDraw: " + touchX + "," + touchY, Toast.LENGTH_SHORT).show();
        mLoadPaint.setARGB(255, 0, 0, 150);
        mLoadPaint.setStyle(Style.FILL);
        canvas.drawRect(100, 100, 800, 1000, mLoadPaint);
        mLoadPaint.setARGB(255, 255, 255, 0);
        canvas.drawText("This is a window", 250, 500, mLoadPaint);
        canvas.drawText("started by a Service.", 250, 560, mLoadPaint);
        canvas.drawText("The circle follows", 250, 620, mLoadPaint);
        canvas.drawText("the user's touch point.", 250, 680, mLoadPaint);
        canvas.drawCircle(touchX, touchY, 100, mLoadPaint);
        //Toast.makeText(getContext(),"onDraw: " + touchX + "," + touchY, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        touchX = event.getX();
        touchY = event.getY();
        this.invalidate();
        Toast.makeText(getContext(),"onTouchEvent: " + touchX + "," + touchY, Toast.LENGTH_LONG).show();
        return true;
    }
}

