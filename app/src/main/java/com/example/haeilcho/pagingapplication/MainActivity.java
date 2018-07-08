package com.example.haeilcho.pagingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));

        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.user_list);
        UserAdapter adapter = new UserAdapter();
        viewModel.usersList.observe(this, pagedList -> adapter.submitList(pagedList));
//        recyclerView.setAdapter(adapter);
    }

}
