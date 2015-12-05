package com.spapps.beachwallpapers;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.spapps.beachwallpapers.GestureUtil.SimpleGestureListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import static com.spapps.beachwallpapers.HeavyLifter.FAIL;
import static com.spapps.beachwallpapers.HeavyLifter.SUCCESS;

public class MainActivity extends Activity implements SimpleGestureListener {

       private static final List<Integer> backgrounds = new ArrayList<Integer>();
       private static final int TOTAL_IMAGES;
       private GestureUtil detector;
       Button button;
       InterstitialAd mInterstitialAd;
       private static final String AD_INTERSTITIAL_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
        static {
        backgrounds.add(R.mipmap.beach_1);        backgrounds.add(R.mipmap.beach_2);
        backgrounds.add(R.mipmap.beach_3);      backgrounds.add(R.mipmap.beach_4);
        backgrounds.add(R.mipmap.beach_5);       backgrounds.add(R.mipmap.beach_6);
        backgrounds.add(R.mipmap.beach_7);      backgrounds.add(R.mipmap.beach_8);
        backgrounds.add(R.mipmap.beach_9);       backgrounds.add(R.mipmap.beach_10);
        backgrounds.add(R.mipmap.beach_11);     backgrounds.add(R.mipmap.beach_12);
        backgrounds.add(R.mipmap.beach_13);        backgrounds.add(R.mipmap.beach_14);
            backgrounds.add(R.mipmap.beach_15);      backgrounds.add(R.mipmap.beach_16);
            backgrounds.add(R.mipmap.beach_17);       backgrounds.add(R.mipmap.beach_18);
            backgrounds.add(R.mipmap.beach_19);      backgrounds.add(R.mipmap.beach_22);
            backgrounds.add(R.mipmap.beach_21);       backgrounds.add(R.mipmap.beach_22);
            backgrounds.add(R.mipmap.beach_23);     backgrounds.add(R.mipmap.beach_24);
            backgrounds.add(R.mipmap.beach_25);      backgrounds.add(R.mipmap.beach_26);
            backgrounds.add(R.mipmap.beach_27);       backgrounds.add(R.mipmap.beach_28);
            backgrounds.add(R.mipmap.beach_29);     backgrounds.add(R.mipmap.beach_30);
            backgrounds.add(R.mipmap.beach_31);       backgrounds.add(R.mipmap.beach_32);
            backgrounds.add(R.mipmap.beach_33);     backgrounds.add(R.mipmap.beach_34);
            backgrounds.add(R.mipmap.beach_35);      backgrounds.add(R.mipmap.beach_36);

            TOTAL_IMAGES = (backgrounds.size() - 1);
    }

        private int currentPosition = 0;
        private ImageView backgroundPreview;
        private HeavyLifter chuckNorris;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent positionIntent = getIntent();
        int position = positionIntent.getExtras().getInt("id");
        currentPosition = position;

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(AD_INTERSTITIAL_UNIT_ID);
        AdRequest mIntestirtialAd = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(mIntestirtialAd);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });

        backgroundPreview = (ImageView) findViewById(R.id.backgroundPreview);
        button = (Button)findViewById(R.id.shareButton);
        changePreviewImage(currentPosition);
        chuckNorris = new HeavyLifter(this, chuckFinishedHandler);
        detector = new GestureUtil(this, this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImage(currentPosition);
            }
        });

    }
    public void gotoPreviousImage(View v) {
        int positionToMoveTo = currentPosition;
        positionToMoveTo--;
        if(positionToMoveTo < 0){
            positionToMoveTo = TOTAL_IMAGES;
        }
        changePreviewImage(positionToMoveTo);
    }
    public void setAsWallpaper(View v) {
        int resourceId = backgrounds.get(currentPosition);
        chuckNorris.setResourceAsWallpaper(resourceId);
    }

    public void gotoNextImage(View v) {
        int positionToMoveTo = currentPosition;
        positionToMoveTo++;
        if(currentPosition == TOTAL_IMAGES){
            positionToMoveTo = 0;
        }
        changePreviewImage(positionToMoveTo);
    }

    public void changePreviewImage(int pos) {
        currentPosition = pos;
        backgroundPreview.setImageResource(backgrounds.get(pos));
        Log.d("Main", "Current position: " + pos);
    }

    public void displayInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void shareImage(int imagePosition)
    {   currentPosition = imagePosition;
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+backgrounds.get(imagePosition));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {

        switch (direction) {
            case GestureUtil.SWIPE_LEFT:
                leftMove();
                break;
            case GestureUtil.SWIPE_RIGHT:
                rightMove();
                break;
            case GestureUtil.SWIPE_DOWN:
                break;
            case GestureUtil.SWIPE_UP:
                break;
        }
    }

    private void leftMove() {
        int positionToMoveTo = currentPosition;
        positionToMoveTo++;
        if(currentPosition == TOTAL_IMAGES){
            positionToMoveTo = 0;
        }

        changePreviewImage(positionToMoveTo);
    }
    void rightMove() {
        int positionToMoveTo = currentPosition;
        positionToMoveTo--;
        if(positionToMoveTo < 0){
            positionToMoveTo = TOTAL_IMAGES;
        }
        changePreviewImage(positionToMoveTo);
    }

    @Override
    public void onDoubleTap() {
        //Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    Handler chuckFinishedHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case SUCCESS:
                    Toast.makeText(MainActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
                    break;
                case FAIL:
                    Toast.makeText(MainActivity.this, "Uh oh, can't do that right now", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
}