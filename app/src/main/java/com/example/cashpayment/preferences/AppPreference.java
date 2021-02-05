package com.example.cashpayment.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * All Shared preference for session maintain will manage here.
 *
 */
@SuppressWarnings({"SameParameterValue", "unused", "JavaDoc"})
public class AppPreference {

    private static com.example.cashpayment.preferences.AppPreference prefManager;
    private SharedPreferences prefs;

    /**
     * Create Shared Preference.
     *
     * @param context
     */
    private AppPreference(Context context) {
        String PREFS_FILE = context.getPackageName();
        prefs = context.getSharedPreferences(PREFS_FILE, 0);
    }

    /**
     * Get Instance of Shared Preference.
     *
     * @param context
     * @return
     */
    public static com.example.cashpayment.preferences.AppPreference getInstance(Context context) {
        if (prefManager == null) {
            prefManager = new com.example.cashpayment.preferences.AppPreference(context);
        }
        return prefManager;
    }

    /**
     * Clear Shared Pref. Object.
     */
    public void clear() {
        if (prefs != null) {
            prefs.edit().clear().commit();
        }
    }

    /**
     * Check whether Particular key Exist or not in Shared Pref.
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return prefs.contains(key);
    }

    /**
     * Remove Shared Pref for Particular Key.
     *
     * @param key
     */
    public void remove(String key) {
        if (prefs != null) {
            prefs.edit().remove(key).commit();
        }
    }

    /**
     * Put Integer value in Shared Pref of Particular Key.
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        if (prefs != null) {
            prefs.edit().putInt(key, value).commit();
        }
    }

    /**
     * Get Integer value from Shared Pref of Particular Key.
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int... defValue) {
        int value = defValue.length > 0 ? defValue[0] : 0;
        if (prefs != null) {
            return prefs.getInt(key, value);
        }
        return value;
    }

    /**
     * Put Float value in Shared Pref of Particular Key.
     * @param key
     * @param value
     */
    public void putFloat(String key, float value) {
        if (prefs != null) {
            prefs.edit().putFloat(key, value).commit();
        }
    }

    /**
     * Get Float value from Shared Pref of Particular Key.
     * @param key
     * @param defValue
     * @return
     */
    public float getFloat(String key, float... defValue) {
        float value = defValue.length > 0 ? defValue[0] : 0;
        if (prefs != null) {
            return prefs.getFloat(key, value);
        }
        return value;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        if (prefs != null) {
            prefs.edit().putLong(key, value).commit();
        }
    }

    /**
     * Get Long value from Shared Pref of Particular Key.
     * @param key
     * @param defValue
     * @return
     */
    public long getLong(String key, long... defValue) {
        long value = defValue.length > 0 ? defValue[0] : 0;
        if (prefs != null) {
            return prefs.getLong(key, value);
        }
        return value;
    }

    /**
     * Put String value in Shared Pref of Particular Key.
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        if (prefs != null) {
            prefs.edit().putString(key, value).commit();
        }
    }


    /**
     * Get String value from Shared Pref of Particular Key.
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String... defValue) {
        String value = defValue.length > 0 ? defValue[0] : null;
        if (prefs != null) {
            return prefs.getString(key, value);
        }
        return value;
    }

    /**
     * Put boolean value in Shared Pref of Particular Key.
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        if (prefs != null) {
            prefs.edit().putBoolean(key, value).commit();
        }
    }


    /**
     * Get boolean value from Shared Pref of Particular Key.
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(String key, boolean... defValue) {
        boolean value = defValue.length > 0 && defValue[0];
        if (prefs != null) {
            return prefs.getBoolean(key, value);
        }
        return value;
    }


}

