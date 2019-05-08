/*
 * @author Zhanghh
 * @date 2019/5/7
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.webserver.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file/")
public class FileController {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 获取头像
     */
//    @GetMapping("getHeadImg")
//    public ResponseEntity<Resource> getHeadImg(HttpServletRequest request){
//        MinioUtil.pushObject();
//        String path = String.valueOf(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
//        String objectName = path.substring(path.indexOf(bucketName) + bucketName.length() + 1);
//
//        InputStream objectStream = minioClient.getObject(bucketName, objectName);
//        String fileName = objectName.substring(objectName.lastIndexOf('/') + 1);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .body(new InputStreamResource(objectStream));
//    }
    @RequestMapping(value = "read")
    public String index() throws Exception {
        return minioUtil.getObjectUrl("head-img", "test.jpg");
    }
}
