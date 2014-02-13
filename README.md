Android-Rate
============

[![wercker status](https://app.wercker.com/status/787f5e7eb79d3b44b9bb7a63b3a6d2b7/m/ "wercker status")](https://app.wercker.com/project/bykey/787f5e7eb79d3b44b9bb7a63b3a6d2b7)

Android-Rate is a library to help you promote your app by prompting users to rate the app after using it for a few days.

![Screen shot](https://raw2.github.com/hotchemi/Android-Rate/master/documents/screen_shot.png)

## Download

Download from maven repository(GitHub) via gradle.

```groovy
repositories {
    mavenCentral()
    maven { url 'https://raw.github.com/hotchemi/Android-Rate/master/repository/' }
}
android {
    dependencies {
        compile 'hotchemi.android.rate:android-rate:0.0.3'
    }
}
```

## Demo

Please try to move the [sample module](https://github.com/hotchemi/Android-Rate/tree/master/sample/).

## How to use

### Implementation

Call `AppRate.monitor(Context)` and `AppRate.showRateDialogIfMeetsConditions(Context)` in your launcher activity.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  // Monitor launch times and duration of feeding period from installation
  AppRate.monitor(this);
  // Show a dialog if meets conditions
  AppRate.showRateDialogIfMeetsConditions(this);
}
```

### Custom conditions

The default conditions to show rate dialog is as below:

* App is launched more than 10 times
* App is launched more than 10 days later than installation.

If you want to use your own condition, please call `AppRate.setLaunchTimes(int)` and `AppRate.setInstallDays(int)`.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // Monitor launch times and duration of feeding period from installation
    AppRate.setInstallDays(0) // default 10, 0 means install day.
         .setLaunchTimes(3) // default 10
         .monitor(this);
    // Show a dialog if meets conditions
    AppRate.showRateDialogIfMeetsConditions(this);
}
```

### Custom rate dialog

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
And if you want to decide whether neutral button is appeared, please call `AppRate.setShowNeutralButton(boolean)`.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // method chain
    AppRate.setInstallDays(0)
           .setShowNeutralButton(false) // default true
           .monitor(this);
}
```

## Localization

Android-Rate currently supports the following languages:

- English
- Japanese
- French
- Spanish
- Chinese
- Korean

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

- Support event counts condition.
- Support uses per week condition.
- deploy to maven central repository.

## ChangeLog

- 2014/02/12 v0.0.2 release.
- 2014/02/13 v0.0.3 release.

## License

```
The MIT License (MIT)

Copyright (c) 2014 Shintaro Katafuchi

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
