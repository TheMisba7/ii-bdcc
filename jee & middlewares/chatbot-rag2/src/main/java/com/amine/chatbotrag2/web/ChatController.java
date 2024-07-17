package com.amine.chatbotrag2.web;

import com.amine.chatbotrag2.services.ChatAiServirce;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {
    private ChatAiServirce chatAiServirce;

    @GetMapping(value = "/chat",produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String question){
        return  chatAiServirce.regChat(question);
    }
}
