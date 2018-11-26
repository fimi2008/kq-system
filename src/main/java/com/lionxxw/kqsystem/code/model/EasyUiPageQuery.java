package com.lionxxw.kqsystem.code.model;

/**
 * 封装easyUI分页参数
 * Created by lionxxw on 2016/7/11.
 */
public class EasyUiPageQuery {
    private int page = 1;
    private int rows = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}