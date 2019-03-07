import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ActivityTest {

    public void setDrviver(ProcessEngineConfiguration cfg){

        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?serverTimezone=GMT%2B8");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("root");
    }

    @Test
    public void test1(){

        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        setDrviver(cfg);
        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/demo1.bpmn")
                .deploy();

        ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1");


    }

    @Test
    public void createTable() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration
                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?serverTimezone=GMT%2B8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        /*
         * public static final String DB_SCHEMA_UPDATE_FALSE = "false";
         * 不能自动创建表，需要表存在 public static final String DB_SCHEMA_UPDATE_CREATE_DROP
         * = "create-drop";先删除表再创建表 public static final String
         * DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
         */

        processEngineConfiguration
                .setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 工作流的核心对象，ProcessEngine对象
        ProcessEngine processEngine = processEngineConfiguration
                .buildProcessEngine();
        System.out.println("processEngine:" + processEngine);

    }

}
