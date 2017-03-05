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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeagueTableActivity extends AppCompatActivity {

    private List<LeagueTable> leagueTables;
    private static ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        Bundle data = getIntent().getExtras();
        final int seasonId = data.getInt("seasonId");
        final int matchDay = data.getInt("currentMatchday");

        ServerRequests sr = new ServerRequests();
        sr.fetchFootballFixtures(LeagueTableActivity.this, seasonId, matchDay, "league", new GetCallback() {
            @Override
            public void done(String details) {
                leagueTables = parseJSON(details);
                PagerAdapter mPagerAdapter;

                // Instantiate a ViewPager and a PagerAdapter.
                mPager = (ViewPager) findViewById(R.id.leaguePager);
                mPagerAdapter = new LeaguePagerAdapter(getSupportFragmentManager(), leagueTables);
                mPager.setAdapter(mPagerAdapter);
            }
        });
    }

    private class LeaguePagerAdapter extends FragmentStatePagerAdapter {
        List<LeagueTable> x;
        public LeaguePagerAdapter(FragmentManager fm, List<LeagueTable> x) {
            super(fm);
            this.x= x;
        }

        @Override
        public Fragment getItem(int position) {
            return new LeagueTableSlideFragment().newInstance(x.get(position));
        }

        @Override
        public int getCount() {
            return x.size();
        }
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

    public List<LeagueTable> parseJSON(String JSONResult){

        List<LeagueTable> lt=new ArrayList<LeagueTable>();
        try{
            JSONObject jObj = new JSONObject(JSONResult);
            JSONArray jsonArray = jObj.getJSONArray("standing");
            if( jsonArray.length()!=0){

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    LeagueTable leagueTable = new LeagueTable(jsonObject.getInt("position"), jsonObject.getString("teamName"), jsonObject.getInt("playedGames"), jsonObject.getInt("points"), jsonObject.getInt("goals"), jsonObject.getInt("goalsAgainst"), jsonObject.getInt("goalDifference"), jsonObject.getInt("wins"), jsonObject.getInt("draws"), jsonObject.getInt("losses"));
                    lt.add(leagueTable);
                }
            }else{
                Log.d("Parse", "Result is empty");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lt;
    }
}
