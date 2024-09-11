package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.common.utils.response.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
@Api("文件上传")
public class UploadController {

    @PostMapping()
    @ApiOperation("文件上传")
    public HttpResult<?> upload(@RequestParam("file") MultipartFile file)
    {
        String savePath = "D:\\uploadtest\\";
        Path path = Paths.get(savePath);
        if (!Files.exists(path)) {
            throw new RuntimeException("上传路径不存在！");
        }

        try {
            String name = file.getOriginalFilename();
            Path dest = path.resolve(name);
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return HttpResult.ok("上传成功！");
    }
}
