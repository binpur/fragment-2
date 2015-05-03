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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
The item in grid view only contains a thumbnail image
 */
public class MyGridAdapter extends ArrayAdapter {
    private Context context;
    private String packageName;
    private String TAG = MyListAdapter.class.getCanonicalName();

    public MyGridAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
        this.packageName = context.getPackageName();
    }

    private class ViewHolder {
        ImageView thumbNailImage;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        MyListItem myListItem = (MyListItem) getItem(position);
        View viewToUse = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            viewToUse = inflater.inflate(R.layout.grid_item, null);
            holder.thumbNailImage = (ImageView) viewToUse.findViewById(R.id.icon);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }
        int iconId = context.getResources().getIdentifier(myListItem.getIconResId(), "drawable", packageName);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),iconId);
        Bitmap thumbNailImage = ThumbnailUtils.extractThumbnail(icon, Constants.THUMBNAIL_SIZE, Constants.THUMBNAIL_SIZE);
        holder.thumbNailImage.setImageBitmap(thumbNailImage);
        return viewToUse;
    }
}
