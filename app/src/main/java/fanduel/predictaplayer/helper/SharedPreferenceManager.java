package fanduel.predictaplayer.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by gcslo on 6/22/2017.
 */
public class SharedPreferenceManager
{
    public static void setDifficulty(Context context, int difficulty)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("difficulty",difficulty);
        editor.commit();
    }

    public static int getDifficulty(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getInt("difficulty",4);
    }
}
