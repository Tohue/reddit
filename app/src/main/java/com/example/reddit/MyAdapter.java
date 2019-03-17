package com.example.reddit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PostViewHolder> {
    List<Post> posts;


    public static class PostViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView text;
        ImageView img;
        PostViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            text = (TextView)itemView.findViewById(R.id.card_text);
            img = (ImageView)itemView.findViewById(R.id.card_photo);
        }
    }

    // Provide a suitable constructor
    public MyAdapter(List<Post> posts) {
        this.posts = posts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        PostViewHolder pvh = new PostViewHolder(v);
        return pvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PostViewHolder holder, int i) {
        holder.text.setText(posts.get(i).getTitle());
        Picasso.get().load(posts.get(i).getImage()).into(holder.img);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }
}
