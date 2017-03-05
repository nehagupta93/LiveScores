package net.cubeservices.livescoresapp;

import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CricketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CricketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CricketFragment extends Fragment {

    private List<CricketMatch> cricketMatches;
    private static ViewPager mPager;
    private static View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CricketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CricketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CricketFragment newInstance(String param1, String param2) {
        CricketFragment fragment = new CricketFragment();
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
        view = inflater.inflate(R.layout.fragment_cricket, container, false);

        ServerRequests sr = new ServerRequests();
        sr.fetchIds(view.getContext(), "cricket", new GetCallback() {
            @Override
            public void done(String details) {
                cricketMatches = parseJSON(details);
                PagerAdapter mPagerAdapter;

                // Instantiate a ViewPager and a PagerAdapter.
                mPager = (ViewPager) view.findViewById(R.id.matchPager);
                mPagerAdapter = new CricketMatchSlideAdapter(getChildFragmentManager(), cricketMatches);
                mPager.setAdapter(mPagerAdapter);
            }
        });

        return view;
    }

    private class CricketMatchSlideAdapter extends FragmentStatePagerAdapter {
        List<CricketMatch> x;
        public CricketMatchSlideAdapter(FragmentManager fm, List<CricketMatch> x) {
            super(fm);
            this.x= x;
        }

        @Override
        public Fragment getItem(int position) {
            return new CricketMatchSlideFragment().newInstance(x.get(position));
        }

        @Override
        public int getCount() {
            return x.size();
        }
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

    public static List<CricketMatch> parseJSON(String JSONResult){

        List<CricketMatch> cm = new ArrayList<CricketMatch>();
        try{
            JSONObject jO = new JSONObject(JSONResult);
            JSONArray jsonArray = jO.getJSONArray("data");
            if( jsonArray.length()==0){
                Log.d("Parse", "Result is empty");
            }
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject jsonObject=jsonArray.getJSONObject(i);
                CricketMatch cricketMatch = new CricketMatch(jsonObject.getInt("unique_id"),jsonObject.getString("description"));
                cm.add(cricketMatch);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        List<CricketMatch> reverse = new ArrayList<CricketMatch>();

        reverse.add(cm.get(cm.size()-2));
        reverse.add(cm.get(cm.size()-1));

        for (int i=cm.size()-3; i>=0; i--){
            reverse.add(cm.get(i));
        }

        return reverse;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
