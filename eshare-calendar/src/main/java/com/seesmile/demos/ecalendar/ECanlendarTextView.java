package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.seesmile.demos.R;

/**
 * 说明：日历单个控件
 * Created by FuPei
 * on 2015/12/23 at 22:40
 */
public class ECanlendarTextView extends TextView {

    private boolean isSignIn;
    private Paint mPaint;

    public ECanlendarTextView(Context context) {
        super(context);
        initData();
    }

    public ECanlendarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public ECanlendarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        isSignIn = false;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setGravity(Gravity.CENTER);

    }

    /**
     * 设置背景圆圈（默然颜色）
     */
    public void setSignIn() {
        isSignIn = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //如果当前日期签到，则绘制背景圆形
        if (isSignIn) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.calendar_data_cb);
            canvas.drawBitmap(bitmap, getWidth() - bitmap.getWidth() - 6,
                    getHeight() - bitmap.getHeight() - 6, new Paint());
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽高取一致
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
