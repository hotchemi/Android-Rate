Android-Rate
============

Android-Rate is a library to Help you promote your app by prompting users to rate the app after using it for a few days.

![Screen shot](https://raw2.github.com/hotchemi/Android-Rate/master/documents/screenshot.png)

## Download

Download from github maven repository via gradle.

```groovy
repositories {
    mavenCentral()
    maven { url 'https://raw.github.com/hotchemi/Android-Rate/master/repository/' }
}
android {
    dependencies {
        compile 'hotchemi.android.rate:android-rate:0.0.1'
    }
}
```

## Demo

Please try to move the sample module！

## How to use

### Implementation

Call `AppRate.monitor(Context)` and `AppRate.showRateDialogWhenMeetsConditions(Context)` in your launcher activity.

```java
@Override
protected void onStart() {
    super.onStart();
    // Monitor launch times and duration of feeding period from installation
    AppRate.monitor(this);
    // call this method whenever event you want to trigger showing rate dialog.
    AppRate.showRateDialogWhenMeetsConditions(this);
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
    AppRate.setInstallDays(0)
           .setLaunchTimes(3)
           .monitor(this);
}
```

### Custom rate dialog labels

If you want to use your own dialog labels, please create `DialogLabels` instance and se to `AppRate.setDialogLabels(DialogLabels)`.

```java
@Override
protected void onStart() {
    super.onStart();

    // override default rate dialog label
    final DialogLabels labels = new DialogLabels();
    labels.title = R.string.title;
    labels.message = R.string.message;
    labels.positiveButton = R.string.positive_button;
    labels.neutralButton = R.string.neutral_button;
    labels.negativeButton = R.string.negative_button;

    // Monitor launch times and duration of feeding period from installation
    AppRate.setInstallDays(0)
           .setLaunchTimes(3)
           // set labels
           .setDialogLabels(labels)
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

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## ToDo

- Add Test
- Add Travis CI badge.
- Support uses per week condition.

## ChangeLog

2014/02/12 v0.0.1 release.


## License

This software is licensed under [MIT Licence](http://opensource.org/licenses/MIT).
