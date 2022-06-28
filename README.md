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