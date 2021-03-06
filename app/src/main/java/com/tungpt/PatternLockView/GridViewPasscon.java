package com.tungpt.PatternLockView;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridViewPasscon extends AppCompatActivity implements ILocations,
        ISendDataService, View.OnClickListener {

    private static List<Locations> sListPoint;
    private static List<Locations> sListPointOnMove;
    private static List<Locations> sListPointNoAction;
    private static ImageView[] mImageViews;
    private static TextView[] mTextViews;
    private static Button mButtonReset;
    private static ISendDataService sInterfaceChecked;

    private List<Locations> sListPointRandom;
    private List<Locations> mIconPasscons;
    private Animation mAnimation;
    private AdapterItemPatternLockView mAdapterGridViewSelectIcon;
    private GridView mGridView;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    private TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    private View mViewDrawLine;
    private ImageView mImageViewContent;

    private int count;

    public static Button getButtonReset() {
        return mButtonReset;
    }

    public static TextView[] getmTextViews() {
        return mTextViews;
    }

    public static void setmTextViews(TextView[] mTextViews) {
        GridViewPasscon.mTextViews = mTextViews;
    }

    public static ImageView[] getmImageViews() {
        return mImageViews;
    }

    public static void setmImageViews(ImageView[] mImageViews) {
        GridViewPasscon.mImageViews = mImageViews;
    }

    public static List<Locations> getListPoint() {
        return sListPoint;
    }

    public static void setListPoint(List<Locations> listPoint) {
        GridViewPasscon.sListPoint = listPoint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_passcon);
        DrawLine.setISendDataService(this);
        initView();
    }

    public void initView() {
        sListPoint = new ArrayList<>();
        sListPointOnMove = new ArrayList<>();
        sListPointNoAction = new ArrayList<>();
        sListPointRandom = new ArrayList<>();
        mIconPasscons = new ArrayList<>();
        mGridView = findViewById(R.id.grid_view);
        mViewDrawLine = findViewById(R.id.view_draw_line);
        mImageViewContent = findViewById(R.id.img_content);
        mButtonReset = findViewById(R.id.btn_reset);

        img1 = findViewById(R.id.img_1);
        img2 = findViewById(R.id.img_2);
        img3 = findViewById(R.id.img_3);
        img4 = findViewById(R.id.img_4);
        img5 = findViewById(R.id.img_5);
        img6 = findViewById(R.id.img_6);
        img7 = findViewById(R.id.img_7);
        img8 = findViewById(R.id.img_8);
        img9 = findViewById(R.id.img_9);
        img10 = findViewById(R.id.img_10);

        text1 = findViewById(R.id.text_result1);
        text2 = findViewById(R.id.text_result2);
        text3 = findViewById(R.id.text_result3);
        text4 = findViewById(R.id.text_result4);
        text5 = findViewById(R.id.text_result5);
        text6 = findViewById(R.id.text_result6);
        text7 = findViewById(R.id.text_result7);
        text8 = findViewById(R.id.text_result8);
        text9 = findViewById(R.id.text_result9);
        text10 = findViewById(R.id.text_result10);

        mButtonReset.setOnClickListener(this);

        mImageViews = new ImageView[10];
        mImageViews[0] = img1;
        mImageViews[1] = img2;
        mImageViews[2] = img3;
        mImageViews[3] = img4;
        mImageViews[4] = img5;
        mImageViews[5] = img6;
        mImageViews[6] = img7;
        mImageViews[7] = img8;
        mImageViews[8] = img9;
        mImageViews[9] = img10;

        mTextViews = new TextView[10];
        mTextViews[0] = text1;
        mTextViews[1] = text2;
        mTextViews[2] = text3;
        mTextViews[3] = text4;
        mTextViews[4] = text5;
        mTextViews[5] = text6;
        mTextViews[6] = text7;
        mTextViews[7] = text8;
        mTextViews[8] = text9;
        mTextViews[9] = text10;

        mIconPasscons = PatternLockUtils.getListIcon();
        Collections.shuffle(mIconPasscons);
        for (int i = 0; i < mIconPasscons.size(); i++) {
            Locations locations = mIconPasscons.get(i);
            sListPoint.add(new Locations(0,
                    0,
                    0,
                    locations.getKey(),
                    locations.getImage(),
                    locations.getHint(),
                    i));
        }
        mIconPasscons = sListPoint;
        mAdapterGridViewSelectIcon = new AdapterItemPatternLockView(this, mIconPasscons, this);
        mGridView.setAdapter(mAdapterGridViewSelectIcon);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sendListLocations(List<Locations> list) {
        setListPoint(list);
    }

    @Override
    public void sendData(int position, String key, int image, String hint, int index) {
        if (position == -1) {
            sListPointOnMove.clear();
            for (int i = 0; i < mImageViews.length; i++) {
                mImageViews[i].setVisibility(View.GONE);
                mTextViews[i].setVisibility(View.GONE);
            }

        } else {
            if (position < 10) {
                mAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.item_animation_right_side);
                sListPointOnMove.add(new Locations(0, 0, 0, key, image, hint, index));
                if ((image + "").length() > 1) {
                    mImageViews[position].setVisibility(View.VISIBLE);
                    mImageViews[position].startAnimation(mAnimation);
                    mImageViews[position].setImageResource(image);
                } else {
                    mTextViews[position].setVisibility(View.VISIBLE);
                    mTextViews[position].startAnimation(mAnimation);
                    mTextViews[position].setText(image + "");
                }

            }
        }
    }

    @Override
    public void ResetRequest() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                sInterfaceChecked.ResetRequest();
                sListPointOnMove.clear();
                for (int i = 0; i < mImageViews.length; i++) {
                    mImageViews[i].setVisibility(View.GONE);
                    mTextViews[i].setVisibility(View.GONE);
                }
                break;
            case R.id.btn_confirm:
                mButtonReset.setEnabled(false);
                sListPointOnMove.clear();
                for (int i = 0; i < mImageViews.length; i++) {
                    mImageViews[i].setVisibility(View.GONE);
                    mTextViews[i].setVisibility(View.GONE);
                }
            default:
                break;

        }
    }

    public void randomPattern() {
        sListPointNoAction.clear();
        sListPointRandom.clear();
        for (int i = 0; i < mIconPasscons.size(); i++) {
            int dem = 0;
            Locations location = mIconPasscons.get(i);
            for (int j = 0; j < sListPointOnMove.size(); j++) {
                Locations locations = sListPointOnMove.get(j);
                if (i == locations.getmId()) {
                    dem++;
                }
            }
            if (dem == 0) {
                sListPointNoAction.add(new Locations(0,
                        0,
                        0,
                        location.getKey(),
                        location.getImage(),
                        location.getHint(), i));
            }
        }
        //random B
        Collections.shuffle(sListPointNoAction);
        for (int i = 0; i < sListPointNoAction.size(); i++) {
            Locations location = sListPointNoAction.get(i);
            sListPointRandom.add(new Locations(0,
                    0,
                    0,
                    location.getKey(),
                    location.getImage(),
                    location.getHint(), i));
        }
        for (int i = 0; i < sListPoint.size(); i++) {
            int dem = 0;
            Locations location = null;
            if (sListPointRandom.size() != 0)
                location = sListPointRandom.get(0);
            for (int j = 0; j < sListPointOnMove.size(); j++) {
                Locations locations = sListPointOnMove.get(j);
                if (i == locations.getmId()) {
                    dem = 1;
                    location = locations;
                }
            }
            location.setmId(i);
            sListPoint.set(i, location);
            if (dem != 1) {
                sListPointRandom.remove(0);
            }
        }
        mIconPasscons = sListPoint;
        mAdapterGridViewSelectIcon = new AdapterItemPatternLockView(this, mIconPasscons, this);
        mGridView.setAdapter(mAdapterGridViewSelectIcon);
    }

    public static void setISendDataService(ISendDataService iSendDataService) {
        GridViewPasscon.sInterfaceChecked = iSendDataService;
    }
}
