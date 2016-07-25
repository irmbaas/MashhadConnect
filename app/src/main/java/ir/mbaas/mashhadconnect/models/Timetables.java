package ir.mbaas.mashhadconnect.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehdi on 7/25/2016.
 */
public class Timetables {
    @JsonProperty("Timetables")
    public List<Timetable> records = new ArrayList<>();
}
