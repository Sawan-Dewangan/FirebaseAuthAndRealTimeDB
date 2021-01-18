package com.skd.gmaillogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
//it is log in activity and firebase auth work implement here
//firebase RTDB work is on PlayerListActivity
    FirebaseAuth mAuth;
    String TAG = "LogIN";
    EditText emailEditText;
    EditText passwordEditText;
    ProgressDialog progressBar;
    Button mButton;
    Button mButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize firebase auth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        //checking user is exist or not
        if(mAuth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(),Profile.class));
        }

        emailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);

        progressBar = new ProgressDialog(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = emailEditText.getText().toString().trim();
                String Pwd = passwordEditText.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    emailEditText.setError("enter email address");
                    return;
                }
                if(TextUtils.isEmpty(Pwd)){
                    passwordEditText.setError("enter password");
                    return;
                }
                progressBar.setMessage("wait for a minute");
                progressBar.show();
                logInToExsitingUser(Email,Pwd);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegisterUser.class);
                startActivity(i);
            }
        });

    }

    // logIn method for existing user.
    private void logInToExsitingUser(final String Email, String Pwd){
        mAuth.signInWithEmailAndPassword(Email,Pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"Login is successful");
                            Intent profileIntent = new Intent(MainActivity.this,Profile.class);
                            profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            profileIntent.putExtra("Email",Email);
                            startActivity(profileIntent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                        progressBar.hide();
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

