package com.skd.gmaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AddLanguage extends AppCompatActivity {

    TextView textViewTittle;
    EditText editTextLang;
    Spinner LangLevel;
    Button buttonAdd;
    DatabaseReference databaseLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_language);

        textViewTittle = findViewById(R.id.textViewTittle);
        editTextLang = findViewById(R.id.editTextLang);
        LangLevel = findViewById(R.id.spinner);
        buttonAdd = findViewById(R.id.AddLangButton);

        Intent i = getIntent();
        String pId = i.getStringExtra(PlayerListActivity.PlayerId);
        String playerName = i.getStringExtra(PlayerListActivity.PlayerName);

        textViewTittle.setText(playerName);
        //initializing dbReference
        databaseLang = FirebaseDatabase.getInstance().getReference("language").child(pId);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLang();
            }
        });

    }
    //method for add data to Firebase database
    private void addLang(){
        String LId = databaseLang.push().getKey();
        String LName = editTextLang.getText().toString().trim();
        String LLevel = LangLevel.getSelectedItem().toString().trim();

        Language language = new Language(LId,LName,LLevel);

        databaseLang.child(LId).setValue(language);
    }
}