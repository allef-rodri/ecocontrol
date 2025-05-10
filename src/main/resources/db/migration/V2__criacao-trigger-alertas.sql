CREATE OR REPLACE TRIGGER TRG_BEFORE_INSERT_DB_LEITURA_CONSUMO
BEFORE INSERT ON DB_LEITURA_CONSUMO
FOR EACH ROW
DECLARE
v_limite_kwh     DB_CONFIG_EQUIPAMENTO.limite_kwh%TYPE;
    v_nome_equip     DB_EQUIPAMENTO.de_equipamento%TYPE;
    v_alerta_tipo    VARCHAR2(50);
    v_mensagem       VARCHAR2(255);
BEGIN
    -- Obter o limite de consumo e o nome do equipamento
SELECT c.limite_kwh, e.de_equipamento
INTO v_limite_kwh, v_nome_equip
FROM DB_CONFIG_EQUIPAMENTO c
         JOIN DB_EQUIPAMENTO e ON e.id_equipamento = c.id_equipamento
WHERE e.id_equipamento = :NEW.id_equipamento;

-- Verificar tipo de alerta com base na ordem desejada
IF :NEW.kwh_consumido >= v_limite_kwh * 1.20 THEN
        v_alerta_tipo := 'Alerta Crítico';
        v_mensagem := 'Consumo ultrapassou o limite de ' || TO_CHAR(v_limite_kwh * 1.20, 'FM9990.00') || ' kWh no ' || v_nome_equip || ' + 20%';
    ELSIF :NEW.kwh_consumido >= v_limite_kwh * 1.10 THEN
        v_alerta_tipo := 'Consumo Muito Alto';
        v_mensagem := 'Consumo ultrapassou o limite de ' || TO_CHAR(v_limite_kwh * 1.10, 'FM9990.00') || ' kWh no ' || v_nome_equip || ' + 10%';
    ELSIF :NEW.kwh_consumido > v_limite_kwh THEN
        v_alerta_tipo := 'Consumo Alto';
        v_mensagem := 'Consumo ultrapassou o limite de ' || TO_CHAR(v_limite_kwh, 'FM9990.00') || ' kWh no ' || v_nome_equip;
END IF;

    -- Inserir alerta, se aplicável
    IF v_alerta_tipo IS NOT NULL THEN
        INSERT INTO DB_ALERTA (id_alerta, tipo_alerta, mensagem, dh_alerta, id_equipamento)
        VALUES (SEQ_DB_ALERTA.NEXTVAL, v_alerta_tipo, v_mensagem, SYSDATE, :NEW.id_equipamento);
END IF;
END;
/