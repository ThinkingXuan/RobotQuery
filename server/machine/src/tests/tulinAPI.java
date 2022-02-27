import com.njcit.utils.GsonUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class tulinAPI {

    public static void post() throws IOException {
//创建连接
        URL url = new URL("http://www.tuling123.com/openapi/api");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());

        bean bean1 = new bean();
        bean1.setKey("916bb1632c9247ee94f73e7fdd46db5d");
        bean1.setInfo("今天天气怎么样");
//        bean1.setUserid("");

        out.writeBytes( GsonUtil.ObjectToJson(bean1, bean1.getClass()));
        System.out.println( GsonUtil.ObjectToJson(bean1, bean1.getClass()));
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }
        System.out.println(sb);
        reader.close();
        // 断开连接
        connection.disconnect();

    }

    public static void main(String[] args) throws IOException {
        tulinAPI.post();
    }



}
