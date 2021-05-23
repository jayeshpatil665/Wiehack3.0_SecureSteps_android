package in.specialsoft.securesteps.Api;


import in.specialsoft.securesteps.InTroubleInOut.InTroubleIn;
import in.specialsoft.securesteps.InTroubleInOut.InTroubleOut;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    //update User API - in Trouble
    @POST("user/inTrouble.php")
    Call<InTroubleOut> updateUser(@Body InTroubleIn i);

}