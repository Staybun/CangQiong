package com.sky.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.sky.properties.AliOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
public class AliOssUtil {

    private final AliOssProperties props;

    // ✅ 构造器注入（Spring 会自动把 AliOssProperties 塞进来）
    public AliOssUtil(AliOssProperties props) {
        this.props = props;
        log.info("AliOssUtil Bean 已创建，bucket={}", props.getBucketName());
    }

    /**
     * 文件上传
     * @param bytes
     * @param objectName
     * @return
     */

    public String upload(byte[] bytes, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(
                props.getEndpoint(),
                props.getAccessKeyId(),
                props.getAccessKeySecret()
        );

        try {
            ossClient.putObject(
                    props.getBucketName(),
                    objectName,
                    new ByteArrayInputStream(bytes)
            );
        } catch (OSSException oe) {
            log.error("OSSException, message={}, code={}, requestId={}, hostId={}",
                    oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            throw oe;
        } catch (ClientException ce) {
            log.error("ClientException, message={}", ce.getMessage());
            throw ce;
        } finally {
            ossClient.shutdown();
        }

        String url = "https://"
                + props.getBucketName() + "."
                + props.getEndpoint() + "/"
                + objectName;

        log.info("文件上传到: {}", url);
        return url;
    }
}
