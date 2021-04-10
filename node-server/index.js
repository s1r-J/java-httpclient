const express = require('express');
const app = express();

app.get('/', function(req, res) {
    // ステータスコード204、レスポンスヘッダ「Content-Length」にゼロを入れて返す
    res.set({
        'Content-Length': '0',
    }).status(204);
    res.end();
});

app.listen(3000);
console.log('Express server listening on port 3000');
