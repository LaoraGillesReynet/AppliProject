package com.polytech.webservice.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.omg.CORBA.Current;

/**
 * Created by User on 03/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoRequest {
    private CityInfo city_info;
    private ForecastInfo forecast_info;
    private CurrentCondition current_condition;
    private FcstDay0 fcst_day_0;

    public CityInfo getCity_info() {
        return city_info;
    }

    public void setCity_info(CityInfo city_info) {
        this.city_info = city_info;
    }

    public ForecastInfo getForecast_info() {
        return forecast_info;
    }

    public void setForecast_info(ForecastInfo forecast_info) {
        this.forecast_info = forecast_info;
    }

    public CurrentCondition getCurrent_condition() {
        return current_condition;
    }

    public void setCurrent_condition(CurrentCondition current_condition) {
        this.current_condition = current_condition;
    }

    public FcstDay0 getFcst_day_0() {
        return fcst_day_0;
    }

    public void setFcst_day_0(FcstDay0 fcst_day_0) {
        this.fcst_day_0 = fcst_day_0;
    }

    @Override
    public String toString() {
        return "MeteoRequest{" +
                "city_info=" + city_info +
                ", forecast_info=" + forecast_info +
                ", current_condition=" + current_condition +
                ", fcst_day_0=" + fcst_day_0 +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CityInfo{
        private String name;
        private double latitude;
        private double longitude;
        private String elevation;
        private String sunrise;
        private String sunset;

        public CityInfo(){

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getElevation() {
            return elevation;
        }

        public void setElevation(String elevation) {
            this.elevation = elevation;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "CityInfo{" +
                    "name='" + name + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", elevation='" + elevation + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ForecastInfo{
        private double latitude;
        private double longitude;
        private double elevation;

        public ForecastInfo(){

        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getElevation() {
            return elevation;
        }

        public void setElevation(double elevation) {
            this.elevation = elevation;
        }

        @Override
        public String toString() {
            return "ForecastInfo{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", elevation=" + elevation +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CurrentCondition{
        private String date;
        private String hour;

        private int tmp;

        private int wnd_spd;
        private int wnd_gust;
        private String wnd_dir;
        private float pressure;
        private int humidity;
        private String condition;


        public CurrentCondition(){

        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public int getTmp() {
            return tmp;
        }

        public void setTmp(int tmp) {
            this.tmp = tmp;
        }

        public int getWnd_spd() {
            return wnd_spd;
        }

        public void setWnd_spd(int wnd_spd) {
            this.wnd_spd = wnd_spd;
        }

        public int getWnd_gust() {
            return wnd_gust;
        }

        public void setWnd_gust(int wnd_gust) {
            this.wnd_gust = wnd_gust;
        }

        public String getWnd_dir() {
            return wnd_dir;
        }

        public void setWnd_dir(String wnd_dir) {
            this.wnd_dir = wnd_dir;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "CurrentCondition{" +
                    "date='" + date + '\'' +
                    ", hour='" + hour + '\'' +
                    ", tmp=" + tmp +
                    ", wnd_spd=" + wnd_spd +
                    ", wnd_gust=" + wnd_gust +
                    ", wnd_dir='" + wnd_dir + '\'' +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", condition='" + condition + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class FcstDay0{
        private String date;
        private String day_short;
        private int t_min;
        private int t_max;
        private String condition;

        public FcstDay0(){

        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDay_short() {
            return day_short;
        }

        public void setDay_short(String day_short) {
            this.day_short = day_short;
        }

        public int getT_min() {
            return t_min;
        }

        public void setT_min(int t_min) {
            this.t_min = t_min;
        }

        public int getT_max() {
            return t_max;
        }

        public void setT_max(int t_max) {
            this.t_max = t_max;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "FcstDay0{" +
                    "date='" + date + '\'' +
                    ", day_short='" + day_short + '\'' +
                    ", t_min=" + t_min +
                    ", t_max=" + t_max +
                    ", condition='" + condition + '\'' +
                    '}';
        }
    }
}
