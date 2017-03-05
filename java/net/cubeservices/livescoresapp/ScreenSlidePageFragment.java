package net.cubeservices.livescoresapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScreenSlidePageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScreenSlidePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScreenSlidePageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static FootballSeason footballSeasons;

    private OnFragmentInteractionListener mListener;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ScreenSlidePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScreenSlidePageFragment newInstance(FootballSeason param1) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();

        footballSeasons = param1;

        /*Bundle args = new Bundle();
        args.put(ARG_PARAM1, param1);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        TextView caption = (TextView) rootView.findViewById(R.id.caption);
        TextView day = (TextView) rootView.findViewById(R.id.vs);
        TextView numberOfTeams = (TextView) rootView.findViewById(R.id.team1);
        TextView numberOfGames = (TextView) rootView.findViewById(R.id.team2);
        caption.setText(footballSeasons.caption);
        numberOfTeams.setText(footballSeasons.numberOfTeams+"");
        numberOfGames.setText(footballSeasons.numberofGames+"");
        day.setText("DAY "+footballSeasons.currentMatchDay+" OF "+footballSeasons.numberOfMatchDays);

        TextView fixtureLink = (TextView) rootView.findViewById(R.id.scoreLink);
        TextView tableLink = (TextView) rootView.findViewById(R.id.tableLink);

        fixtureLink.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent i = new Intent(rootView.getContext(), FootballFixturesActivity.class);
                        i.putExtra("seasonId", footballSeasons.id);
                        i.putExtra("currentMatchday", footballSeasons.currentMatchDay);
                        startActivity(i);
                    }
                }
        );

        tableLink.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent i = new Intent(rootView.getContext(), LeagueTableActivity.class);
                        i.putExtra("seasonId", footballSeasons.id);
                        i.putExtra("currentMatchday", footballSeasons.currentMatchDay);
                        startActivity(i);
                    }
                }
        );

        ImageButton leftButton = (ImageButton) rootView.findViewById(R.id.leftButton);
        ImageButton rightButton = (ImageButton) rootView.findViewById(R.id.rightButton);

        leftButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        leftClicked(v);
                    }
                }
        );

        rightButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        rightClicked(v);
                    }
                }
        );

        return rootView;
    }

    public void leftClicked(View view){
        new FootballFragment().left(view);
    }

    public void rightClicked(View view){
        new FootballFragment().right(view);
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
