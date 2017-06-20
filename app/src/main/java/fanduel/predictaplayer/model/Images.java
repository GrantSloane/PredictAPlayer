
package fanduel.predictaplayer.model;


import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("default")
    private Default defaultImage;

    public Default getDefault() {
        return defaultImage;
    }

    public void setDefault(Default defaultImage) {
        this.defaultImage = defaultImage;
    }

}
