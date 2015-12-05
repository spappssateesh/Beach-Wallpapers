package com.spapps.beachwallpapers;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ImageTextListViewActivity extends AppCompatActivity implements OnItemClickListener {

    public static final String[] titles = new String[] {
            "Beach Wallpapers",
            "Nature Wallpapers",
            "Space Wallpapers",
            "New York City Wallpapers",
            "Nature Live Wallpapers",
            "More Free Apps",
            "Beach Live Wallpapers",
            "sunrise Live Wallpapers",
            "Flowers Live Wallpapers",
            "Love Live Wallpapers",
            "Cute Cats Live Wallpapers"
            };

    public static final String[] descriptions = new String[] {
            "Tap Here To Enter",
            "Get it on Google Play",
            "Get it on Google Play",
            "Get it on Google Play",
            "Get it on Google Play",
            " ",
            "Get it on Google Play",
            "Get it on Google Play",
            "Get it on Google Play",
            "Get it on Google Play",
            "Get it on Google Play",
    };
    public static final Integer[] images = {
            R.drawable.beach,
            R.drawable.nature,
            R.drawable.space,
            R.drawable.newyork,
            R.drawable.nature,
            R.drawable.moreapps,
            R.drawable.beach_live,
            R.drawable.sunrise_live,
            R.drawable.flowers_live,
            R.drawable.love_live,
            R.drawable.cats_live
    };

    ListView listView;
    List<RowItem> rowItems;
    private Toolbar mToolbar;
    private AlertDialog.Builder dialog,liceceDialog;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_textview);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AdView mAdView = (AdView) this.findViewById(R.id.adView_homescreen);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position+1;
        switch(pos) {
            case 1://beach com.spapps.beachwallpapers
                Intent material = new Intent(ImageTextListViewActivity.this,ImagesGridView.class);
                startActivity(material);
                break;
            case 2://nature  com.spapps.naturewallpapers
                Intent intentOne = new Intent(Intent.ACTION_VIEW);
                intentOne.setData(Uri.parse("market://details?id=com.spapps.naturewallpapers"));
                startActivity(intentOne);
                break;
            case 3://space com.spapps.spacewallpapers
                Intent intentTwo = new Intent(Intent.ACTION_VIEW);
                intentTwo.setData(Uri.parse("market://details?id=com.spapps.spacewallpapers"));
                startActivity(intentTwo);
                break;
            case 4://new york city com.spapps.newyorkcitywallpapers
                Intent nycw = new Intent(Intent.ACTION_VIEW);
                nycw.setData(Uri.parse("market://details?id=com.spapps.newyorkcitywallpapers"));
                startActivity(nycw);
                break;
            case 5://nature live wallpapers
                Intent natureLive = new Intent(Intent.ACTION_VIEW);
                natureLive.setData(Uri.parse("market://details?id=com.spapps.naturelivewallpapers"));
                startActivity(natureLive);
                break;
            case 6://more apps
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://search?q=pub:RockingApps")));
                break;
            case 7://beach live wallpapers
                Intent beachLive = new Intent(Intent.ACTION_VIEW);
                beachLive.setData(Uri.parse("market://details?id=com.spapps.beachlivewallpapers"));
                startActivity(beachLive);
                break;
            case 8://sunrise live wallpapers
                Intent sunriseLive = new Intent(Intent.ACTION_VIEW);
                sunriseLive.setData(Uri.parse("market://details?id=com.spapps.sunriselivewallpapers"));
                startActivity(sunriseLive);
                break;
            case 9://flowers live wallpapers
                Intent flowersLive = new Intent(Intent.ACTION_VIEW);
                flowersLive.setData(Uri.parse("market://details?id=com.spapps.flowerslivewallpapers"));
                startActivity(flowersLive);
                break;
            case 10://Love live wallpapers
                Intent loveLive = new Intent(Intent.ACTION_VIEW);
                loveLive.setData(Uri.parse("market://details?id=com.spapps.lovelivewallpapers"));
                startActivity(loveLive);
                break;
            case 11://Love live wallpapers
                Intent cutecatsLive = new Intent(Intent.ACTION_VIEW);
                cutecatsLive.setData(Uri.parse("market://details?id=com.spapps.cutecatslivewallpapers"));
                startActivity(cutecatsLive);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share_title) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Take a look at Beach Wallpapers App - https://play.google.com/store/apps/details?id=com.spapps.beachwallpapers");
            startActivity(Intent.createChooser(shareIntent,"Share Nature Live Wallpapers App Via"));
            return true;
        }
        if (id == R.id.more_title) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:RockingApps")));
            return true;
        }
        if(id==R.id.licence_title){
            liceceDialog = new AlertDialog.Builder(ImageTextListViewActivity.this);
            liceceDialog.setTitle("Licence");
            liceceDialog.setMessage("All images on Beach Wallpapers are licensed under the Creative Commons Zero (CC0) license. " +
                    "\n\nThis means the images are completely free to be used for any legal purpose."
                    + "\n\nThe images are free for personal and even for commercial use also."
                    + "\n\nYou can modify, change copy and distribute the images."
                    + "\n\nAll without asking for permission or setting a link to the source. So that attribution is not required."
                    + "\n\nThe CC0 license was released by the non-profit organization Creative Commons (CC).\n\nGet more information about the license on the official license page."
                    + "\n\nhttps://creativecommons.org/publicdomain/zero/1.0/"
                    + "\n\nPhotos Credits: https://www.pexels.com/");
            liceceDialog.setCancelable(false);
            liceceDialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert =  liceceDialog.create();
            alert.show();

        }
        if(id==R.id.about_title) {
            dialog = new AlertDialog.Builder(ImageTextListViewActivity.this);
            dialog.setTitle("About");
            dialog.setMessage("Rocking Apps \nBeach Wallpapers \nVersion 1.0");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert =  dialog.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

}

