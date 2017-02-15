package cn.blur.self.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.blur.self.BaseApplication;
import cn.blur.self.R;
import cn.blur.self.module.ResourceTemp;
import cn.blur.self.presenter.DownloadListener;


/**
 * Created by MyPC on 2016/12/2.
 */

public class DownloadPagerAdapter extends PagerAdapter {

    Context mContext;
    /**
     * 资源数据
     */
    List<ResourceTemp> datas;

    DownloadListener instance;


    public DownloadPagerAdapter(Context cxt, List<ResourceTemp> datas, DownloadListener instance) {
        this.instance = instance;
        this.mContext = cxt;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pager_download, null);
        ResourceTemp mDownloadInfo = datas.get(position);
        SimpleDraweeView bgImage = (SimpleDraweeView) view.findViewById(R.id.facebook_movie_bg);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_res_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_res_sub_title);
        TextView tvEnjoy = (TextView) view.findViewById(R.id.tv_res_enjoy);

        TextView tvShared = (TextView) view.findViewById(R.id.tv_shared_qm);
        TextView tvDownload = (TextView) view.findViewById(R.id.tv_download_qm);
        TextView tvInstall = (TextView) view.findViewById(R.id.tv_install_qm);
        TextView tvOpen = (TextView) view.findViewById(R.id.tv_open_qm);
        ImageView ivSharedPicture = (ImageView) view.findViewById(R.id.iv_shared_main);

        ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.iv_download_progress);// 进度条


        tvDownload.setText(mContext.getString(R.string.integration_format_teo, mDownloadInfo.getDownload_msg() == null ? "" : mDownloadInfo.getDownload_msg(), mDownloadInfo.getDownload_score() == null ? 0 : mDownloadInfo.getDownload_score()));
        tvInstall.setText(getValue(mDownloadInfo.getDownload_score(), mDownloadInfo.getInstall_score(), mDownloadInfo.getInstall_msg()));
        tvOpen.setText(getValue(mDownloadInfo.getDownload_score(), mDownloadInfo.getActive_score(), mDownloadInfo.getActive_msg()));
        tvShared.setText(getValue(mDownloadInfo.getDownload_score(), mDownloadInfo.getShare_score(), mDownloadInfo.getShare_msg()));

        bgImage.setImageURI(Uri.parse(mDownloadInfo.getBackground_img()));
        tvTitle.setText(mDownloadInfo.getRes_name());

        tvSubTitle.setText(mDownloadInfo.getRes_title());
        if (mDownloadInfo.getRes_type() == 3) {
            tvEnjoy.setText(mContext.getString(R.string.app_program_enjoy_format, mDownloadInfo.getDownload_num() == null ? 0 : mDownloadInfo.getDownload_num()));
        } else {
            tvEnjoy.setText(mContext.getString(R.string.app_game_enjoy_format, mDownloadInfo.getDownload_num() == null ? "0" : mDownloadInfo.getDownload_num()));
        }
        container.addView(view, position);
        setListener(bgImage, mDownloadInfo);

        if (mDownloadInfo != null && mDownloadInfo.getShare_status() != null && mDownloadInfo.getShare_status() == 1) {
            tvShared.setTextColor(BaseApplication.getInstance().getResources().getColor(R.color.color_29d2ff));
            ivSharedPicture.setImageResource(R.mipmap.ic_integration_shared);
            setSharedListener(ivSharedPicture, mDownloadInfo);
            setSharedListener(tvShared, mDownloadInfo);
            setSharedListener(view.findViewById(R.id.tv_res_shared), mDownloadInfo);
        } else {
            ivSharedPicture.setImageResource(R.mipmap.ic_integration_shared_grey);
            tvShared.setTextColor(BaseApplication.getInstance().getResources().getColor(R.color.color_d9e1e8));
        }


        return view;
    }

    private String getValue(Integer baseNum, Integer curNum, String val) {
        if (val == null) {
            val = "";
        }
        if (baseNum == null) {
            baseNum = 0;
        }
        if (curNum == null) {
            curNum = 0;
        }
        if (curNum != 0 && baseNum != 0 && baseNum < curNum) {
            return mContext.getString(R.string.double_format_teo, val, curNum / baseNum);
        } else {
            return mContext.getString(R.string.integration_format_teo, val, curNum);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }




    public String getBackgroundUrl(int index) {
        if (datas.size() > index) {
            ResourceTemp mDownloadInfo = datas.get(index);
            if (mDownloadInfo != null) {
                return mDownloadInfo.getBackground_img();
            }
        }
        return "";

    }

    /**
     * 图片监听
     *
     * @param view
     */
    private void setListener(View view, final ResourceTemp mDownloadInfo) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (instance != null) {
                    instance.onItemClick(mDownloadInfo);
                }
            }
        });
    }


    /**
     * 分享按钮 监听
     *
     * @param view
     * @param mDownloadInfo
     */
    private void setSharedListener(View view, final ResourceTemp mDownloadInfo) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (instance != null) {
                    instance.onItemSharedClick(mDownloadInfo);
                }
            }
        });
    }
}