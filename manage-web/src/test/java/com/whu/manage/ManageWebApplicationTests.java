package com.whu.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
manageWeb服务(controller层)不需使用数据库，
pom.xml中不需添加jdbc，mysql等依赖，
springboot启动时会默认配置datasource,
所以添加exclude取消该配置，否则无法启动
**/
//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class})
//@EnableDubbo
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageWebApplicationTests {

    @Test
    public void contextLoads()throws IOException, MyException {

        String tracker = ManageWebApplicationTests.class.getResource("/tracker.conf").getPath();

        ClientGlobal.init(tracker);

        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getTrackerServer();

        StorageClient storageClient = new StorageClient(trackerServer,null);

        String originalFileName = "d://test.jpg";

        String[] upload_file = storageClient.upload_file(originalFileName,"jpg",null);

        String url = "192.168.0.100";

        for(String uploadInfo : upload_file){
            url += "/" + uploadInfo;
        }
        System.out.println(url);
    }
}
