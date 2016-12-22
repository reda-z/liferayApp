package zango.org.liferayapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.push.PushScreensActivity;

import org.json.JSONObject;


import zango.org.liferayapp.utils.ConstantsUtil;

public class HomeActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    TextView id,email,fullname;
    Button coutnries;
    private static final String TAG = "Virtuoso";
    private boolean isReceiverRegistered;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        id=(TextView) findViewById(R.id.id);
        email=(TextView) findViewById(R.id.email);
        fullname=(TextView) findViewById(R.id.fullname);
        coutnries=(Button) findViewById(R.id.country) ;

        Bundle extras=getIntent().getExtras();

        if(extras!=null){
            id.setText(""+extras.getString("USER_ID"));
            email.setText(extras.getString("USER_MAIL"));
            fullname.setText(extras.getString("USER_NAME"));
        }
        /*Session session = new SessionImpl(ConstantsUtil.SERVER,
                new BasicAuthentication(ConstantsUtil.USERNAME,ConstantsUtil.PASSWORD));*/

        System.out.println("Liferay Notification Start");
        try {
            Push.with(SessionContext.createSessionFromCurrentSession()).register(this, "377259878374");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Liferay Notification End");



        /*SignIn.signIn(session, new JSONObjectCallback() {
            @Override
            public void onFailure(Exception exception) {
                System.out.println("Virtuoso Exception :"+exception.getMessage());
            }

            @Override
            public void onSuccess(JSONObject result) {
                System.out.println("Virtuoso Successful sign-in, answer is : " + result);

                coutnries.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(HomeActivity.this,CountryActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });*/


       /* mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Toast.makeText(HomeActivity.this,getString(R.string.gcm_send_message),Toast.LENGTH_SHORT);
                } else {

                    Toast.makeText(HomeActivity.this,getString(R.string.token_error_message),Toast.LENGTH_SHORT);
                }
            }
        };
*/
        /*// Registering BroadcastReceiver
        registerReceiver();

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }else{
            System.out.println("Virtuoso Not Supported");
        }*/

    }
/*

    @Override
    protected Session getDefaultSession() {

        System.out.println("Hello World Virtuoso");
        Session session = new SessionImpl(ConstantsUtil.SERVER,
                new BasicAuthentication(ConstantsUtil.USERNAME,ConstantsUtil.PASSWORD));
        return session;
    }


    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {
        System.out.println("Virtuoso Notification Push Receiver"+jsonObject);
    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        System.out.println("Virtuoso Push Problem"+message);
        e.printStackTrace();

    }

    @Override
    protected String getSenderId() {
        return "544719390143";
    }
*/

   /* @Override
    protected void onResume() {
        super.onResume();
   //     registerReceiver();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }
*/

    /*private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }*/

   /* private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }*/
}
