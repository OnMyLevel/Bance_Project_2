package com.example.christanismerilbanzouzi.bance_project.Fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.R;


public class AdresseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEXTE = "texte";

    // TODO: Rename and change types of parameters
    private String mtexte;
    private TextView info;
    private OnFragmentInteractionListener mListener;


    public AdresseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AdresseFragment newInstance(String text) {
        AdresseFragment fragment = new AdresseFragment();
        Bundle args = new Bundle();
        args.putString(TEXTE, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mtexte= getArguments().getString(TEXTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adresse, container, false);
        info = (TextView) view.findViewById(R.id.adresseinfo);
        info.setText(Common.currentUser.getAdresse().toString());

        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
