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
import zango.org.liferayapp.utils.FunctionUtil;
import zango.org.liferayapp.utils.HttpManager;
import zango.org.liferayapp.utils.URLs;

public class BooksActivity extends AppCompatActivity {

ListView listbooks;
List<Book> books=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        listbooks=(ListView)findViewById(R.id.list_books);


        // Get The session
        Session session=SessionContext.createSessionFromCurrentSession();
        try {
            //Register Device on portal
            Push.with(session).register(this, getString(R.string.sender_id));
        }
        catch (Exception e) {
            e.printStackTrace();
        };

        new getBookTasks().execute(URLs.GET_ALL_BOOKS_URL);

    }
    // Task to get books
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
