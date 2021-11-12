package com.simon.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.simon.java.util.MD5Utils;
import com.simon.java.util.StringUtils;
import io.reactivex.Observable;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class JsonTest {
    private final static Gson outer;
    public static final String SIGN_KEY = "F1F2EE3CCD4A3A73B728DD154FC32F1C";

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(JsonBean.class, new DataDeserializer());
        outer = gsonBuilder.create();
    }

    public static void main(String[] args) {
        String json = readJsonFile("src/main/resourse/test.json");
        System.out.println(json);
        System.out.println("\n\n\n");
        System.out.println(getSignJson(json, SIGN_KEY));
        System.out.println("\n\n\n");
        System.out.println(getSignGson(json, SIGN_KEY));
    }

    public static class DataDeserializer implements JsonDeserializer<Object> {

        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            System.out.println(json);
            return null;
        }
    }

    /**
     * 读取json文件，返回json串
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public class JsonBean<T> {
        @SerializedName("content")
        public T data;
    }

    public static String getSignGson(String requestParams, String privateKey) {
        String queryStr = getSortedStr(outer.fromJson(requestParams, new TypeToken<Map<String, String>>() {
        }.getType()));
        String unsignStr = queryStr + "&key=" + privateKey;
        return Objects.requireNonNull(MD5Utils.getMD5(unsignStr)).toUpperCase();
    }

    public static String getSignJson(String requestParams, String privateKey) {
        String queryStr = getSortedStr(JSON.parseObject(requestParams, new TypeReference<Map<String, String>>() {
        }.getType()));
        String unsignStr = queryStr + "&key=" + privateKey;
        return Objects.requireNonNull(MD5Utils.getMD5(unsignStr)).toUpperCase();
    }


    /**
     * Map排序去除空值并拼接为query string
     *
     * @param unSortedMap 未排序的 map
     * @return 排序后的 map 的字符串形式
     */
    public static String getSortedStr(Map<String, String> unSortedMap) {
        if (unSortedMap == null || unSortedMap.isEmpty()) {
            return "";
        }
        StringBuilder builder = Observable.fromIterable(unSortedMap.entrySet())
                .filter(entry -> !StringUtils.isEmpty(entry.getValue()))
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    System.out.println(entry.getKey() + "=" + entry.getValue());
                    return entry.getKey() + "=" + entry.getValue();
                })
                .collectInto(new StringBuilder(), (stringBuilder, s) -> {
                    stringBuilder.append('&').append(s);
                })
                .blockingGet();
        return builder.length() > 1 ? builder.substring(1) : "";
    }
}
