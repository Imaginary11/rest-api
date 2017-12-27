package cn.org.imaginary.web.restapi;

import cn.org.imaginary.web.restapi.common.dao.cache.redis.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:25
 * @see :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTest {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testRedisCache() {
        redisCache.set("hello", "world");
        System.out.println("hello " + redisCache.get("hello"));
    }
}
