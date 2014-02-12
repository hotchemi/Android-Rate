Android-Rate
============

Android-Rate is a library to help you promote your app by prompting users to rate the app after using it for a few days.

![Screen shot](https://raw2.github.com/hotchemi/Android-Rate/master/documents/screenshot.png)

## Download

Download from maven repository(GitHub) via gradle.

```groovy
repositories {
    mavenCentral()
    maven { url 'https://raw.github.com/hotchemi/Android-Rate/master/repository/' }
}
android {
    dependencies {
        compile 'hotchemi.android.rate:android-rate:0.0.2'
    }
}
```

## Demo

Please try to move the [sample module](https://github.com/hotchemi/Android-Rate/tree/master/sample)！

## How to use

### Implementation

Call `AppRate.monitor(Context)` and `AppRate.showRateDialogIfMeetsConditions(Context)` in your launcher activity.

```java
@Override
protected void onStart() {
    super.onStart();
    // Monitor launch times and duration of feeding period from installation
    AppRate.monitor(this);
    // call this method whenever event you want to trigger showing rate dialog
    AppRate.showRateDialogIfMeetsConditions(this);
}
```

### Custom conditions

The default condition to show rate dialog is as below:

* App is launched more than 10 times
* App is launched more than 10 days later than installation.

If you want to use your own condition, please call `AppRate.setLaunchTimes(int)` and `AppRate.setInstallDays(int)`.

```java
@Override
protected void onStart() {
    super.onStart();
    // method chain
    AppRate.setInstallDays(0) // default 10
           .setLaunchTimes(3) // default 10
           .monitor(this);
}
```

### Custom rate dialog

If you want to use your own dialog labels, override string xml resources at your application.

```xml
<resources>
    <string name="rate_dialog_title">Rate this app</string>
    <string name="rate_dialog_message">If you enjoy playing this app, would you mind taking a moment to rate it? It won\'t take more than a minute. Thanks for your support!</string>
    <string name="rate_dialog_ok">Rate It Now</string>
    <string name="rate_dialog_cancel">Remind Me Later</string>
    <string name="rate_dialog_no">No, Thanks</string>
</resources>
```
And if you want to decide whether neutral button is appeared, please call `AppRate.setIsShowNeutralButton(boolean)`.

```java
@Override
protected void onStart() {
    super.onStart();
    // method chain
    AppRate.setInstallDays(0)
           .setIsShowNeutralButton(false) // default true
           .monitor(this);
}
```

## Localization

Android-Rate currently supports the following languages:

 * English
 * Japanese
 * French
 * Spanish
 * Chinese
 * Korean

## Requirements

Supports Android 2.2 or greater.

## Build

```sh
$ ./gradlew uploadArchives
```

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## ToDo

- Add unit test.
- Add travis badge.
- Support event counts condition.
- Support uses per week condition.

## ChangeLog

2014/02/12 v0.0.2 release.

## License

This software is licensed under [MIT Licence](http://opensource.org/licenses/MIT).
