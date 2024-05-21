package com.demo.ai.services.impl;

import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;
import com.demo.ai.services.OpenAIService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatCliente;

    public OpenAIServiceImpl(ChatClient chatCliente) {
        this.chatCliente = chatCliente;
    }

    @Override
    public String obtenerRespuesta(String pregunta) {
        PromptTemplate promptTemplate = new PromptTemplate(pregunta);
        Prompt prompt = promptTemplate.create();
        ChatResponse respuesta = chatCliente.call(prompt);
        return respuesta.getResult().getOutput().getContent();
    }

    @Override
    public Respuesta obtenerRespuesta(Pregunta pregunta) {
        PromptTemplate promptTemplate = new PromptTemplate(pregunta.pregunta());
        Prompt prompt = promptTemplate.create();
        ChatResponse respuesta = chatCliente.call(prompt);
        return new Respuesta(respuesta.getResult().getOutput().getContent());
    }
}
