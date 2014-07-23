Android-Rate
============

[![Build Status](https://travis-ci.org/hotchemi/Android-Rate.png?branch=master)](https://travis-ci.org/hotchemi/Android-Rate)

Android-Rate is a library to help you promote your android app by prompting users to rate the app after using it for a few days.

![screen shot](http://gifzo.net/BI5e2qMJVi0.gif)

## Getting Started

you can download from maven central.

```groovy
dependencies {
  compile 'com.github.hotchemi:android-rate:0.3.2'
}
```

## Sample

Please try to move the [sample module](https://github.com/hotchemi/Android-Rate/tree/master/sample).

## How to use

### Configuration

Android-rate provides class methods to configure its behavior.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  AppRate appRate = AppRate.build()
      .setInstallDays(0) // default 10, 0 means install day.
      .setLaunchTimes(3) // default 10
      .setRemindInterval(2) // default 1
      .setShowNeutralButton(true) // default true
      .setDebug(false) // default false
      .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
          @Override
          public void onClickButton(int which) {
              Log.d(MainActivity.class.getName(), Integer.toString(which));
          }
      })
      .monitor(this);

  // Show a dialog if meets conditions
  appRate.showRateDialogIfMeetsConditions(this);
}
```

The default conditions to show rate dialog is as below:

1. App is launched more than 10 days later than installation. Chenge via `AppRate#setInstallDays(int)`.
2. App is launched more than 10 times. Change via `AppRate#setLaunchTimes(int)`.
3. App is launched more than 2 days after neutral button clicked. Change via `AppRate#setRemindInterval(int)`.
4. App shows neutral dialog(Remind me later) by default. Change via `setShowNeutralButton(boolean)`.
5. To specify the callback when the button is pressed. The same value as the second argument of `DialogInterface.OnClickListener#onClick` will be passed in the argument of `onClickButton`. 
6. Setting `AppRate#setDebug(boolean)` will ensure that the rating request is shown each time the app is launched. **This feature is only development!**.

### Event Tracking

When you want to track significant events, write code as below.

```java

private AppRate mAppRate;

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  mAppRate = AppRate.build().setEventTimes(2).monitor(this);
}

@Override
public void onClick() {
  mAppRate.passSignificantEvent(this); // when user pass this line for the third time, dialog appears.
}
```

### Clear show dialog flag

When you want to show the dialog again, call `AppRate#clearAgreeShowDialog(Context)`.

```java
AppRate.clearAgreeShowDialog(this);
```

### When the button presses on

call `AppRate#showDialog(Context)`.

```java
AppRate.showDialog(this);
```

### Custom dialog

If you want to use your own dialog labels, override string xml resources on your application.

```xml
<resources>
    <string name="rate_dialog_title">Rate this app</string>
    <string name="rate_dialog_message">If you enjoy playing this app, would you mind taking a moment to rate it? It won\'t take more than a minute. Thanks for your support!</string>
    <string name="rate_dialog_ok">Rate It Now</string>
    <string name="rate_dialog_cancel">Remind Me Later</string>
    <string name="rate_dialog_no">No, Thanks</string>
</resources>
```

## Localization

Android-Rate currently supports the following languages:

- English
- Spanish
- French
- Chinese
- Korean
- Japanese
- Vietnamese
- Polish
- Czech
- Russian
- Ukrainian
- Hebrew

## ChangeLog

- 2014/07/23 0.3.2 release.
- 2014/07/05 0.3.1 release.
- 2014/07/03 0.3.0 release.
- 2014/07/02 0.2.1 release.
- 2014/06/20 0.2.0 release.
- 2014/06/19 0.1.3 release.
- 2014/06/16 0.1.2 release.
- 2014/06/15 0.1.1 release.
- 2014/05/25 0.1.0 release.
- 2014/04/13 0.0.6 release.
- 2014/04/12 0.0.5 release.
- 2014/04/07 0.0.4 release.
- 2014/02/13 0.0.3 release.
- 2014/02/12 0.0.2 release.
- 2014/02/11 0.0.1 release.

## Contribute

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## Contributor

- [androhi](https://github.com/androhi)
- [hoang8f](https://github.com/hoang8f)
- [mrmike](https://github.com/mrmike)
- [maarekj](https://github.com/maarekj)
- [TomasValenta](https://github.com/TomasValenta)
- [nein37](https://github.com/nein37)
- [marta-rodriguez](https://github.com/marta-rodriguez)
- [Bersh](https://github.com/Bersh)
- [amitkot](https://github.com/amitkot)

## Used

- [Zaim](https://play.google.com/store/apps/details?id=net.zaim.android)
- [就活マネージャー](https://play.google.com/store/apps/details?id=jp.co.recruit.shukatsumgr2016)
- [candlecake](https://play.google.com/store/apps/details?id=com.eranamit.candlecake&referrer=utm_source%3DAndroid-Rate)