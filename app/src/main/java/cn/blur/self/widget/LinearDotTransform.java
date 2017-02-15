package cn.blur.self.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.blur.self.R;


/**
 * Created by MyPC on 2016/10/20.
 */

public class LinearDotTransform extends LinearLayout {

    Context mContext;

    ImageView currentImageView;

    /**
     * 焦点
     */
    int resSelect;
    /**
     * 非焦点
     */
    int resNormal;

    /**
     * 间隔距离
     */
    int interval = 5;

    public LinearDotTransform(Context context) {
        super(context);
        init(context);
    }

    public LinearDotTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LinearDotTransform(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        resSelect = R.drawable.bg_circle_2290f9_dot; // 默认
        resNormal = R.drawable.bg_circle_white_dot;
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }


    /**
     * 设置切换图标
     * @param resSelect
     * @param resNormal
     */
    public void setResDrawable(int resSelect, int resNormal){
        this.resSelect = resSelect;
        this.resNormal = resNormal;
    }

    public void createTabItems(int count, int currentCount) {
        removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = interval;
            params.rightMargin = interval;
            params.gravity = Gravity.CENTER;
            imageView.setLayoutParams(params);
            if (currentCount == i) {
                imageView.setImageResource(resSelect);
                currentImageView = imageView;
            } else {
                imageView.setImageResource(resNormal);
            }
            addView(imageView);
        }
    }


    public void setUpSelected(int position) {
        if (currentImageView != null) {
            currentImageView.setImageResource(resNormal);
        }
        ImageView mImageView = (ImageView) getChildAt(position);
        if (mImageView != null) {
            mImageView.setImageResource(resSelect);
            currentImageView = mImageView;
        }
    }




}