package com.sun.image;

import java.net.URLEncoder;
import com.sun.image.baidu.AuthService;
import com.sun.image.baidu.HttpUtil;

public class ImageExcelResult {

    /**
     * 图像文字识别
     */
    public static void main(String[] args) {

        //返回    result:{"result":[{"request_id":"14681867_668303"}],"log_id":15414123806946949}
        String resultHost = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/get_request_result";

        try {
            String params = URLEncoder.encode("request_id", "UTF-8") + "=" + URLEncoder.encode("14681867_689739", "UTF-8");
            params += "&" + URLEncoder.encode("result_type", "UTF-8") + "=" + URLEncoder.encode("excel", "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(resultHost, accessToken, params);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
