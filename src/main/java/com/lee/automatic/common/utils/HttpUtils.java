package com.lee.automatic.common.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * http请求
 *
 * @author Lee
 */
public class HttpUtils {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * 发送get请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param params  参数
     */
    public static Response get(String url,
                               @Nullable Map<String, String> headers,
                               @Nullable Map<String, Object> params) throws IOException {

        url = spliceUrl(url, params);

        Request.Builder request = new Request.Builder().url(url);

        if (Objects.nonNull(headers) && !headers.isEmpty()) {
            headers.forEach((k, v) -> request.addHeader(k, v));
        }

        return client.newCall(request.build()).execute();
    }

    /**
     * 发送post请求
     *
     * @param url     链接
     * @param headers 请求头
     * @param body    请求体
     */
    public static Response post(String url,
                                @Nullable Map<String, String> headers,
                                @Nullable Object body) throws IOException {

        Request.Builder request = new Request.Builder().url(url);

        RequestBody requestBody;
        if (Objects.nonNull(body)) {
            requestBody = RequestBody.create(JSONObject.toJSONString(body), JSON);
        } else {
            requestBody = RequestBody.create("", null);
        }
        request.post(requestBody);

        if (Objects.nonNull(headers) && !headers.isEmpty()) {
            headers.forEach((k, v) -> request.addHeader(k, v));
        }

        return client.newCall(request.build()).execute();
    }

    /**
     * 发送post请求(form形式发送)
     *
     * @param url     链接
     * @param headers 请求头
     * @param body    请求体
     */
    public static Response postForm(String url,
                                    @Nullable Map<String, String> headers,
                                    Map<String, String> body) throws IOException {

        FormBody.Builder formBody = new FormBody.Builder();

        if (Objects.nonNull(body) && !body.isEmpty()) {
            body.forEach((k, v) -> formBody.add(k, v));
        }

        Request.Builder request = new Request.Builder()
                .url(url)
                .post(formBody.build());

        if (Objects.nonNull(headers) && !headers.isEmpty()) {
            headers.forEach((k, v) -> request.addHeader(k, v));
        }

        return client.newCall(request.build()).execute();
    }

    /**
     * 拼接url
     *
     * @param url    链接
     * @param params 参数
     * @return 拼接好的链接
     */
    public static String spliceUrl(String url, Map<String, Object> params) {

        if (Objects.isNull(url) || Objects.isNull(params) || params.isEmpty()) {
            return url;
        }

        StringBuilder link = new StringBuilder(url);
        link.append("?");
        params.forEach((k, v) ->
                link.append(k)
                        .append("=")
                        .append(v)
                        .append("&")
        );

        link.deleteCharAt(link.length() - 1);

        return link.toString();
    }

}
