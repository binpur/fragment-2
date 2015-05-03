package com.app.ada.task2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
Each pager contains 2 fragments(listView and gridView)
Because we cannot add child fragments in a fragment in XML file,
we use this BaseFragment as a container to dynamically add child fragments.
 */
public class BaseFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_DISPLAY_STYLE ="display_style" ;//style=0, display list. style= 1, display grid
    private static final String ARG_SORT_STYLE ="sort_style" ;//style =0 : sort by alphabet. style =1 : by time
    private int sectionNumber,displayStyle,sortStyle;

    private OnFragmentInteractionListener mListener;

    public static BaseFragment newInstance(int sectionNumber, int displayStyle, int sortStyle) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_DISPLAY_STYLE, displayStyle);
        args.putInt(ARG_SORT_STYLE, sortStyle);
        fragment.setArguments(args);
        return fragment;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            displayStyle = getArguments().getInt(ARG_DISPLAY_STYLE);
            sortStyle = getArguments().getInt(ARG_SORT_STYLE);
        }
        addInnerFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void addInnerFrag() {
        Fragment fragment;
        if(displayStyle==0)
            fragment = NestedListFragment.newInstance(sectionNumber,sortStyle);
        else
            fragment = NestedGridFragment.newInstance(sectionNumber,sortStyle);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.base_frag, fragment).commit();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
