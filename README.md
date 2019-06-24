# CLog
log打印管理，log打印到文件。Toast简化，基础对话框。
## 引入
```
  implementation 'xyz.cq.base:clog:1.1.4'
```
## 使用
### log打印
#### 初始化
```
  CLog.init("Ctools", BuildConfig.DEBUG);
```
#### 设置logFile
```
  CLog.logFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Clog");//会请求文件管理权限,拒绝权限将不会打印到文件
```
#### 打印log
```
  CLog.log("tag2").i("msg");
```
#### 打印到文件
```
  CLog.log().iFile("msg");//共用上面的TAG
```
### Toast
#### Toast重用
小米或者其他手机会出现弹出不正常情况可以使用正常Toast
```
  CLog.show("msg");//Toast.LENGTH_SHORT
  CLog.showLong("msg");//Toast.LENGTH_LONG
```
#### 正常Toast
```
  //Toast.LENGTH_SHORT
  CLog.show1("msg");
  //Toast.LENGTH_LONG
  CLog.showLong1("msg");
```
### 对话框
#### LoadingDialog
```
 final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
         loadingDialog.show();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Thread.sleep(2000);
                     MainActivity.this.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             loadingDialog.dismiss();
                         }
                     });
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
```
![loadingDialog](https://github.com/ChengQian505/CLog/img/loadingDialog.png)
#### CommonDialog
```
    new CommonDialog(MainActivity.this)
                .setTitle("标题")
                .setContent("内容")
                .setLeftBtnText("左边按钮")
                .setRightBtnText("右边按钮")
                .setOnBtnClickListener(new CommonDialog.OnDialogBtnClickListener() {
                    @Override
                    public void onLeftBtnClicked(CommonDialog paramTipDialog) {
                        paramTipDialog.dismiss();
                    }

                    @Override
                    public void onRightBtnClicked(CommonDialog paramTipDialog) {
                        paramTipDialog.dismiss();
                    }
                }).show();
```
![commonDilog](https://github.com/ChengQian505/CLog/img/commonDilog.png)
#### BottomDialog
```
    new BottomDialog(MainActivity.this)
                .setText("第一个", "第二个", "第三个")
                .setOnClickListener(new BottomDialog.OnClickListener() {
                    @Override
                    public void onClick(View positionV, int position) {
                        CLog.show("点击了第" + (position + 1) + "个按钮");
                    }
                }).show();
```
![bottomDialog](https://github.com/ChengQian505/CLog/img/bottomDialog.png)
