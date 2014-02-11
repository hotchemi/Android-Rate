package hotchemi.android.rate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Shintaro Katafuchi
 */
public class DialogLabels implements Parcelable {

    public int title;

    public int message;

    public int positiveButton;

    public int neutralButton;

    public int negativeButton;

    public static final Parcelable.Creator<DialogLabels> CREATOR = new Parcelable.Creator<DialogLabels>() {
        public DialogLabels createFromParcel(Parcel in) {
            return new DialogLabels(in);
        }

        public DialogLabels[] newArray(int size) {
            return new DialogLabels[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(message);
        dest.writeInt(positiveButton);
        dest.writeInt(neutralButton);
        dest.writeInt(negativeButton);
    }

    private DialogLabels(Parcel in) {
        title = in.readInt();
        message = in.readInt();
        positiveButton = in.readInt();
        neutralButton = in.readInt();
        negativeButton = in.readInt();
    }

    public DialogLabels(int title, int message, int positiveButton,
                        int neutralButton, int negativeButton) {
        this.title = title;
        this.message = message;
        this.positiveButton = positiveButton;
        this.neutralButton = neutralButton;
        this.negativeButton = negativeButton;
    }

    public DialogLabels() {
    }

}