package cn.org.imaginary.web.restapi.common.dao.cache;

import java.util.Set;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:14
 * @see :
 */
public interface Cache {
    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 获取缓存 泛型
     *
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> tClass);

    /**
     * 缓存大小
     *
     * @return
     */
    int size();


    /**
     * 根据正则获取缓存key
     *
     * @param regx
     * @return
     */
    Set<String> keys(String regx);

    /**
     * 获取所有缓存
     *
     * @return
     */
    Set<String> keys();

    /**
     * 移除缓存
     *
     * @param key
     */
    void remove(String key);

    /**
     * 缓存是否存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);
}
