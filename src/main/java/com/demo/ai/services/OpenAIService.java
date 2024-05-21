package com.demo.ai.services;

import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;

public interface OpenAIService {
    String obtenerRespuesta(String pregunta);
    Respuesta obtenerRespuesta(Pregunta pregunta);

}
