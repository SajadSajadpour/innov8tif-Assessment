package com.example.innov8tif.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.innov8tif.Adapter.CommentsAdapter;
import com.example.innov8tif.Adapter.PostAdapter;
import com.example.innov8tif.MainActivity;
import com.example.innov8tif.Model.CommentsData;
import com.example.innov8tif.Model.PostData;
import com.example.innov8tif.Network.Api;
import com.example.innov8tif.Network.ApiInterface;
import com.example.innov8tif.R;
import com.example.innov8tif.Util.NetworkChangeListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CommentsData> commentsDataList;
    CommentsAdapter commentsAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    String Id_;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            Id_ =(String) b.get("EXTRA_PARAM");
        }

        // Getting reference of swipeRefreshLayout and recyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getCommentsListData(Id_);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getCommentsListData(Id_); // call a method in which we have implement our GET type web API
    }

    private void getCommentsListData(String id_) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(CommentsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getComments(Integer.parseInt(id_),new Callback<List<CommentsData>>(){
            @Override
            public void success(List<CommentsData> CommentsData, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                commentsDataList = CommentsData;
                setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(CommentsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        commentsAdapter = new CommentsAdapter(CommentsActivity.this ,commentsDataList, (view, position) -> {
//            Intent intent = new Intent(this, CommentsActivity.class);
//            String post_id =  postDataList.get(position).getId();
//            intent.putExtra(EXTRA_PARAM, post_id);
//            startActivity(intent);
        });
        recyclerView.setAdapter(commentsAdapter); // set the Adapter to RecyclerView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                commentsAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return true;
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}