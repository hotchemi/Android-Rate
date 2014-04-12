package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * @author Shintaro Katafuchi
 */
class DialogManager {

    private DialogManager() {
    }

    /**
     * Create rate dialog.
     *
     * @param context             context
     * @param isShowNeutralButton whether neutral button show or not
     */
    static Dialog create(final Context context, final boolean isShowNeutralButton) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.rate_dialog_title);
        builder.setMessage(R.string.rate_dialog_message);
        builder.setPositiveButton(R.string.rate_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String packageName = context.getPackageName();
                Intent intent = new Intent(Intent.ACTION_VIEW, UriUtils.getGooglePlayUri(packageName));
                context.startActivity(intent);
                PreferenceUtils.setAgreeShowDialog(context, false);
            }
        });
        if (isShowNeutralButton) {
            builder.setNeutralButton(R.string.rate_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceUtils.clearSharedPreferences(context);
                }
            });
        }
        builder.setNegativeButton(R.string.rate_dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceUtils.setAgreeShowDialog(context, false);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PreferenceUtils.clearSharedPreferences(context);
            }
        });
        return builder.create();
    }

}