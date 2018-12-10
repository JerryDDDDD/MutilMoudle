package com.layman.fastdfs;

import org.csource.fastdfs.*;
import org.junit.Test;

public class FastdfsTest {


    @Test
    public void testUpload() throws Exception{

        // 1. 添加FastDfS到工程中
        // 2. 初始化全局配置,加载一个配置文件
        ClientGlobal.init("E:\\Code\\GitHub\\MutilMoudle\\test-protal\\src\\main\\resources\\properties\\client.conf");
        // 3. 创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 4. 创建一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5. 声明一个StorageServer对象为NULL
        StorageServer storageServer = null;
        // 6. 获得StorageClient对象
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        // 7. 直接调用StorageClient对象方法上传文件即可
        String[] strings = storageClient.upload_file("E:\\img2test\\1.jpg","jpg",null);
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void testFastDfsClient() throws Exception{
        FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
        String strings = client.uploadFile("F:\\img2test\\2.jpg","jpg");
        System.out.println(strings);
    }
}
