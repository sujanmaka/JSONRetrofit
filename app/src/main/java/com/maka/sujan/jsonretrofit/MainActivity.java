package com.maka.sujan.jsonretrofit;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//Class having OnItemClickListener to handle the clicks on list
public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //Root URL of our web service
    public static final String ROOT_URL = "https://jsonplaceholder.typicode.com/";

    //Strings to bind with intent will be used to send data to other activity

    public static final String KEY_ID = "id";

    private SQLiteHandler db;


    //List view to show data
    private ListView listView;
    private List<Post> posts;

    private RecyclerView mRecyclerView;

    //List of type posts this list will store type Post which is our data model
  //  private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewPost);


        db = new SQLiteHandler(getApplicationContext());

       // db.addUser("sujan","maka","don","king");

        NetworkUtils utils = new NetworkUtils(MainActivity.this);
        if(utils.isConnectingToInternet())
        {
            //Toast.makeText(this, "Network is connected ", Toast.LENGTH_SHORT).show();

            //Calling the method that will fetch data
              getPosts();

            //Setting onItemClickListener to listview
            listView.setOnItemClickListener(this);

        }
        else
        {
            Toast.makeText(this, "No connection available ", Toast.LENGTH_SHORT).show();
            ArrayList<Post> postList = (ArrayList<Post>) db.getAllPosts();
            PostAdapter adapter = new PostAdapter(MainActivity.this,postList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);


        }

    }


    private void getPosts(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        PostAPI api = adapter.create(PostAPI.class);

        //Defining the method
        api.getPosts(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                posts = list;

                //Calling a method to show the list
                showList();
                //dbHandler.addPost((Post) posts);
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names

            db.deletePosts();
      //  db.deleteUsers();
        String[] items = new String[posts.size()];




        //Traversing through the whole list to get all the names
        for(int i = 0; i< posts.size(); i++){
            //Storing names to string array
            //items[i] = String.valueOf(posts.get(i).getUserId() + posts.get(i).getId());

            items[i] = "User Id: " + String.valueOf(posts.get(i).getUserId()) + "\n" + "Id: " +
                    posts.get(i).getId() + "\n" + "Title: " + posts.get(i).getTitle() + "\n" + "Body: " + posts.get(i).getBody();

            db.addPosts(String.valueOf(posts.get(i).getUserId()), String .valueOf(posts.get(i).getId()), posts.get(i).getTitle(), posts.get(i).getBody());

        }



        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list, items);



        //Setting adapter to listview
         listView.setAdapter(adapter);



    }


    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(this, "the clicked id is " + id+1, Toast.LENGTH_SHORT).show();

        //Creating an intent
        Intent intent = new Intent(this, ShowPostDetails.class);
        intent.putExtra(KEY_ID,String.valueOf(id+1));

        //Starting another activity to show post details
        startActivity(intent);
    }
}