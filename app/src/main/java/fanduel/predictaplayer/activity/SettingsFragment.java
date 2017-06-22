package fanduel.predictaplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import fanduel.predictaplayer.R;
import fanduel.predictaplayer.helper.SharedPreferenceManager;

/**
 * Created by gcslo on 6/22/2017.
 */
public class SettingsFragment extends Fragment
{
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null, false);
        final NumberPicker difficultyLevel = (NumberPicker) view.findViewById(R.id.number_picker_difficulty);
        difficultyLevel.setMinValue(2);
        difficultyLevel.setMaxValue(8);
        difficultyLevel.setValue(SharedPreferenceManager.getDifficulty(getContext()));

        Button setDifficulty = (Button) view.findViewById(R.id.btn_set_difficulty);
        setDifficulty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferenceManager.setDifficulty(getContext(),difficultyLevel.getValue());
                AppCompatActivity activity = (AppCompatActivity) getContext();
                GuessFragment guessFragment = new GuessFragment() ;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.lyt_container, guessFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }


}
