package cn.org.imaginary.web.restapi.common.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:15
 * @see : 主库（账号库）操作bean
 */
@Service("accountBaseDao")
public class AccountBaseDaoImpl extends BaseDaoImpl {
    @Autowired
    @Qualifier("primarySqlSessionFactory")
    private SqlSessionFactory sqlSessionFactoryBean;

    public AccountBaseDaoImpl() {
        sqlSessionFactory = sqlSessionFactoryBean;
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactoryBean;
    }

}
