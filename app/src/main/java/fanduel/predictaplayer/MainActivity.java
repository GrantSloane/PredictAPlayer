package fanduel.predictaplayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.List;

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

        NetworkAPI apiService = NetworkClient.getClient().create(NetworkAPI.class);
        Call<PlayerResponse> call = apiService.getPlayerDetails();
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {

                List<Player> players = response.body().getPlayers();
                for(int i=0 ; i <players.size() ; i++)
                {
                    System.out.println("PLAYER " + Integer.toString(i) + " " + players.get(i).getFirstName() + " " +  players.get(i).getLastName()  );
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
