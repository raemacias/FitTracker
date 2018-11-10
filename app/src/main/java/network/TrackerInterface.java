package network;

import com.raemacias.foodandfittracker.BuildConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.getCaloriesBurnedForExercises.Exercise;
import models.getCaloriesBurnedForExercises.ExerciseBase;
import models.getCaloriesBurnedForExercises.ExerciseRequest;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface TrackerInterface {

    //The interface defines the possible HTTP operations
    //define base URL

    String BASE_URL = "https://trackapi.nutritionix.com/";
    String API_KEY = BuildConfig.API_KEY;

    //searchExercises

    @Headers({
            "content-type: application/json",
            "x-app-id:c9a9b2dd",
            "x-app-key:xxxxxxx"
    })
    @POST("v2/natural/exercise")
    Call<Exercise> getStringScalar(@Body ExerciseRequest query);

}
