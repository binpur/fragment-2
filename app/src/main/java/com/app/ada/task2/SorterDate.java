package com.app.ada.task2;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2015/5/3.
 */
public class SorterDate implements Comparator<MyListItem> {


    private String TAG = SorterDate.class.getCanonicalName();
    private final int ASCENDING_ORDER = -1;
    int order;

    public SorterDate() {//If not specified, the default order is ascending order
        this.order= ASCENDING_ORDER;

    }

    public SorterDate(int order) {
        this.order = order;
    }

    @Override
    public int compare(MyListItem lhs, MyListItem rhs) {
        Date date1=lhs.getDateObject();
        Date date2=rhs.getDateObject();
        boolean result = date2.after(date1);
        if(result)
            return order;
        else
            return -order;

    }
}
