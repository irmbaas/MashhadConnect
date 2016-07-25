package ir.mbaas.mashhadconnect.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mehdi on 7/25/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Timetable {
    @JsonProperty("Title")
    public String title;

    @JsonProperty("Description")
    public String description;

    @JsonSetter("BeginTime")
    public void setBeginTime(String beginTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            this.beginTime = dateFormat.parse(beginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty("EndTime")
    public void setEndTime(String endTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            this.endTime = dateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty("Lecturer")
    public String lecturer;

    @JsonProperty("LecturerImage")
    public String lecturerImage;

    public Date beginTime;
    public Date endTime;

    public Timetable() {
    }
}
