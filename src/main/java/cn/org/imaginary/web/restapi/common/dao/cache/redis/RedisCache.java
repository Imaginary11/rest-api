package cn.org.imaginary.web.restapi.common.dao.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:24
 * @see : Redis 缓存Component
 */
@Component
public class RedisCache extends BaseRedisCache {
    @Autowired
    @Qualifier("cacheTemplate")
    private StringRedisTemplate cacheTemplate;

    @Override
    protected StringRedisTemplate getRedisTemplate() {
        return cacheTemplate;
    }
}
