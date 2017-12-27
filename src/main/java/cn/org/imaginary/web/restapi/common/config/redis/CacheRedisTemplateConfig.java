package cn.org.imaginary.web.restapi.common.config.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:11
 * @see : 缓存String类型redisTemplate
 */
@Configuration
public class CacheRedisTemplateConfig extends BaseRedisTemplateConfig {
    @Bean(name = "cacheTemplate")
    @Qualifier("cacheTemplate")
    public StringRedisTemplate cacheTemplate(
            @Value("${redis.cache.host}") String host,
            @Value("${redis.cache.port}") int port,
            @Value("${redis.cache.maxIdle}") int maxIdle,
            @Value("${redis.cache.maxAwait}") long maxAwait,
            @Value("${redis.cache.timeout}") int timeout,
            @Value("${redis.cache.testOnBorrow}") boolean testOnBorrow) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(connectionFactory(host, port, null,
                maxIdle, maxAwait, 0, timeout, testOnBorrow));
        return temple;
    }
}
