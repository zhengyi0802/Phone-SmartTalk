package com.example.smarttalk.database;

import android.util.Log;

public class Login {
    private static final String TAG = "Login";

    private BackendConnection backendConnection;
    public Login(String uri, String databasename, String username, String password, boolean isRegister) {
        Log.d(TAG, "Login()");
        String cmd;
        if (!isRegister) {
            cmd = "login&dbname=" + databasename +
                    "&username=" + username + "&password=" + password;
        } else {
            cmd = "register&dbname=" + databasename +
                    "&username=" + username + "&password=" + password;
        }
        backendConnection = new BackendConnection(uri, cmd);
    }

    public String getResponseString() {
        Log.d(TAG, "getResponseString()");
        return backendConnection.getResponseString();
    }
}
