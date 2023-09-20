package com.rakibofc.onlinestoreexplorer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.widget.Toast;

import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.utility.Constants;

public class ConnectionReceiver extends BroadcastReceiver {

    private boolean isNoInternetMessageSent;

    @Override
    public void onReceive(Context context, Intent intent) {

        String actionOfIntent = intent.getAction();
        boolean isConnected = isNetworkAvailable(context);

        if (actionOfIntent != null && actionOfIntent.equals(Constants.CONNECTIVITY_ACTION)) {

            if (isConnected && isNoInternetMessageSent) {

                // Toast restored message if "no_connection_msg" already shown otherwise no need to show
                Toast.makeText(context, R.string.internet_connection_restored_msg, Toast.LENGTH_SHORT).show();
                isNoInternetMessageSent = false;

            } else if (!isConnected) {
                Toast.makeText(context, R.string.no_connection_msg, Toast.LENGTH_SHORT).show();
                isNoInternetMessageSent = true;
            }
        }
    }

    /**
     * Checks if the device has an active network connection.
     *
     * @param context The context of the application or activity.
     * @return {@code true} if a network connection is available,
     * {@code false} otherwise.
     */
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Network network = connectivityManager.getActiveNetwork();
        if (network == null) return false;

        NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(network);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));

    }
}
