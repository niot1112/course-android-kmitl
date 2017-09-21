package kmitl.lab03.chanchai58070029.simplemydot;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import kmitl.lab03.chanchai58070029.simplemydot.fragment.DotViewFragment;
import kmitl.lab03.chanchai58070029.simplemydot.fragment.EditDotFragment;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            initialFragment();
        }
    }
    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, new DotViewFragment())
                .commit();
    }

}
