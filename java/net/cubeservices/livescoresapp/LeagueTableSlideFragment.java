package net.cubeservices.livescoresapp;

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
 * {@link LeagueTableSlideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeagueTableSlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeagueTableSlideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static LeagueTable leagueTable;

    private OnFragmentInteractionListener mListener;

    public LeagueTableSlideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LeagueTableSlideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeagueTableSlideFragment newInstance(LeagueTable param1) {
        LeagueTableSlideFragment fragment = new LeagueTableSlideFragment();

        leagueTable = param1;

        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_league_table_slide, container, false);

        TextView position = (TextView) rootView.findViewById(R.id.title);
        TextView teamName = (TextView) rootView.findViewById(R.id.teamName);
        TextView games = (TextView) rootView.findViewById(R.id.numberOfGames);
        TextView wins = (TextView) rootView.findViewById(R.id.numberOfWins);
        TextView draws = (TextView) rootView.findViewById(R.id.numberOfDraws);
        TextView losses = (TextView) rootView.findViewById(R.id.numberOfLosses);
        TextView points = (TextView) rootView.findViewById(R.id.numberOfPoints);
        TextView goals = (TextView) rootView.findViewById(R.id.numberOfGoals);
        TextView goalsAgainst = (TextView) rootView.findViewById(R.id.numberOfGoalsAgainst);
        TextView goalsDifference = (TextView) rootView.findViewById(R.id.numberOfGoalsDifference);

        position.setText("#"+leagueTable.position);
        teamName.setText(leagueTable.teamName);
        games.setText(leagueTable.playedGames+"");
        wins.setText(leagueTable.wins+"");
        draws.setText(leagueTable.draws+"");
        losses.setText(leagueTable.losses+"");
        points.setText(leagueTable.points+"");
        goals.setText(leagueTable.goals+"");
        goalsAgainst.setText(leagueTable.goalsAgainst+"");
        goalsDifference.setText(leagueTable.goalsDifference+"");

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
        new LeagueTableActivity().leftClicked(view);
    }

    public void rightClicked(View view){
        new LeagueTableActivity().rightClicked(view);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
