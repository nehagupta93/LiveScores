package net.cubeservices.livescoresapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FootballFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballFragment extends Fragment {

    private List<FootballSeason> footballSeasons;
    private static ViewPager mPager;
    private static View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FootballFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FootballFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FootballFragment newInstance(String param1, String param2) {
        FootballFragment fragment = new FootballFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_football, container, false);

        ServerRequests sr = new ServerRequests();
        sr.fetchIds(view.getContext(), "football", new GetCallback() {
            @Override
            public void done(String details) {
                footballSeasons = parseJSON(details);
                PagerAdapter mPagerAdapter;

                // Instantiate a ViewPager and a PagerAdapter.
                mPager = (ViewPager) view.findViewById(R.id.pager);
                mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), footballSeasons);
                mPager.setAdapter(mPagerAdapter);
            }
        });
        return view;
    }

    public void left(View view){
        if (mPager.getCurrentItem()!=0){
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void right(View view){
        if (mPager.getCurrentItem()!=mPager.getAdapter().getCount()-1){
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<FootballSeason> x;
        public ScreenSlidePagerAdapter(FragmentManager fm, List<FootballSeason> x) {
            super(fm);
            this.x= x;
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment().newInstance(x.get(position));
        }

        @Override
        public int getCount() {
            return x.size();
        }
    }

    public static List<FootballSeason> parseJSON(String JSONResult){

        List<FootballSeason> fs=new ArrayList<FootballSeason>();
        try{
            JSONArray jsonArray = new JSONArray(JSONResult);
            if( jsonArray.length()!=0){

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    FootballSeason footballSeason = new FootballSeason(jsonObject.getInt("id"), jsonObject.getString("caption"), jsonObject.getInt("currentMatchday"), jsonObject.getInt("numberOfMatchdays"), jsonObject.getInt("numberOfTeams"), jsonObject.getInt("numberOfGames"));
                    fs.add(footballSeason);
                }
            }else{
                Log.d("Parse", "Result is empty");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return fs;
    }
}
