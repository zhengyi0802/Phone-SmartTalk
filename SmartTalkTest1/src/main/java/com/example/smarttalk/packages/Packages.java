package com.example.smarttalk.packages;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public class Packages {

    private Context mContext;
    private ArrayList<PInfo> apps;

    public Packages(Context context) {
        mContext = context;
    }

    public ArrayList<PInfo> getPackages() {
        apps = getInstalledApps(false);
        /* false = no system packages */
        final int max = apps.size();
        for (int i=0; i<max; i++) {
            apps.get(i).prettyPrint();
        }
        return apps;
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            PInfo newInfo = new PInfo(mContext, p);
            res.add(newInfo);
        }
        return res;
    }

}
