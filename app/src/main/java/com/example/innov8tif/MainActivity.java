package com.example.innov8tif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.innov8tif.Activity.CommentsActivity;
import com.example.innov8tif.Adapter.PostAdapter;
import com.example.innov8tif.Model.PostData;
import com.example.innov8tif.Network.Api;
import com.example.innov8tif.Util.NetworkChangeListener;

import org.w3c.dom.Text;

import java.util.List;

import retrofit.RetrofitError;

import retrofit.Callback;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<PostData> postDataList;
    public static final String EXTRA_PARAM = "EXTRA_PARAM";
    PostAdapter  usersAdapter;
    TextView empty;
    SwipeRefreshLayout swipeRefreshLayout;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference of swipeRefreshLayout and recyclerView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getPostListData();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // call a method in which we have implement our GET type web API
        getPostListData();

    }

    private void getPostListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getPostList(new Callback<List<PostData>>() {
            @Override
            public void success(List<PostData> postData, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                postDataList = postData;
                setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // call the constructor of UsersAdapter to send the reference and data to Adapter
            usersAdapter = new PostAdapter(MainActivity.this ,postDataList, (view, position) -> {

                Intent intent = new Intent(this, CommentsActivity.class);
                String post_id =  postDataList.get(position).getId();
                intent.putExtra(EXTRA_PARAM, post_id);
                startActivity(intent);

            });
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
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