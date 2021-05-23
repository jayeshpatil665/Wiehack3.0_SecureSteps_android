package in.specialsoft.securesteps.Api;


import in.specialsoft.securesteps.GetMyChildInOut.GetMyChildIn;
import in.specialsoft.securesteps.GetMyChildInOut.GetMyChildOut;
import in.specialsoft.securesteps.InTroubleInOut.InTroubleIn;
import in.specialsoft.securesteps.InTroubleInOut.InTroubleOut;
import in.specialsoft.securesteps.MyStateInOut.MyStateIn;
import in.specialsoft.securesteps.MyStateInOut.MyStateOut;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    //update User API - in Trouble
    @POST("user/inTrouble.php")
    Call<InTroubleOut> updateUser(@Body InTroubleIn i);

    //check User/child API
    @POST("user/getMyChild.php")
    Call<GetMyChildOut> getTheUser(@Body GetMyChildIn i);

    //Update safe status API
    @POST("user/safeStatus.php")
    Call<MyStateOut> updateState(@Body MyStateIn i);
}
