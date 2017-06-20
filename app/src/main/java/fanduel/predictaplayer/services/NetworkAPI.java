package fanduel.predictaplayer.services;

import android.database.Observable;

import fanduel.predictaplayer.model.PlayerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gcslo on 6/19/2017.
 */
public interface NetworkAPI {

    @GET("Player.json")
    Call<PlayerResponse> getPlayerDetails();
}
