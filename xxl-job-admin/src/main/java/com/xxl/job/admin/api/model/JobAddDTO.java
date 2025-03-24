package com.xxl.job.admin.api.model;

public class JobAddDTO {

  private String jobName;
  private String businessKey;
  private String scheduleConf;
  private String handler;
  private String executorAppName;

  public String getJobName() {
    return jobName;
  }

  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public void setBusinessKey(final String businessKey) {
    this.businessKey = businessKey;
  }

  public String getScheduleConf() {
    return scheduleConf;
  }

  public void setScheduleConf(final String scheduleConf) {
    this.scheduleConf = scheduleConf;
  }

  public String getHandler() {
    return handler;
  }

  public void setHandler(final String handler) {
    this.handler = handler;
  }

  public String getExecutorAppName() {
    return executorAppName;
  }

  public void setExecutorAppName(final String executorAppName) {
    this.executorAppName = executorAppName;
  }

}
