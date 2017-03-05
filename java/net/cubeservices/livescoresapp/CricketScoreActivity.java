package net.cubeservices.livescoresapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CricketScoreActivity extends AppCompatActivity {

    private CricketScore cricketScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_score);

        Bundle data = getIntent().getExtras();
        final int id = data.getInt("id");

        ServerRequests sr = new ServerRequests();
        sr.fetchCricketScore(CricketScoreActivity.this, id, new GetCallback() {
            @Override
            public void done(String details) {
                List<CricketScore> cs = parseJSON(details);

                    cricketScore = cs.get(0);

                TextView line1 = (TextView) findViewById(R.id.line1);
                TextView line2 = (TextView) findViewById(R.id.line2);
                TextView line3 = (TextView) findViewById(R.id.line3);
                TextView line4 = (TextView) findViewById(R.id.line4);

                line1.setText(cricketScore.line1);
                line2.setText(cricketScore.line2);
                line3.setText(cricketScore.line3);
                line4.setText(cricketScore.line4);
            }
        });
    }


    public List<CricketScore> parseJSON(String details){
            List<CricketScore> cs=new ArrayList<CricketScore>();
            try{
                JSONObject jsonObject = new JSONObject(details);
                CricketScore cricketScore = new CricketScore(jsonObject.getString("score"));
                cs.add(cricketScore);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return cs;
    }
}
