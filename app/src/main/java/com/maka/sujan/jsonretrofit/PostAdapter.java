package com.maka.sujan.jsonretrofit;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;


public class PostAdapter extends BaseAdapter {

    Context context;
    ArrayList<Post> listData;

    public PostAdapter(Context context,ArrayList<Post> listData){
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
        Post post = listData.get(position);
        String uId = String.valueOf(post.getUserId());
        String id = String.valueOf(post.getId());
        String title = post.getTitle();
        String body = post.getBody();


        viewHolder.txtView.setText("User Id: " + uId + "\n" + "Id: " +
               id+ "\n" + "Title: " + title + "\n" + "Body: " + body);
        return view;
    }



}