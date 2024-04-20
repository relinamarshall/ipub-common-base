package com.ipub.common.base.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * FastJson2实现
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
public class FastJsonImpl implements Json {

    static {
        log.info("init FastJsonImpl, version={}", JSON.VERSION);
    }

    /**
     * 从给定的JSON字符串中解析对象
     *
     * @param json  JSON字符串
     * @param clazz 对象的类型
     * @return 解析得到的对象
     */
    @Override
    public <T> T parseFrom(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 从给定的JSON字符串中解析对象列表
     *
     * @param json  JSON字符串
     * @param clazz 对象的类型
     * @return 解析得到的对象列表
     */
    @Override
    public <T> List<T> parseListFrom(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * 从给定的输入流中解析对象
     *
     * @param stream 输入流
     * @param clazz  对象的类型
     * @return 解析得到的对象
     */
    @Override
    public <T> T parseFrom(InputStream stream, Class<T> clazz) {
        return JSON.parseObject(stream, clazz);
    }

    /**
     * 从给定的输入流中解析对象列表
     *
     * @param stream 输入流
     * @param clazz  对象的类型
     * @return 解析得到的对象列表
     */
    @Override
    public <T> List<T> parseListFrom(InputStream stream, Class<T> clazz) {
        return JSON.parseArray(stream).toList(clazz);
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param o      对象
     * @param pretty 是否开启格式化输出
     * @return 转换得到的JSON字符串
     */
    @Override
    public String toJson(Object o, boolean pretty) {
        return JSON.toJSONString(o, pretty ? new JSONWriter.Feature[]{JSONWriter.Feature.PrettyFormat} :
                new JSONWriter.Feature[0]);
    }

    /**
     * 将对象写入到给定的输出流中
     *
     * @param o      对象
     * @param stream 输出流
     * @param pretty 是否开启格式化输出
     */
    @Override
    public void writeTo(Object o, OutputStream stream, boolean pretty) {
        JSON.writeTo(stream, o, pretty ? new JSONWriter.Feature[]{JSONWriter.Feature.PrettyFormat} :
                new JSONWriter.Feature[0]);
    }

    /**
     * 将Map转换为对象
     *
     * @param map  Map
     * @param type 对象类型
     * @return 转换得到的对象
     */
    @SuppressWarnings("rawtypes")
    @Override
    public <T> T parseFrom(Map map, Class<T> type) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toJavaObject(type);
    }
}
