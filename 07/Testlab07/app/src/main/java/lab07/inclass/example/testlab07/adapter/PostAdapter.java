package lab07.inclass.example.testlab07.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import lab07.inclass.example.testlab07.MainActivity;
import lab07.inclass.example.testlab07.R;

/**
 * Created by student on 10/6/2017 AD.
 */
class Holder extends RecyclerView.ViewHolder{

    public ImageView image;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}

public class PostAdapter extends  RecyclerView.Adapter<Holder>{

    String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n5.jpg"
    };

    private Context context;
    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(data[position]).into(image);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}
