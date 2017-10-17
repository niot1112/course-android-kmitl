package lab07.inclass.example.MyLazyInstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lab07.inclass.example.MyLazyInstagram.adapter.PostAdapter;
import lab07.inclass.example.MyLazyInstagram.api.LazyinstragramApi;
import lab07.inclass.example.testlab07.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String user = "nature";
    private int viewIn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile(user);


        Button btnAndroid = (Button) findViewById(R.id.btnAndroid);
        btnAndroid.setOnClickListener(this);
        Button btnNature = (Button) findViewById(R.id.btnNature);
        btnNature.setOnClickListener(this);
        Button btnCartoon = (Button) findViewById(R.id.btnCartoon);
        btnCartoon.setOnClickListener(this);
        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);
    }


    private void getUserProfile(String usrName) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(LazyinstragramApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyinstragramApi lazyinstragramApi = retrofit.create(LazyinstragramApi.class);

        Call<UserProfile> call = lazyinstragramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    TextView textUser = (TextView) findViewById(R.id.textUser);
                    textUser.setText("@"+userProfile.getUser());
                    TextView textPost = (TextView) findViewById(R.id.textPost);
                    textPost.setText("POST\n"+userProfile.getPost());
                    TextView textFollower = (TextView) findViewById(R.id.textFollower);
                    textFollower.setText("Follower\n"+userProfile.getFollower());
                    TextView textFollowing = (TextView) findViewById(R.id.textFollowing);
                    textFollowing.setText("Following\n"+userProfile.getFollowing());
                    TextView textBio = (TextView) findViewById(R.id.textBio);
                    textBio.setText(userProfile.getBio());

                    ImageView imageView = (ImageView) findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this)
                            .load(userProfile.getUrlProfile())
                            .into(imageView);

                    postImage(response);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnAndroid){
            viewIn=1;
            user="android";
            getUserProfile(user);
        }
        else if(id == R.id.btnNature){
            viewIn=1;
            user="nature";
            getUserProfile(user);
        }
        else if(id == R.id.btnCartoon){
            viewIn=1;
            user="cartoon";
            getUserProfile(user);
        }
        else if(id == R.id.btnChange){
            getUserProfile(user);
        }

    }
    private void postImage(Response<UserProfile> response){
        UserProfile userProfile = response.body();
        if(viewIn == 1){
            viewIn=0;
            PostAdapter postAdapter = new PostAdapter(this, userProfile.getPosts());
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listRecycle);
            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycle2);
            recyclerView1.setAdapter(null);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setAdapter(postAdapter);
        }else{
            viewIn=1;
            PostAdapter postAdapter = new PostAdapter(this, userProfile.getPosts());
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle2);
            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.listRecycle);
            recyclerView1.setAdapter(null);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(postAdapter);
        }
    }
}
