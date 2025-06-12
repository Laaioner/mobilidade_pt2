// Atualize SimplePaint.java para suportar modos de desenho
package com.example.meupaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {
    public enum Mode {
        FREE_DRAW,
        CIRCLE,
        RECTANGLE
    }

    private Mode currentMode = Mode.FREE_DRAW;

    List<Paint> mPaintList;
    List<Path> mPathList;
    private Paint currentPaint;
    private Path currentPath;
    ColorDrawable currentColor;
    private float startX, startY;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintList = new ArrayList<>();
        mPathList = new ArrayList<>();
        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        initLayerDraw();
    }

    public void initLayerDraw(){
        currentPaint = new Paint();
        currentPath = new Path();
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setColor(currentColor.getColor());
    }

    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }

    public void setMode(Mode mode) {
        this.currentMode = mode;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mPaintList.size(); i++) {
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                currentPath.reset();
                if (currentMode == Mode.FREE_DRAW) {
                    currentPath.moveTo(x, y);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentMode == Mode.FREE_DRAW) {
                    currentPath.lineTo(x, y);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (currentMode == Mode.FREE_DRAW) {
                } else if (currentMode == Mode.CIRCLE) {
                    currentPath.reset();
                    float radius = (float) Math.hypot(x - startX, y - startY);
                    currentPath.addCircle(startX, startY, radius, Path.Direction.CW);
                } else if (currentMode == Mode.RECTANGLE) {
                    currentPath.reset();
                    currentPath.addRect(startX, startY, x, y, Path.Direction.CW);
                }

                mPaintList.add(currentPaint);
                mPathList.add(currentPath);
                initLayerDraw();
                break;
        }
        invalidate();
        return true;
    }
    public void limparTela() {
        mPathList.clear();
        mPaintList.clear();
        currentPath.reset();
        invalidate();
    }
}
