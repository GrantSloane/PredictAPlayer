package fanduel.predictaplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fanduel.predictaplayer.R;
import fanduel.predictaplayer.model.Player;
import fanduel.predictaplayer.model.Players;

/**
 * Created by gcslo on 6/20/2017.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>  {

    private static final String TAG = PlayerAdapter.class.getSimpleName();
    private List<Player> players;
    private int rowLayout;
    private Context context;
    private int cellHeight ;

    public static class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout playerLayout;
        ImageView profile ;
        TextView name ;
        TextView fppg ;
        private int itemIndex;

        public PlayerViewHolder(View v) {
            super(v);
            playerLayout = (LinearLayout) v.findViewById(R.id.lyt_player);
            profile = (ImageView) v.findViewById(R.id.img_profile);
            name = (TextView) v.findViewById(R.id.txt_name);
            fppg = (TextView) v.findViewById(R.id.txt_fppg);
            v.setOnClickListener(this);
        }

        public void setItem(int item) {
            itemIndex = item;
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getAdapterPosition() + " >>>>>>>>>>>> " + itemIndex);


        }
    }

    public PlayerAdapter(List<Player> players, int rowLayout,int cellHeight, Context context) {
        this.players = players;
        this.rowLayout = rowLayout;
        this.context = context;
        this.cellHeight = cellHeight ;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, final int position) {

        holder.playerLayout.setMinimumHeight(cellHeight);
        holder.setItem(position);
        holder.name.setText(players.get(position).getFirstName() + " " + players.get(position).getLastName());

        if(players.get(position).getFppg() != null)
            holder.fppg.setText("FPPG : " + Float.toString(players.get(position).getFppg()));
        else  holder.fppg.setText("FPPG : NULL ");

//        if(players.get(position).getImages().getDefault().getUrl() != null) {
            Picasso.with(context)
                    .load(players.get(position).getImages().getDefault().getUrl())
                    .placeholder(R.drawable.progress_animation)
                    .into(holder.profile);


       // }



    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}