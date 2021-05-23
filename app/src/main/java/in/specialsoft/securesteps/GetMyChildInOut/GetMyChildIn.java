package in.specialsoft.securesteps.GetMyChildInOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMyChildIn {
    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
