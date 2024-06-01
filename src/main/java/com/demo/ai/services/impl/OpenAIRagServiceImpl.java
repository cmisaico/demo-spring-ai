package com.demo.ai.services.impl;

import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;
import com.demo.ai.services.OpenAIRagService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAIRagServiceImpl implements OpenAIRagService {

    final ChatClient chatCliente;
    final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Override
    public Respuesta obtenerRespuesta(Pregunta pregunta) {
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatCliente.call(prompt);
        return new Respuesta(response.getResult().getOutput().getContent());
    }
}
