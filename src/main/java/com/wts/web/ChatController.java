package com.wts.web;

import com.wts.model.Shout;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by weitaosheng on 2017/3/12.
 */

@Controller
public class ChatController {

    @MessageMapping("/chat")
    public void handleShout(Shout incoming) {
        System.out.println("hello");
        System.out.println("Received message: " + incoming.getMessage());
    }

}
