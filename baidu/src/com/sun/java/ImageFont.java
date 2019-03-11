
import baidu.AuthService;
import baidu.Base64Util;
import baidu.FileUtil;
import baidu.HttpUtil;

import java.net.URLEncoder;

/**
 * 图像文字识别
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class ImageFont {

    public static void main(String[] args) {

        /* 图像文字识别 */
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

        // 本地图片路径
        String filePath = "D:/1987877234.jpg";
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
