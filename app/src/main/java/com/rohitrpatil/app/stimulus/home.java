package com.rohitrpatil.app.stimulus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.robertsimoes.quoteable.Quoteable;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.data;
import static android.R.attr.key;
import static android.R.attr.tag;

public class home extends AppCompatActivity {

    private final String BASE_URL="https://wisdomapi.herokuapp.com/v1/random";
    Button mNext;
    TextView mTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         mTxt= (TextView)findViewById(R.id.textView3);
        mNext = (Button)findViewById(R.id.btn);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Networking();
            }
        });
    }

    protected void Networking(){
        AsyncHttpClient client= new AsyncHttpClient();
        client.get(BASE_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                DataModel dataModel= DataModel.fromJSON(response);
                mTxt.setText(dataModel.getContent());
                Log.d( "zen","json:"+dataModel.getAuthor().toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                Log.d( "zen","FAIL:"+e.toString());
            }
        });
    }
}
