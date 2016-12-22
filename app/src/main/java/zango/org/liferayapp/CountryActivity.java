package zango.org.liferayapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.country.CountryService;
import com.liferay.mobile.push.Push;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import zango.org.liferayapp.utils.ConstantsUtil;

public class CountryActivity extends AppCompatActivity {

    CountryService countryService;
    ListView list;
    JSONArray countries=new JSONArray();
    Button show_port;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Session session = new SessionImpl(getString(R.string.server),
                new BasicAuthentication(getString(R.string.username),getString(R.string.password)));


        countryService=new CountryService(session);
        show_port=(Button) findViewById(R.id.show_portrait);
        list=(ListView)findViewById(R.id.listCountry) ;
        show_port.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CountryActivity.this,PortraitActivity.class);
                startActivity(intent);
            }
        });

        new GetClass(this).execute();
       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1);

        setListAdapter(adapter);*/
    }


    private class GetClass extends AsyncTask<String, Void, Void> {

        private final Context context;

        public GetClass(Context c){
            this.context = c;
        }



        @Override
        protected Void doInBackground(String... params) {

            try {
               countries=countryService.getCountries();
                System.out.println("Virtuoso Here alone"+countries);

            } catch (Exception e) {
                System.out.println("Virtuoso Error !");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            ArrayList<String> countrys=new ArrayList<>();
            for(int i=0;i<countries.length();i++){
                try {
                    countrys.add(countries.getJSONObject(i).getString("nameCurrentValue"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

             ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,countrys);
            list.setAdapter(adapter);
        }
    }


}
