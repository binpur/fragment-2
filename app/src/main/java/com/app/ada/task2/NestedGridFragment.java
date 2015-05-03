package com.app.ada.task2;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.List;


public class NestedGridFragment extends Fragment implements AbsListView.OnItemClickListener {

    /*
    Arguments to instantiate a fragment
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SORT_STYLE = "sort_style";

    private int sectionNumber;
    private List<MyListItem> mSelectedList;
    private OnFragmentInteractionListener mListener;
    private GridView mListView;
    private ListAdapter mAdapter;

    public static NestedGridFragment newInstance(int sectionNumber, int sortStyle) {
        NestedGridFragment fragment = new NestedGridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NestedGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        Data data = new Data(getActivity());
        mSelectedList= data.getListByIndex(sectionNumber);
        mAdapter = new MyGridAdapter(getActivity(), mSelectedList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_grid, container, false);
        mListView = (GridView) view.findViewById(R.id.grid_view);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        return view;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onFragmentInteraction(mSelectedList.get(position));
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(MyListItem item);
    }

}
