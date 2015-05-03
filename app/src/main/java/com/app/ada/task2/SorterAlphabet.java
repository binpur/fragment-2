package com.app.ada.task2;

import android.nfc.Tag;
import android.util.Log;

import java.util.Comparator;


public class SorterAlphabet implements Comparator<MyListItem> {

    private final int ASCENDING_ORDER = -1;
    int order;

    public SorterAlphabet() {//If not specified, the default order is ascending order
        this.order= ASCENDING_ORDER;
    }

    public SorterAlphabet(int order) {
        this.order = order;
    }

    @Override
    public int compare(MyListItem lhs, MyListItem rhs) {
        int result = lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
        if(result==0)
            return 0;
        else if(result<0)
            return order;
        else
            return -order;

    }
}
