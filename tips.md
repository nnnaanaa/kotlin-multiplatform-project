- tips

.\gradlew.bat clean
.\gradlew.bat :composeApp:packageMsi

Kotlinプラグインのインストール時にプロキシ設定が原因でエラーが出る場合、IntelliJ IDEA（またはAndroid Studio）自体のネットワーク設定を修正する必要があります。

以下の手順を順に試してみてください。

---

## 1. IDE のプロキシ設定を確認する

まず、IDEがネットワークに接続できるよう設定を変更します。

1. **Settings (Ctrl + Alt + S)** を開きます（macOSは `IntelliJ IDEA` -> `Settings`）。
2. **Appearance & Behavior** > **System Settings** > **HTTP Proxy** を選択します。
3. 現在の環境に合わせて以下を選択してください。
* **Auto-detect proxy settings:** 自動設定ファイル（PAC）がある場合。
* **Manual proxy configuration:** プロキシサーバーのホスト名とポート番号を直接入力する場合。


4. 「Check connection」をクリックして、`https://plugins.jetbrains.com` に接続できるかテストします。

---

## 2. カスタム証明書のインポート（社内プロキシ等の場合）

社内ネットワークなどでSSL通信のデコードが行われている場合、証明書エラーでダウンロードが止まることがあります。

1. **HTTP Proxy** 設定画面の下部にある **Accept untrusted certificates** にチェックを入れて試してみてください。
2. それでもダメな場合は、**Tools** > **Server Certificates** から、会社のプロキシ証明書（.cerや.crtファイル）を信頼済みリストに追加します。

---

## 3. `gradle.properties` の設定（ビルド時のエラーの場合）

プラグインのインストールはできても、プロジェクト作成後のKotlinライブラリのダウンロードでエラーが出る場合は、Gradleの設定が必要です。

プロジェクト直下（または `~/.gradle/`）にある **`gradle.properties`** ファイルに以下の記述を追加します。

```properties
systemProp.http.proxyHost=プロキシホスト名
systemProp.http.proxyPort=ポート番号
systemProp.https.proxyHost=プロキシホスト名
systemProp.https.proxyPort=ポート番号
# 認証が必要な場合
systemProp.http.proxyUser=ユーザー名
systemProp.http.proxyPassword=パスワード
systemProp.https.proxyUser=ユーザー名
systemProp.https.proxyPassword=パスワード

```

---

## 4. 最終手段：手動インストール

どうしてもプロキシを越えられない場合は、ブラウザでプラグインファイルを直接ダウンロードしてインストールできます。

1. [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/6954-kotlin) にブラウザでアクセスし、使用しているIDEのバージョンに合う **zipファイル** をダウンロードします。
2. IDEの **Settings** > **Plugins** を開きます。
3. 歯車アイコン（⚙️）をクリックし、**Install Plugin from Disk...** を選択します。
4. ダウンロードしたzipファイルを選択してインストールし、IDEを再起動します。

---

現在のエラーメッセージ（「Connection refused」や「SSLHandshakeException」など）を教えていただければ、より具体的な解決策を提案できます。

**エラーメッセージをコピーしてこちらに貼り付けていただけますか？**