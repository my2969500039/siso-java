package com.siso.web.upload;

import com.siso.Result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/upload")
@Api(value = "/upload",tags = {"文件上传地址"})
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> uploadVideoCoverImage(HttpServletRequest request) throws Exception {
        return uploadService.upload(request);
    }

    @GetMapping("{time}/{filename}")
    @ApiOperation("文件获取地址")
    public void images(@PathVariable("time") String time,
                       @PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
        File file=new File("./resource/image/"+time+"/"+filename);
        FileInputStream inputStream=new FileInputStream(file);
        byte[] data=new byte[(int)file.length()];
        int lenght=inputStream.read(data);
        inputStream.close();
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }


}
