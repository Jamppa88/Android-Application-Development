package fi.jamk.k8958.notificationsandmenus;

import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ChangeTextDialogFragment.ChangeTextDialogListener {

    private int notification_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    //Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call:
                //Toast.makeText(getApplicationContext(), R.string.call_toast, Toast.LENGTH_SHORT).show();

                // create action intent to open application in device (ResultActivity)
                Intent contentIntent = new Intent(this,ResultActivity.class);
                // Adds the back stack - see manifest
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                // adds the Intent to the top of the stack
                stackBuilder.addParentStack(ResultActivity.class);
                // adds the Intent to the top of the stack
                stackBuilder.addNextIntent(contentIntent);
                // gets a PendingIntent containing the entire back stack
                PendingIntent contentPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                // Lets make a notification :)
                Notification notification = new NotificationCompat.Builder(this)
                        .setCategory(Notification.CATEGORY_MESSAGE)
                        .setContentTitle("Here be title")
                        .setContentText("Here be content")
                        .setSmallIcon(R.drawable.ic_action_call)
                        .setAutoCancel(true)
                        .setContentIntent(contentPendingIntent)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(notification_id, notification);
                notification_id++;
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), R.string.search_toast, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_pie:
                //Toast.makeText(getApplicationContext(), R.string.pie_toast, Toast.LENGTH_SHORT).show();
                PieDialogFragment pDialog = new PieDialogFragment();
                pDialog.show(getFragmentManager(), "pie");
                return true;
            case R.id.changeText:
                ChangeTextDialogFragment cDialog = new ChangeTextDialogFragment();
                cDialog.show(getFragmentManager(), "change");
                return true;
            case R.id.action_quit:
                Toast.makeText(getApplicationContext(), R.string.quit_text, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String changeText) {
        TextView tView = (TextView) findViewById(R.id.hello);
        tView.setText(changeText);
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), R.string.cancel_toast, Toast.LENGTH_SHORT).show();
    }
}
