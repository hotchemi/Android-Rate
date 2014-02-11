package hotchemi.android.rate;

import android.net.Uri;

/**
 * @author Shintaro Katafuchi
 */
class UriUtils {

    private static final String GOOGLE_PLAY_PREFIX = "https://play.google.com/store/apps/details?id=";

    private UriUtils() {
    }

    static Uri getGooglePlayUri(final String packageName) {
        return Uri.parse(GOOGLE_PLAY_PREFIX + packageName);
    }

}