package bw.com.lx_view_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View{

    private Paint paint;//画笔
    private int circleX;//圆的x轴起始坐标
    private int circleY;//圆的y轴起始坐标
    private int raduis = 100;//半径
    public MyView(Context context) {
        super(context);
        init();
    }



    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleX,circleY,60,paint);
    }

    /**
     * 设置触发事件效果
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                circleX = (int) event.getX();//获取距离父控件的x轴坐标
                circleX = (int) event.getRawX();//获取距离屏幕边缘的x轴坐标
                circleY = (int) event.getY();
                invalidate();//重绘
                break;
            case MotionEvent.ACTION_MOVE :
                circleX = (int) event.getX();
                circleY = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP ://抬起事件
                break;
        }
        return true;//返回true,代表所有事件都可以触发,fales只触发down事件

    }
}
