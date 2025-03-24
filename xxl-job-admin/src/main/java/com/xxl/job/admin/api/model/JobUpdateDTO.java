package com.xxl.job.admin.api.model;

public class JobUpdateDTO {

  private int id;
  private String jobName;
  private String businessKey;
  private String scheduleConf;

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

}
