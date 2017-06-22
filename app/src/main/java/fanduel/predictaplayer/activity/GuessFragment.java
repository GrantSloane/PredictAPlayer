package fanduel.predictaplayer.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fanduel.predictaplayer.R;
import fanduel.predictaplayer.adapter.PlayerAdapter;
import fanduel.predictaplayer.listhandler.PlayerRoundGenerator;
import fanduel.predictaplayer.model.Player;
import fanduel.predictaplayer.model.PlayerResponse;
import fanduel.predictaplayer.services.NetworkAPI;
import fanduel.predictaplayer.services.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gcslo on 6/21/2017.
 */
public class GuessFragment extends Fragment
{
    private static final String TAG = GuessFragment.class.getSimpleName();

    public static GuessFragment newInstance() {
        GuessFragment fragment = new GuessFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guess, null, false);

        TextView settings = (TextView) view.findViewById(R.id.txt_settings);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fontawesome-webfont.ttf");
        settings.setTypeface(tf);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_players);
        if(recyclerView != null)
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        NetworkAPI apiService = NetworkClient.getClient().create(NetworkAPI.class);
        Call<PlayerResponse> call = apiService.getPlayerDetails();
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {

                if(response != null) {
                    int sampleSize = response.body().getPlayers().size() ;
                    int SETTINGS_SIZE = 5 ;
                    List<Player> players = response.body().getPlayers();

                    PlayerRoundGenerator round = new PlayerRoundGenerator(players,SETTINGS_SIZE);
                    List<Player> randomPlayers = round.generateRandomPlayers() ;

                    int listViewHeight = (int) Math.round(recyclerView.getMeasuredHeight()/SETTINGS_SIZE);
                    recyclerView.setAdapter(new PlayerAdapter(randomPlayers, R.layout.player_listview_item,listViewHeight, getContext()));

                }

            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }
}
