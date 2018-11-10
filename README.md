# Food and Fit Tracker

This app requires your own API key which can be provided by https://www.nutritionix.com.

Just add your own API key to the TrackerInterface file in the Header as shown below with xxxxx:

@Headers({
            "content-type: application/json",
            "x-app-id:c9a9b2dd",
            "x-app-key:xxxxxxxxxxxxxxxxxx"
    })
    @POST("v2/natural/exercise")
    Call<Exercise> getStringScalar(@Body ExerciseRequest query);

 An API Key is also used in the Firebase analytics, which has been removed for submission on GitHub.


This project is the Capstone Project for the Android Nanodegree with Udacity and
the Grow With Google Scholarship.

Code is written in Java and XML. Libraries used are
Picasso, Retrofit2, RecyclerView and Gson, Room and Espresso.
