
package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public final class RateDialogFragment extends DialogFragment implements AppRateDialog,
        DialogInterface.OnClickListener {

    public RateDialogFragment() {
    }

    public static RateDialogFragment getInstance(boolean showNeutralButton, int requestCode) {
        RateDialogFragment dialog = new RateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        bundle.putInt(KEY_REQUEST_CODE, requestCode);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        boolean isShowNeutralButton = arguments.getBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.create(getActivity(), isShowNeutralButton, this);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        PreferenceHelper.clearSharedPreferences(getActivity());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int requestCode = getArguments().getInt(KEY_REQUEST_CODE);
        Intent result = new Intent();
        result.putExtra(AppRate.EXTRA_WHICH, which);

        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(requestCode, Activity.RESULT_OK, result);
        } else {
            PendingIntent pi = getActivity().createPendingResult(requestCode, result,
                    PendingIntent.FLAG_ONE_SHOT);
            try {
                pi.send();
            } catch (PendingIntent.CanceledException ex) {
                // send failed
            }
        }
    }

}
