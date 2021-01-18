package com.skd.gmaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    TextView mTextView;
    TextView mTextView2;
    Button mButton;
    Button mPlayerButton;
    FirebaseAuth firebaseAuth;
    DatabaseReference referenceUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mTextView = findViewById(R.id.textView);
        mTextView2 = findViewById(R.id.textView2);
        mButton = findViewById(R.id.button2);
        mPlayerButton = findViewById(R.id.PlayerButton);

        //getting reference of firebase Database.
        referenceUser = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user == null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        mTextView.setText("Welcome "+ user.getEmail());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,PlayerListActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}