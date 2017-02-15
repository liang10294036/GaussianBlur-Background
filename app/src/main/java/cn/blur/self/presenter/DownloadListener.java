package cn.blur.self.presenter;

import java.util.List;

import cn.blur.self.module.ResourceTemp;

/**
 * Created by MyPC on 2017/2/15.
 */
public interface DownloadListener {

    void onItemClick(ResourceTemp info);

    void onItemDownloadClick(ResourceTemp resourceTemp);

    void onItemSharedClick(ResourceTemp resourceTemp);

    /**
     * 资源请求 成功
     */
    void httpResourceSuccess(List<ResourceTemp> resourceTempList);
}
