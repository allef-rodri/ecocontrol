package br.com.fiap.ecocontrol.controller;

import br.com.fiap.ecocontrol.dto.HealthStatusDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/") // Mantive o /api que sugeri anteriormente, ajuste se necessário
public class HomeController {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @GetMapping
    public ResponseEntity<HealthStatusDto> getApplicationStatus() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long maxMemory = runtime.maxMemory();

        HealthStatusDto statusDto = new HealthStatusDto(
                "UP",
                activeProfile,
                totalMemory,
                freeMemory,
                maxMemory
        );
        return ResponseEntity.ok(statusDto);
    }
}