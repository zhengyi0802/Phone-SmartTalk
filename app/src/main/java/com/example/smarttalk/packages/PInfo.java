package com.example.smarttalk.packages;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;

public class PInfo {
    private String appname = "";
    private String pname = "";
    private String versionName = "";
    private int versionCode = 0;
    private Drawable icon;
    private ActivityInfo[] activityInfos;
    private ProviderInfo[] providerInfos;
    private ServiceInfo[] serviceInfos;

    public PInfo(Context context, PackageInfo packageInfo) {
        appname = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        pname = packageInfo.packageName;
        versionName = packageInfo.versionName;
        versionCode = packageInfo.versionCode;
        icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
        activityInfos = packageInfo.activities;
        providerInfos = packageInfo.providers;
        serviceInfos = packageInfo.services;
    }

    public String prettyPrint() {
        String str = appname + "\t" + pname + "\t" + versionName + "\t" + versionCode;
        return str;
    }

    public String getAppname() {
        return appname;
    }

    public String getPName() {
        return pname;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public ActivityInfo[] getActivityInfos() {
        return activityInfos;
    }

    public ProviderInfo[] getProviderInfos() {
        return providerInfos;
    }

    public ServiceInfo[] getServiceInfos() {
        return serviceInfos;
    }

}
