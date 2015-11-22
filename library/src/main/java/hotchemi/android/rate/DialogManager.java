package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import static hotchemi.android.rate.IntentHelper.createIntentForGooglePlay;
import static hotchemi.android.rate.PreferenceHelper.setAgreeShowDialog;
import static hotchemi.android.rate.PreferenceHelper.setRemindInterval;
import static hotchemi.android.rate.Utils.getDialogBuilder;

final class DialogManager {

    private DialogManager() {
    }

    static Dialog create(final Context context, DialogOptions options) {
        AlertDialog.Builder builder = getDialogBuilder(context);
        if(options.getMessageText() == null) {
            builder.setMessage(options.getMessageResId());
        }
        else{
            builder.setMessage(options.getMessageText());
        }

        if (options.shouldShowTitle()){
            if(options.getTitleText() == null) {
                builder.setTitle(options.getTitleResId());
            } else{
                builder.setTitle(options.getTitleText());
            }
        }

        builder.setCancelable(options.getCancelable());

        View view = options.getView();
        if (view != null) builder.setView(view);

        final OnClickButtonListener listener = options.getListener();

        DialogInterface.OnClickListener positiveClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(createIntentForGooglePlay(context));
                setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        };

        DialogInterface.OnClickListener neutralClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setRemindInterval(context);
                if (listener != null) listener.onClickButton(which);
            }
        };

        DialogInterface.OnClickListener negativeClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        };

        if(options.getPositiveText() == null){
            builder.setPositiveButton(options.getTextPositiveResId(), positiveClickListener);
        }
        else{
            builder.setPositiveButton(options.getPositiveText(), positiveClickListener);
        }

        if (options.shouldShowNeutralButton()) {
            if(options.getNeutralText() == null){
                builder.setNeutralButton(options.getTextNeutralResId(), neutralClickListener);
            }
            else{
                builder.setNeutralButton(options.getNeutralText(), neutralClickListener);
            }
        }

        if(options.getNegativeText() == null){
            builder.setNegativeButton(options.getTextNegativeResId(), negativeClickListener);
        }
        else{
            builder.setNegativeButton(options.getNegativeText(), negativeClickListener);
        }

        return builder.create();
    }

}