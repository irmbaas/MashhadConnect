package ir.mbaas.mashhadconnect.models;

/**
 * Created by Mehdi on 7/10/2016.
 */
public class Sponsor {

    public int title;
    public int description;
    public int logo;
    public int url;
    public int rss;

    public Sponsor(int title, int description, int logo, int url, int rss) {
        this.title = title;
        this.description = description;
        this.logo = logo;
        this.url = url;
        this.rss = rss;
    }
}
