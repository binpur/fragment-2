package com.app.ada.task2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
The item in list view contains a thumbnail image, a title, a date and a description
 */
public class MyListAdapter extends ArrayAdapter {
    private Context context;
    private String packageName;
    private String TAG = MyListAdapter.class.getCanonicalName();


    public MyListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.packageName = context.getPackageName();
    }

    /*
    Holder for the list items
     */
    private class ViewHolder {
        ImageView thumbNailImage;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MyListItem myListItem = (MyListItem) getItem(position);
        View viewToUse;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            viewToUse = inflater.inflate(R.layout.list_item, null);
            holder.thumbNailImage = (ImageView) viewToUse.findViewById(R.id.icon);
            holder.tvTitle = (TextView) viewToUse.findViewById(R.id.tv_title);
            holder.tvDescription = (TextView) viewToUse.findViewById(R.id.tv_description);
            holder.tvDate = (TextView) viewToUse.findViewById(R.id.tv_date);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        int iconId = context.getResources().getIdentifier(myListItem.getIconResId(), "drawable", packageName);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), iconId);
        Bitmap thumbNailImage = ThumbnailUtils.extractThumbnail(icon, Constants.THUMBNAIL_SIZE, Constants.THUMBNAIL_SIZE);
        holder.thumbNailImage.setImageBitmap(thumbNailImage);
        holder.tvTitle.setText(myListItem.getTitle());
        holder.tvTitle.setSelected(true);
        holder.tvDescription.setText(myListItem.getDescription());
        holder.tvDate.setText(myListItem.getDate());
        return viewToUse;

    }
}
