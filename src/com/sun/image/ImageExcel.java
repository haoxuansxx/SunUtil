package com.sun.image;

import java.net.URLEncoder;
import com.sun.image.baidu.AuthService;
import com.sun.image.baidu.FileUtil;
import com.sun.image.baidu.HttpUtil;
import com.baidu.aip.util.Base64Util;

/**
 * 图像文字识别
 * @author Sun
 * @date 2019-02-20
 */
public class ImageExcel {

    public static void main(String[] args) {

        /* 图像文字识别 */
        String otherHost = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";

        // 本地图片路径
        String filePath = "D:/test.png";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
