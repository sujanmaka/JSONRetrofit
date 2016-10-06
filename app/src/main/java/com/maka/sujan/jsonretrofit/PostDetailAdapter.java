package com.maka.sujan.jsonretrofit;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailAdapter.ContactViewHolder> {

    private List<PostDetail> items;
    private int itemLayout;

    public PostDetailAdapter(List<PostDetail> items, int itemLayout){
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
        PostDetail item = items.get(position);
       /* holder.post.setText(item.getPostId() + "\n" + item.getId() +
                "\n" + item.getName() + "\n" + item.getEmail() + "\n" + item.getBody());
*/

        holder.txtViewName.setText(item.getName());
        holder.txtViewEmail.setText(item.getEmail());
        holder.txtViewPostId.setText(item.getPostId());
        holder.txtViewId.setText(item.getId());
        holder.txtViewBody.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {

       // private TextView post;
          private TextView txtViewName, txtViewEmail, txtViewPostId, txtViewId, txtViewBody;

        public ContactViewHolder(View v) {
            super(v);
          //  post = (TextView) v.findViewById(R.id.txtViewDetail);
            txtViewName = (TextView) v.findViewById(R.id.txtViewName);
            txtViewEmail = (TextView) v.findViewById(R.id.txtViewEmail);
            txtViewPostId = (TextView) v.findViewById(R.id.txtViewPostId);
            txtViewId = (TextView) v.findViewById(R.id.txtViewId);
            txtViewBody = (TextView) v.findViewById(R.id.txtViewBody);

        }
    }
}