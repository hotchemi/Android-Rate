package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

final class DialogManager {

    private DialogManager() {
    }

    static Dialog create(final Context context, final boolean isShowNeutralButton, final OnClickButtonListener listener,final int requestCoce) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.rate_dialog_title);
        builder.setMessage(R.string.rate_dialog_message);
        builder.setPositiveButton(R.string.rate_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String packageName = context.getPackageName();
                Intent intent = new Intent(Intent.ACTION_VIEW, UriHelper.getGooglePlay(packageName));
                context.startActivity(intent);
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(requestCoce,which);
            }
        });
        if (isShowNeutralButton) {
            builder.setNeutralButton(R.string.rate_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setRemindInterval(context);
                    if (listener != null) listener.onClickButton(requestCoce,which);
                }
            });
        }
        builder.setNegativeButton(R.string.rate_dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(requestCoce,which);
            }
        });
        return builder.create();
    }

}