package kmitl.lab03.chanchai58070029.simplemydot.fragment;


import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

import kmitl.lab03.chanchai58070029.simplemydot.R;
import kmitl.lab03.chanchai58070029.simplemydot.model.Dot;
import kmitl.lab03.chanchai58070029.simplemydot.model.PlaceDots;
import kmitl.lab03.chanchai58070029.simplemydot.model.Save;
import kmitl.lab03.chanchai58070029.simplemydot.view.DotView;

public class DotViewFragment extends Fragment implements PlaceDots.onDotChangedListener, View.OnClickListener {
    private Dot dot;
    private DotView dotview;
    private PlaceDots placeDots = new PlaceDots(this);
    private ImageView imageView;
    private Button btnsave;
    private Button btnrandom;
    private Button btnshare;
    private Button btnclear;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);
        dot = new Dot(0, 0, 70, Color.RED);
        dotview = rootView.findViewById(R.id.dotView);
        btnshare = rootView.findViewById(R.id.btnShare);
        imageView = rootView.findViewById(R.id.imgView);
        btnsave = rootView.findViewById(R.id.Save);
        btnrandom = rootView.findViewById(R.id.RandomDot);
        btnclear = rootView.findViewById(R.id.RemoveDot);

        btnshare.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnrandom.setOnClickListener(this);
        btnclear.setOnClickListener(this);
        Touchlistener(rootView);

        return rootView;
    }

    private void Touchlistener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                onTouchEvent(event);
                return true;
            }
        });

    }

    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        //hold
        public void onLongPress(MotionEvent e) {
//            createDotTouch(e);
            if (placeDots.checkdot((int) e.getX(), (int) e.getY())) {
                Bundle args = new Bundle();
                int numx = (int) e.getX();
                float numy = e.getY();
                args.putString("numx", String.valueOf(numx));
                args.putString("numy", String.valueOf(numy));
                args.putParcelable("dot",dot);
                EditDotFragment nextFrag = new EditDotFragment();
                nextFrag.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        }

        //tap
        public boolean onSingleTapUp(MotionEvent e) {
            if (placeDots.removesomedot((int) e.getX(), (int) e.getY())) {
                createDotTouch(e);
            }
            return true;
        }

        //swipe
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (placeDots.removesomedot((int) e1.getX(), (int) e1.getY())) {
                createDotTouch(e1);
            }
            return true;
        }
    });

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void createDotTouch(MotionEvent e) {
        dot = new Dot(0, 0, 70, Color.RED);
        Random random = new Random();
        int centerX = (int) e.getX();
        int centerY = (int) e.getY();
        int rad = random.nextInt(70) + 20;
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        dot.setColor(color);
        dot.setRadius(rad);
        dot.setCenterX(centerX);
        dot.setCenterY(centerY);
        placeDots.setSet(dot);
    }

    public void onRandomDot(View view) {
        dot = new Dot(0, 0, 70, Color.RED);
        Random random = new Random();
        int centerX = random.nextInt(dotview.getWidth());
        int centerY = random.nextInt(dotview.getHeight());
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int rad = random.nextInt(70) + 20;
        this.dot.setColor(color);
        this.dot.setRadius(rad);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        placeDots.setSet(this.dot);
    }

    public void onSave(View view) {
        checkSave();
        Save.startSave(imageView);
    }

    public void checkSave() {
        File file = Save.getDisc();
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        if (!file.exists() && !file.mkdirs()) {
            Toast.makeText(getActivity(), "cant create", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
    }

    public void onShare(View v) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("image/*");

        Save.startSave(imageView);
        File file = Save.getDisc();
        String name = "img.jpg";
        String file_name = file.getAbsolutePath() + "/" + name;
        File new_file = new File(file_name);
        Uri uri = Uri.fromFile(new_file);

        myIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(myIntent, "Share via"));
    }

    public void onRemoveDot(View view) {
        placeDots.removeDot(this);
    }

    @Override
    public void onDotChanged(PlaceDots placeDots) {
        dotview.setDot(placeDots);
        dotview.invalidate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RandomDot:
                onRandomDot(view);
                break;
            case R.id.RemoveDot:
                onRemoveDot(view);
                break;
            case R.id.Save:
                onSave(view);
                break;
            case R.id.btnShare:
                onShare(view);
                break;
        }
    }

}

