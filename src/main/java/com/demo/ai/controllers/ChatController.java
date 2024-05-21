package com.demo.ai.controllers;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {


    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String chat(@RequestParam String text){
        return chatClient.call(text);
    }


//    private final OpenAiChatClient chatClient;

//    public ChatController(OpenAiChatClient chatClient) {
//        this.chatClient = chatClient;
//    }

//    @GetMapping("/ai/generar")
//    public Map generar(@RequestParam(value = "message",
//            defaultValue = "Dime que es la inteligencia artificial") String message){
//        return Map.of("generation", chatClient.call(message));
//    }
//
//    @GetMapping("/ai/generarStream")
//    public Flux<ChatResponse> generarStream(@RequestParam(value = "message",
//            defaultValue = "Dime que es la inteligencia artificial") String message){
//        Prompt prompt = new Prompt(new UserMessage(message));
//        return chatClient.stream(prompt);
//    }

}
