package com.app.ada.task2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

public class NestedListFragment extends Fragment implements AbsListView.OnItemClickListener {
    private String TAG = NestedListFragment.class.getCanonicalName();
    /*
    Arguments to instantiate a fragment
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SORT_STYLE = "sort_style";
    private int sectionNumber, sortStyle;

    private OnFragmentInteractionListener mListener;
    private List<MyListItem> mSelectedList;
    private AbsListView mListView;
    private ListAdapter mAdapter;

    public static NestedListFragment newInstance(int sectionNumber, int sortStyle) {
        NestedListFragment fragment = new NestedListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_SORT_STYLE, sortStyle);
        fragment.setArguments(args);
        return fragment;
    }

    public NestedListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Log.i(TAG,"sectionNumber="+sectionNumber);
            sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            sortStyle = getArguments().getInt(ARG_SORT_STYLE);
        }

        Data data = new Data(getActivity());
        mSelectedList= data.getListByIndex(sectionNumber);
        switch(sortStyle){
            case(Constants.SORT_BY_ALPHABET):
                Collections.sort(mSelectedList, new SorterAlphabet());break;
            case(Constants.SORT_BY_DATE):
                 Collections.sort(mSelectedList, new SorterDate());break;
        }
        mAdapter = new MyListAdapter(getActivity(), mSelectedList);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);
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
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
