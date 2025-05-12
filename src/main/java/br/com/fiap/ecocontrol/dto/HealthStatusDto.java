package br.com.fiap.ecocontrol.dto;

public record HealthStatusDto (
    String status,
    String environment,
    long totalMemory,
    long freeMemory,
    long maxMemory,
    long usedMemory
) {
    public HealthStatusDto(String status, String environment, long totalMemory, long freeMemory, long maxMemory) {
        this(
                status,
                environment,
                totalMemory,
                freeMemory,
                maxMemory,
                totalMemory - freeMemory
        );
    }
}
