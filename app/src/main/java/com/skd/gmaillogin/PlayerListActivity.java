package com.skd.gmaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerListActivity extends AppCompatActivity {

    public static final String PlayerId = "playerId";
    public static final String PlayerName = "playerName";
    EditText editTextPlayerName;
    Spinner mSpinner;
    Button mButton;
    ListView mplayerList;
    DatabaseReference referenceUser;
    List<Player> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        //initializing dbReference
        referenceUser = FirebaseDatabase.getInstance().getReference("Player");

        editTextPlayerName = findViewById(R.id.playerName);
        mSpinner = findViewById(R.id.spinner);
        mButton = findViewById(R.id.button);
        mplayerList = findViewById(R.id.playerList);
        mList = new ArrayList<>();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDB();
            }
        });

        mplayerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player player =  mList.get(i);

                Intent intent = new Intent(getApplicationContext(),AddLanguage.class);
                intent.putExtra(PlayerId,player.getpId());
                intent.putExtra(PlayerName,player.getPlayerName());
                startActivity(intent);
            }
        });
    }

    //method for add data to Firebase database
    private void addToDB(){
        String pName = editTextPlayerName.getText().toString().trim();
        String game = mSpinner.getSelectedItem().toString().trim();
        String pId = referenceUser.push().getKey();

        Player player = new Player(pId,pName,game);

        referenceUser.child(pId).setValue(player);

    }

    //Retrieving data from firebase database
    @Override
    protected void onStart() {
        super.onStart();

        referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                mList.clear();
                    for (DataSnapshot UserSnapShot:snapshot.getChildren())
                    {
                        Player user = UserSnapShot.getValue(Player.class);
                        mList.add(user);
                    }
                    PlayerList adapter = new PlayerList(PlayerListActivity.this, mList);
                    mplayerList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Hello", "loadPost:onCancelled", error.toException());

            }
        });
    }

}