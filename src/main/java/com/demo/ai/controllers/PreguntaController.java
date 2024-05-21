package com.demo.ai.controllers;

import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;
import com.demo.ai.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreguntaController {
    private final OpenAIService openAIService;

    public PreguntaController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/realizar")
    public Respuesta realizarPregunta(@RequestBody Pregunta pregunta) {
        return openAIService.obtenerRespuesta(pregunta);
    }
}
