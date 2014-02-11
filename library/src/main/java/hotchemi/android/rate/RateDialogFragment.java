package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;

/**
 * @author Shintaro Katafuchi
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class RateDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle params = getArguments();
        DialogLabels labels;
        if (params != null) {
            labels = params.getParcelable(Constants.BUNDLE_KEY_DIALOG_LABELS);
        } else {
            labels = new DialogLabels();
        }
        return DialogManager.createDialog(getActivity(), labels);
    }

}