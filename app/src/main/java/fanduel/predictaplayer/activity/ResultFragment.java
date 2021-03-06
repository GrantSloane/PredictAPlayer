package fanduel.predictaplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import fanduel.predictaplayer.R;

/**
 * Created by gcslo on 6/21/2017.
 */
public class ResultFragment extends Fragment
{
    public static String EXTRA_IMAGE_URL = "extra_image_url" ;
    public static String EXTRA_PLAYER_NAME = "extra_player_name " ;
    public static String EXTRA_PLAYER_FPPG = "extra_player_fppg " ;
    public static String EXTRA_RESULT = "extra_result" ;

    public static int CORRECT_GUESSES_REQUIRED = 10 ;

    private static final String TAG = ResultFragment.class.getSimpleName();
    private static String imageURL ;
    private static String playerName ;
    private static String playerFppg ;
    private static boolean correct ;
    private MainActivity mainActivity ;

    public static ResultFragment newInstance(String URL,String name,double fppg, boolean result) {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, null, false);
        CircleImageView profilePicture = (CircleImageView) view.findViewById(R.id.img_profile) ;
        TextView name = (TextView) view.findViewById(R.id.txt_name) ;
        TextView fppg = (TextView) view.findViewById(R.id.txt_fppg) ;
        TextView result = (TextView) view.findViewById(R.id.txt_result) ;
        mainActivity = (MainActivity) getActivity() ;

        imageURL = getArguments().getString(EXTRA_IMAGE_URL);
        playerName = getArguments().getString(EXTRA_PLAYER_NAME);
        playerFppg = getArguments().getString(EXTRA_PLAYER_FPPG);
        correct = getArguments().getBoolean(EXTRA_RESULT) ;

        Picasso.with(getContext())
                .load(imageURL)
                .placeholder(R.drawable.user)
                .into(profilePicture);
        name.setText(playerName);
        fppg.setText(playerFppg);
        if(correct) {

            profilePicture.setFillColor(getResources().getColor(R.color.green));
            result.setText(getResources().getString(R.string.correct));
            mainActivity.increaseCorrectScore();
            if(mainActivity.getCorrectScore() == CORRECT_GUESSES_REQUIRED)
            {
                    double totalGuesses = CORRECT_GUESSES_REQUIRED + mainActivity.getIncorrectScore() ;
                    double accuracy =  ( CORRECT_GUESSES_REQUIRED/totalGuesses)*100 ;
                    LinearLayout lytWinner = (LinearLayout) view.findViewById(R.id.lyt_winner);
                    lytWinner.setVisibility(View.VISIBLE);
                    TextView txtWinner = (TextView) view.findViewById(R.id.txt_winner);
                    txtWinner.setText(getResources().getString(R.string.congratulations) + " " + String.format("%.2f", accuracy).replace("00","") + "%");
                    mainActivity.clearScore();
            }
        }
        else
        {
            profilePicture.setFillColor(getResources().getColor(R.color.red));
            result.setText(getResources().getString(R.string.incorrect));
            mainActivity.increaseIncorrectScore();
        }

        return view;
    }
}
