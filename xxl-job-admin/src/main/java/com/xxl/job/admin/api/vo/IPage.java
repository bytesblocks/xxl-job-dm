package com.xxl.job.admin.api.vo;

import java.util.List;

public class IPage<T> {

  private long total;
  private int size;
  private int current;
  private int pages;
  private List<T> records;

  public long getTotal() {
    return total;
  }

  public void setTotal(final long total) {
    this.total = total;
  }

  public int getSize() {
    return size;
  }

  public void setSize(final int size) {
    this.size = size;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(final int current) {
    this.current = current;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(final int pages) {
    this.pages = pages;
  }

  public List<T> getRecords() {
    return records;
  }

  public void setRecords(final List<T> records) {
    this.records = records;
  }

}
