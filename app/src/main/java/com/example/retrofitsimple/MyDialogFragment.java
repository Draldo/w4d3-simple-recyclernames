package com.example.retrofitsimple;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by admin on 8/10/2016.
 */
public class MyDialogFragment extends DialogFragment {

    private static final String TAG = MyDialogFragment.class.getSimpleName() + "TAG_";
    private TextView mName;

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String pass);
    }

    public static MyDialogFragment newInstance(String name, int pos) {
        MyDialogFragment frag = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("position", pos);
        frag.setArguments(args);
        return frag;
    }

    DialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String namearg = getArguments().getString("name");

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_fragment, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText pass = (EditText) view.findViewById(R.id.password_text);
                        mListener.onDialogPositiveClick(MyDialogFragment.this, pass.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        mName = (TextView) view.findViewById(R.id.username_text);
        mName.setText(namearg);

        return builder.create();

    }

}
