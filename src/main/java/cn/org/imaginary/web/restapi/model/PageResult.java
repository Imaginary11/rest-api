package cn.org.imaginary.web.restapi.model;

import java.util.List;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 0:11
 * @see : 分页查询bean
 */
public class PageResult {
    private int pagesize = 20;
    private int page = 1;
    private long total = 1;
    private List<?> lists;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getLists() {
        return lists;
    }

    public void setLists(List<?> lists) {
        this.lists = lists;
    }
}
