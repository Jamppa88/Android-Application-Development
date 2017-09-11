package fi.jamk.k8958.notificationsandmenus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by K8958 on 11.9.2017.
 */

public class PieDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState) {
        // Use builder class to create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pie)
            .setMessage(R.string.dialog_content)
            .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), R.string.pie_toast, Toast.LENGTH_SHORT).show();

                }
            })
            .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), R.string.cancel_toast, Toast.LENGTH_SHORT).show();
                }
            });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
