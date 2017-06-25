package fanduel.predictaplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import fanduel.predictaplayer.activity.GuessFragment;
import fanduel.predictaplayer.activity.ResultFragment;
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
    private int winningIndex ;

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{

        LinearLayout playerLayout;
        ImageView profile ;
        TextView name ;
        private int itemIndex;

        public PlayerViewHolder(View v) {
            super(v);
            playerLayout = (LinearLayout) v.findViewById(R.id.lyt_player);
            profile = (ImageView) v.findViewById(R.id.img_profile);
            name = (TextView) v.findViewById(R.id.txt_name);
        }

        public void setItem(int item) {
            itemIndex = item;
        }

//        @Override
//        public void onClick(View view) {
//            Log.d(TAG, "onClick " + getAdapterPosition() + " >>>>>>>>>>>> " + itemIndex);
//
//        }
    }

    public PlayerAdapter(List<Player> players, int rowLayout,int cellHeight,int winningIndex, Context context) {
        this.players = players;
        this.rowLayout = rowLayout;
        this.context = context;
        this.cellHeight = cellHeight ;
        this.winningIndex = winningIndex ;
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
        //The 3 pixels taken off the cellHeight are to account for the rounding errors. Determined through trial and error
        layoutParams.height = cellHeight - 3;
        holder.playerLayout.setLayoutParams(layoutParams);
        final String url = players.get(position).getImages().getDefault().getUrl();
        final String name = players.get(position).getFirstName() + " " + players.get(position).getLastName() ;
        final double fppg = (players.get(position).getFppg() != null) ? players.get(position).getFppg() : 0 ;
        final boolean correct = (position == winningIndex) ? true : false;


        holder.setItem(position);
        holder.name.setText(name);

            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.user)
                    .into(holder.profile);

        holder.playerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ResultFragment resultFragment = new ResultFragment() ;
                Bundle bundle = new Bundle() ;
                bundle.putString(ResultFragment.EXTRA_IMAGE_URL,url);
                bundle.putString(ResultFragment.EXTRA_PLAYER_NAME, name);
                bundle.putBoolean(ResultFragment.EXTRA_RESULT,correct);
                bundle.putString(ResultFragment.EXTRA_PLAYER_FPPG, String.format("%.2f", fppg) );
                resultFragment.setArguments(bundle) ;
                //Create a bundle to pass data, add data, set the bundle to your fragment and:
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.lyt_container, resultFragment).addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}