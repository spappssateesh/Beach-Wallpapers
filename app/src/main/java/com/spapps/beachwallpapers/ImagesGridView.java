package com.spapps.beachwallpapers;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImagesGridView extends Activity {

    InterstitialAd interstitial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(AD_UNIT_ID);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest1);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });
    }
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Take a look at Green Wallpapers App - https://play.google.com/store/apps/details?id=com.stylishapps.greenwallpapers");
            startActivity(Intent.createChooser(shareIntent,"Share Green Screen Wallpapers App Via"));
            return true;
        }
        if (id == R.id.action_topfree) {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://search?q=pub:StylishApps")));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

