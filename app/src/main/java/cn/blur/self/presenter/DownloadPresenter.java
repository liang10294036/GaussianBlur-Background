package cn.blur.self.presenter;

import android.content.Context;

import java.util.List;

import cn.blur.self.module.ResourceTemp;
import cn.blur.self.okHttp.Result;
import cn.blur.self.okHttp.ResultCallback;
import cn.blur.self.okHttp.gateway.AbsGateway;
import cn.blur.self.utils.ListUtils;
import cn.blur.self.widget.CustomProgressDialog;

/**
 * Created by MyPC on 2017/2/15.
 */

public class DownloadPresenter {

    public final static String USER_ID = "780704307954716672";

    DownloadListener instance;

    public DownloadPresenter(DownloadListener instance) {
        this.instance = instance;
    }


    /**
     * 网络获取资源
     *
     * @param model_id
     */
    public void getQueryResource(Context cxt,  String model_id) {
        AbsGateway.getInstance().httpGetIntegrationResource(USER_ID, model_id, new ResultResultCallback(cxt, model_id));
    }

    /**
     * 请求网路资源回调
     */
    class ResultResultCallback extends ResultCallback<Result<List<ResourceTemp>>> {
        String model_id;
        Context cxt;
        CustomProgressDialog dialog;

        public ResultResultCallback(Context cxt, String model_id) {
            this.cxt = cxt;
            this.model_id = model_id;
        }

        @Override
        public void onStart() {
            dialog = new CustomProgressDialog(cxt);
            dialog.showDialog();
        }

        @Override
        public void onFinish() {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }

        @Override
        public void onResponse(Result<List<ResourceTemp>> response) {

            List<ResourceTemp> resourceTemps = (List<ResourceTemp>) response.getResult();
            if (!ListUtils.isEmpty(resourceTemps)) {
                instance.httpResourceSuccess(resourceTemps);
            }
        }


    }

}
