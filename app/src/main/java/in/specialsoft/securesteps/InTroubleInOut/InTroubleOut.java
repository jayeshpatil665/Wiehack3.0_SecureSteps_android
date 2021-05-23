package in.specialsoft.securesteps.InTroubleInOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InTroubleOut {
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
