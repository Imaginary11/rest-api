package cn.org.imaginary.web.restapi.common.config.redis;

import cn.org.imaginary.web.restapi.util.StringUtil;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:07
 * @see :
 */
public class BaseRedisTemplateConfig {

    /**
     * Redis连接工厂
     *
     * @param hostName
     * @param port
     * @param password
     * @param maxIdle
     * @param maxAwait
     * @param index
     * @param timeout
     * @param testOnBorrow
     * @return
     */
    public RedisConnectionFactory connectionFactory(String hostName, int port,
                                                    String password, int maxIdle, long maxAwait, int index,
                                                    int timeout, boolean testOnBorrow) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);
        if (!StringUtil.isEmpty(password)) {
            jedis.setPassword(password);
        }
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setTimeout(timeout);
        jedis.setPoolConfig(poolCofig(maxIdle, maxAwait,
                testOnBorrow));
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
        return factory;
    }

    /**
     * 连接池配置
     *
     * @param maxIdle
     * @param maxAwait
     * @param testOnBorrow
     * @return
     */
    public JedisPoolConfig poolCofig(int maxIdle, long maxAwait, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxWaitMillis(maxAwait);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }
}
