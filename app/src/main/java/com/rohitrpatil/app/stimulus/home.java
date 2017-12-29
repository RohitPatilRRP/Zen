package com.rohitrpatil.app.stimulus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView mAthr;
    ImageButton mShare;
    String shareBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTxt= (TextView)findViewById(R.id.textView3);
        mAthr= (TextView)findViewById(R.id.athr);
        mNext = (Button)findViewById(R.id.btn);
        mShare = (ImageButton) findViewById(R.id.share);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Networking();
            }
        });

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("zen", shareBody);
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_SUBJECT, "App By RRP");
                    share.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(share, "Share Via"));
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No quote available",Toast.LENGTH_SHORT).show();
                }
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
                mAthr.setText("-"+dataModel.getAuthor());
                shareBody = dataModel.getContent() + "-" + dataModel.getAuthor() +" ,An app by RRP";
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                Log.d( "zen","FAIL:"+e.toString());
                Toast.makeText(getApplicationContext(),"Check your Internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
