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

    private ArrayList<PhotoAttribute> photos;
    private float rating;
    private OpeningHours opening_hours;
    private ArrayList<Review> reviews;

    private int utc_offset;
    private String website;

    public PlaceDetailValue(){

    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
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
    public static class PhotoAttribute {
        private int height;
        private String photo_reference;
        private int width;

        public PhotoAttribute(){

        }

        public PhotoAttribute(int height, String photo_reference, int width) {
            this.height = height;
            this.photo_reference = photo_reference;
            this.width = width;
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
    public static class Review {
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
        public static class Aspects{
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OpeningHours{
        private boolean open_now;
        private ArrayList<HoursDay> periods;
        private ArrayList<String> weekday_text;


        public OpeningHours(){

        }

        public boolean isOpen_now() {
            return open_now;
        }

        public void setOpen_now(boolean open_now) {
            this.open_now = open_now;
        }

        public ArrayList<HoursDay> getPeriods() {
            return periods;
        }

        public void setPeriods(ArrayList<HoursDay> periods) {
            this.periods = periods;
        }

        public ArrayList<String> getWeekday_text() {
            return weekday_text;
        }

        public void setWeekday_text(ArrayList<String> weekday_text) {
            this.weekday_text = weekday_text;
        }

        @Override
        public String toString() {
            return "OpeningHours{" +
                    "open_now=" + open_now +
                    ", periods=" + periods +
                    ", weekday_text=" + weekday_text +
                    '}';
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class HoursDay{
            private Hours open;
            private Hours close;

            public HoursDay(){

            }

            public Hours getOpen() {
                return open;
            }

            public void setOpen(Hours open) {
                this.open = open;
            }

            public Hours getClose() {
                if (close == null)
                    return new Hours();
                else
                    return close;
            }

            public void setClose(Hours close) {
                if (close == null)
                    this.close = new Hours();
                else
                    this.close = close;
            }

            @Override
            public String toString() {
                return "HoursDay{" +
                        "open=" + open +
                        ", close=" + close +
                        '}';
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Hours{
                int day;
                String time;

                public Hours(){

                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                @Override
                public String toString() {
                    return "Hours{" +
                            "day=" + day +
                            ", time='" + time + '\'' +
                            '}';
                }
            }
        }


    }
}
