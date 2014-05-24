package hotchemi.android.rate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

class PreferenceUtils {

    private static final String PREF_FILE_NAME = "Android-Rate";

    private PreferenceUtils() {
    }

    static SharedPreferences getPreferences(final Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    static Editor getPreferencesEditor(final Context context) {
        return getPreferences(context).edit();
    }

    /**
     * Clear data in shared preferences.<br>
     * This API is called when the rate dialog is approved or canceled.
     *
     * @param context context
     */
    static void clearSharedPreferences(final Context context) {
        final SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.remove(Constants.PREF_KEY_INSTALL_DATE);
        editor.remove(Constants.PREF_KEY_LAUNCH_TIMES);
        editor.commit();
    }

    /**
     * Set agree flag about show dialog.<br/>
     * If it is false, rate dialog will never shown unless data is cleared.
     *
     * @param context context
     * @param isAgree agree with showing rate dialog
     */
    static void setAgreeShowDialog(final Context context, final boolean isAgree) {
        final SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(Constants.PREF_KEY_IS_AGREE_SHOW_DIALOG, isAgree);
        editor.commit();
    }

}