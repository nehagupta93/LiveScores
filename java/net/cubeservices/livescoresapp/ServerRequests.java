package net.cubeservices.livescoresapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by neha on 4/15/2016.
 */
public class ServerRequests {

    private static final String SEASONS_API_URL = "http://digdeep.pe.hu/livescore/fetchIds.php";
    private static final String FIXTURES_API_URL = "http://digdeep.pe.hu/livescore/fetchFixtures.php";
    private static final String CRICKET_SCORE_API_URL = "http://digdeep.pe.hu/livescore/fetchCricketScore.php";

    public void fetchIds(Context context, String tag, GetCallback callback){

        class GetIds extends AsyncTask<Void, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            Context context;
            String tag;
            GetCallback callback;
            ProgressDialog loading;

            GetIds(Context context, String tag, GetCallback callback){
                this.context = context;
                this.tag = tag;
                this.callback = callback;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals(null))
                    Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show();
                callback.done(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> data = new HashMap<String, String>();

                data.put("tag", tag);

                String result = ruc.sendPostRequest(SEASONS_API_URL, data);
                return result;
            }
        }

        new GetIds(context, tag, callback).execute();
    }

    public void fetchFootballFixtures(Context context, int id, int matchDay, String tag, GetCallback callback){

        class GetFootballFixtures extends AsyncTask<Void, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            Context context;
            int id;
            int matchDay;
            String tag;
            GetCallback callback;
            ProgressDialog loading;

            GetFootballFixtures(Context context, int id, int matchDay, String tag, GetCallback callback){
                this.context = context;
                this.id = id;
                this.matchDay = matchDay;
                this.tag = tag;
                this.callback = callback;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals(null))
                    Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show();
                callback.done(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("id", id+"");
                data.put("matchDay", matchDay+"");
                data.put("tag", tag);

                String result = ruc.sendPostRequest(FIXTURES_API_URL, data);
                return result;
            }
        }

        new GetFootballFixtures(context, id, matchDay, tag, callback).execute();
    }

    public void fetchCricketScore(Context context, int id, GetCallback callback){

        class GetCricketScore extends AsyncTask<Void, Void, String> {

            RegisterUserClass ruc = new RegisterUserClass();

            Context context;
            int id;
            GetCallback callback;
            ProgressDialog loading;

            GetCricketScore(Context context, int id, GetCallback callback){
                this.context = context;
                this.id = id;
                this.callback = callback;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals(null))
                    Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show();
                callback.done(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("id", id+"");

                String result = ruc.sendPostRequest(CRICKET_SCORE_API_URL, data);
                return result;
            }
        }

        new GetCricketScore(context, id, callback).execute();
    }
}
