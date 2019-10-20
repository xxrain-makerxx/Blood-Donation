package com.example.blood_donation.ui.Requests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.blood_donation.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.cloud.audit.RequestMetadata;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {
    String city;
    List<RequestsModel> modelList=new ArrayList<>();
    List<RequestsModel> newList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        RecyclerView recyclerView=findViewById(R.id.rv_requests);
        RequestsAdapter adapter=new RequestsAdapter();
        adapter.setData(modelList);
        recyclerView.setAdapter(adapter);
        city=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("City","");
        FirebaseFirestore.getInstance().collection("Requests").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    modelList=task.getResult().toObjects(RequestsModel.class);
                    for(RequestsModel i:modelList){
                        if(i.getCity().equals(city))
                            newList.add(i);
                    }
                    adapter.setData(newList);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
