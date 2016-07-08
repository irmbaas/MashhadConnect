package ir.mbaas.mashhadconnect.apis;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.mbaas.mashhadconnect.models.RssFeed;
import ir.mbaas.sdk.dfapi.ApiException;
import ir.mbaas.sdk.dfapi.BaseAsyncRequest;

/**
 * Created by Mahdi on 4/20/2016.
 */
public class RequestRss extends BaseAsyncRequest {

    private enum RSSXMLTag {
        TITLE, DATE, LINK, CONTENT, GUID, IGNORETAG;
    }

    private String TAG = "RequestRss";
    private Context ctx;
    private String url;
    private List<RssFeed> rssFeeds;
    private RSSXMLTag currentTag;
    private IRssCallback ic;

    public RequestRss(Context ctx, String url, IRssCallback ic) {
        this.ctx = ctx;
        this.url = url;
        this.ic = ic;
    }

    @Override
    protected void doSetup() {
        callerName = "requestRss";

        baseInstanceUrl = url;
        contentType = "application/xml";
        verb = "GET";
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO Auto-generated method stub
        InputStream is = null;

        try {
            doSetup();

            URL url = new URL(baseInstanceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod(verb);
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            is = connection.getInputStream();
            processResponse(parseRss(is));
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            onError(e);
        }

        return false;
    }

    protected void processResponse(List<RssFeed> response) {
        rssFeeds = response;
        Log.d(TAG, "Token Response Received.");
    }

    @Override
    protected void onError(Exception e) {
        super.onError(e);
    }

    @Override
    protected void onCompletion(boolean success) {
        if(success){
            if (ic != null)
                ic.onSuccess(rssFeeds);
        }
    }

    private List<RssFeed> parseRss(InputStream is) {
        List<RssFeed> rssFeeds = null;

        try {
            rssFeeds = new ArrayList<>();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);

            int eventType = xpp.getEventType();
            RssFeed rssFeed = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        rssFeed = new RssFeed();
                        currentTag = RSSXMLTag.IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = RSSXMLTag.TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = RSSXMLTag.LINK;
                    } else if (xpp.getName().equals("pubDate")) {
                        currentTag = RSSXMLTag.DATE;
                    }  else if (xpp.getName().equals("description")) {
                        currentTag = RSSXMLTag.CONTENT;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in Adapter
                        rssFeed.pubDate = dateFormat.parse(rssFeed.pubDateStr);
                        rssFeeds.add(rssFeed);
                    } else {
                        currentTag = RSSXMLTag.IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (rssFeed != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (rssFeed.title != null) {
                                        rssFeed.title += content;
                                    } else {
                                        rssFeed.title = content;
                                    }
                                }
                                break;
                            case LINK:
                                if (content.length() != 0) {
                                    if (rssFeed.link != null) {
                                        rssFeed.link += content;
                                    } else {
                                        rssFeed.link = content;
                                    }
                                }
                                break;
                            case DATE:
                                if (content.length() != 0) {
                                    if (rssFeed.pubDateStr != null) {
                                        rssFeed.pubDateStr += content;
                                    } else {
                                        rssFeed.pubDateStr = content;
                                    }
                                }
                                break;
                            case CONTENT:
                                if (content.length() != 0) {
                                    if (rssFeed.description != null) {
                                        rssFeed.description += content;
                                    } else {
                                        rssFeed.description = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
            Log.v("tst", String.valueOf((rssFeeds.size())));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rssFeeds;
    }
}
