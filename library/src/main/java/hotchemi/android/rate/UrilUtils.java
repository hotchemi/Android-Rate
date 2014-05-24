package hotchemi.android.rate;

import android.net.Uri;

final class UriUtils {

    private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";

    private UriUtils() {
    }

    static Uri getGooglePlayUri(final String packageName) {
        return Uri.parse(GOOGLE_PLAY + packageName);
    }

}