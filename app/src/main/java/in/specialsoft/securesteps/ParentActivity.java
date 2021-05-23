package in.specialsoft.securesteps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.specialsoft.securesteps.Api.ApiClient;
import in.specialsoft.securesteps.Api.ApiInterface;
import in.specialsoft.securesteps.GetMyChildInOut.GetMyChildIn;
import in.specialsoft.securesteps.GetMyChildInOut.GetMyChildOut;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentActivity extends AppCompatActivity {

    EditText et_id;
    CheckBox cb_continous,cb_aleart;
    TextView tv_out;

    int notificationId = 0;
    static int flag_check = 0;

    private Handler mHandler = new Handler();

    static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        et_id = findViewById(R.id.et_id);
        cb_continous = findViewById(R.id.cb_continous);
        cb_aleart = findViewById(R.id.cb_aleart);
        tv_out = findViewById(R.id.tv_out);

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id),name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void displayNotification() {
        //Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_bell)
                .setContentTitle("In problem")
                .setContentText("Aleart .. aleart ..")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setAutoCancel(true);

        // Notification Sound
        Uri alearmSound = Uri.parse("android.resource://"+ this.getPackageName() + "/" + R.raw.the_good_doctor);
        builder.setSound(alearmSound);

        //Show Notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId,builder.build());
    }

    // repeat after every 5 seconds
    Runnable mOperationRepeat = new Runnable() {
        @Override
        public void run() {
            loadSeccionDetails(id);

            Toast.makeText(ParentActivity.this, "Checking !", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(this,9000);
        }
    };

    public void toStopRepeating(View view) {
        mHandler.removeCallbacks(mOperationRepeat);
        cb_continous.setChecked(false);
        cb_aleart.setChecked(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkConnected() {
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return  cn.getActiveNetworkInfo() != null && cn.getActiveNetworkInfo().isConnected();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toCheckNow(View view) {

        if (!et_id.getText().toString().trim().equals(""))
        {
            if (isNetworkConnected())
            {
                 id = et_id.getText().toString().trim();

                Toast.makeText(this, "starting...", Toast.LENGTH_SHORT).show();

                if (cb_continous.isChecked())
                {
                    // mHandler.postDelayed(mOperationRepeat,5000);
                    mOperationRepeat.run();
                    flag_check = flag_check +1;
                }
                else {
                    loadSeccionDetails(id);
                }

            }
            else
            {
                Toast.makeText(this, "Check Internet connection !", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this, "Please enter id to check !", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSeccionDetails(String id) {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        GetMyChildIn i = new GetMyChildIn();
        i.setId(id);
        api.getTheUser(i).enqueue(new Callback<GetMyChildOut>() {
            @Override
            public void onResponse(Call<GetMyChildOut> call, Response<GetMyChildOut> response) {
                if (response.body().getUser().get(0).getIntrouble().equals("1")){
                    Toast.makeText(ParentActivity.this, "In Trouble", Toast.LENGTH_SHORT).show();
                    tv_out.setText("message : "+response.body().getUser().get(0).getMessage()+"\n Address : "+response.body().getUser().get(0).getAddress()+"\n date : "+response.body().getUser().get(0).getDate()+"\n Time : "+response.body().getUser().get(0).getTime());
                    displayNotification();
                }
                else
                {
                    Toast.makeText(ParentActivity.this, "Not in Trouble", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetMyChildOut> call, Throwable t) {

            }
        });

    }
}