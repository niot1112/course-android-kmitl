package kmitl.lab03.chanchai58070029.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import kmitl.lab03.chanchai58070029.simplemydot.model.*;
import kmitl.lab03.chanchai58070029.simplemydot.view.DotView;

import java.util.Random;

public class MainActivity extends AppCompatActivity  implements SetDot.onDotChangedListener {

    private Dot dot;
    private DotView dotview;
    private SetDot setDot = new SetDot(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot = new Dot(0,0,70);

        dotview = (DotView) findViewById(R.id.dotView);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int[] realview = new int[2];
            dotview.getLocationOnScreen(realview);
            if (setDot.removesomedot(this, (int) event.getX() - realview[0], (int) event.getY() - realview[1])){
                dot = new Dot(0,0,70);
                int centerX = (int) event.getX() - realview[0];
                int centerY = (int) event.getY() - realview[1];
                this.dot.setCenterX(centerX);
                this.dot.setCenterY(centerY);
                setDot.setSet(this.dot);
            }
        }
        return super.onTouchEvent(event);
    }

    public void onRandomDot(View view) {
        dot = new Dot(0,0,70);
        Random random = new Random();
        int centerX = random.nextInt(dotview.getWidth());
        int centerY = random.nextInt(dotview.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        setDot.setSet(this.dot);


    }

    @Override
    public void onDotChanged(SetDot setDot) {

        dotview.setDot(setDot);
        dotview.invalidate();


    }

    public void onRemoveDot(View view) {
        setDot.removeDot(this);
    }
}
