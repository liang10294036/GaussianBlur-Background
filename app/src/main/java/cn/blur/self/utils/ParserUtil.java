package cn.blur.self.utils;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cn.blur.self.BaseApplication;
import cn.blur.self.module.ResourceTemp;

/**
 * Created by MyPC on 2017/2/15.
 */

public class ParserUtil {

    public static  List<ResourceTemp> jsonParser() {
        //从JSon文件读取数据
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader br = null;
        InputStream in = null;
        String line;
        try {
            in = BaseApplication.getInstance().getResources().getAssets().open("data.json");
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }
            //将Json文件数据形成JSONObject对象
            JSONArray jsonObject = new JSONArray(stringBuffer.toString());
            List<ResourceTemp> resourceTemps = BaseApplication.getInstance().getGson().fromJson(jsonObject.toString(), new TypeToken<List<ResourceTemp>>() {}.getType());
            return resourceTemps;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
