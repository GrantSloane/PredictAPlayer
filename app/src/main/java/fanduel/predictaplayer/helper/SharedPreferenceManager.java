package fanduel.predictaplayer.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by gcslo on 6/22/2017.
 */
public class SharedPreferenceManager
{

    public static final String SHARE_PREFERENCE_DIFFICULTY = "share_preference_difficulty";

    public static void setDifficulty(Context context, int difficulty)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(SHARE_PREFERENCE_DIFFICULTY,difficulty);
        editor.commit();
    }

    public static int getDifficulty(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getInt(SHARE_PREFERENCE_DIFFICULTY,4);
    }
}
