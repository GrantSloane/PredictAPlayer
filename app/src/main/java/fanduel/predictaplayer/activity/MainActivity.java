package fanduel.predictaplayer.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import fanduel.predictaplayer.R;
import fanduel.predictaplayer.listhandler.PlayerRoundGenerator;
import fanduel.predictaplayer.model.Player;
import fanduel.predictaplayer.model.PlayerResponse;
import fanduel.predictaplayer.services.NetworkAPI;
import fanduel.predictaplayer.services.NetworkClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PlayerRoundGenerator round ;
    private static int CORRECT_GUESSES_REQUIRED = 10 ;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static TextView loadingText;
    private static ImageView loadingSpinner;
    private static Button btnRetry;
    private static final String SELECTED_ITEM_POSITION = "ItemPosition";
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            loadingSpinner = (ImageView) findViewById(R.id.loading_spinner);
            loadingText = (TextView) findViewById(R.id.txt_loading);
            btnRetry = (Button) findViewById(R.id.btn_retry);
            loadPlayerData();
        }


    }

    public void loadPlayerData()
    {

        btnRetry.setVisibility(View.GONE);
        loadingSpinner.setVisibility(View.VISIBLE);
        loadingText.setText("Loading Player Data");
        Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        loadingSpinner.startAnimation(startRotateAnimation);

        NetworkAPI apiService = NetworkClient.getClient().create(NetworkAPI.class);
        Call<PlayerResponse> call = apiService.getPlayerDetails();
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {

                System.out.println("<><><><><> RESPONSE <><><><><>");

                loadingSpinner.clearAnimation();

                if (response != null) {
                    List<Player> players = response.body().getPlayers();
                    System.out.println("RESPONSE IS NOT NULL");
                    round = new PlayerRoundGenerator(getApplicationContext(),players);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.lyt_container, new GuessFragment());
                    ft.commit();
                }
                else System.out.println("RESPONSE IS NULL");

            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                loadingSpinner.clearAnimation();
                System.out.println("<><><><><> FAILURE <><><><><>");
                btnRetry.setVisibility(View.VISIBLE);
                loadingSpinner.setVisibility(View.GONE);
                loadingText.setText("Unable to load player data. Please check you network connectivity");
                btnRetry.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                       loadPlayerData();
                    }
                });
                Log.e(TAG, t.toString());
            }
        });
    }

    public PlayerRoundGenerator getRound()
    {
        return round ;
    }

    public void resetDifficultyRound()
    {
        round.resetDifficulty();
    }

    public void increaseCorrectScore()
    {
        int score = round.getCorrectScoreCount() +1 ;
        if(score == CORRECT_GUESSES_REQUIRED)
        {
            double totalGuesses = CORRECT_GUESSES_REQUIRED+round.getIncorrectScoreCount() ;
            double accuracy =  ( CORRECT_GUESSES_REQUIRED/totalGuesses)*100 ;
            System.out.println("Congratulations. You achieved a guess accuracy of " + String.format("%.2f", accuracy).replace("00","") + "%");
            round.setCorrectScore(0);
            round.setIncorrectScore(0);
        }
        else round.setCorrectScore(score);


    }

    public void increaseInCorrectScore()
    {
        int score = round.getIncorrectScoreCount() + 1 ;
        round.setIncorrectScore(score);
    }

}
