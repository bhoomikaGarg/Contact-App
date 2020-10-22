package com.example.contactapp.activity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapp.R;

public class AppCreator extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_creator_activity);
        txt=findViewById(R.id.txt);
        txt.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
