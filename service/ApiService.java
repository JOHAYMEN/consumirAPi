package com.example.consumirApi.service;

import com.example.consumirApi.domain.ApiResponse;
import com.example.consumirApi.exceptions.NameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class ApiService {
    private static final String API_URL = "https://sisedevco.ikeasistencia.com/api-swagger/api/v1/controller/test?name=";

    public ApiResponse getNombreResponse(String name) {
        String urlString = API_URL + name;
        ApiResponse apiResponse = new ApiResponse();

        // Crear una instancia de RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Hacer la solicitud GET
            ResponseEntity<ApiResponse> response = restTemplate.getForEntity(urlString, ApiResponse.class);

            // Comprobar el código de respuesta usando getStatusCode().value()
            int statusCode = response.getStatusCode().value();
            if (statusCode == 302) {
                // Si la respuesta es 302, procesamos los datos
                apiResponse = response.getBody();
            } else if (statusCode == 404) {
                // Si la respuesta es 404, asignamos "Sin resultados"
                apiResponse.setNombre("Sin resultados.");
                apiResponse.setFecha(null);
            } else {
                // En cualquier otro código de respuesta, mostramos el código y el error
                apiResponse.setNombre("Error inesperado. Código de respuesta: " + statusCode);
                apiResponse.setFecha(null);
            }
        } catch (Exception e) {
            // Si ocurre un error, mostramos el mensaje de excepción
            apiResponse.setNombre("Ocurrió un error: " + e.getMessage());
            apiResponse.setFecha("Sin resultados.");
            throw new NameNotFoundException();
        }

        return apiResponse;
    }
}
