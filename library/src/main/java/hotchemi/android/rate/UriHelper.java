package hotchemi.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.List;

final class UriHelper {

    private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";

    private static final String AMAZON_APPSTORE = "amzn://apps/android?p=";

    private UriHelper() {
    }

    static Uri getGooglePlay(String packageName) {
        return packageName == null ? null : Uri.parse(GOOGLE_PLAY + packageName);
    }

    static Uri getAmazonAppstore(String packageName) {
        return packageName == null ? null : Uri.parse(AMAZON_APPSTORE + packageName);
    }

    static boolean isPackageExists(Context context, String targetPackage) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage)) return true;
        }
        return false;
    }
}