# innov8tif-Assessment

This project is an assessment for Innov8tif company

I have used MVP method to develop this application

the Java folder contains few folders such as:
Activity 
Adapter
Model
Network
Util

Activity folder has 1 file Comments Activity which will show list of comments once user click on any item in post list. 
once user click on any item in post list the id will be passed to the next activity and that id will be used to populate the data for comments accordingly.

MainActivity is for post list, once user run the application the data will be populated.

There are 2 adapters for post and comments list, these adapters are used to generate the data and show in recycleview.

Model folder contains 2 files comments data and post data.

Network Folder is for managing the api calls. Api adapter and Api interface. Api adapter for building and setting the base URL.
Api interface is for setting the end points for each api calles in our activities.

Util folder contains 2 files, they are created to handle the network connectivity. once there is no internet connection the dialog will be pop up and
the user can notice there is no internet connection. user is able to retry until there is internet connection.

In AndroidManifest we need to set the permission for accessing to the internet so our app can retrieve the data from api and also NETWORK_STATE to check 
the connectivity.

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    
for api call i have used Retrofit.
Retrofit is a type-safe HTTP networking library used for Android and Java.

in this project i have 2 end points which i used GET method.
1. /post 
to get the post data

2. /post/{post_id}/comments
to get the comments data (post_id will be from post list data)

I have used below dependences to develop this application:


    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'com.google.android.material:material:1.6.0'


    // Retrofit with OkHttp and Gson
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit:retrofit:1.9.0' // dependency for Retrofit

    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'


============================ Screenshots ===================================

![1](https://user-images.githubusercontent.com/46099808/176118150-b383c10e-973c-447d-9dca-427bb3d62897.png)

![2](https://user-images.githubusercontent.com/46099808/176118179-1ffcce4f-cbd7-453e-96d7-46d5ab4ce3f3.png)

![3](https://user-images.githubusercontent.com/46099808/176118193-35ea26aa-8b1e-42d9-b8e2-a53b72597f7e.png)

![4](https://user-images.githubusercontent.com/46099808/176118213-8447da3b-d4ce-42d8-88ae-68ec62a105db.png)

