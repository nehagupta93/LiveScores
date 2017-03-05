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
 * {@link CricketMatchSlideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CricketMatchSlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CricketMatchSlideFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private static CricketMatch cricketMatch;

    private OnFragmentInteractionListener mListener;

    public CricketMatchSlideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CricketMatchSlideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CricketMatchSlideFragment newInstance(CricketMatch param1) {
        CricketMatchSlideFragment fragment = new CricketMatchSlideFragment();

        cricketMatch = param1;

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
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cricket_match_slide, container, false);

        TextView team1 = (TextView) rootView.findViewById(R.id.team1);
        TextView team2 = (TextView) rootView.findViewById(R.id.team2);

        team1.setText(cricketMatch.line1);
        team2.setText(cricketMatch.line2);

        final int id = cricketMatch.unique_id;

        TextView scoreLink = (TextView) rootView.findViewById(R.id.scoreLink);

        scoreLink.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent i = new Intent(rootView.getContext(), CricketScoreActivity.class);
                        i.putExtra("id", id);
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
        new CricketFragment().left(view);
    }

    public void rightClicked(View view){
        new CricketFragment().right(view);
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
