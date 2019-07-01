package com.simon.java.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestDemo {

    private static List<JsonObject> groupOne = new ArrayList<>();
    private static List<JsonObject> groupTwo = new ArrayList<>();
    private static List<JsonObject> groupThree = new ArrayList<>();
    private static List<JsonObject> groupFour = new ArrayList<>();
    private static List<JsonObject> groupFive = new ArrayList<>();
    private static List<JsonObject> groupOther = new ArrayList<>();


    public static void main(String[] args) {

        String path = "F:\\INtellijProject\\BasicKnowledge\\KotlinBase\\src\\main\\java\\com\\simon\\java\\test\\";

        String fileName = "User_Profile.json";

        String readJsonFile = readJsonFile(path, fileName);

        JsonObject jsonObject = new JsonParser().parse(readJsonFile).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
        List<String> userProfileKeys = new ArrayList<>();
        JsonElement OBUVersion = jsonObject.get("OBUUSPID_OBUversion");
        JsonObject version = new JsonObject();
        version.addProperty("OBUUSPID_OBUversion", OBUVersion.getAsString());

        System.out.println(version.toString());

        for (Map.Entry<String, JsonElement> stringJsonElementEntry : entrySet) {
            String key = stringJsonElementEntry.getKey();
            if (key.startsWith("uid")) {
                userProfileKeys.add(key);
            }
        }

        if (userProfileKeys.size() > 0) {
            for (int i = 0; i < userProfileKeys.size(); i++) {
                if (i < 20) {
                    //第一组数据 每组20条
                    initGroup(jsonObject, userProfileKeys, groupOne, i);
                } else if (i < 40) {
                    //第二组数据 每组20条
                    initGroup(jsonObject, userProfileKeys, groupTwo, i);
                } else if (i < 60) {
                    //第三组数据 每组20条
                    initGroup(jsonObject, userProfileKeys, groupThree, i);
                } else if (i < 80) {
                    //第四组数据 每组20条
                    initGroup(jsonObject, userProfileKeys, groupFour, i);
                } else if (i < 100) {
                    //第五组数据 每组20条
                    initGroup(jsonObject, userProfileKeys, groupFive, i);
                } else {
                    //预留拓展使用
                    initGroup(jsonObject, userProfileKeys, groupOther, i);
                }
            }
        }

        try {
            traversalSending(groupOne);
            System.out.println("/n");
            Thread.sleep(3000);
            traversalSending(groupTwo);
            System.out.println("/n");
            traversalSending(groupThree);
            System.out.println("/n");
            traversalSending(groupFour);
            System.out.println("/n");
            traversalSending(groupFive);
            System.out.println("/n");
            traversalSending(groupOther);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void traversalSending(List<JsonObject> group) {
        String path = "F:\\INtellijProject\\BasicKnowledge\\KotlinBase\\src\\main\\java\\com\\simon\\java\\test\\";
        if (group.size() > 0) {
            for (JsonObject item : group) {
                System.out.println(item.toString());
                // 写入数据
                String fileName = "test.json";
                File file = null;
                BufferedSink bufferSink = null;
                try {
                    file = new File(path, fileName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    bufferSink = Okio.buffer(Okio.sink(file));
                    bufferSink.writeString(item.toString(), Charset.forName("utf-8"));
                    bufferSink.close();

                } catch (Exception e) {

                }
            }
        }

    }

    private static void initGroup(JsonObject jsonObject, List<String> userProfileKeys, List<JsonObject> group, int index) {
        JsonElement value = jsonObject.get(userProfileKeys.get(index));
        JsonObject item = new JsonObject();
        item.add(userProfileKeys.get(index), value);
        group.add(item);
    }

    public static String readJsonFile(String filePath, String fileName) {
        String content = null;
        Source source;
        BufferedSource bufferedSource = null;
        try {
            File file = new File(filePath, fileName);
            source = Okio.source(file);
            bufferedSource = Okio.buffer(source);
            content = bufferedSource.readString(Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedSource) {
                    bufferedSource.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

}
