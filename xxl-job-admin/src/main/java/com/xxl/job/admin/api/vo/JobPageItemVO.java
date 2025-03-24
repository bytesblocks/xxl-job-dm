package com.xxl.job.admin.api.vo;

public class JobPageItemVO {

  private int id;
  private String jobName;
  private String scheduleType;
  private String scheduleConf;
  private String glueType;
  private String executorHandler;
  private int triggerStatus;
  private String businessKey;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  public String getScheduleType() {
    return scheduleType;
  }

  public void setScheduleType(final String scheduleType) {
    this.scheduleType = scheduleType;
  }

  public String getScheduleConf() {
    return scheduleConf;
  }

  public void setScheduleConf(final String scheduleConf) {
    this.scheduleConf = scheduleConf;
  }

  public String getGlueType() {
    return glueType;
  }

  public void setGlueType(final String glueType) {
    this.glueType = glueType;
  }

  public String getExecutorHandler() {
    return executorHandler;
  }

  public void setExecutorHandler(final String executorHandler) {
    this.executorHandler = executorHandler;
  }

  public int getTriggerStatus() {
    return triggerStatus;
  }

  public void setTriggerStatus(final int triggerStatus) {
    this.triggerStatus = triggerStatus;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public void setBusinessKey(final String businessKey) {
    this.businessKey = businessKey;
  }

}
