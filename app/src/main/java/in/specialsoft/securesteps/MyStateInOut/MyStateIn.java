package in.specialsoft.securesteps.MyStateInOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyStateIn {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("introuble")
    @Expose
    private String introuble;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntrouble() {
        return introuble;
    }

    public void setIntrouble(String introuble) {
        this.introuble = introuble;
    }
}
