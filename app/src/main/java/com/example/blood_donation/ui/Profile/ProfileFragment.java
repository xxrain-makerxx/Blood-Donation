package com.example.blood_donation.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blood_donation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    TextView textViewName,textViewEmail,textViewAge
            ,textViewPhone,textViewBloodType,textViewCity;
    FirebaseUser user;
    FirebaseFirestore database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        textViewName = root.findViewById(R.id.textViewName);
        textViewEmail = root.findViewById(R.id.textViewEmail);
        textViewAge = root.findViewById(R.id.textViewAge);
        textViewPhone = root.findViewById(R.id.textViewPhone);
        textViewBloodType = root.findViewById(R.id.textViewBloodType);
        textViewCity = root.findViewById(R.id.textViewCity);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseFirestore.getInstance();
        setValues();
        return root;
    }

    public void setValues(){
        String Uid = user.getUid();
        DocumentReference dbRef = database.collection("Users").document(Uid);
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isComplete()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        textViewName.setText(documentSnapshot.getString("name"));
                        textViewEmail.setText(documentSnapshot.getString("email"));
                        textViewAge.setText(documentSnapshot.getString("age"));
                        textViewBloodType.setText(documentSnapshot.getString("blood"));
                        textViewCity.setText(documentSnapshot.getString("city"));
                        textViewPhone.setText(documentSnapshot.getString("phone"));
                    }
                    else{
                        Toast.makeText(getContext(), "not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}