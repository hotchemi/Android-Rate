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
     * @param context context
     */
    static Dialog createDialog(final Context context, final DialogLabels labels) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        int titleId = labels.title == 0 ? R.string.rate_dialog_title : labels.title;
        builder.setTitle(titleId);
        int messageId = labels.message == 0 ? R.string.rate_dialog_message : labels.message;
        builder.setMessage(messageId);
        int positiveButtonId = labels.positiveButton == 0 ? R.string.rate_dialog_ok : labels.positiveButton;
        builder.setPositiveButton(positiveButtonId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String packageName = context.getPackageName();
                Intent intent = new Intent(Intent.ACTION_VIEW, UriUtils.getGooglePlayUri(packageName));
                context.startActivity(intent);
                PreferenceUtils.setAgreeShowDialog(context, false);
            }
        });
        int neutralButtonId = labels.neutralButton == 0 ? R.string.rate_dialog_cancel : labels.neutralButton;
        builder.setNeutralButton(neutralButtonId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceUtils.clearSharedPreferences(context);
            }
        });
        int negativeButtonId = labels.negativeButton == 0 ? R.string.rate_dialog_no : labels.negativeButton;
        builder.setNegativeButton(negativeButtonId, new DialogInterface.OnClickListener() {
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