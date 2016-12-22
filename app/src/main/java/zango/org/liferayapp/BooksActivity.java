package zango.org.liferayapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.push.PushScreensActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zango.org.liferayapp.adapters.BookAdapter;
import zango.org.liferayapp.models.Book;
import zango.org.liferayapp.utils.ConstantsUtil;
import zango.org.liferayapp.utils.HttpManager;
import zango.org.liferayapp.utils.URLs;

public class BooksActivity extends PushScreensActivity {

ListView listbooks;
List<Book> books=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        listbooks=(ListView)findViewById(R.id.list_books);
      //  Session session = new SessionImpl(ConstantsUtil.SERVER, new BasicAuthentication(ConstantsUtil.USERNAME, ConstantsUtil.PASSWORD));
        try {
          //  Push.with(SessionContext.createSessionFromCurrentSession()).register(this, "921393871558");


            Push.with(SessionContext.createSessionFromCurrentSession())
                    .onSuccess(new Push.OnSuccess() {

                        @Override
                        public void onSuccess(JSONObject jsonObject) {
                            System.out.println("Device was registered!");

                        }
                    })
                    .onFailure(new Push.OnFailure() {

                        @Override
                        public void onFailure(Exception e) {
                            System.out.println("Some error occurred!");
                        }

                    })
                    .register(getString(R.string.sender_id));

            Push.with(SessionContext.createSessionFromCurrentSession()).onPushNotification(new Push.OnPushNotification(){
                @Override
                public void onPushNotification(JSONObject pushNotification) {
                    System.out.println("I Got this message !"+pushNotification);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        new getBookTasks().execute(URLs.GET_ALL_BOOKS_URL);

    }

    @Override
    protected Session getDefaultSession() {
        return SessionContext.createSessionFromCurrentSession();
    }

    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {
        System.out.println("Virtuoso I got a message  here : "+jsonObject);

    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        System.out.println("Virtuoso Problem : "+message);
        e.getStackTrace();
    }

    @Override
    protected String getSenderId() {
        return getString(R.string.sender_id);
    }

    private class getBookTasks extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
        System.out.println("Virtuoso Result "+ result);

            try {
                JSONArray jsonBooks=new JSONArray(result);
                for(int i=0;i<jsonBooks.length();i++){
                    JSONObject jsonBook=jsonBooks.getJSONObject(i);
                    Book book=new Book();
                    book.setNameBook(jsonBook.getString("bookName"));
                    book.setAuthorBook(jsonBook.getString("bookAuthor"));
                    book.setResumeBook(jsonBook.getString("bookResume"));

                    books.add(book);
                }

                BookAdapter bookAdapter=new BookAdapter(BooksActivity.this,books);
                listbooks.setAdapter(bookAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
