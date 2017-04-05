package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailValue {
    private String formatted_address;
    private String formatted_phone_number;
    private String international_phone_number;
    private PlaceValue.OpeningHours opening_hours;

    private ArrayList<PhotoAttribute> photos;
    private float rating;
    private ArrayList<Review> reviews;

    private int utc_offset;
    private String website;

    public PlaceDetailValue(){

    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
    }

    public PlaceValue.OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(PlaceValue.OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public ArrayList<PhotoAttribute> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PhotoAttribute> photos) {
        this.photos = photos;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public int getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(int utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "PlaceDetailValue{" +
                "formatted_address='" + formatted_address + '\'' +
                ", formatted_phone_number='" + formatted_phone_number + '\'' +
                ", international_phone_number='" + international_phone_number + '\'' +
                ", opening_hours=" + opening_hours +
                ", photos=" + photos +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", utc_offset=" + utc_offset +
                ", website='" + website + '\'' +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class PhotoAttribute {
        int height;
        String photo_reference;
        int width;

        public PhotoAttribute(){

        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getPhoto_reference() {
            return photo_reference;
        }

        public void setPhoto_reference(String photo_reference) {
            this.photo_reference = photo_reference;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public String toString() {
            return "PhotoAttribute{" +
                    "height=" + height +
                    ", photo_reference='" + photo_reference + '\'' +
                    ", width=" + width +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Review {
        private String author_name;
        private String language;
        private int rating;
        private String text;
        private int time;
        private ArrayList<Aspects> aspects;

        public Review(){

        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ArrayList<Aspects> getAspects() {
            return aspects;
        }

        public void setAspects(ArrayList<Aspects> aspects) {
            this.aspects = aspects;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Aspects{
            private String types;
            private int rating;

            public Aspects(){

            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            @Override
            public String toString() {
                return "Aspects{" +
                        "types='" + types + '\'' +
                        ", rating=" + rating +
                        '}';
            }
        }
    }
}
