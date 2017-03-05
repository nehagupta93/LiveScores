package net.cubeservices.livescoresapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FootballFixturesActivity extends AppCompatActivity {

    private List<FootballFixture> footballFixtures;
    private static ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_fixtures);

        Bundle data = getIntent().getExtras();
        final int seasonId = data.getInt("seasonId");
        final int matchDay = data.getInt("currentMatchday");

        ServerRequests sr = new ServerRequests();
        sr.fetchFootballFixtures(FootballFixturesActivity.this, seasonId, matchDay, "fixtures", new GetCallback() {
            @Override
            public void done(String details) {
                footballFixtures = parseJSON(details);
                PagerAdapter mPagerAdapter;

                // Instantiate a ViewPager and a PagerAdapter.
                mPager = (ViewPager) findViewById(R.id.fixturePager);
                mPagerAdapter = new FootballPagerAdapter(getSupportFragmentManager(), footballFixtures);
                mPager.setAdapter(mPagerAdapter);
            }
        });
    }

    private class FootballPagerAdapter extends FragmentStatePagerAdapter {
        List<FootballFixture> x;
        public FootballPagerAdapter(FragmentManager fm, List<FootballFixture> x) {
            super(fm);
            this.x= x;
        }

        @Override
        public Fragment getItem(int position) {
            return new FootballFixtureSlideFragment().newInstance(x.get(position));
        }

        @Override
        public int getCount() {
            return x.size();
        }
    }

    public List<FootballFixture> parseJSON(String JSONResult){

        List<FootballFixture> ff=new ArrayList<FootballFixture>();
        try{
            JSONObject jObj = new JSONObject(JSONResult);
            JSONArray jsonArray = jObj.getJSONArray("fixtures");
            if( jsonArray.length()!=0){

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    JSONObject resultObj = jsonObject.getJSONObject("result");
                    int homeGoals=0, awayGoals=0;
                    String hGoals = resultObj.get("goalsHomeTeam").toString();
                    String aGoals = resultObj.get("goalsAwayTeam").toString();
                    if(!hGoals.equals("null")&&!aGoals.equals("null")){
                        homeGoals = Integer.parseInt(hGoals);
                        awayGoals = Integer.parseInt(aGoals);
                    }/*else{
                        homeGoals = -1;
                        awayGoals = -1;
                    }*/
                    FootballFixture footballFixture = new FootballFixture(jsonObject.getString("status"), jsonObject.getString("homeTeamName"), jsonObject.getString("awayTeamName"), homeGoals, awayGoals);
                    ff.add(footballFixture);
                }
            }else{
                Log.d("Parse", "Result is empty");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ff;
    }

    public void leftClicked(View view){
        if (mPager.getCurrentItem()!=0){
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void rightClicked(View view){
        if (mPager.getCurrentItem()!=mPager.getAdapter().getCount()-1){
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        }
    }
}
