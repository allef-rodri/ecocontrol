package br.com.fiap.ecocontrol.exception.healthStatus;

public class HealthStatusException extends RuntimeException {
    public HealthStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
