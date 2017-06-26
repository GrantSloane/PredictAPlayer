package fanduel.predictaplayer.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.List;

import fanduel.predictaplayer.R;
import fanduel.predictaplayer.adapter.PlayerAdapter;
import fanduel.predictaplayer.helper.SharedPreferenceManager;
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
    public static View view ;

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static GuessFragment newInstance() {
        GuessFragment fragment = new GuessFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guess, null, false);
        TextView settings = (TextView) view.findViewById(R.id.txt_settings);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fontawesome-webfont.ttf");
        settings.setTypeface(tf);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                SettingsFragment settingsFragment = new SettingsFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.lyt_container, settingsFragment).addToBackStack(null).commit();
            }
        });

        //Wait for the view to be drawn on the screen before calculating the height
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_players);
                if (recyclerView != null)
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                MainActivity activity = (MainActivity) getActivity();
                PlayerRoundGenerator round = activity.getRound();
                List<Player> randomPlayers = round.generateRandomPlayers();
                int winnerIndex = round.determineHighestFPPG();
                int listViewHeight = Math.round(recyclerView.getMeasuredHeight() / SharedPreferenceManager.getDifficulty(getContext()));
                recyclerView.setAdapter(new PlayerAdapter(randomPlayers, R.layout.player_listview_item, listViewHeight, winnerIndex, getContext()));
            }
        });

        return view;
    }



}
