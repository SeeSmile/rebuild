package com.a360ads.eshare.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.a360ads.eshare.utils.Elog;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/19 at 16:25
 */
public class GuideProgressView extends View {

    //各种属性默认值
    private final int DEFAULT_COLOR_CHOOSE = Color.parseColor("#1E7E3A");;
    private final int DEFAULT_COLOR_NORMAL = Color.WHITE;
    private final int DEFAULT_SIZE_CHOOSE = 15;
    private final int DEFAULT_SIZE_NORMAL = 10;
    private final int DEFAULT_WIDTH_CRICLE = 50;

    /**
     * 当前页面圆点颜色
     */
    private int color_choose;
    /**
     * 正常圆点颜色
     */
    private int color_normal;
    /**
     * 圆点圆心之间的间隔
     */
    private int width_cricle;
    /**
     * 当前页面圆点的半径
     */
    private int size_cricle_normal;
    /**
     * 正在页面圆点的半径
     */
    private int size_cricle_choose;
    /**
     * 总个数
     */
    private int mCount;
    /**
     * 当前页面下标
     */
    private int currentIndex;
    /**
     * 绘制工具
     */
    private Paint mPaint;

    public GuideProgressView(Context context) {
        this(context, null);
    }

    public GuideProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        //初始化UI
        color_choose = DEFAULT_COLOR_CHOOSE;
        color_normal = DEFAULT_COLOR_NORMAL;
        width_cricle = DEFAULT_WIDTH_CRICLE;
        size_cricle_normal = DEFAULT_SIZE_NORMAL;
        size_cricle_choose = DEFAULT_SIZE_CHOOSE;
        mCount = 3;
        //设置画笔属性
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color_normal);
    }

    public void setAllPage(int count) {
        this.mCount = count;
        invalidate();
    }

    public void setCurrentIndex(int index) {
        if (index >= 0 && index < mCount) {
            currentIndex = index;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCount > 0) {
            int length = size_cricle_choose;
            Elog.i("onDraw():\n" + "mCount = " + mCount);
            for (int i = 0; i < mCount; i++) {
                if (i == currentIndex) {
                    mPaint.setColor(color_choose);
                    canvas.drawCircle(length, size_cricle_choose, size_cricle_choose, mPaint);
                } else {
                    mPaint.setColor(color_normal);
                    canvas.drawCircle(length, size_cricle_choose, size_cricle_normal, mPaint);
                }
                length += width_cricle;
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //计算控件总宽度
        int width = (mCount - 1) * width_cricle + size_cricle_choose * 2;
        int height = 2 * size_cricle_choose;
        setMeasuredDimension(width, height);
    }
}
