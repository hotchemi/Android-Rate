package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import static hotchemi.android.rate.PreferenceUtils.clearSharedPreferences;
import static hotchemi.android.rate.PreferenceUtils.setAgreeShowDialog;
import static hotchemi.android.rate.PreferenceUtils.setRemindInterval;
import static hotchemi.android.rate.UriUtils.getGooglePlayUri;

final class DialogManager {

    private DialogManager() {
    }

    static Dialog create(final Context context, final boolean isShowNeutralButton) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.rate_dialog_title);
        builder.setMessage(R.string.rate_dialog_message);
        builder.setPositiveButton(R.string.rate_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String packageName = context.getPackageName();
                Intent intent = new Intent(Intent.ACTION_VIEW, getGooglePlayUri(packageName));
                context.startActivity(intent);
                setAgreeShowDialog(context, false);
            }
        });
        if (isShowNeutralButton) {
            builder.setNeutralButton(R.string.rate_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setRemindInterval(context);
                }
            });
        }
        builder.setNegativeButton(R.string.rate_dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setAgreeShowDialog(context, false);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                clearSharedPreferences(context);
            }
        });
        return builder.create();
    }

}