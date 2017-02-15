package cn.blur.self;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import java.util.List;
import cn.blur.self.adapter.DownloadPagerAdapter;
import cn.blur.self.config.AppPagerConfig;
import cn.blur.self.module.ResourceTemp;
import cn.blur.self.presenter.DownloadListener;
import cn.blur.self.presenter.DownloadPresenter;
import cn.blur.self.snappy.DBConfigUtils;
import cn.blur.self.snappy.DBFactoryUtils;
import cn.blur.self.utils.ListUtils;
import cn.blur.self.utils.blur.BlurDrawableUtils;
import cn.blur.self.widget.LinearDotTransform;
import cn.blur.self.widget.ZoomOutPageTransformer;

public class MainActivity extends BasicActivity implements DownloadListener, ViewPager.OnPageChangeListener{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    ViewPager mViewPager;
    FrameLayout frameLayout;
    DownloadPagerAdapter adapter;
    DownloadPresenter instance;

    final String modelId = "1106";

    /**
     * 导航点
     */
    LinearDotTransform linearDotTransform;



    @Override
    protected String getCurrentId() {
        return AppPagerConfig.INTEGRATION_PLAY;
    }

    @Override
    protected void initView(Bundle bundle) {
        instance = new DownloadPresenter(this);

        frameLayout = (FrameLayout) findViewById(R.id.linear_frame_back);
        mViewPager = (ViewPager) findViewById(R.id.vp_resource);
        linearDotTransform = getView(R.id.linear_dot_transform);
        getView(R.id.tv_refresh_times).setOnClickListener(this);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);

        linearDotTransform.setResDrawable(R.mipmap.ic_selected_page, R.mipmap.ic_select_page);

        List<ResourceTemp> datas = DBFactoryUtils.getInstance().getArrayList(DBConfigUtils.DB_DOWNLOAD, ResourceTemp.class);
//        List<ResourceTemp> datas = ParserUtil.jsonParser(); // 可切换本地数据

        if (!ListUtils.isEmpty(datas)) {
            adapter = new DownloadPagerAdapter(this, datas, this);
            mViewPager.setAdapter(adapter);
            linearDotTransform.createTabItems(adapter.getCount(), mViewPager.getCurrentItem());
            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    BlurDrawableUtils.setBlurBack(frameLayout, adapter.getBackgroundUrl(0));
                }
            });
        }
        instance.getQueryResource(this, modelId);

    }

    @Override
    protected void onViewClick(int key) {
        switch (key) {
            case R.id.tv_refresh_times:
                instance.getQueryResource(this, modelId);
                break;

        }
    }

    @Override
    public void onItemClick(ResourceTemp info) {

    }

    @Override
    public void onItemDownloadClick(ResourceTemp info) {
    }



    @Override
    public void onItemSharedClick(ResourceTemp resourceTemp) {


    }

    @Override
    public void httpResourceSuccess(List<ResourceTemp> resourceTempList) {
        adapter = new DownloadPagerAdapter(this, resourceTempList, this);
        mViewPager.setAdapter(adapter);
        linearDotTransform.createTabItems(adapter.getCount(), mViewPager.getCurrentItem());
        DBFactoryUtils.getInstance().putList(DBConfigUtils.DB_DOWNLOAD, resourceTempList);
        frameLayout.post(new Runnable() {
            @Override
            public void run() {
                BlurDrawableUtils.setBlurBack(frameLayout, adapter.getBackgroundUrl(0));
            }
        });
    }

    int position;
    int isPageSelected = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        linearDotTransform.setUpSelected(position);
        if (isPageSelected == 2) {
            isPageSelected = 0;
            BlurDrawableUtils.setBlurBack(frameLayout, adapter.getBackgroundUrl(position));
        } else {
            isPageSelected = 1;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 0) {// 等待切换动画执行完毕
            if (isPageSelected == 1) {
                isPageSelected = 0;
                BlurDrawableUtils.setBlurBack(frameLayout, adapter.getBackgroundUrl(position));
            } else {
                isPageSelected = 2;
            }
        }

    }
}
