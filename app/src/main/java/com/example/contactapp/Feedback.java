package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    int maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Button button=findViewById(R.id.button);
        final EditText tv=findViewById(R.id.tv);
        final EditText mail=findViewById(R.id.mail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=tv.getText().toString();
                if(str.equals("") || mail.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill the valid information!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String user_mail=mail.getText().toString();
                    Toast.makeText(getApplicationContext(),"Thanks for your time! ",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                maxid= (int) snapshot.getChildrenCount();
                            }else{

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    myRef.child("Id : "+ (maxid + 1)).setValue(user_mail + "--- "+tv.getText().toString());
                }
            }
        });

    }

}