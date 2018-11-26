package com.lionxxw.kqsystem.code.model;

import java.io.Serializable;

/**
 * <p>Description: 分页查询参数 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/5 下午8:13
 */
public class PageQuery implements Serializable {
    public PageQuery(){
    }

    public PageQuery(long total, int currPage, int pageSize){
        this.total = total;
        this.pageSize = pageSize;
        this.currPage = currPage;
    }

    public PageQuery(long total, int pageSize){
        this.pageSize = pageSize;
        this.total = total;
        currPage = 1;
    }

    public PageQuery(int pageSize){
        this.pageSize = pageSize;
        currPage = 1;
    }

    public PageQuery(EasyUiPageQuery query){
        this.pageSize = query.getRows();
        currPage = query.getPage();
    }

    private long total;

    private int pageSize = 10;

    private int currPage = 1;

    public long getStartNum(){
        return  pageSize * (currPage - 1);
    }

    public void nextPage(){
        currPage = currPage + 1;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
}
