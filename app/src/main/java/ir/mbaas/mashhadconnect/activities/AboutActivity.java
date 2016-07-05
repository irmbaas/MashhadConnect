package ir.mbaas.mashhadconnect.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import ir.mbaas.mashhadconnect.MyApplication;
import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.helpers.StaticMethods;
import ir.mbaas.mashhadconnect.views.MBTextView;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);

        MBTextView tvTitle = (MBTextView) findViewById(R.id.tv_dialogs_title);
        tvTitle.setText(R.string.about_app);

        MBTextView tvVersionName = (MBTextView) findViewById(R.id.tv_version_name);
        tvVersionName.setText(String.format(getResources().getString(R.string.version_name),
                        MyApplication.versionName));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView mbaas = (ImageView) findViewById(R.id.iv_mbaas);
        mbaas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.openBrowser(AboutActivity.this, getResources().getString(R.string.mbaas_site));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
