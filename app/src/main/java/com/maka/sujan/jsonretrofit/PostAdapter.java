package com.maka.sujan.jsonretrofit;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ContactViewHolder> {

    private List<Post> items;
    private int itemLayout;

    public PostAdapter(List<Post> items, int itemLayout){
        this.items = items;
        this.itemLayout = itemLayout;
    }



    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Post item = items.get(position);
     //   holder.post.setText(item.getUserId() + "\n" + item.getId() +
       // "\n" + item.getName() + "\n" + item.getBody());

        holder.txtViewTitle.setText(item.getName());
        holder.txtViewUid.setText(item.getUserId());
        holder.txtViewId.setText(item.getId());
        holder.txtViewBody.setText(item.getBody());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        //private TextView post;
        private TextView txtViewTitle, txtViewUid, txtViewId, txtViewBody;


        public ContactViewHolder(View v) {
            super(v);
          //  post = (TextView) v.findViewById(R.id.txtView);

            txtViewTitle = (TextView) v.findViewById(R.id.txtViewTitle);
            txtViewUid = (TextView) v.findViewById(R.id.txtViewUserId);
            txtViewId = (TextView) v.findViewById(R.id.txtViewId);
            txtViewBody = (TextView) v.findViewById(R.id.txtViewBody);


        }
    }
}