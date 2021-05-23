package in.specialsoft.securesteps.MyStateInOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyStateOut {
    @SerializedName("output")
    @Expose
    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
