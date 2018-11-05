package models.getCaloriesBurnedForExercises;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Exercise implements Serializable {
    //This is the result of the query

    public Exercise createFromParcel(Parcel in) {
        return new Exercise(in);
    }

    public Exercise[] newArray(int size) {
        return (new Exercise[size]);
    }

    public Exercise(Integer tagId, String userInput, Double durationMin, Double met, Double nfCalories, String name, String description, String benefits) {
        super();
        this.tagId = tagId;
        this.userInput = userInput;
        this.durationMin = durationMin;
        this.met = met;
        this.nfCalories = nfCalories;
        this.name = name;
        this.description = description;
        this.benefits = benefits;
    }


    @SerializedName("tag_id")
    private int tagId;
    @SerializedName("user_input")
    private String userInput;
    @SerializedName("duration_min")
    private double durationMin;
    @SerializedName("met")
    private double met;
    @SerializedName("nf_calories")
    private double nfCalories;
    @SerializedName("photo")
    private Photo photo;
    @SerializedName("compendium_code")
    private int compendiumCode;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("benefits")
    private String benefits;
//    @SerializedName("")
//    private String workout;


    public Exercise(Parcel in) {

    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public double getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(float durationMin) {
        this.durationMin = durationMin;
    }

    public double getMet() {
        return met;
    }

    public void setMet(float met) {
        this.met = met;
    }

    public double getNfCalories() {
        return nfCalories;
    }

    public void setNfCalories(float nfCalories) {
        this.nfCalories = nfCalories;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public int getCompendiumCode() {
        return compendiumCode;
    }

    public void setCompendiumCode(int compendiumCode) {
        this.compendiumCode = compendiumCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    @Override
    public String toString() {
        return "Exercise {" +
                "tagId='" + tagId + '\'' +
                ", userInput'" + userInput + '\'' +
                ", duration_min'" + durationMin + '\'' +
                ", met'" + met + '\'' +
                ", nf_calories'" + nfCalories + '\'' +
                ", photo'" + photo + '\'' +
                ", compendium_code'" + compendiumCode + '\'' +
                ", name'" + name + '\'' +
                ", description'" + description + '\'' +
                ", benefits'" + benefits + '\'' +
                '}';
    }
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(tagId);
            dest.writeValue(userInput);
            dest.writeValue(durationMin);
            dest.writeValue(met);
            dest.writeValue(nfCalories);
            dest.writeValue(name);
            dest.writeValue(description);
            dest.writeValue(benefits);
        }

        public int describeContents() {
            return 0;
        }


}



