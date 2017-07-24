package hotchemi.android.rate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import static hotchemi.android.rate.IntentHelper.createIntentForAmazonAppstore;
import static hotchemi.android.rate.IntentHelper.createIntentForGooglePlay;
import static hotchemi.android.rate.PreferenceHelper.setAgreeShowDialog;
import static hotchemi.android.rate.PreferenceHelper.setRemindInterval;
import static hotchemi.android.rate.Utils.getDialogBuilder;

public class DefaultDialogManager implements DialogManager {

    static class Factory implements DialogManager.Factory {
        @Override
        public DialogManager createDialogManager(Context context, DialogOptions options) {
            return new DefaultDialogManager(context, options);
        }
    }

    private final Context context;
    private final DialogOptions options;
    private final OnClickButtonListener listener;

    protected final DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            final Intent intentToAppstore = options.getStoreType() == StoreType.GOOGLEPLAY ?
                    createIntentForGooglePlay(context) : createIntentForAmazonAppstore(context);
            context.startActivity(intentToAppstore);
            setAgreeShowDialog(context, false);
            if (listener != null) listener.onClickButton(which);
        }
    };
    protected final DialogInterface.OnClickListener negativeListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            setAgreeShowDialog(context, false);
            if (DefaultDialogManager.this.listener != null) DefaultDialogManager.this.listener.onClickButton(which);
        }
    };
    protected final DialogInterface.OnClickListener neutralListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            setRemindInterval(context);
            if (listener != null) listener.onClickButton(which);
        }
    };

    public DefaultDialogManager(final Context context, final DialogOptions options) {
        this.context = context;
        this.options = options;
        this.listener = options.getListener();
    }

    public Dialog createDialog() {
        AlertDialog.Builder builder = getDialogBuilder(context);
        builder.setMessage(options.getMessageText(context));

        if (options.shouldShowTitle()) builder.setTitle(options.getTitleText(context));

        builder.setCancelable(options.getCancelable());

        View view = options.getView();
        if (view != null) builder.setView(view);

        builder.setPositiveButton(options.getPositiveText(context), positiveListener);

        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context), neutralListener);
        }

        if (options.shouldShowNegativeButton()) {
            builder.setNegativeButton(options.getNegativeText(context), negativeListener);
        }

        return builder.create();
    }

}