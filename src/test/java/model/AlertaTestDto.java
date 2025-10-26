package model;

public class AlertaTestDto {

    private Long idAlerta;
    private String tipoAlerta;
    private String mensagem;
    private String dataHoraAlerta;
    private EquipamentoTestDto equipamento;

    // Getters e Setters

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDataHoraAlerta() {
        return dataHoraAlerta;
    }

    public void setDataHoraAlerta(String dataHoraAlerta) {
        this.dataHoraAlerta = dataHoraAlerta;
    }

    public EquipamentoTestDto getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(EquipamentoTestDto equipamento) {
        this.equipamento = equipamento;
    }
}
