package com.example.consumirApi.controller;

import com.example.consumirApi.domain.ApiResponse;
import com.example.consumirApi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/api/consumirApi/{name}")
    public ApiResponse consumeApi(@PathVariable String name) {
        return apiService.getNombreResponse(name);
    }
}


