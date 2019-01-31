package com.bolo.test.controllers;

import com.bolo.test.bean.CommconResponse;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("a")
public class ActivitiController {
    private Logger log = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 部署流程
     */
    @RequestMapping("deploy")
    public CommconResponse deployment(String fileName) {
        try {
            final String path = new StringBuilder("D:/Tem/").append(fileName).toString();
            // 发布流程
            repositoryService.createDeployment()
                    .addInputStream(fileName, new FileInputStream(path))
                    .deploy();
        } catch (Exception e) {
            log.error("部署流程失败", e);
            return CommconResponse.fail(e.getLocalizedMessage());
        }
        return CommconResponse.success();
    }


    /**
     * 发起流程
     */
    @RequestMapping("start")
    public CommconResponse startProcessInstanceByKey(String key) {
        try {
            runtimeService.startProcessInstanceByKey(key);
        } catch (Exception e) {
            log.error("发起流程失败", e);
            return CommconResponse.fail(e.getLocalizedMessage());
        }
        return CommconResponse.success();
    }


    /**
     * 审核
     */


    /**
     * 查询数量
     */
    @RequestMapping("count")
    public CommconResponse count() {
        try {
            long pCount = repositoryService.createProcessDefinitionQuery().count();
            long tCount = taskService.createTaskQuery().count();
            log.info("Number of process definitions : {}", pCount);
            log.info("Number of tasks : {}", tCount);
            return CommconResponse.success();
        } catch (Exception e) {
            log.error("查询数量失败", e);
            return CommconResponse.fail(e.getLocalizedMessage());
        }
    }


    /**
     * 检索图像资源
     */
    @RequestMapping("resource")
    public void getResource(String key, HttpServletResponse response) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .latestVersion()
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();

        try (InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
             OutputStream outputStream = response.getOutputStream()) {
            int len;
            byte[] bytes = new byte[2048];
            while ((len = imageStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
