package com.rohitrpatil.app.stimulus;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.data;

/**
 * Created by Rohit Patil on 12/29/2017.
 */

public class DataModel {
    private String mContent;
    private String mAuthor;

    public static DataModel fromJSON(JSONObject jsonObject){

        try{
            DataModel dataModel = new DataModel();
            dataModel.mContent = jsonObject.getString("content");
            dataModel.mAuthor = jsonObject.getJSONArray("author").getJSONObject(4).getString("name");
            return dataModel;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    public String getContent() {
        return mContent;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
