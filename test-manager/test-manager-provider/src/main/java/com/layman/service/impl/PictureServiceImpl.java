package com.layman.service.impl;

import com.layman.pojo.PictureResult;
import com.layman.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSClient;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_CLIENT_URL}")
    private String IMAGE_CLIENT_URL;

    @Override
    public PictureResult uploadPic(MultipartFile picFile) {

        PictureResult result = new PictureResult();

        // 判断图片是否为空
        if (picFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        // 上传到图片服务器
        try {
            // 取图片的扩展名
            String originalFilename = picFile.getOriginalFilename();
            // 取扩展名不要"."
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
            String url = fastDFSClient.uploadFile(picFile.getBytes(),extName);
            // 把url响应给客户端
            result.setError(0);
            result.setUrl(IMAGE_CLIENT_URL+"/"+url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}
