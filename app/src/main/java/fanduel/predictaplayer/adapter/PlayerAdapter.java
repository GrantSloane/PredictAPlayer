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
 * This adapter is used to display the players in the main activity recycler view
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
        private int itemIndex;

        public PlayerViewHolder(View v) {
            super(v);
            playerLayout = (LinearLayout) v.findViewById(R.id.lyt_player);
            profile = (ImageView) v.findViewById(R.id.img_profile);
            name = (TextView) v.findViewById(R.id.txt_name);
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

        //Scale each cell to fill the whole screen
        android.view.ViewGroup.LayoutParams layoutParams = holder.playerLayout.getLayoutParams();
        layoutParams.height = cellHeight;
        holder.playerLayout.setLayoutParams(layoutParams);

        holder.setItem(position);
        holder.name.setText(players.get(position).getFirstName() + " " + players.get(position).getLastName());

            Picasso.with(context)
                    .load(players.get(position).getImages().getDefault().getUrl())
                    .placeholder(R.drawable.user)
                    .into(holder.profile);

    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}