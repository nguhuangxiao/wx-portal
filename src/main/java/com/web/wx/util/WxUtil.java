package com.web.wx.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/24
 */
@Service
public class WxUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String upload(MultipartFile file, String url) throws IOException {

        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("media", file.getResource());
        params.add("name", "media");
        params.add("filename", file.getName());
        String result = rest.postForObject(url, params, String.class);
        return result;
    }

}
