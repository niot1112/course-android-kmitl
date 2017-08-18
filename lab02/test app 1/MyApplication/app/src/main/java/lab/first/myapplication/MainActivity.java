package lab.first.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout_lab02);

    }

    public void clickme(View view){
        Toast.makeText(
                getBaseContext(),
                "SUFFER",
                Toast.LENGTH_LONG)
                .show();

    }
}
