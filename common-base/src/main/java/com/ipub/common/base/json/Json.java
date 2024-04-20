package com.ipub.common.base.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * JSON解析接口
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public interface Json {
    /**
     * 解析json字符串为对象
     *
     * @param json  json字符串
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型
     * @return 目标对象
     */
    <T> T parseFrom(String json, Class<T> clazz);

    /**
     * 解析json字符串为对象列表
     *
     * @param json  json字符串
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型
     * @return 目标对象列表
     */
    <T> List<T> parseListFrom(String json, Class<T> clazz);

    /**
     * 从InputStream流解析对象
     *
     * @param stream json流
     * @param clazz  目标对象类型
     * @param <T>    目标对象类型
     * @return 目标
     */
    <T> T parseFrom(InputStream stream, Class<T> clazz);

    /**
     * 从InputStream流解析对象列表
     *
     * @param stream json流
     * @param clazz  目标对象类型
     * @param <T>    目标对象类型
     * @return 目标对象列表
     */
    <T> List<T> parseListFrom(InputStream stream, Class<T> clazz);

    /**
     * 将对象序列化为json字符串
     *
     * @param o      对象
     * @param pretty 是否格式化
     * @return json字符串
     */
    String toJson(Object o, boolean pretty);

    /**
     * 将对象序列化为json字符串
     *
     * @param o      对象
     * @param pretty 是否格式化
     */
    void writeTo(Object o, OutputStream stream, boolean pretty);

    /**
     * 解析map为对象
     *
     * @param map  map
     * @param type 目标对象类型
     * @param <T>  目标对象类型
     * @return 目标对象
     */
    @SuppressWarnings("rawtypes")
    <T> T parseFrom(Map map, Class<T> type);
}
