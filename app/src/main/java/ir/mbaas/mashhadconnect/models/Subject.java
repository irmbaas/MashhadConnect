package ir.mbaas.mashhadconnect.models;

import java.util.Date;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class Subject {

    public int title;
    public int description;
    public int lecturer;
    public int lecturerImg;
    public int image;

    public Subject(int title, int description, int lecturer, int image, int lecturerImg) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.lecturer = lecturer;
        this.lecturerImg = lecturerImg;
    }
}
