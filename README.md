java-httpclient
==

Java11で標準APIに追加されたHttpClientと、OSSであるOkHttpの挙動を試したリポジトリ。

Javaアプリからリクエストを送り、Nodejsアプリがリクエストを受け取ってレスポンスをJavaアプリに返します。

記事は[Qiita](https://qiita.com/s1r/items/a1cd37174d4069829531)もしくは[Crieit](https://crieit.net/posts/Java11-HttpClient-204-IOException)を御覧ください。

## 必要な環境

- Nodejs実行環境
- Eclipse（もしくはJava実行環境）

## 動かし方

1. サーバであるNodejsアプリを起動する

node-serverフォルダ以下はNodejsアプリになっています。

node-serverフォルダ直下で必要な依存関係をインストールしてください。

```
npm install
```

インストールが完了したら、サーバとして起動します。

```
node index
```

`http://localhost:3000/`へのリクエストに対し、ステータスコード204かつレスポンスヘッダにContent-Lengthをゼロとしたレスポンスを返却するようになっています。

2. Javaアプリからリクエストを送り、レスポンスを受け取る

JavaAppフォルダはGradleプロジェクトになっています。Eclipseにインポート等をおこない、Javaで実行できる状態にしてください。

Mainクラスのmainメソッドを実行すると、先述のサーバに対してOkHttpで実装した処理とHttpClientクラスで実装した処理を実行します。  
レスポンスヘッダを標準出力に出力します。


