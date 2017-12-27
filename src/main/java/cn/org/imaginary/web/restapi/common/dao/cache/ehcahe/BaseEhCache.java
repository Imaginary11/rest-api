package cn.org.imaginary.web.restapi.common.dao.cache.ehcahe;

import cn.org.imaginary.web.restapi.common.dao.cache.redis.ConfigurableRedisCache;
import com.alibaba.fastjson.JSONObject;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:34
 * @see : Ehcache 实现
 */
public abstract class BaseEhCache implements ConfigurableEhCache {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurableRedisCache.class);

    @Override
    public void set(String key, Object value) {
        try {
            net.sf.ehcache.Cache cache = getCache();
            cache.put(new Element(key, value));
        } catch (Exception e) {
            logger.error(">>> put cache " + getCache() + "error", e);
        }
    }


    @Override
    public Object get(String key) {
        Object value = null;
        try {
            net.sf.ehcache.Cache cache = getCache();
            value = cache.get(key).getObjectValue();
        } catch (Exception e) {
            logger.error(">>> get cahet " + getCache() + "error", e);
        }
        return value;
    }

    @Override
    public int size() {
        return getCache().getSize();
    }

    @Override
    public <T> T get(String key, Class<T> tClass) {
        Object obj = get(key);
        return obj == null ? null : JSONObject.parseObject(JSONObject.toJSONString(obj), tClass);
    }

    @Override
    public void init() {
        try {
            initCache();
        } catch (Exception e) {
            logger.error(">>> init cache error ", e);
        }
    }

    @Override
    public Set<String> keys() {
        Set<String> keys = new HashSet<>();
        List<String> keysList = getCache().getKeys();
        if (keysList != null && keysList.size() > 0) {
            keysList.forEach(key -> keys.add(key));
        }
        return keys;
    }

    @Override
    public Set<String> keys(String regx) {
        //todo
        return new HashSet<>();
    }

    @Override
    public void remove(String key) {
        getCache().remove(key);
    }


    @Override
    public boolean exists(String key) {
        return get(key) != null;
    }

    /**
     * 获取cache对象
     *
     * @return
     */
    protected abstract net.sf.ehcache.Cache getCache();

    /**
     * 初始化缓存
     *
     * @throws Exception
     */
    protected abstract void initCache() throws Exception;

}
