This is a bunch of Helper Functions that are required in almost all projects.
These Helpers will save your time as they are made so that work of tens of code
can now be done in just a single line.

## To implement
Add this in your root project level build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }   
}
```
Also add below in your app level build.gradle
```
dependencies{
    ...
    implementation 'com.github.shreyanshumalviya:AndroidNativeHelpers:0.0.1'
}
```

### Your Helpers are now ready to assist you.

## Notification Helper
To send a simple Notification use the below line.
```
NotificationHelper.sendNotification(this,"Title","App Launched Successfully");
```
You can also edit the previously sent Notifications.
1. save the notificationID returned while sending the notification
```
    val notificationId = NotificationHelper.sendNotification(this,"Title","App Launched Successfully");
```
2.  Pass the notificationID as shown 
```
    NotificationHelper.sendNotification(this,"Title","Notification sent",notificationId);
```