package fi.jamk.k8958.basicui2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView)
                findViewById(R.id.login_name);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, new String[]
                {"Pasi", "Juha", "Kari", "Jouni", "Esa", "Hannu"});
        actv.setAdapter(aa);
    }

    public void loginClicked(View view) {
        AutoCompleteTextView name = (AutoCompleteTextView) findViewById(R.id.login_name);
        EditText password = (EditText) findViewById(R.id.login_password);
        String text = name.getText().toString() + "@" + password.getText().toString();

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
