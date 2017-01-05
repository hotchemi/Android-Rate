package hotchemi.android.rate;

import android.app.Dialog;
import android.content.Context;

public interface DialogManager {

    interface Factory {
        DialogManager createDialogManager(final Context context, final DialogOptions options);
    }

    Dialog createDialog();

}
