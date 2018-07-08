package com.example.haeilcho.pagingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button insertButton;
    Button selectButton;

    private AppDatabase appDatabase;
    private UserDao userDao;
    private List<User> userList;
    private DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.main_textview);
        insertButton = (Button)findViewById(R.id.insert_button);
        selectButton = (Button)findViewById(R.id.select_button);

        appDatabase = AppDatabase.getAppDatabase(this);
        userDao = appDatabase.userDao();

        databaseController = new DatabaseController(this);

//        userList = userDao.getAll();
//        for (User user:userList) {
//            textView.append(user.getFirstName());
//            textView.append(user.getLastName());
//        }

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseController.insertUser("조","해일");
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                userList = databaseController.selectUser();
                for (User user:userList) {
                    textView.append(user.getFirstName());
                    textView.append(user.getLastName() + " ");
                }
            }
        });

        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.user_list);
        UserAdapter adapter = new UserAdapter();
        viewModel.getUsersList().observe(this, pagedList -> adapter.submitList(pagedList));
        recyclerView.setAdapter(adapter);
    }

}
