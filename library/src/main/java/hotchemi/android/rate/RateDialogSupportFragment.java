package hotchemi.android.rate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author Shintaro Katafuchi
 */
class RateDialogSupportFragment extends DialogFragment {

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