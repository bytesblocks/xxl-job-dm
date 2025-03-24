package com.xxl.job.admin.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xxl.job.admin.api.model.*;
import com.xxl.job.admin.api.service.ApiService;
import com.xxl.job.admin.api.vo.IPage;
import com.xxl.job.admin.api.vo.JobPageItemVO;
import com.xxl.job.admin.api.vo.LogPageItemVO;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.dao.XxlJobLogDao;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.GsonTool;

import groovy.util.logging.Slf4j;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

  private final XxlJobService xxlJobService;
  private final XxlJobInfoDao xxlJobInfoDao;
  private final XxlJobUserDao xxlJobUserDao;
  private final XxlJobGroupDao xxlJobGroupDao;
  private final XxlJobLogDao xxlJobLogDao;

  public ApiServiceImpl(final XxlJobService xxlJobService, final XxlJobInfoDao xxlJobInfoDao,
      final XxlJobUserDao xxlJobUserDao, final XxlJobGroupDao xxlJobGroupDao, final XxlJobLogDao xxlJobLogDao) {
    this.xxlJobService = xxlJobService;
    this.xxlJobInfoDao = xxlJobInfoDao;
    this.xxlJobUserDao = xxlJobUserDao;
    this.xxlJobGroupDao = xxlJobGroupDao;
    this.xxlJobLogDao = xxlJobLogDao;
  }

  private XxlJobUser getDefaultLoginUser() {
    final XxlJobUser user = xxlJobUserDao.loadByUserName("admin");
    return Optional.ofNullable(user).orElse(new XxlJobUser());
  }

  private XxlJobGroup getJobGroup(String executorName) {
    return this.xxlJobGroupDao.getByAppName(executorName);
  }

  @Override
  public ReturnT<String> addJob(final JobAddDTO addParam) {
    final XxlJobGroup jobGroup = getJobGroup(addParam.getExecutorAppName());
    if (jobGroup == null) {
      return new ReturnT<>(ReturnT.FAIL_CODE, "执行器未找到");
    }
    XxlJobInfo jobInfo = new XxlJobInfo();
    jobInfo.setJobGroup(jobGroup.getId());
    jobInfo.setJobDesc(addParam.getJobName());
    jobInfo.setAuthor("ETL");
    jobInfo.setScheduleType("CRON");
    jobInfo.setScheduleConf(addParam.getScheduleConf());
    jobInfo.setExecutorHandler(addParam.getHandler());
    jobInfo.setExecutorRouteStrategy("FIRST");
    jobInfo.setGlueType("BEAN");
    jobInfo.setMisfireStrategy("DO_NOTHING");
    jobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
    jobInfo.setExecutorTimeout(0);
    jobInfo.setExecutorFailRetryCount(0);
    jobInfo.setExecutorParam(buildExecutorParam(addParam.getBusinessKey()));
    XxlJobUser loginUser = getDefaultLoginUser();
    return xxlJobService.add(jobInfo, loginUser);
  }

  @Override
  public ReturnT<String> updateJob(final JobUpdateDTO updateParam) {
    final XxlJobInfo jobInfo = this.xxlJobInfoDao.loadById(updateParam.getId());
    if (jobInfo == null) {
      return new ReturnT<>(ReturnT.FAIL_CODE, "任务不存在");
    }
    if (StringUtils.hasText(updateParam.getJobName())) {
      jobInfo.setJobDesc(updateParam.getJobName());
    }
    if (StringUtils.hasText(updateParam.getScheduleConf())) {
      jobInfo.setScheduleConf(updateParam.getScheduleConf());
    }
    if (StringUtils.hasText(updateParam.getBusinessKey())) {
      jobInfo.setExecutorParam(buildExecutorParam(updateParam.getBusinessKey()));
    }
    return xxlJobService.update(jobInfo, getDefaultLoginUser());
  }

  @Override
  public ReturnT<String> deleteJob(final JobDeleteDTO deleteParam) {
    return this.xxlJobService.remove(deleteParam.getId());
  }

  @Override
  public ReturnT<String> pageJob(final JobPageDTO pageParam) {
    final int pageNumber = pageParam.getPageNumber();
    final int pageSize = pageParam.getPageSize();
    final int triggerStatus = pageParam.getTriggerStatus();
    final String jobName = pageParam.getJobName();
    String author = "ETL";
    List<XxlJobInfo> list = xxlJobInfoDao.pageList((pageNumber - 1) * pageSize, pageSize, 0, triggerStatus, jobName, "",
        author);
    int list_count = xxlJobInfoDao.pageListCount((pageNumber - 1) * pageSize, pageSize, 0, triggerStatus, jobName, "",
        author);
    IPage<JobPageItemVO> page = new IPage<>();
    page.setTotal(list_count);
    page.setCurrent(pageNumber);
    page.setSize(pageSize);
    page.setPages((int) Math.ceil((double) list_count / pageSize));
    page.setRecords(list.stream().map(e -> {
      JobPageItemVO itemVO = new JobPageItemVO();
      itemVO.setId(e.getId());
      itemVO.setJobName(e.getJobDesc());
      itemVO.setScheduleType(e.getScheduleType());
      itemVO.setScheduleConf(e.getScheduleConf());
      itemVO.setGlueType(e.getGlueType());
      itemVO.setExecutorHandler(e.getExecutorHandler());
      itemVO.setTriggerStatus(e.getTriggerStatus());
      if (e.getGlueType().equals("BEAN") && StringUtils.hasText(e.getExecutorParam())) {
        itemVO.setBusinessKey(extractBusinessKey(e.getExecutorParam()));
      }
      return itemVO;
    }).collect(Collectors.toList()));
    return new ReturnT<>(GsonTool.toJson(page));
  }

  @Override
  public ReturnT<String> pageLog(final LogPageDTO logParam) {
    final int pageNumber = logParam.getPageNumber();
    final int pageSize = logParam.getPageSize();
    List<LogPageItemVO> list = this.xxlJobLogDao.pageLog(logParam.getJobName(), logParam.getTriggerTimeStart(), logParam
        .getTriggerTimeEnd(), (pageNumber - 1) * pageSize, pageSize);
    long total = this.xxlJobLogDao.pageLogCount(logParam.getJobName(), logParam.getTriggerTimeStart(), logParam
        .getTriggerTimeEnd(), (pageNumber - 1) * pageSize, pageSize);
    IPage<LogPageItemVO> page = new IPage<>();
    page.setTotal(total);
    page.setCurrent(pageNumber);
    page.setSize(pageSize);
    page.setPages((int) Math.ceil((double) total / pageSize));
    page.setRecords(list);
    return new ReturnT<>(GsonTool.toJson(page));
  }

  @Override
  public ReturnT<String> startJob(final JobStartDTO startParam) {
    return this.xxlJobService.start(startParam.getId());
  }

  @Override
  public ReturnT<String> stopJob(final JobStopDTO stopParam) {
    return this.xxlJobService.stop(stopParam.getId());
  }

  @Override
  public ReturnT<String> triggerJob(final JobTriggerDTO triggerParam) {
    return this.xxlJobService.trigger(getDefaultLoginUser(), triggerParam.getId(), "", "");
  }

  private String extractBusinessKey(String executorParam) {
    Map<String, String> param = extractExecutorParam(executorParam);
    return param.get("businessKey");
  }

  @SuppressWarnings("unchecked")
  private Map<String, String> extractExecutorParam(String executorParam) {
    Map<String, String> param = GsonTool.fromJson(executorParam, Map.class);
    if (param == null) {
      param = new HashMap<>();
    }
    return param;
  }

  private String buildExecutorParam(String value) {
    return buildExecutorParam("businessKey", value);
  }

  private String buildExecutorParam(String key, String value) {
    Map<String, String> param = new HashMap<>();
    param.put(key, value);
    return GsonTool.toJson(param);
  }

}
