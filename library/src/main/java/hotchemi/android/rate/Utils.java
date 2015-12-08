package hotchemi.android.rate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

final class Utils {

    private Utils() {
    }

    static boolean underHoneyComb() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
    }

    static boolean isLollipop() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP || Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    static int getDialogTheme(int defaultStyle) {
        return defaultStyle==0 && isLollipop() ? R.style.CustomLollipopDialogStyle : defaultStyle;
    }

    @SuppressLint("NewApi")
    static AlertDialog.Builder getDialogBuilder(Context context, int defaultStyle) {
        if (underHoneyComb()) {
            return new AlertDialog.Builder(context);
        } else {
            return new AlertDialog.Builder(context, getDialogTheme(defaultStyle));
        }
    }

}
