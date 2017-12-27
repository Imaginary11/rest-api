package cn.org.imaginary.web.restapi.common.dao.cache.redis;

import cn.org.imaginary.web.restapi.common.dao.cache.Cache;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:16
 * @see : Redis Cache 配置接口
 */
public interface ConfigurableRedisCache extends Cache {
    /**
     * 过期Redis key
     *
     * @param key
     */
    void expire(String key);

    /**
     * 设置带超时缓存
     *
     * @param key
     * @param value
     * @param expire
     */
    void set(String key, Object value, long expire);

    /**
     * 初始化缓存
     */
    void init();

    /**
     * 获取缓存并重新设置超时时间
     *
     * @param key
     * @param timeout
     * @return
     */
    String getAndUpate(String key, long timeout);
}
