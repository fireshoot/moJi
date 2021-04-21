package com.osyangxin.moji.bean;


import com.github.pagehelper.PageInfo;
import java.util.List;


public class ResultPageView<T> {

    private Long total = 0L;

    private Integer current = 1;

    private Integer pageCount = 0;

    private List<T> list;

    public ResultPageView() {
    }

    public ResultPageView(Long total, int current, int pageCount, List list) {
        this.total = total;
        this.current = current;
        this.pageCount = pageCount;
        this.list = list;
    }

    public ResultPageView(PageInfo<T> pageInfo){
        this.total = pageInfo.getTotal();
        this.current = pageInfo.getPageNum();
        this.pageCount = pageInfo.getPageSize();
        this.list = pageInfo.getList();
    }

    public void coverByPageInfo(PageInfo<T> pageInfo){
        this.total = pageInfo.getTotal();
        this.current = pageInfo.getPageNum();
        this.pageCount = pageInfo.getPageSize();
        this.list = pageInfo.getList();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
