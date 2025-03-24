package com.xxl.job.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxl.job.admin.api.model.*;
import com.xxl.job.admin.api.service.ApiService;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.GsonTool;
import com.xxl.job.core.util.XxlJobRemotingUtil;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
@RequestMapping("/api")
public class JobApiController {

  @Resource
  private AdminBiz adminBiz;
  private final ApiService apiService;

  public JobApiController(final ApiService apiService) {
    this.apiService = apiService;
  }

  /**
   * api
   */
  @RequestMapping("/{uri}")
  @ResponseBody
  @PermissionLimit(limit = false)
  public ReturnT<String> api(HttpServletRequest request, @PathVariable("uri") String uri,
      @RequestBody(required = false) String data) {
    // valid
    if (!"POST".equalsIgnoreCase(request.getMethod())) {
      return new ReturnT<>(ReturnT.FAIL_CODE, "invalid request, HttpMethod not support.");
    }
    if (uri == null || uri.trim().isEmpty()) {
      return new ReturnT<>(ReturnT.FAIL_CODE, "invalid request, uri-mapping empty.");
    }
    final String accessToken = XxlJobAdminConfig.getAdminConfig().getAccessToken();
    if (accessToken != null && !accessToken.trim().isEmpty() && !accessToken.equals(request.getHeader(
        XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN))) {
      return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
    }
    // services mapping
    switch (uri) {
      case "callback":
        List<HandleCallbackParam> callbackParamList = GsonTool.fromJson(data, List.class, HandleCallbackParam.class);
        return adminBiz.callback(callbackParamList);
      case "registry":
        RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
        return adminBiz.registry(registryParam);
      case "registryRemove":
        RegistryParam registryRemove = GsonTool.fromJson(data, RegistryParam.class);
        return adminBiz.registryRemove(registryRemove);
      case "jobAdd":
        JobAddDTO addParam = GsonTool.fromJson(data, JobAddDTO.class);
        return apiService.addJob(addParam);
      case "jobUpdate":
        JobUpdateDTO updateParam = GsonTool.fromJson(data, JobUpdateDTO.class);
        return apiService.updateJob(updateParam);
      case "jobDelete":
        JobDeleteDTO deleteParam = GsonTool.fromJson(data, JobDeleteDTO.class);
        return apiService.deleteJob(deleteParam);
      case "jobStart":
        JobStartDTO startParam = GsonTool.fromJson(data, JobStartDTO.class);
        return apiService.startJob(startParam);
      case "jobStop":
        JobStopDTO stopParam = GsonTool.fromJson(data, JobStopDTO.class);
        return apiService.stopJob(stopParam);
      case "jobTrigger":
        JobTriggerDTO triggerParam = GsonTool.fromJson(data, JobTriggerDTO.class);
        return apiService.triggerJob(triggerParam);
      case "jobPage":
        JobPageDTO pageParam = GsonTool.fromJson(data, JobPageDTO.class);
        return apiService.pageJob(pageParam);
      case "logPage":
        LogPageDTO logParam = GsonTool.fromJson(data, LogPageDTO.class);
        return apiService.pageLog(logParam);
      default:
        return new ReturnT<>(ReturnT.FAIL_CODE, "invalid request, uri-mapping(" + uri + ") not found.");
    }
  }

}
