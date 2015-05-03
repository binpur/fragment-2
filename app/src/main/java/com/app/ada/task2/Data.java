package com.app.ada.task2;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private final String TAG = Data.class.getCanonicalName();
    private final String[] fileName = new String[]{"fruit.json","vegetable.json","animal.json"};
    private List resultArray;

    public Data(Context context) {


        resultArray = new ArrayList();
        try {
            int nbFiles = fileName.length;
            AssetManager assetManager = context.getAssets();
            InputStream imsArray[] = new InputStream[nbFiles];
            Reader readerArray[] = new Reader[nbFiles];
            resultArray = new ArrayList<>();
            Gson gson = new Gson();
            for(int i = 0; i<nbFiles;i++){
                imsArray[i] = assetManager.open(fileName[i]);
                readerArray[i] = new InputStreamReader(imsArray[i]);
                MyListItem[] result = gson.fromJson(readerArray[i] , MyListItem[].class);
                ArrayList<MyListItem> list = new ArrayList<MyListItem>();
                for( MyListItem item :result)
                    list.add(item);
                resultArray.add(list);
            }


        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<MyListItem> getListByIndex(int index){
        return (ArrayList<MyListItem>)resultArray.get(index);
    }

}
