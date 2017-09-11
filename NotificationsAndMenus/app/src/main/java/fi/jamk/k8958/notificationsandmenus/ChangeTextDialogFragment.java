package fi.jamk.k8958.notificationsandmenus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by K8958 on 11.9.2017.
 */

public class ChangeTextDialogFragment extends DialogFragment {
    // Create a new interface for class to implement
    public interface ChangeTextDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String changedText);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface
    ChangeTextDialogListener iListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify the host activity implements the callback interface
        try {
            iListener = (ChangeTextDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ChangeTextDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView)
            .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText editText = (EditText) dialogView.findViewById(R.id.editText);
                    String changedText = editText.getText().toString();
                    // Send the positive button event back to the host activity
                    iListener.onDialogPositiveClick(ChangeTextDialogFragment.this, changedText);
                }
            })
            .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    iListener.onDialogNegativeClick(ChangeTextDialogFragment.this);
                }
            });

        return builder.create();
    }
}
