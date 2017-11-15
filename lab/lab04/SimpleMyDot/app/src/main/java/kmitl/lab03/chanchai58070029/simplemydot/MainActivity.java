package kmitl.lab03.chanchai58070029.simplemydot;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import kmitl.lab03.chanchai58070029.simplemydot.model.*;
import kmitl.lab03.chanchai58070029.simplemydot.view.DotView;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity  implements PlaceDots.onDotChangedListener  {

    private Dot dot;
    private DotView dotview;
    private PlaceDots placeDots = new PlaceDots(this);
    private Button btn;
    private ImageView imageView;
    private AlertDialog dialog;
    private Button save;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(0,0,70);
        dotview = (DotView) findViewById(R.id.dotView);
        btn = (Button) findViewById(R.id.btnShare);
        imageView = (ImageView) findViewById(R.id.imgView);
        save = (Button) findViewById(R.id.button4);
    }
    //when click save button
    public void onSave(View view){
        dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("save");
        dialog.setMessage("sure?");
        dialog.setButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkSave();
                Save.startSave(imageView);
            }
        });
        dialog.setButton2("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
//to check is it saveable
    public void checkSave(){
        File file = Save.getDisc();
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        if(!file.exists() && !file.mkdirs()){
            Toast.makeText(this, "cant create", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }
//when click share button
    public void onShare(View v){
        checkSave();
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("image/*");

        Save.startSave(imageView);
        File file = Save.getDisc();
        String name = "img.jpg";
        String file_name = file.getAbsolutePath()+"/"+name;
        File new_file = new File(file_name);
        Uri uri = Uri.fromFile(new_file);

        myIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(myIntent, "Share via"));
    }
//when touch screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int[] realview = new int[2];
            dotview.getLocationOnScreen(realview);
            if (placeDots.removesomedot(this, (int) event.getX() - realview[0], (int) event.getY() - realview[1])){
                dot = new Dot(0,0,70);
                int centerX = (int) event.getX() - realview[0];
                int centerY = (int) event.getY() - realview[1];
                this.dot.setCenterX(centerX);
                this.dot.setCenterY(centerY);
                placeDots.setSet(this.dot);
            }
        }
        return super.onTouchEvent(event);
    }
//when click randomdot
    public void onRandomDot(View view) {
        dot = new Dot(0,0,70);
        Random random = new Random();
        int centerX = random.nextInt(dotview.getWidth());
        int centerY = random.nextInt(dotview.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        placeDots.setSet(this.dot);
    }

    @Override
    public void onDotChanged(PlaceDots placeDots) {
        dotview.setDot(placeDots);
        dotview.invalidate();
    }

    public void onRemoveDot(View view) {
        placeDots.removeDot(this);
    }


}
