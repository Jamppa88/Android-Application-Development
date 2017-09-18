package fi.jamk.k8958.loadfrominternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;
    // Path to images
    private final String PATH = "http://student.labranet.jamk.fi/~K8958/ttms0500-harkat/h03/kuvat/";
    // Image names
    private String[] images = {"talo1.jpg", "talo2.jpg", "talo3.jpg", "talo4.jpg", "talo5.jpg"};
    // Which image is visible
    private int imageIndex;
    // AsyncTask to load a new image
    private DownloadImageTask task;
    // swipe down and up values
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get views
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // start showing images
        imageIndex = 0;
        showImage();
    }

    public void showImage() {
        // create a new AsyncTask object
        task = new DownloadImageTask();
        // start asynctask
        task.execute(PATH + images[imageIndex]);
    }

    // Method gets called when user performs any touch event on screen
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if (x1 < x2) {
                    imageIndex--;
                    if (imageIndex < 0) imageIndex = images.length -1;
                } else {
                    imageIndex++;
                    if (imageIndex > (images.length-1)) imageIndex = 0;
                }
                showImage();
                break;
        }
        return false;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        // This is done in UI thread, nothing this time
        @Override
        protected void onPreExecute() {
            // Show loading progressBar
            progressBar.setVisibility(View.VISIBLE);
        }

        // This is done in background thread, load image and pass it to onPostExecute
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL imageUrl;
            Bitmap bitmap = null;
            try {
                imageUrl = new URL(urls[0]);
                InputStream in = imageUrl.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception ex) {
                Log.e("<<LoadImage>>", ex.getMessage());
            }

            return bitmap;
        }

        // This is done in UI thread
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
            textView.setText("Image " + (imageIndex + 1) + "/" + images.length);
            // hide loading progressBar
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
