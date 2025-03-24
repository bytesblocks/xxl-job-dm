package com.xxl.job.admin.api.service;

import com.xxl.job.admin.api.model.*;
import com.xxl.job.core.biz.model.ReturnT;

public interface ApiService {

  ReturnT<String> addJob(JobAddDTO addParam);

  ReturnT<String> updateJob(JobUpdateDTO updateParam);

  ReturnT<String> deleteJob(JobDeleteDTO deleteParam);

  ReturnT<String> pageJob(JobPageDTO pageParam);

  ReturnT<String> pageLog(LogPageDTO logParam);

  ReturnT<String> startJob(JobStartDTO startParam);

  ReturnT<String> stopJob(JobStopDTO stopParam);

  ReturnT<String> triggerJob(JobTriggerDTO triggerParam);

}
