package cn.org.imaginary.web.restapi.common.dao.cache.redis;

import cn.org.imaginary.web.restapi.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:18
 * @see : Redis 缓存接口实现
 */
public abstract class BaseRedisCache implements ConfigurableRedisCache {
    private Logger logger = LoggerFactory.getLogger(BaseRedisCache.class);

    /**
     * 支持多服务器缓存配置
     *
     * @return
     */
    protected abstract StringRedisTemplate getRedisTemplate();

    @Override
    public String get(String key) {
        if (getRedisTemplate() == null) {
            return null;
        }
        String value = getRedisTemplate().opsForValue().get(key);
        return StringUtil.isEmpty(value) ? null : value;
    }

    @Override
    public void remove(String key) {
        expire(key);
    }

    @Override
    public String getAndUpate(String key, long timeout) {
        if (getRedisTemplate() == null) {
            return "";
        }
        String value = getRedisTemplate().opsForValue().get(key);
        if (!StringUtil.isEmpty(value)) {
            set(key, value, timeout);
        }
        return value;
    }

    @Override
    public void set(String key, Object value, long timeout) {
        if (getRedisTemplate() == null) {
            return;
        }
        getRedisTemplate().opsForValue().set(key, JSONObject.toJSONString(value), timeout, TimeUnit.MINUTES);
    }

    @Override
    public void set(String key, Object value) {
        if (getRedisTemplate() == null) {
            return;
        }
        getRedisTemplate().opsForValue().set(key, JSONObject.toJSONString(value));
    }

    @Override
    public void expire(String key) {
        if (getRedisTemplate() == null) {
            return;
        }
        if (exists(key)) {
            getRedisTemplate().delete(key);
        }
    }

    @Override
    public boolean exists(String key) {
        return getRedisTemplate().hasKey(key);
    }

    @Override
    public <T> T get(String key, Class<T> tClass) {
        T t = null;
        try {
            t = JSONObject.parseObject(get(key), tClass);
        } catch (Exception e) {
            logger.error("error in get", e);
        }
        return t;
    }

    @Override
    public int size() {
        int size = 0;
        try {
            size = keys().size();
        } catch (Exception e) {
            logger.error("error in size", e);
        }
        return size;
    }

    @Override
    public void init() {

    }

    @Override
    public Set<String> keys() {
        Set<String> keys = null;
        try {
            keys = getRedisTemplate().keys("*");
        } catch (Exception e) {
            logger.error("error in keys", e);
        }
        return keys;
    }

    @Override
    public Set<String> keys(String regx) {
        Set<String> keys = null;
        try {
            keys = getRedisTemplate().keys(regx);
        } catch (Exception e) {
            logger.error("error in keys regx", e);
        }
        return keys;
    }
}
