package model;

public class EquipamentoTestDto {

    private Long idEquipamento;
    private String deEquipamento;
    private String tipo;
    private Double consumoMaximo;
    private String status;
    private SetorTestDto setor;

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getDeEquipamento() {
        return deEquipamento;
    }

    public void setDeEquipamento(String deEquipamento) {
        this.deEquipamento = deEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getConsumoMaximo() {
        return consumoMaximo;
    }

    public void setConsumoMaximo(Double consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SetorTestDto getSetor() {
        return setor;
    }

    public void setSetor(SetorTestDto setor) {
        this.setor = setor;
    }
}
