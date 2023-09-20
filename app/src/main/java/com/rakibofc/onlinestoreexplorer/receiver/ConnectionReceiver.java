package com.rakibofc.onlinestoreexplorer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.rakibofc.onlinestoreexplorer.helper.NetworkStateChangeListener;
import com.rakibofc.onlinestoreexplorer.utility.Constants;

public class ConnectionReceiver extends BroadcastReceiver {

    private NetworkStateChangeListener networkStateChangeListener;

    public void setNetworkStateChangeListener(NetworkStateChangeListener listener) {
        this.networkStateChangeListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String actionOfIntent = intent.getAction();
        boolean isConnected = isNetworkAvailable(context);

        if (actionOfIntent != null && actionOfIntent.equals(Constants.CONNECTIVITY_ACTION)) {
            if (networkStateChangeListener != null) {
                networkStateChangeListener.onNetworkStateChanged(isConnected);
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
