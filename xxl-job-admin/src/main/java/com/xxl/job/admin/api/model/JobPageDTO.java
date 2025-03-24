package com.xxl.job.admin.api.model;

public class JobPageDTO {

  private int triggerStatus;
  private String jobName;
  private int pageNumber;
  private int pageSize;

  public int getTriggerStatus() {
    return triggerStatus;
  }

  public void setTriggerStatus(final int triggerStatus) {
    this.triggerStatus = triggerStatus;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(final int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(final int pageSize) {
    this.pageSize = pageSize;
  }

}
