package fi.jamk.k8958.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectButtonClicked(View view) {
        // Find radiogroup
        RadioGroup rg = (RadioGroup) findViewById(R.id.myRadioGroup);
        int id = rg.getCheckedRadioButtonId();
        // Find button
        RadioButton rb = (RadioButton) findViewById(id);
        // Get radiobutton text
        String text = (String) rb.getText();
        // Toast message to screen
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
