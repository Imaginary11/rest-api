package cn.org.imaginary.web.restapi;

import cn.org.imaginary.web.restapi.common.dao.cache.ehcahe.ConfigurableEhCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:44
 * @see :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EhcacheTest {
    @Autowired
    private ConfigurableEhCache ouiEhcache;

    @Test
    public void testInit() {
        ouiEhcache.init();
        System.out.println("size = " + ouiEhcache.size());
    }
}
