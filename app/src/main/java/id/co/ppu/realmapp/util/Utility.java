package id.co.ppu.realmapp.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

import id.co.ppu.realmapp.R;

public class Utility {

    public static final String PREF_REALM = "RealmPref";

    public final static String INFO = "Info";
    public final static String WARNING = "Warning";

    public final static String ORDERPREF = "OrderPref";
    public final static String ORDEROBJ = "Order";
    public final static String ORDERIDPREF = "OrderIdPref";
    public final static String ORDERIDOBJ = "OrderId";
    public final static String USERPREF = "UserPref";
    public final static String USEROBJ = "User";
    public final static String REGTOKENPREF = "RegTokenPref";
    public final static String REGTOKENOBJ = "RegToken";

    public static final int PERMISSION_REQUEST_LOCATION = 0;
    public static String[] PERMISSION_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    public static final int PERMISSION_REQUEST_READPHONESTATE = 1;
    public static String[] PERMISSION_READPHONESTATE = {Manifest.permission.READ_PHONE_STATE};

    public static void disableScreen(Activity act, boolean disable) {
        if (disable) {
            act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {
            act.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
    public static void showDialog(Context ctx, String title, String msg){

//        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AppTheme_Teal_Dialog))
        new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AppTheme))
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .show();

    }

    public static void toast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    public static void showSettingsDialog(Context mContext) {
        final Context ctx = mContext;

//        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AppTheme_Teal_Dialog))
        AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AppTheme))
                .setTitle(INFO)
                .setMessage("Your location is unavailable. Please enable GPS from the Settings menu.")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        ctx.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public static void savePreference(Context ctx, String key, String value){
        SharedPreferences objPrefs = ctx.getSharedPreferences(PREF_REALM, 0); // 0 - for private mode
        SharedPreferences.Editor prefsEditor = objPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply(); //asynkron
    }

    public static void saveObjPreference(Context ctx, String key, Object value){

        if (value == null) return;

        SharedPreferences objPrefs = ctx.getSharedPreferences(PREF_REALM, 0); // 0 - for private mode
        SharedPreferences.Editor prefsEditor = objPrefs.edit();

        String json = new Gson().toJson(value);
        prefsEditor.putString(key, json);
        prefsEditor.commit();   //synkron
    }

    public static Object getObjPreference(Context ctx, String key, Class cls){
        String val = null;

        try{
            //Get Reg Token on shared pref
            SharedPreferences userPrefs = ctx.getSharedPreferences(PREF_REALM, 0); // 0 - for private mode

            Gson gson = new Gson();
            String json = userPrefs.getString(key, "");

            return new Gson().fromJson(json, cls);

        }
        catch (Exception e){
            return null;
        }
    }

    public static String getPreference(Context ctx, String key){
        String val = null;

        try{
            //Get Reg Token on shared pref
            SharedPreferences userPrefs = ctx.getSharedPreferences(PREF_REALM, 0); // 0 - for private mode
            val = userPrefs.getString(key, "");
        }
        catch (Exception e){
            return null;
        }
        return val;
    }

    public static void clearObjOnSharedPref(Context ctx, String ObjPref){
        SharedPreferences objPrefs = ctx.getSharedPreferences(ObjPref, 0); // 0 - for private mode
        SharedPreferences.Editor prefsEditor = objPrefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }
/*
    public static ArrayList<Order> getOrder(Context ctx){
        ArrayList<Order> orderData = null;

        try {
            //Get Order on shared pref
            SharedPreferences orderPrefs = ctx.getSharedPreferences(Utility.ORDERPREF, 0); // 0 - for private mode
            String sOrderId = orderPrefs.getString(Utility.ORDEROBJ, "");

            Gson gson = new GsonBuilder().create();
            GSONOrderList gsonRes = gson.fromJson(sOrderId, GSONOrderList.class);
            orderData = gsonRes.getData();
        }
        catch (Exception e){
            return null;
        }
        return orderData;
    }

    public static User getUser(Context ctx){
        User userData = null;

        try {
            //Get User on shared pref
            SharedPreferences userPrefs = ctx.getSharedPreferences(Utility.USERPREF, 0); // 0 - for private mode
            String sUser = userPrefs.getString(Utility.USEROBJ, "");

            Gson gson = new GsonBuilder().create();
            ResponseLogin gsonRes = gson.fromJson(sUser, ResponseLogin.class);
            userData = gsonRes.getData();
        }
        catch (Exception e){
            return null;
        }
        return userData;
    }
*/
    public static String getAndroidID(Context ctx){
        String _id = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
        return _id;
    }

    public static String getDeviceID(Context ctx){
        TelephonyManager tManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String _id = tManager.getDeviceId();
        return _id;
    }

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void setViewGroupEnebled(ViewGroup view, boolean enabled)
    {
        int childern = view.getChildCount();

        for (int i = 0; i< childern ; i++)
        {
            View child = view.getChildAt(i);
            if (child instanceof ViewGroup)
            {
                setViewGroupEnebled((ViewGroup) child,enabled);
            }
            child.setEnabled(enabled);
        }
        view.setEnabled(enabled);
    }
    public static void setViewGroupFocusable(ViewGroup view, boolean enabled)
    {
        int childern = view.getChildCount();

        for (int i = 0; i< childern ; i++)
        {
            View child = view.getChildAt(i);
            if (child instanceof ViewGroup)
            {
                setViewGroupFocusable((ViewGroup) child,enabled);
            }
            child.setFocusable(enabled);
        }
        view.setFocusable(enabled);
    }

}
