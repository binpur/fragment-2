package com.app.ada.task2;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyListItem {

    private String TAG = MyListItem.class.getCanonicalName();
    private String iconResId;
    private String title;
    private String description;
    private String date;
    private Date dateObject;
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public MyListItem(String iconResId, String title, String description, String date) {
        this.iconResId = iconResId;
        this.title = title;
        this.description = description;
        this.date = date;

    }

    public String getIconResId() {
        return iconResId;
    }

    public void setIconResId(String iconResId) {
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Date getDateObject()
    {
        if(dateObject==null){
            try {
                this.dateObject = format.parse(date);
                Log.i(TAG, "dateObject=" + String.valueOf(dateObject == null));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateObject;
    }

    public void setDateObject(Date dateObject) {
        this.dateObject = dateObject;
    }
}
