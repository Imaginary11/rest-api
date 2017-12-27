package cn.org.imaginary.web.restapi;

import cn.org.imaginary.web.restapi.common.base.BaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 21:10
 * @see :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {
    @Autowired
    private BaseDao accountBaseDao;

    @Test
    public void testDB() {
        accountBaseDao.query("getAdminNames").forEach(p -> System.out.println(p.toString()));
    }
}
