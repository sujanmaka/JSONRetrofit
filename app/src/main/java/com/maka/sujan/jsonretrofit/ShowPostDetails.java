package com.maka.sujan.jsonretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ShowPostDetails extends AppCompatActivity {

    //Defining views
   /* private TextView textViewUserId;
    private TextView textViewId;*/

    public static final String ROOT_URL = "https://jsonplaceholder.typicode.com/";

    private ListView listView;
    private List<PostDetail> posts;
    private String id;

    private SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details1);



       // textViewId = (TextView) findViewById(R.id.textViewId);


        //Getting intent
        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        Toast.makeText(this, "You have clicked " + id , Toast.LENGTH_SHORT).show();
        //Displaying values by fetching from intent
        //textViewId.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_ID, 0)));

        listView = (ListView) findViewById(R.id.listViewPost);
        db = new SQLiteHandler(getApplicationContext());

        //Calling the method that will fetch data
       // getPostsDetails();



        NetworkUtils utils = new NetworkUtils(ShowPostDetails.this);
        if(utils.isConnectingToInternet())
        {
            //Toast.makeText(this, "Network is connected ", Toast.LENGTH_SHORT).show();

            //Calling the method that will fetch data
            getPostsDetails();



        }
        else
        {
            Toast.makeText(this, "No connection available ", Toast.LENGTH_SHORT).show();
            ArrayList<PostDetail> postDetailList = (ArrayList<PostDetail>) db.getAllPostsDetail(id);
            PostDetailAdapter adapter = new PostDetailAdapter(ShowPostDetails.this,postDetailList);
            listView.setAdapter(adapter);

        }

    }

    private void getPostsDetails(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        PostDetailAPI api = adapter.create(PostDetailAPI.class);

        //Defining the method
        api.getPosts1(id, new Callback<List<PostDetail>>() {
            @Override
            public void success(List<PostDetail> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                posts = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }


    private void showList(){

        //Delete previously stored database
        db.deletePostsDetail();

        //String array to store all the book names
        String[] items = new String[posts.size()];


        //Traversing through the whole list to get all the names
        for(int i = 0; i< posts.size(); i++){
            //Storing names to string array

            items[i] = "Post Id: " + String.valueOf(posts.get(i).getPostId()) + "\n" + "Id: " +
                    posts.get(i).getId() + "\n" + "Name: " + posts.get(i).getName() + "\n" + "Email: " + posts.get(i).getEmail()
                    + "\n" + "Body: " + posts.get(i).getBody();

            db.addPostDetail(String.valueOf(posts.get(i).getPostId()), String .valueOf(posts.get(i).getId()), posts.get(i).getName(),
                    posts.get(i).getEmail(), posts.get(i).getBody());


        }


        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter(adapter);



    }


}