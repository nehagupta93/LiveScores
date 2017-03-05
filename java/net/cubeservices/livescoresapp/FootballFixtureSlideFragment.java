package net.cubeservices.livescoresapp;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FootballFixtureSlideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FootballFixtureSlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballFixtureSlideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static FootballFixture footballFixture;

    private OnFragmentInteractionListener mListener;

    public FootballFixtureSlideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FootballFixtureSlideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FootballFixtureSlideFragment newInstance(FootballFixture param1) {
        FootballFixtureSlideFragment fragment = new FootballFixtureSlideFragment();

        footballFixture = param1;

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
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_football_fixture_slide, container, false);

        TextView status = (TextView) rootView.findViewById(R.id.status);
        TextView homeTeam = (TextView) rootView.findViewById(R.id.homeTeam);
        TextView homeGoals = (TextView) rootView.findViewById(R.id.homeGoals);
        TextView awayTeam = (TextView) rootView.findViewById(R.id.awayTeam);
        TextView awayGoals = (TextView) rootView.findViewById(R.id.awayGoals);

        status.setText(footballFixture.status);
        homeTeam.setText(footballFixture.homeTeam);
        awayTeam.setText(footballFixture.awayTeam);
        homeGoals.setText(footballFixture.homeTeamGoals+"");
        awayGoals.setText(footballFixture.awayTeamGoals+"");

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
        new FootballFixturesActivity().leftClicked(view);
    }

    public void rightClicked(View view){
        new FootballFixturesActivity().rightClicked(view);
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
