package com.forev.obsidian;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cosmetics.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cosmetics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cosmetics extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Cosmetics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Grains.
     */
    // TODO: Rename and change types and number of parameters
    public static Cosmetics newInstance(String param1, String param2) {
        Cosmetics fragment = new Cosmetics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static ArrayList<String> getArray()
    {
        return items;
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
        View view= inflater.inflate(R.layout.fragment_cosmetics, container, false);
        view.findViewById(R.id.powderButton).setOnClickListener(this);
        view.findViewById(R.id.creamButton).setOnClickListener(this);
        view.findViewById(R.id.hOilButton).setOnClickListener(this);
        view.findViewById(R.id.fWashButton).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {Toast.makeText(getActivity(), "Item Added", Toast.LENGTH_SHORT).show();

        switch (v.getId())
        {
            case R.id.fWashButton:
            if(!items.contains("Face wash"))
                    items.add("Face wash");
                break;
            case R.id.hOilButton:
                if(!items.contains("Hair Oil"))
                    items.add("Hair Oil");
                break;
            case R.id.creamButton:
                if(!items.contains("Cream"))
                    items.add("Cream");
                break;
            case R.id.powderButton:
                if(!items.contains("Powder"))
                    items.add("Powder");
                break;
        }

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
    static ArrayList<String> items = new ArrayList<>();
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
