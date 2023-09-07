# sms-android-java
An Android app that receives SMS messages and displays the message body in a toast message within the app.

### How it works?
- Step-1: Clone this repo into your Android Studio.
- Step-2: Run the project with emulator or device which supports the API level between 21 and 33.
- Step-3: On start it will request for SMS read permission:
  - On permission granted: This will show the list of sms in simple list-view to demonstrate the sms read operation from the device.
  - Otherwise, an empty screen will be shown.
- Step-4: While running the app if an sms is received, it'll display a toast with the message body and sender number. And restarts the activity to load new sms which will be updated with better approach in near future.

### Design choices
This app is designed with a simple list-view using an Activity to demonstrate the core operations. 
This design will be updated using fragments to support differenct screen sizes.
