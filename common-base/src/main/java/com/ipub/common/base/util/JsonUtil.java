package com.ipub.common.base.util;

import com.ipub.common.base.json.Json;
import com.ipub.common.base.svc.ServiceProvider;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * JSON工具接口
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
public interface JsonUtil {
    /**
     * 解析json
     *
     * @param json  String
     * @param clazz Class<T>
     * @param <T>   T
     * @return T
     */
    static <T> T parseFrom(String json, Class<T> clazz) {
        return ServiceProvider.service(Json.class).parseFrom(json, clazz);
    }

    /**
     * 解析json
     *
     * @param json  parseListFrom
     * @param clazz Class<T>
     * @param <T>   T
     * @return List<T>
     */
    static <T> List<T> parseListFrom(String json, Class<T> clazz) {
        return ServiceProvider.service(Json.class).parseListFrom(json, clazz);
    }

    /**
     * 解析json
     *
     * @param stream InputStream
     * @param clazz  Class<T>
     * @param <T>    T
     * @return T
     */
    static <T> T parseFrom(InputStream stream, Class<T> clazz) {
        return ServiceProvider.service(Json.class).parseFrom(stream, clazz);
    }

    /**
     * 解析json
     *
     * @param stream InputStream
     * @param clazz  Class<T>
     * @param <T>    T
     * @return List<T>
     */
    static <T> List<T> parseListFrom(InputStream stream, Class<T> clazz) {
        return ServiceProvider.service(Json.class).parseListFrom(stream, clazz);
    }

    /**
     * 序列化json
     *
     * @param o Object
     * @return String
     */
    static String toJson(Object o) {
        return toJson(o, false);
    }

    /**
     * 序列化json
     *
     * @param o      Object
     * @param pretty boolean
     * @return String
     */
    static String toJson(Object o, boolean pretty) {
        return ServiceProvider.service(Json.class).toJson(o, pretty);
    }

    /**
     * 序列化json
     *
     * @param o      Object
     * @param stream OutputStream
     * @param pretty boolean
     */
    static void writeTo(Object o, OutputStream stream, boolean pretty) {
        ServiceProvider.service(Json.class).writeTo(o, stream, pretty);
    }

    /**
     * 解析map
     *
     * @param map  Map
     * @param type Class<T>
     * @param <T>  T
     * @return T
     */
    @SuppressWarnings("rawtypes")
    static <T> T parseFrom(Map map, Class<T> type) {
        return ServiceProvider.service(Json.class).parseFrom(map, type);
    }
}
