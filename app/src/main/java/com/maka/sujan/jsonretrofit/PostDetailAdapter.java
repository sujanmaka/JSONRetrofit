package com.maka.sujan.jsonretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class PostDetailAdapter extends BaseAdapter {

    Context context;
    ArrayList<PostDetail> listData;

    public PostDetailAdapter(Context context,ArrayList<PostDetail> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {

        private TextView txtView;

    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list,null);
            viewHolder = new ViewHolder();

            viewHolder.txtView = (TextView) view.findViewById(R.id.txtView);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        PostDetail postDetail = listData.get(position);
        String postId = String.valueOf(postDetail.getPostId());
        String id = String.valueOf(postDetail.getId());
        String name = postDetail.getName();
        String email = postDetail.getEmail();
        String body = postDetail.getBody();


        viewHolder.txtView.setText("Post Id:  " + postId + "\n" + "Id: " +
                id + "\n" + "Name: " + name + "\n" + "Email: " + email
                + "\n" + "Body: " + body);
        return view;
    }
}