INSERT INTO DB_SETOR (id_setor, de_setor, localizacao)
VALUES (1, 'Setor Energia', 'Prédio Sustentável');

INSERT INTO DB_EQUIPAMENTO (id_equipamento, de_equipamento, tipo, consumo_maximo, status, id_setor)
VALUES (1, 'Medidor Solar', 'SENSOR', 200.0, 'ATIVO', 1);

INSERT INTO DB_USUARIO (id, login, senha, role)
VALUES (1, 'analista', 'analista123', 'ADMIN');

INSERT INTO DB_LEITURA_CONSUMO (id_leitura, dh_leitura, kwh_consumido, id_equipamento)
VALUES (1, CURRENT_TIMESTAMP, 150.5, 1);

INSERT INTO DB_ALERTA (id_alerta, tipo_alerta, mensagem, dh_alerta, id_equipamento)
VALUES (1, 'CONSUMO', 'Consumo acima do limite', CURRENT_TIMESTAMP, 1);

ALTER SEQUENCE SEQ_DB_SETOR RESTART WITH 2;
ALTER SEQUENCE SEQ_DB_EQUIPAMENTO RESTART WITH 2;
ALTER SEQUENCE SEQ_DB_LEITURA_CONSUMO RESTART WITH 2;
ALTER SEQUENCE SEQ_DB_ALERTA RESTART WITH 2;
