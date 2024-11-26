package com.dscomm.proxy.controller;

import com.dscomm.proxy.service.MessageSenderService;
import com.dscomm.proxy.utils.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangsheng
 * Time 2024/10/28 16:06
 */
@RestController
@RequestMapping("/mq")
@Slf4j
public class MessageController {

    private final MessageSenderService messageSender;

    @Autowired
    public MessageController(MessageSenderService messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping("/send")
    public AjaxResult sendMessage(@RequestBody String message) {
        messageSender.sendMessage(message);
        return AjaxResult.success();
    }
}
