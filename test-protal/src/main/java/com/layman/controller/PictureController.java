package com.layman.controller;


import com.layman.pojo.PictureResult;
import com.layman.utils.FastDFSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pic")
public class PictureController {

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploadPic(MultipartFile uploadFile){
        PictureResult result = new PictureResult();

        // 判断图片是否为空
        if (uploadFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        // 上传到图片服务器
        try {
            // 取图片的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            // 取扩展名不要"."
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
            String url = client.uploadFile(uploadFile.getBytes(),extName);
            // 把url响应给客户端
            result.setError(0);
            result.setUrl("http://118.24.27.161:8081"+"/"+url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}
