package ir.mbaas.mashhadconnect.apis;

import java.util.List;

import ir.mbaas.mashhadconnect.models.RssFeed;

/**
 * Created by Mahdi on 6/5/2016.
 */
public interface IRssCallback {
    public void onSuccess(List<RssFeed> rssFeeds);
    public void onError();
}
