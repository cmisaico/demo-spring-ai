package com.demo.ai.services;

import com.demo.ai.models.Pregunta;
import com.demo.ai.models.Respuesta;

public interface OpenAIRagService {

    public Respuesta obtenerRespuesta(Pregunta pregunta);

}
