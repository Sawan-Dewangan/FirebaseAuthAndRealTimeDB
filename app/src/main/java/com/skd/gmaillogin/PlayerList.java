package com.skd.gmaillogin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlayerList<playerList> extends ArrayAdapter<Player> {

    private Activity context;
    private List<Player> playerList;

    public PlayerList(Activity context, List<Player> playerList){
        super(context,R.layout.player_list_item,playerList);
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View mListViewItem = inflater.inflate(R.layout.player_list_item,null,true);

        TextView textViewPlayer = mListViewItem.findViewById(R.id.textView);
        TextView textViewGame = mListViewItem.findViewById(R.id.textView2);

        Player player = playerList.get(position);

        textViewPlayer.setText(player.getPlayerName());
        textViewGame.setText(player.getGame());

        return mListViewItem;
    }
}
