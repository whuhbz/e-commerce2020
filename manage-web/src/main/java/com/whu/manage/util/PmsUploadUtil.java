package com.whu.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

public class PmsUploadUtil {
    public static String uploadImage(MultipartFile multipartFile){

        String url = "192.168.0.100";

        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        try {
            ClientGlobal.init(tracker);
        }catch (Exception e){
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        }catch (Exception e){
            e.printStackTrace();
        }

        StorageClient storageClient = new StorageClient(trackerServer,null);

        try {
            byte[] bytes = multipartFile.getBytes();//上传的二进制对象

            int i = multipartFile.getOriginalFilename().lastIndexOf('.');
            String extName = multipartFile.getOriginalFilename().substring(i+1);

            String[] upload_file = storageClient.upload_file(bytes,extName,null);

            for(String uploadInfo : upload_file){
                url += "/" + uploadInfo;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  url;
    }
}
