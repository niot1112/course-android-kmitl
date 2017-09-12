package kmitl.lab03.chanchai58070029.simplemydot.model;

import java.util.ArrayList;

public class Dot extends ArrayList{

    private int centerX;
    private int centerY;
    private int radius;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public Dot(int centerX, int centerY, int radius, OnDotChangedListener listener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public interface OnDotChangedListener{
        void onDotChanged(Dot dot);
    }

    private OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }
}
