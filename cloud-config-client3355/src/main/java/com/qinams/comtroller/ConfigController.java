package com.qinams.comtroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @Value("${config.info}")
    private String info;

    /**
     * 读取配置文件内容
     * @return
     */
    @GetMapping("get")
    public String getConfiginfo(){
        return this.info;
    }
}
