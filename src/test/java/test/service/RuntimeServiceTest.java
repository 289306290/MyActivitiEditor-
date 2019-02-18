package test.service;

import com.bolo.test.Start;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = Start.class)
@RunWith(SpringRunner.class)
public class RuntimeServiceTest {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    /* 测试用流程变量 */
    final static String emp = "tom";
    final static String manager = "bolo";

    /**
     * 发起请假流程
     */
    @Test
    public void testStartProcessInstance() {
        Map<String, Object> val = new HashMap<>();
        val.put("emp", emp);
        ProcessInstance instance = runtimeService.startProcessInstanceById("发起请假流程:1:23", val);
        System.out.println(instance);
    }

    /**
     * 发起请假申请
     */
    @Test
    public void apply() {
        // 查询指定用户的所有任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(emp).list();

        for (Task task : tasks) {
            System.out.println("完成" + task.getName() + "任务...");
            if ("发送请假申请".equals(task.getName())) {
                Map<String, Object> val = new HashMap<>();
                val.put("manager", manager);
                taskService.complete(task.getId(), val);
            }
        }
    }

    /**
     * 请假审核
     */
    @Test
    public void audit() {
        // 查询指定用户的所有任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(manager).list();

        for (Task task : tasks) {
            System.out.println("完成" + task.getName() + "任务...");
            if ("请假审核".equals(task.getName())) {
                Map<String, Object> val = new HashMap<>();
                val.put("appr", true); // 流转条件
                taskService.complete(task.getId(), val);
            }
        }
    }
}
