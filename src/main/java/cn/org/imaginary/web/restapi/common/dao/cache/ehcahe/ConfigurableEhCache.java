package cn.org.imaginary.web.restapi.common.dao.cache.ehcahe;

import cn.org.imaginary.web.restapi.common.dao.cache.Cache;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:32
 * @see : Ehcahce 接口补充
 */
public interface ConfigurableEhCache extends Cache {
    /**
     * 初始化缓存
     */
    void init();
}
