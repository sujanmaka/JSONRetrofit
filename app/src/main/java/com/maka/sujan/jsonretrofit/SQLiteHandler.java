package com.maka.sujan.jsonretrofit;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getSimpleName();
    private static final String DATABASE_NAME = "postscomments.db";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;


    //table names
    protected static final String TABLE_POSTS = "posts";
    private static final String TABLE_COMMENTS = "comments";

    //post column name
    private static final String KEY_POSTS_USERID = "posts_userid";
    protected static final String KEY_POSTS_ID = "posts_id";
    private static final String KEY_POSTS_TITLE = "posts_title";
    private static final String KEY_POSTS_BODY = "posts_body";

    //comments column name
    private static final String KEY_COMMENTS_POSTID = "comments_postid";
    private static final String KEY_COMMENTS_ID = "comments_id";
    private static final String KEY_COMMENTS_NAME = "comments_name";
    private static final String KEY_COMMENTS_EMAIL = "comments_email";
    private static final String KEY_COMMENTS_BODY = "comments_body";

    private static final String CREATE_TABLE_POSTS = "CREATE TABLE "
            + TABLE_POSTS + "(" + KEY_POSTS_USERID + " INTEGER," + KEY_POSTS_ID
            + " INTEGER ," + KEY_POSTS_TITLE + " TEXT," + KEY_POSTS_BODY
            + " TEXT" + ")";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE "
            + TABLE_COMMENTS + "(" + KEY_COMMENTS_POSTID + " INTEGER," + KEY_COMMENTS_ID
            + " INTEGER," + KEY_COMMENTS_NAME + " TEXT," + KEY_COMMENTS_EMAIL
            + " TEXT," + KEY_COMMENTS_BODY + " TEXT" + ")";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_POSTS);
        db.execSQL(CREATE_TABLE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

    //for post
    public void addPosts(String  uid, String  id, String title, String body) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_POSTS_USERID, uid);
        values.put(KEY_POSTS_ID, id);
        values.put(KEY_POSTS_TITLE, title);
        values.put(KEY_POSTS_BODY, body);
        db.insert(TABLE_POSTS, null, values);
        db.close();
    }

    public void deletePosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_POSTS, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }


    //for postDetail
    public void addPostDetail(String  postId, String  id, String name, String email, String body) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMMENTS_POSTID, postId);
        values.put(KEY_COMMENTS_ID, id);
        values.put(KEY_COMMENTS_NAME, name);
        values.put(KEY_COMMENTS_EMAIL, email);
        values.put(KEY_COMMENTS_BODY, body);
        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    public void deletePostsDetail(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        //db.delete(TABLE_COMMENTS, null, null);

        //Delete previously stored comments
        db.delete(TABLE_COMMENTS, "comments_postid=?", new String[] { id });
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }





    /**
     * getting all Posts
     */
    public List<Post> getAllPosts() {
        List<Post> postsList = new ArrayList<Post>();

        String selectQuery = "SELECT  * FROM " + TABLE_POSTS + " ORDER BY " + KEY_POSTS_ID + " ASC ";

        Log.e(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Post post = new Post();
               // post.setUserId(c.getString((c.getColumnIndex(KEY_POSTS_USERID))));
               //post.setId(c.getString((c.getColumnIndex(KEY_POSTS_ID))));


                post.setName((c.getString(c.getColumnIndex(KEY_POSTS_TITLE))));
               // post.setBody(c.getString(c.getColumnIndex(KEY_POSTS_BODY)));

                // adding to todo list
                postsList.add(post);
            } while (c.moveToNext());
        }

        return postsList;
    }


    /**
     * getting all comments
     */
    public List<PostDetail> getAllPostsDetail(String post_id) {
        List<PostDetail> postListDetail = new ArrayList<PostDetail>();

        String selectQuery = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_COMMENTS_POSTID + " = " + post_id;
        Log.e(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PostDetail post = new PostDetail();
                post.setPostId(c.getString((c.getColumnIndex(KEY_COMMENTS_POSTID))));
                post.setId(c.getString((c.getColumnIndex(KEY_COMMENTS_ID))));
                post.setName((c.getString(c.getColumnIndex(KEY_COMMENTS_NAME))));
                post.setUserName(c.getString(c.getColumnIndex(KEY_COMMENTS_EMAIL)));
                post.setBody(c.getString(c.getColumnIndex(KEY_COMMENTS_BODY)));

                // adding to todo list
                postListDetail.add(post);
            } while (c.moveToNext());
        }

        return postListDetail;
    }

    public int getIds() {
        String selectQuery = "SELECT " + KEY_POSTS_ID + " FROM " + TABLE_POSTS;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;
    }

    public int getCommentsIds(String id) {
        String selectQuery = "SELECT " + KEY_COMMENTS_ID + " FROM " + TABLE_COMMENTS + " WHERE " + KEY_COMMENTS_POSTID + " = " + id;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;
    }
}
