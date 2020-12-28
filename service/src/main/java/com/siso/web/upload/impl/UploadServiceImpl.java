package com.siso.web.upload.impl;

import com.siso.Result.Result;
import com.siso.dto.CMSUserDTO;
import com.siso.exception.NormalException;
import com.siso.web.upload.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public Result<String> upload(HttpServletRequest request) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String url = this.MultipartFileUploadlocal(request);
        return Result.<String>builder().success().data(url).build();

    }

    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String MultipartFileUploadlocal(HttpServletRequest request) throws IOException {//本地存储
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        File dir=new File("./resource/image/"+dateNowStr);
        if (!dir.exists()){
            dir.mkdirs();
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile=multipartRequest.getFile("file");
        String filename=dateNowStr+String.valueOf((int)(Math.random() * 10000000))+".png";//随机文件名
        String url="./resource/image/"+dateNowStr+"/"+filename;
        File file = new File(url);
        assert multipartFile != null;
        multipartFile.transferTo(file.getAbsoluteFile());
        return "http://"+request.getServerName()+"/upload/"+dateNowStr+"/"+filename;
    }





}
