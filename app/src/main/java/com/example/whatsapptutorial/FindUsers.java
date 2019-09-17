package com.example.whatsapptutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

public class FindUsers extends AppCompatActivity {
    private RecyclerView mUsersList;
    private RecyclerView.Adapter mUsersAddapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);
        mUsersList=findViewById(R.id.userList);
        mUsersList.setNestedScrollingEnabled(false);
        mUsersList.setHasFixedSize(false);
        mUsersList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
    }
}
