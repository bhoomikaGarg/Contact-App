package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Rating extends AppCompatActivity {
    RatingBar ratingBar;
    TextView displayrating;
    Button submit;
    String rate;
    DataProvider dataProvider;
    int max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        displayrating = findViewById(R.id.tv1);
        ratingBar = findViewById(R.id.ratingBar);
        submit = findViewById(R.id.submit);
        displayrating.setVisibility(View.GONE);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate = String.valueOf(ratingBar.getRating());
                displayrating.setText(rate);
                displayrating.setVisibility(View.VISIBLE);

                ratingList();

            }
        });
    }

    public void ratingList() {
        LinkedList<String> rating = new LinkedList<>();
        String stars = displayrating.getText().toString();
        rating.add(stars);

        Iterator<String> i = rating.iterator();
        while (i.hasNext()) {

            String x = i.next();
            Toast.makeText(getApplicationContext(), x+" stars have been sent as a feedback\n THANKS !", Toast.LENGTH_LONG).show();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        max= (int) snapshot.getChildrenCount();
                    }else{

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            myRef.child(String.valueOf((max + 1))).setValue(x+" stars");
        }
    }
}