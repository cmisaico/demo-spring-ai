package com.demo.ai.services.impl;

import com.demo.ai.services.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void obtenerRespuesta() {
        String respuesta = openAIService.obtenerRespuesta("Escribe una script para calcular la suma de dos numeros en java.");
        System.out.println("Respuesta obtenida");
        System.out.println(respuesta);
    }
}