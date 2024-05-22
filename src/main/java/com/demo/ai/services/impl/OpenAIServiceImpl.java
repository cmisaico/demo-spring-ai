package com.demo.ai.services.impl;

import com.demo.ai.models.GetCapitalRequest;
import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;
import com.demo.ai.services.OpenAIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatCliente;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:templates/obtener-capital-prompt.st")
    private Resource obtenerCapitalPrompt;

    @Value("classpath:templates/obtener-capital-con-info.st")
    private Resource obtenerCapitalConInfo;

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

    @Override
    public Respuesta obtenerCapital(GetCapitalRequest request) {
//        PromptTemplate promptTemplate = new PromptTemplate("Cual es la capital de " + request.estadoOPais() + "?");
        PromptTemplate promptTemplate = new PromptTemplate(obtenerCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("estadoOPais", request.estadoOPais()));
        ChatResponse response = chatCliente.call(prompt);

        System.out.println(response.getResult().getOutput().getContent());
        String respuestaString;
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getResult().getOutput().getContent());
            respuestaString = jsonNode.get("respuesta").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar respuesta", e);
        }
        return new Respuesta(respuestaString);
//        return new Respuesta(response.getResult().getOutput().getContent());
    }
}
