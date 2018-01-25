package com.allenliu.ticket.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.allenliu.ticket.R;


/**
 * Created by allenliu on 2017/8/4.
 */

public class TitleBar extends FrameLayout {
    private int leftImg, rightImg, textColor;
    private String title, font = "1", rightTitle, leftTitle;
    private static final String REGULAR = "1";
    private static final String BOLD = "3";
    private static final String MEDIUM = "2";
    private static final String LIGHT = "4";

    View view;

    public TitleBar(Context context) {
        super(context);
        init(null);
    }


    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
            leftImg = typedArray.getResourceId(R.styleable.TitleBar_left_img, 0);
            rightImg = typedArray.getResourceId(R.styleable.TitleBar_right_img, 0);
            textColor = typedArray.getColor(R.styleable.TitleBar_title_text_color, Color.WHITE);
            title = typedArray.getString(R.styleable.TitleBar_title);
            rightTitle = typedArray.getString(R.styleable.TitleBar_right_title);
            leftTitle = typedArray.getString(R.styleable.TitleBar_left_title);

            font = typedArray.getString(R.styleable.TitleBar_title_font);
            view = LayoutInflater.from(getContext()).inflate(R.layout.fooyo_titlebar_layout, null);
            ImageView left = (ImageView) view.findViewById(R.id.iv_left);
            ImageView right = (ImageView) view.findViewById(R.id.iv_right);
            TextView titleView = (TextView) view.findViewById(R.id.tv_title);
            TextView rightTextView = (TextView) view.findViewById(R.id.tv_right);
            TextView leftTextView = (TextView) view.findViewById(R.id.tv_left);


//            LogUtils.e(font);
//            if (font.equalsIgnoreCase(REGULAR)) {
//                FooyoFontUtil.applyFontRegular(titleView, rightTextView, leftTextView);
//            } else if (font.equalsIgnoreCase(BOLD)) {
//                FooyoFontUtil.applyFontBold(titleView, rightTextView, leftTextView);
//            } else if (font.equalsIgnoreCase(MEDIUM)) {
//                FooyoFontUtil.applyFontMedium(titleView, rightTextView, leftTextView);
//            } else if (font.equalsIgnoreCase(LIGHT)) {
//                FooyoFontUtil.applyFontLight(titleView, rightTextView, leftTextView);
//            }
            titleView.setTextColor(textColor);
            if (leftImg != 0)
                left.setImageResource(leftImg);
            if (rightImg != 0)
                right.setImageResource(rightImg);
            if (title != null && !title.isEmpty())
                titleView.setText(title);
            if (rightTitle != null && !rightTitle.isEmpty())
                rightTextView.setText(rightTitle);
            if (leftTitle != null && !leftTitle.isEmpty())
                leftTextView.setText(leftTitle);
            addView(view);
            typedArray.recycle();
        } else {

        }

    }

    public TitleBar setLeftImg(int leftImg) {
        this.leftImg = leftImg;
        ImageView left = (ImageView) view.findViewById(R.id.iv_left);
        if (leftImg == 0)
            left.setVisibility(GONE);
        else {
            left.setVisibility(VISIBLE);
            left.setImageResource(leftImg);
        }
        return this;
    }

    public TitleBar setLeftOnClickListener(OnClickListener listener) {
        view.findViewById(R.id.linear_left_container).setOnClickListener(listener);
        return this;
    }

    public TitleBar setRightOnClickListener(OnClickListener listener) {
        view.findViewById(R.id.linear_right_container).setOnClickListener(listener);
        return this;

    }

    public TitleBar setTitle(String title) {
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        return this;
    }

    public TitleBar setTitleTextColor(@ColorInt int color) {
        ((TextView) view.findViewById(R.id.tv_title)).setTextColor(color);
        return this;
    }

}
