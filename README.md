# CLog
用来log打印管理，Toast简化,log打印到文件，loading对话框
### 引入
```
  compile 'xyz.cq.base:clog:1.1.0'
```
### 使用
配置是否打印log
```
  //true为打印log，默认为true
  CLog.isLog(BulidConfig.DEBUG);
```
配置一级TAG
```
  //默认为CLog为一级TAG
  CLog.baseLog("tag1");
```
打印log
```
  //CLog.log()可以传入二级log，默认为CLog
  CLog.log("tag2").i("msg");
```
设置打印的文件
```
  CLog.logFile(filePath);
```
打印到文件
```
  //不设置logFile不会打印到文件
  //沿用上的面TAG
  //支持两个等级info error
  CLog.log().iFile("msg");
```
Toast<br/>
*解决Toast多次触发问题*
```
  //Toast.LENGTH_SHORT
  CLog.show("msg");
  //Toast.LENGTH_LONG
  CLog.showLong("msg");
```
*正常Toast*
```
  //Toast.LENGTH_SHORT
  CLog.show1("msg");
  //Toast.LENGTH_LONG
  CLog.showLong1("msg");
```
loading对话框
```
  HttpDialog httpdialog=new HttpDialog(context);
  httpdialog.show();//展示
  httpdialog.dismiss();//关闭
```
