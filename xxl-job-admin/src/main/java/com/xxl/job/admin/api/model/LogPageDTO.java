package com.xxl.job.admin.api.model;

public class LogPageDTO {

  private String jobName;
  private String triggerTimeStart;
  private String triggerTimeEnd;
  private int pageNumber;
  private int pageSize;

  public String getJobName() {
    return jobName;
  }

  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  public String getTriggerTimeStart() {
    return triggerTimeStart;
  }

  public void setTriggerTimeStart(final String triggerTimeStart) {
    this.triggerTimeStart = triggerTimeStart;
  }

  public String getTriggerTimeEnd() {
    return triggerTimeEnd;
  }

  public void setTriggerTimeEnd(final String triggerTimeEnd) {
    this.triggerTimeEnd = triggerTimeEnd;
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
