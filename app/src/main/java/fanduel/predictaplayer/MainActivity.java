package fanduel.predictaplayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;


import java.util.List;

import fanduel.predictaplayer.adapter.PlayerAdapter;
import fanduel.predictaplayer.listhandler.PlayerRoundGenerator;
import fanduel.predictaplayer.listhandler.RandomNumberGenerator;
import fanduel.predictaplayer.model.Player;
import fanduel.predictaplayer.model.PlayerResponse;
import fanduel.predictaplayer.services.NetworkAPI;
import fanduel.predictaplayer.services.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView settings = (TextView) findViewById(R.id.txt_settings);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),"fontawesome-webfont.ttf");
        settings.setTypeface(tf);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_players);
        if(recyclerView != null)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NetworkAPI apiService = NetworkClient.getClient().create(NetworkAPI.class);
        Call<PlayerResponse> call = apiService.getPlayerDetails();
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {

                if(response != null) {
                    int sampleSize = response.body().getPlayers().size() ;
                    int SETTINGS_SIZE = 4 ;
                    List<Player> players = response.body().getPlayers();

                    PlayerRoundGenerator round = new PlayerRoundGenerator(players,SETTINGS_SIZE);
                    List<Player> randomPlayers = round.generateRandomPlayers() ;

                    int listViewHeight = (int) Math.round(recyclerView.getMeasuredHeight()/SETTINGS_SIZE);
                    recyclerView.setAdapter(new PlayerAdapter(randomPlayers, R.layout.player_listview_item,listViewHeight, getApplicationContext()));

                }

            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
