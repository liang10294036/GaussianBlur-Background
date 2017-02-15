package cn.blur.self.okHttp.gateway;

import cn.blur.self.okHttp.ResultCallback;

/**
 * Created by lkk on 2016/5/10.
 */
interface Api {


    // API域名

    String API_BASE_DOCTOR = "http://xl.1000mob.com";//线上接口地址 app域名

    /**
     * 获取赚陌宝界面数据
     */
    String APP_GET_INTEGRATION_RES = "/configAPI/findSimpleResByNavModuleId";



    /**
     * @param user_id       用户id
     * @param nav_module_id 模块id
     * @param callback
     */
    void httpGetIntegrationResource(String user_id, String nav_module_id, ResultCallback callback);



}