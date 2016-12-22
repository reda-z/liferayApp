package zango.org.liferayapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.screens.push.PushScreensActivity;

import org.json.JSONObject;

import zango.org.liferayapp.utils.ConstantsUtil;

public class PushActivity extends PushScreensActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
    }

    @Override
    protected Session getDefaultSession() {
        System.out.println("Virtuoso Called!");
        return new SessionImpl(ConstantsUtil.SERVER,new BasicAuthentication(ConstantsUtil.USERNAME,ConstantsUtil.PASSWORD));
    }

    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {
        System.out.println("Hello World Virtuoso"+jsonObject);
    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        System.out.println("Error Virtuoso Called");

    }

    @Override
    protected String getSenderId() {
        System.out.println("Sender Id Virtuoso Called");
        return "87868386504";
    }

}
