package com.example.blood_donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    EditText editTextSearch;
    Button buttonSearch;
    TextView textViewSearch;
    FirebaseFirestore database;
    List<ProfileModel> list = new ArrayList<>(), listnew = new ArrayList<>();
    SearchActivityAdapter activityAdapter;
    RecyclerView recyclerView;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewSearch = findViewById(R.id.text_view_search_for);

        database = FirebaseFirestore.getInstance();
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchForBlood();
            }
        });

        recyclerView = findViewById(R.id.recycler_view_search);

    }

    private void searchForBlood() {


        search = editTextSearch.getText().toString();
        Toast.makeText(this, search, Toast.LENGTH_SHORT).show();
        database.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    list = Objects.requireNonNull(task.getResult()).toObjects(ProfileModel.class);

                    for (int i = 0; i < list.size(); i++) {
                        if (search.equals(list.get(i).getBlood())) {
                            ProfileModel model = new ProfileModel();
                            model.name = list.get(i).getName();
                            model.age = list.get(i).getAge();
                            model.phone = list.get(i).getPhone();
                            model.city = list.get(i).getCity();
                            model.email = list.get(i).getEmail();
                            model.blood = list.get(i).getBlood();
                            model.uid = list.get(i).getUid();

                            listnew.add(model);
                        }

                    }

                }


                    activityAdapter = new SearchActivityAdapter(SearchActivity.this, listnew);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerView.setAdapter(activityAdapter);
                    activityAdapter.notifyDataSetChanged();
                    String msg = "showing results for " + search;
                    textViewSearch.setText(msg);

            }


        });

    }
}
