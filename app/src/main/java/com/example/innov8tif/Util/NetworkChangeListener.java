package com.example.innov8tif.Util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.innov8tif.Adapter.PostAdapter;
import com.example.innov8tif.MainActivity;
import com.example.innov8tif.R;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //internet is not connected
        if (!Common.IsConnectedToInternet(context)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
            builder.setView(layout_dialog);

            AppCompatButton retryBtn = layout_dialog.findViewById(R.id.retryBtn);


            // show dial
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            retryBtn.setOnClickListener(v -> {
                dialog.dismiss();
                onReceive(context, intent);

            });
        }
    }
}
