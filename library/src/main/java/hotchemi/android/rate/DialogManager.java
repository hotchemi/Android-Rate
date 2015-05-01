package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;

final class DialogManager {
    private static final String GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending";

    private DialogManager() {
    }

    static Dialog create(final Context context, final boolean isShowNeutralButton,
                         final boolean isShowTitle, final OnClickButtonListener listener,
                         final View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.rate_dialog_message);
        if (view != null) builder.setView(view);
        builder.setPositiveButton(R.string.rate_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String packageName = context.getPackageName();
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, UriHelper.getGooglePlay(packageName));
                    if (UriHelper.isPackageExists(context, GOOGLE_PLAY_PACKAGE_NAME)) {
                        intent.setPackage(GOOGLE_PLAY_PACKAGE_NAME);
                    }
                    context.startActivity(intent);
                    PreferenceHelper.setAgreeShowDialog(context, false);
                    if (listener != null) listener.onClickButton(which);
                } 
                catch (ActivityNotFoundException ex) {
                    Toast.makeText(this, "Please install Google Play Store first.", Toast.LENGTH_SHORT).show();
                }
           
            }
        });
        if (isShowNeutralButton) {
            builder.setNeutralButton(R.string.rate_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setRemindInterval(context);
                    if (listener != null) listener.onClickButton(which);
                }
            });
        }
        builder.setNegativeButton(R.string.rate_dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        });
        final AlertDialog dialog = builder.create();
        if (!isShowTitle) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } else {
            dialog.setTitle(R.string.rate_dialog_title);
        }
        return dialog;
    }

}
