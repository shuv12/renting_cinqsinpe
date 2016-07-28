package app.com.example.android.renting.model;

import java.io.Serializable;

/**
 * Created by shuvam on 27-07-2016.
 */
public class Rent implements Serializable {
    private static final long serialVersionUID = 1;
    private String address;
    private String daysLeft;
    private String favs;
    private String description;
    private String image;
    private String latitude;
    private String longitude;
    private String notes;
    private String price;
    private String title;
    private String userProfileName;
    private String userProfilePic;


    public String getUserProfilePic(){
        return this.userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic){
        this.userProfilePic = userProfilePic;
    }

    public String getUserProfileName(){
        return this.userProfileName;
    }

    public void setUserProfileName(String userProfileName){
        this.userProfileName = userProfileName;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getDaysLeft(){
        return this.daysLeft;
    }

    public void setDaysLeft(String daysLeft){
        this.daysLeft = daysLeft;
    }

    public  String getFavs(){
        return this.favs;
    }

    public void setFavs(String favs){
        this.favs = favs;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getLatitude(){
        return this.latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLongitude(){
        return this.longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getNotes(){
        return this.notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public String getPrice(){
        return this.price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
