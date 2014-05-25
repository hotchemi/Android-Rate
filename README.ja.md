Android-Rate
============

[![Build Status](https://travis-ci.org/hotchemi/Android-Rate.png?branch=master)](https://travis-ci.org/hotchemi/Android-Rate)

Android-Rateはアプリのレーティング促進ダイアログを出す事ができるライブラリです｡最適なタイミングでの促進を可能にする細かな条件設定が可能となっています｡

![screen shot](http://gifzo.net/BI5e2qMJVi0.gif)

## Getting Started

maven centralからダウンロードできます. 最新バージョンは **0.1.0**です.

```groovy
dependencies {
  compile 'com.github.hotchemi:android-rate:{latest.version}'
}
```

## Sample

[sample module](https://github.com/hotchemi/Android-Rate/tree/master/sample)を動かして動作を確認してください.

## How to use

### Configuration

Android-Rateはレーティングを出す条件設定をする為のクラスメソッドを提供しています.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  AppRate.setInstallDays(0) // default 10, 0 means install day.
      .setLaunchTimes(3) // default 10
      .setRemindInterval(2) // default 1
      .setShowNeutralButton(true) // default true
      .setDebug(false) // default false
      .monitor(this);
  
  // Show a dialog if meets conditions
  AppRate.showRateDialogIfMeetsConditions(this);
}
```

デフォルトの条件設定は下記の:

1. アプリのインストール後日数をモニタリング.デフォルトは10日.`AppRate.setInstallDays(int)`を使用して変更.
2. アプリの起動回数をモニタリング.デフォルトは10.`AppRate.setLaunchTimes(int)`を利用して変更.
3. ｢後でする｣を押下した後の日数経過をモニタリング.デフォルトは2日.`AppRate.setRemindInterval(int)`を利用して変更.
4. ｢後でする｣ボタンを出現させるかどうかを設定します.デフォルトはtrue.`setShowNeutralButton(boolean)`を利用して変更.
4. `AppRate.setDebug(boolean)`をtrueにすると常時ダイアログが出現する用になります. **必ずデバッグ時のみの利用としてください!**.

### Event Tracking

特定のイベントの通過回数をモニタリングしたい場合は､下記の様なコードを書いてください.

> Configurationで説明した条件設定とは独立して動作しますのでご注意下さい.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  AppRate.setEventTimes(2).monitor(this);// Event通過回数が3回以上でダイアログを出現させる
}

@Override
public void onClick() {
  AppRate.passSignificantEvent(this); // when user pass this line for the third time, dialog appears.
}
```

### Clear show dialog flag

アプリのアップデート時に再度ダイアログを出現させたい場合は､`AppRate.clearAgreeShowDialog(Context)`を利用してフラグをクリアしてください.

```java
AppRate.clearAgreeShowDialog(this);
```

### When the button presses on

ボタンを押下した際にダイアログを出したい場合は`AppRate.showDialog(Context)`を直接呼び出してください.

```java
AppRate.showDialog(this);
```

### Custom dialog

ダイアログの文言をカスタマイズしたい場合は､アプリ側でxmlリソースを上書きしてください.

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

Android-Rateは下記の言語をサポートしています:

- 英語
- スペイン語
- フランス語
- 中国語
- 韓国語
- 日本語
- ベトナム語
- ポーランド語

## Requirements

Android 1.6以上をサポートしています.

## Test

```sh
$ ./gradlew connectedCheck
```

## ChangeLog

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

## Used

- [Zaim](https://play.google.com/store/apps/details?id=net.zaim.android)