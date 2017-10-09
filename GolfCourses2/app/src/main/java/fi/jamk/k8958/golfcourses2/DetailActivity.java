package fi.jamk.k8958.golfcourses2;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // get and show course data
        try {

            // get json object from launching intent
            final JSONObject jsonObject = new JSONObject(getIntent().getStringExtra("course"));

            // set title
            getSupportActionBar().setTitle("SGKY: " + jsonObject.getString("course"));
            // image
            ImageView imageView = (ImageView) findViewById(R.id.courseImageView2);
            Glide.with(this).load("http://ptm.fi/jamk/android/golfcourses/" + jsonObject.getString("image")).into(imageView);
            // address
            TextView addressTextView = (TextView) findViewById(R.id.courseAddressTextView);
            addressTextView.setText(jsonObject.getString("address"));
            // phone
            TextView phoneTextView = (TextView) findViewById(R.id.coursePhoneTextView);
            phoneTextView.setText(jsonObject.getString("phone"));
            // email
            TextView emailTextView = (TextView) findViewById(R.id.courseEmailTextView);
            emailTextView.setText(jsonObject.getString("email"));
            // info
            TextView infoTextView = (TextView) findViewById(R.id.courseInfoTextView);
            infoTextView.setText(jsonObject.getString("text"));
            // web
            TextView webTextView = (TextView) findViewById(R.id.courseWebTextView);
            webTextView.setText(jsonObject.getString("web"));
            // map
            TextView mapTextView = (TextView) findViewById(R.id.courseMapTextView);
            mapTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            mapTextView.setText("Näytä kartalla");
            mapTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    try {
                        // we need to parse below string content
                        // Uri.parse("geo:<" + myLatitude + ">,<" + myLongitude + ">?q=<" + myLatitude + ">,<" + myLongitude + ">(" + labelLocation + ")"));
                        Double lat = jsonObject.getDouble("lat");
                        Double lng = jsonObject.getDouble("lng");
                        String course = jsonObject.getString("course");
                        intent.setData(Uri.parse("geo:<"+lat+">,<"+lng+">?q=<"+lat+">,<"+lng+">("+course+")"));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
