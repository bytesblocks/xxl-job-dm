package com.xxl.job.admin.api.vo;

import java.util.Date;

public class LogPageItemVO {

  private int id;
  private int jobId;
  private String jobName;
  private Date triggerTime;
  private int triggerCode;
  private String triggerMsg;
  private Date handleTime;
  private int handleCode;
  private String handleMsg;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getJobId() {
    return jobId;
  }

  public void setJobId(final int jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  public Date getTriggerTime() {
    return triggerTime;
  }

  public void setTriggerTime(final Date triggerTime) {
    this.triggerTime = triggerTime;
  }

  public int getTriggerCode() {
    return triggerCode;
  }

  public void setTriggerCode(final int triggerCode) {
    this.triggerCode = triggerCode;
  }

  public String getTriggerMsg() {
    return triggerMsg;
  }

  public void setTriggerMsg(final String triggerMsg) {
    this.triggerMsg = triggerMsg;
  }

  public Date getHandleTime() {
    return handleTime;
  }

  public void setHandleTime(final Date handleTime) {
    this.handleTime = handleTime;
  }

  public int getHandleCode() {
    return handleCode;
  }

  public void setHandleCode(final int handleCode) {
    this.handleCode = handleCode;
  }

  public String getHandleMsg() {
    return handleMsg;
  }

  public void setHandleMsg(final String handleMsg) {
    this.handleMsg = handleMsg;
  }

}
