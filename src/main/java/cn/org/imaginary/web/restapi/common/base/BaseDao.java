package cn.org.imaginary.web.restapi.common.base;

import cn.org.imaginary.web.restapi.model.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:10
 * @see :
 */
public interface BaseDao {
    String ORDER_BY_INFO = "ORDER_BY_INFO";

    /**
     * add date
     *
     * @param sqlId
     * @return
     */
    int add(String sqlId);

    /**
     * add date
     *
     * @param sqlId     sql id
     * @param parameter
     * @return
     */
    int add(String sqlId, Object parameter);


    /**
     * update
     *
     * @param sqlId
     * @return
     */
    int update(String sqlId);

    /**
     * update
     *
     * @param parameter
     * @return
     */
    int update(String sqlId, Object parameter);

    /**
     * delete
     *
     * @param sqlId
     * @return
     */
    int delete(String sqlId);

    /**
     * delete
     *
     * @param sqlId
     * @param parameter
     * @return
     */
    int delete(String sqlId, Object parameter);

    /**
     * fetch one
     *
     * @param sqlId
     * @return
     */
    <T> T get(String sqlId);

    /**
     * fetch one
     * SQL results can only have one data, otherwise it will be Exception
     * if has multiple data, use function selectOne(sqlId, parameter);
     *
     * @param parameter
     * @return
     */
    <T> T get(String sqlId, Object parameter);

    /**
     * query
     *
     * @param sqlId
     * @return
     */
    <E> List<E> query(String sqlId);

    /**
     * query
     *
     * @param sqlId
     * @param parameter
     * @return
     */
    <E> List<E> query(String sqlId, Object parameter);

    <E> List<E> query(String sqlId, Object parameter, String oderBy);


    /**
     * @param sqlId
     * @return
     */
    <T> T selectOne(String sqlId);

    /**
     * @param sqlId
     * @param parameter
     * @return
     */
    <T> T selectOne(String sqlId, Object parameter);

    /**
     * page query
     *
     * @param sqlId
     * @param parameter
     * @param size
     * @param num
     * @return
     */
    PageResult pageQuery(String sqlId, Object parameter, int size, int num);

    PageResult pageQuery(String sqlId, Object parameter, int size, int num, String orderBy);

    /**
     * return map result
     * sql must config  resultType="map", for example
     * <select id="subListSize" resultType="map">
     * select count(*) as totleNum from location_t where pid = #{id}
     * </select>
     *
     * @param sqlId
     * @param parameter
     * @param culName
     * @return
     */
    Map<Object, Object> selectMap(String sqlId, Object parameter, String culName);
}
