package kmitl.lab03.chanchai58070029.simplemydot.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import kmitl.lab03.chanchai58070029.simplemydot.R;
import kmitl.lab03.chanchai58070029.simplemydot.model.Dot;
import kmitl.lab03.chanchai58070029.simplemydot.model.PlaceDots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {
    private EditText placex;
    private EditText placey;
    private View rootView;
    private Dot dot;
    private Button btnDone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        dot = new Dot(0, 0, 70, Color.RED);
        btnDone = rootView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);
        rootView.setBackgroundColor(Color.rgb(169,169,169));
        Bundle bundle = this.getArguments();
        String numberX = bundle.getString("numx", "");
        String numberY = bundle.getString("numy", "");
        placex = rootView.findViewById(R.id.numX);
        placey = rootView.findViewById(R.id.numY);

        placex.setText(numberX);
        placey.setText(numberY);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
    }

    private Dot onDone() {
        EditText placex = rootView.findViewById(R.id.numX);
        EditText placey = rootView.findViewById(R.id.numY);
        dot = new Dot(0, 0, 70, Color.RED);
        Random random = new Random();
        int centerX = Integer.parseInt(placex.getText().toString());
        int centerY = Integer.parseInt(placey.getText().toString());
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int rad = random.nextInt(70) + 20;
        this.dot.setColor(color);
        this.dot.setRadius(rad);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        return dot;
    }
}
