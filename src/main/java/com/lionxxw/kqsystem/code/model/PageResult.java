package com.lionxxw.kqsystem.code.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description: 分页数据封装 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/5 下午8:12
 */
public class PageResult<T> implements Serializable {

    public PageResult(){

    }

    public PageResult(long total, int currPage, int pageSize, List<T> results){
        this.total = total;
        this.pageSize = pageSize;
        this.currPage = currPage;
        setTotalPage(total, pageSize);
    }

    public PageResult(long total, int pageSize, List<T> results){
        this.pageSize = pageSize;
        this.total = total;
        currPage = 1;
        setTotalPage(total, pageSize);
    }

    public PageResult(PageQuery query,  List<T> results){
        this.total = query.getTotal();
        this.pageSize = query.getPageSize();
        this.currPage = query.getCurrPage();
        this.results = results;
        setTotalPage(total, pageSize);
    }

    private long total;

    private long totalPage;  // 总页数

    private int pageSize;

    private int currPage = 1;

    private List<T> results;

    public long getStartNum(){
        return  pageSize * (currPage - 1);
    }

    private void setTotalPage(long total, int pageSize){
        if(pageSize > 0){
            if (total%pageSize == 0){
                this.totalPage = total/pageSize;
            }else{
                this.totalPage = total/pageSize + 1;
            }
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
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

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}