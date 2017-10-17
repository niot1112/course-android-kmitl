package lab07.inclass.example.MyLazyInstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lab07.inclass.example.MyLazyInstagram.Posts;
import lab07.inclass.example.testlab07.R;

/**
 * Created by student on 10/6/2017 AD.
 */
class Holder extends RecyclerView.ViewHolder{

    public ImageView image;
    public TextView like;
    public TextView comment;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        like = (TextView) itemView.findViewById(R.id.like);
        comment = (TextView) itemView.findViewById(R.id.comment);
    }
}

public class PostAdapter extends  RecyclerView.Adapter<Holder>{

    private Context context;
    private List<Posts> posts;
    public PostAdapter(Context context, List<Posts> posts) {
        this.context = context;
        this.posts = posts;
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
        Glide.with(context).load(posts.get(position).getUrl()).into(image);

        TextView like = holder.like;
        like.setText("Like : "+String.valueOf(posts.get(position).getLike()));

        TextView comment = holder.comment;
        comment.setText("Comment : "+String.valueOf(posts.get(position).getComment()));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
