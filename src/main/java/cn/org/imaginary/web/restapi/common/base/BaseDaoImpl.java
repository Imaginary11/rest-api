package cn.org.imaginary.web.restapi.common.base;

import cn.org.imaginary.web.restapi.common.exception.DBOptException;
import cn.org.imaginary.web.restapi.model.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:12
 * @see : 数据库基本操作实现类
 */
public class BaseDaoImpl implements BaseDao {
    public SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Override
    public int add(String sqlId) {
        return add(sqlId, null);
    }

    @Override
    public int add(String sqlId, Object parameter) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();
            return session.insert(sqlId, parameter);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public int update(String sqlId) {
        return update(sqlId, null);
    }

    @Override
    public int update(String sqlId, Object parameter) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();
            return session.update(sqlId, parameter);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }


    @Override
    public int delete(String sqlId) {
        return delete(sqlId, null);
    }

    @Override
    public int delete(String sqlId, Object parameter) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();
            return session.delete(sqlId, parameter);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public <T> T get(String sqlId) {
        return get(sqlId, null);
    }

    @Override
    public <T> T get(String sqlId, Object parameter) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();
            return session.selectOne(sqlId, parameter);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public <E> List<E> query(String sqlId) {
        return query(sqlId, null);
    }

    @Override
    public <E> List<E> query(String sqlId, Object parameter) {
        return query(sqlId, parameter, null);
    }

    @Override
    public <E> List<E> query(String sqlId, Object parameter, String orderBy) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();

            if (!StringUtils.isEmpty(orderBy)) {
                PageHelper.orderBy(orderBy);
            }
            return session.selectList(sqlId, parameter);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public PageResult pageQuery(String sqlId, Object parameter, int size, int num, String orderBy) {

        SqlSession session = null;
        try {

            session = getSqlSessionFactory().openSession();

            PageHelper.startPage(num, size);
            if (!StringUtils.isEmpty(orderBy)) {
                PageHelper.orderBy(orderBy);
            }

            List<Object> temp = session.selectList(sqlId, parameter);
            PageResult result = null;
            if (temp instanceof Page) {
                result = new PageResult();
                Page<Object> page = (Page<Object>) temp;
                result.setLists(new LinkedList<>(page));
                result.setPage(page.getPageNum());
                result.setPagesize(page.getPageSize());
                result.setTotal(page.getTotal());
            }
            return result;
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public PageResult pageQuery(String sqlId, Object parameter, int size, int num) {
        return pageQuery(sqlId, parameter, size, num, null);
    }

    @Override
    public <T> T selectOne(String sqlId) {
        return selectOne(sqlId, null);
    }

    @Override
    public <T> T selectOne(String sqlId, Object parameter) {

        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();

            PageHelper.startPage(1, 1);
            List<T> temp = session.selectList(sqlId, parameter);
            if (temp == null || temp.size() < 1) {
                return null;
            }
            return temp.get(0);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Map<Object, Object> selectMap(String sqlId, Object parameter, String culName) {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession();
            return session.selectMap(sqlId, parameter, culName);
        } catch (Exception e) {
            throw new DBOptException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
