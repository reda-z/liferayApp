package zango.org.liferayapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.blogsentry.BlogsEntryService;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.push.PushScreensActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import zango.org.liferayapp.utils.ConstantsUtil;

public class MainActivity extends AppCompatActivity implements LoginListener{

    LoginScreenlet loginScreenlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginScreenlet =(LoginScreenlet) findViewById(R.id.login_screenlet);
        loginScreenlet.setListener(this);
       /* Session session = new SessionImpl("http://localhost:8080",
                new BasicAuthentication("test@liferay.com", "zango"));

        BlogsEntryService service = new BlogsEntryService(session);
        System.out.println("Server Test : "+session.getServer());
        try {

            JSONArray jsonArray = service.getGroupEntries(10182, 0, 0, -1, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

/*    @Override
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
    }*/

    @Override
    public void onLoginSuccess(User user) {
        System.out.println("Virtuoso Successful sign-in, user details: " + user.getId());
        Intent intent = new Intent(this, BooksActivity.class);
        intent.putExtra("USER_ID", user.getId());
        intent.putExtra("USER_MAIL", user.getEmail());
        intent.putExtra("USER_NAME", user.getFullName());


        startActivity(intent);
    }

    @Override
    public void onLoginFailure(Exception e) {
        System.out.println("Virtuoso ERROR: " + e.getMessage());
    }
}
