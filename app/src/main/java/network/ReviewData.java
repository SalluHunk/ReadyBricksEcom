package network;

import com.google.gson.annotations.SerializedName;

public class ReviewData {

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("ManuReview")
    public String rating;

    @SerializedName("ManuDescription")
    public String desc;

    @SerializedName("Image")
    public String image;

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRating() {
        return rating;
    }
}
