package com.seesmile.demos.ecalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.seesmile.demos.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 说明：日历组件
 * Created by FuPei
 * on 2015/12/23 at 19:09
 */
public class ECalendarView extends LinearLayout {

    /**
     * 背景颜色
     */
    private final int COLOR_BG = Color.parseColor("#F49B16");
    /**
     * 星期背景颜色
     */
    public static final int COLOR_WEEK = Color.parseColor("#FFDA59");
    /**
     * 日期线条颜色
     */
    public final int COLOR_DATE = Color.parseColor("#C3C3C3");
    /**
     * 最外面的内边距
     */
    private final int PADDING_MAIN = 20;
    /**
     * 星期数显示
     */
    private final String[] WEEKS = {"日", "一", "二", "三", "四", "五", "六"};
    /**
     * 数据列表
     */
    private ArrayList<ECalendarItem> data;
    /**
     * 时间显示组件
     */
    private GridView gv_CalendarView;
    /**
     * 星期显示组件
     */
    private GridView gv_WeekView;
    /**
     * 日期适配器
     */
    private ECalendarAdapter mAdapter;
    /**
     * 上下文对象
     */
    private Context mContext;
    /**
     * 当前月份
     */
    private int currentMonth;
    /**
     * 当前日期
     */
    private Calendar mCalendar;
    /**
     * 月份显示
     */
    private TextView tv_month;
    /**
     * 上个月在日历显示的天数
     */
    private int lastMonthDay;
    /**
     * 下个月在日历显示的天数
     */
    private int nextMonthDay;
    /**
     * 今天是否签到
     */
    private boolean isSign;
    /** 提醒签到 */
    private CheckBox cb_sign;

    public ECalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public ECalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ECalendarView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        isSign = false;
        mCalendar=Calendar.getInstance();
        currentMonth = mCalendar.get(Calendar.MONTH) + 1;
        setBackgroundColor(COLOR_BG);
        setPadding(PADDING_MAIN, PADDING_MAIN, PADDING_MAIN, PADDING_MAIN);
        setOrientation(VERTICAL);
        initMonthView();
        initWeekView();
        initCalendarData();
    }

    /**
     * 是否签到
     *
     * @return
     */
    public boolean isSign() {
        return isSign;
    }

    /**
     * 签到成功调用此方法
     */
    public void setSignSuccess() {
        this.isSign = true;
    }

    /**
     * 初始化月份显示控件
     */
    private void initMonthView() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.item_calendar_top, null);
        linearLayout.setPadding(0, 0, 0, PADDING_MAIN);
        tv_month = (TextView) linearLayout.findViewById(R.id.top_tv_date);
        cb_sign = (CheckBox) linearLayout.findViewById(R.id.top_cb_sign);
        addView(linearLayout);
    }

    /**
     * 是否提醒签到
     * @return
     */
    public boolean getIsNotfiSign() {
        if(cb_sign != null) {
            return cb_sign.isChecked();
        }
        return false;
    }

    /**
     * 设置是否提醒签到
     * @param noti
     */
    public void setNotfiSign(boolean noti) {
        if(cb_sign != null) {
            cb_sign.setChecked(noti);
        }
    }

    /**
     * 初始化月份数据
     */
    private void initMonthViewData(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        tv_month.setText(str);
    }

    /**
     * 设置当前日期
     *
     * @param date 当前日期
     */
    public void setCalendarData(Date date) {
        mCalendar.setTime(date);
        currentMonth = mCalendar.get(Calendar.MONTH) + 1;
        initMonthViewData(date);
        setNewCalendarData();
    }

    /**
     * 获取当前月份的天数
     *
     * @param calendar 当前日期
     * @return 天数
     */
    private int getSumDayInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 设置当前显示时间戳界面
     * @param time
     */
    public void setcurrTime(long time) {
        mCalendar.setTime(new Date(time));
        setNewCalendarData();
    }

    /**
     * 填充日期数据
     */
    public void initCalendarData() {
        gv_CalendarView = new GridView(mContext){
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, expandSpec);
            }
        };
        gv_CalendarView.setNumColumns(7);
        gv_CalendarView.setEnabled(false);
        gv_CalendarView.setHorizontalSpacing(2);
        gv_CalendarView.setVerticalSpacing(2);
        gv_CalendarView.setPadding(-2, -2, -2, -2);
        gv_CalendarView.setBackgroundColor(COLOR_DATE);
        gv_CalendarView.setGravity(Gravity.CENTER_HORIZONTAL);
        data = new ArrayList<>();
        mAdapter = new ECalendarAdapter(mContext, data);
        gv_CalendarView.setAdapter(mAdapter);
        setNewCalendarData();
        addView(gv_CalendarView, new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.WRAP_CONTENT));
    }

    private void setNewCalendarData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tv_month.setText(format.format(mCalendar.getTime()));
        data.clear();
        lastMonthDay = getFirstWeekInMonth();
        ECalendarItem item;
        //添加上月日期空数据
        for (int i = 0; i < lastMonthDay; i++) {
            item = new ECalendarItem(null);
            data.add(item);
        }
        for (int i = 0; i < getSumDayInMonth(mCalendar); i++) {
            item = new ECalendarItem((i + 1) + "");
            data.add(item);
        }
        //下个月的日期空数据
        nextMonthDay = 7 - data.size() % 7;
        for (int i = 0; i < nextMonthDay; i++) {
            item = new ECalendarItem(null);
            data.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 获取当月第一天的星期
     * @return 星期
     */
    private int getFirstWeekInMonth() {
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, mCalendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return firstDay;
    }

    /**
     * 初始化week数据
     */
    private void initWeekView() {
        gv_WeekView = new GridView(mContext);
        gv_WeekView.setGravity(Gravity.CENTER_HORIZONTAL);
        gv_WeekView.setEnabled(false);
        gv_WeekView.setNumColumns(7);
        gv_WeekView.setBackgroundColor(COLOR_WEEK);
        gv_WeekView.setPadding(0, -PADDING_MAIN / 2, 0, -PADDING_MAIN / 2);
        ArrayList<ECalendarItem> items = new ArrayList<>();
        ECalendarItem item;
        for (String value : WEEKS) {
            item = new ECalendarItem(value);
            items.add(item);
        }
        gv_WeekView.setAdapter(new ECalendarAdapter(mContext, items));
        addView(gv_WeekView, new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,
                GridView.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 签到
     * 如果设置的日期不是当月的日期，则不成功
     *
     * @param date 对指定日期
     */
    public void signAtday(Date date) {
        mCalendar.setTime(date);
        int month = mCalendar.get(Calendar.MONTH);
        if (month != (currentMonth - 1)) {
            setCalendarData(date);
        }
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        signAtDay(day);
    }

    /**
     * 根据位置进行签到
     * @param index 日期在月份的第几天
     */
    public void signAtDay(int index) {
        data.get(lastMonthDay + index - 1).setIsSignIn(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.UNSPECIFIED);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}






