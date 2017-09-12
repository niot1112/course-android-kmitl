package kmitl.lab03.chanchai58070029.simplemydot.model;


import android.graphics.Bitmap;
import android.view.View;

public class Screenshot {
    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

}
