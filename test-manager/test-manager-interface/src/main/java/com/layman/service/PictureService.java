package com.layman.service;

import com.layman.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    PictureResult uploadPic(MultipartFile picFile);
}
