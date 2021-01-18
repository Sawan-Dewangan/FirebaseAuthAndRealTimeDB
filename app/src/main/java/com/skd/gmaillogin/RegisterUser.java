package com.skd.gmaillogin;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterUser extends AppCompatActivity {

    EditText mEditTextEmail;
    EditText mEditTextPwd;
    EditText mEditTextName;
    Button buttonSignIn;
    private String name;
    private String email;
    private String password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //initialize firebase auth.
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        //getting reference of firebase database


        //initialize the views.
        mEditTextEmail = findViewById(R.id.editTextEmailAddress);
        mEditTextPwd = findViewById(R.id.editTextPassword);
        mEditTextName = findViewById(R.id.editTextName);
        buttonSignIn = findViewById(R.id.button3);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = mEditTextName.getText().toString().trim();
                email = mEditTextEmail.getText().toString().trim();
                password = mEditTextPwd.getText().toString().trim();

                if(name != null) {
                    if(email != null) {
                        createAccount(email, password);
                        //addToDb(name, email);
                    }
                    else{
                        mEditTextEmail.requestFocus();
                        mEditTextPwd.setError("email required");
                    }
                }
                else{
                    mEditTextName.requestFocus();
                    mEditTextName.setError("Name Required");
                }
            }

        });
    }

    public void createAccount(final String Email, String Password){
        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(RegisterUser.this, Profile.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(RegisterUser.this,"unsucess account cration"
                                    ,Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
/*
    private void addToDb(String name, String email) {

        if(!TextUtils.isEmpty(name)) {
            String id = referenceUser.push().getKey();
            User user = new User(id,name,email);
            referenceUser.child(id).setValue(user);
        }
        else{
            Toast.makeText(this,"Plese enter your name",Toast.LENGTH_LONG).show();
        }
    }

*/
}