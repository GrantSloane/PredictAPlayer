package fanduel.predictaplayer.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import fanduel.predictaplayer.R;

public class GuessActivity extends AppCompatActivity {

    GuessFragment guess_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lyt_container, new GuessFragment());
        ft.commit();

    }
}
