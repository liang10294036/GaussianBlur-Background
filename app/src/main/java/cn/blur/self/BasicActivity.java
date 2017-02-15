package cn.blur.self;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/2.
 * <p>
 * 项目中 所有activity的基类
 */

public abstract class BasicActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        ActivityStack.getInstance().pushActivity(this);
        initToolbar();
        initView(getIntent().getExtras());
    }

    /**
     * 获取加载页面id
     * 不可为空
     */
    protected abstract int getLayoutId();
    /**
     * 当前界面的id
     *
     * @return
     */
    protected abstract String getCurrentId();

    /**
     * view binding
     *
     * @param bundle
     */
    protected abstract void initView(Bundle bundle);

    /**
     * 获取控件
     */
    @SuppressWarnings("unchecked")
    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }


    protected Toolbar setHideToolbar(boolean isHide) {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        }
        if (isHide) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }

        return toolbar;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);//  释放资源
    }

    /**
     * 添加toolbar标题
     */
    public void setToolbarTitle(String resText) {
        if (toolbar != null) {
            if (tvTitle == null) {
                tvTitle = (TextView) findViewById(R.id.app_title_text);
            }
            tvTitle.setText(resText);
        }
    }


    /**
     * toolbar 添加左边按钮以及点击事件
     *
     * @param resId
     */
    protected void setToolbarNavigationIcon(int resId) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(resId);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLeftMenu();
                }
            });
        }
    }


    /**
     * toolbar左边按钮响应点击事件
     */
    public void onClickLeftMenu() {
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        onViewClick(v.getId());
    }

    protected void onViewClick(int key) {
    }

    /**
     * 隐藏输入键盘
     *
     * @param
     */
    public static void hideSortInput(View v) {
        InputMethodManager inputmanger = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivityStack.getInstance().popActivity(this);
    }






}
