-- CRIAÇÃO DE TABELAS
CREATE TABLE DB_SETOR (
                          id_setor NUMBER NOT NULL,
                          de_setor VARCHAR2(100) NOT NULL,
                          localizacao VARCHAR2(200) NOT NULL
);

ALTER TABLE DB_SETOR
    ADD CONSTRAINT DB_SETOR_PK PRIMARY KEY (id_setor);

CREATE TABLE DB_EQUIPAMENTO (
                                id_equipamento NUMBER NOT NULL,
                                de_equipamento VARCHAR2(100) NOT NULL,
                                tipo VARCHAR2(100) NOT NULL,
                                consumo_maximo NUMBER(10,2) NOT NULL,
                                status VARCHAR2(20) NOT NULL,
                                id_setor NUMBER NOT NULL
);

ALTER TABLE DB_EQUIPAMENTO
    ADD CONSTRAINT DB_EQUIPAMENTO_PK PRIMARY KEY (id_equipamento);

CREATE TABLE DB_CONFIG_EQUIPAMENTO (
                                       id_config NUMBER NOT NULL,
                                       limite_kwh NUMBER(10,2) NOT NULL,
                                       id_equipamento NUMBER NOT NULL
);

CREATE UNIQUE INDEX DB_CONFIG_EQUIPAMENTO_IDX ON DB_CONFIG_EQUIPAMENTO (id_equipamento ASC);

ALTER TABLE DB_CONFIG_EQUIPAMENTO
    ADD CONSTRAINT CONFIG_EQUIPAMENTO_PK PRIMARY KEY (id_config);

CREATE TABLE DB_LEITURA_CONSUMO (
                                    id_leitura NUMBER NOT NULL,
                                    dh_leitura DATE NOT NULL,
                                    kwh_consumido NUMBER(10,2) NOT NULL,
                                    id_equipamento NUMBER NOT NULL
);

ALTER TABLE DB_LEITURA_CONSUMO
    ADD CONSTRAINT LEITURA_CONSUMO_PK PRIMARY KEY (id_leitura);

CREATE TABLE DB_ALERTA (
                           id_alerta NUMBER NOT NULL,
                           tipo_alerta VARCHAR2(50) NOT NULL,
                           mensagem VARCHAR2(255) NOT NULL,
                           dh_alerta DATE NOT NULL,
                           id_equipamento NUMBER NOT NULL
);

ALTER TABLE DB_ALERTA
    ADD CONSTRAINT ALERTA_PK PRIMARY KEY (id_alerta);

-- RELACIONAMENTOS

ALTER TABLE DB_EQUIPAMENTO
    ADD CONSTRAINT DB_EQUIPAMENTO_ID_SETOR_FK FOREIGN KEY (id_setor)
        REFERENCES DB_SETOR(id_setor);

ALTER TABLE DB_CONFIG_EQUIPAMENTO
    ADD CONSTRAINT DB_CONFIG_ID_EQUIPAMENTO_FK FOREIGN KEY (id_equipamento)
        REFERENCES DB_EQUIPAMENTO(id_equipamento);

ALTER TABLE DB_LEITURA_CONSUMO
    ADD CONSTRAINT DB_LEITURA_ID_EQUIPAMENTO_FK FOREIGN KEY (id_equipamento)
        REFERENCES DB_EQUIPAMENTO(id_equipamento);

ALTER TABLE DB_ALERTA
    ADD CONSTRAINT DB_ALERTA_ID_EQUIPAMENTO_FK FOREIGN KEY (id_equipamento)
        REFERENCES DB_EQUIPAMENTO(id_equipamento);

-- INSERÇÃO DE DADOS

-- DB_SETOR
INSERT INTO DB_SETOR VALUES (1, 'Diretoria', 'Décimo Andar - Sala 1002');
INSERT INTO DB_SETOR VALUES (2, 'Desenvolvimento de Software', 'Terceiro Andar - Sala 308');
INSERT INTO DB_SETOR VALUES (3, 'RH - Recursos Humanos', 'Sétimo Andar - Sala 705');
INSERT INTO DB_SETOR VALUES (4, 'Faturamento', 'Segundo Andar - Sala 202');
INSERT INTO DB_SETOR VALUES (5, 'Refeitório', 'Primeiro Andar');

-- DB_EQUIPAMENTO
INSERT INTO DB_EQUIPAMENTO VALUES (1, 'Ar Condicionado', 'Climatização', 1500.00, 'Ligado', 1);
INSERT INTO DB_EQUIPAMENTO VALUES (2, 'Geladeira', 'Refrigeração', 55.00, 'Ligado', 5);
INSERT INTO DB_EQUIPAMENTO VALUES (3, 'Computador (Hostname 557867)', 'Informática', 250.00, 'Manutenção', 3);
INSERT INTO DB_EQUIPAMENTO VALUES (4, 'Servidor', 'Infraestrutura', 2000.00, 'Ligado', 2);
INSERT INTO DB_EQUIPAMENTO VALUES (5, 'Cafeteira', 'Eletrodoméstico', 30.00, 'Desligado', 4);

-- CONFIG_EQUIPAMENTO
INSERT INTO DB_CONFIG_EQUIPAMENTO VALUES (1, 1500.00 * 1.10, 1);
INSERT INTO DB_CONFIG_EQUIPAMENTO VALUES (2, 55.00 * 1.10, 2);
INSERT INTO DB_CONFIG_EQUIPAMENTO VALUES (3, 250.00 * 1.10, 3);
INSERT INTO DB_CONFIG_EQUIPAMENTO VALUES (4, 2000.00 * 1.10, 4);
INSERT INTO DB_CONFIG_EQUIPAMENTO VALUES (5, 30.00 * 1.10, 5);

-- LEITURA_CONSUMO
INSERT INTO DB_LEITURA_CONSUMO VALUES (1, SYSDATE - 5, 1500.00 * 0.90, 1);
INSERT INTO DB_LEITURA_CONSUMO VALUES (2, SYSDATE - 4, 55.00 * 0.90, 2);
INSERT INTO DB_LEITURA_CONSUMO VALUES (3, SYSDATE - 3, 250.00 * 0.90, 3);
INSERT INTO DB_LEITURA_CONSUMO VALUES (4, SYSDATE - 2, 2000.00 * 0.90, 4);
INSERT INTO DB_LEITURA_CONSUMO VALUES (5, SYSDATE - 1, 30.00 * 0.90, 5);

-- ALERTA
INSERT INTO DB_ALERTA VALUES (1, 'Consumo Alto', 'Consumo ultrapassou o limite de 1650 kWh no Ar Condicionado', SYSDATE - 5, 1);
INSERT INTO DB_ALERTA VALUES (2, 'Consumo Alto', 'Consumo ultrapassou o limite de 60.5 kWh na Geladeira', SYSDATE - 4, 2);
INSERT INTO DB_ALERTA VALUES (3, 'Consumo Muito Alto', 'Consumo ultrapassou o limite de 275 kWh no Computador + 10%', SYSDATE - 3, 3);
INSERT INTO DB_ALERTA VALUES (4, 'Alerta Crítico', 'Consumo ultrapassou o limite de 2200 kWh no Servidor + 20%', SYSDATE - 2, 4);
INSERT INTO DB_ALERTA VALUES (5, 'Consumo Alto', 'Consumo ultrapassou o limite de 33 kWh na Cafeteira', SYSDATE - 1, 5);

-- CRIAÇÃO DAS SEQUENCES

CREATE SEQUENCE SEQ_DB_SETOR START WITH 6 INCREMENT BY 1;
CREATE SEQUENCE SEQ_DB_EQUIPAMENTO START WITH 6 INCREMENT BY 1;
CREATE SEQUENCE SEQ_DB_CONFIG_EQUIPAMENTO START WITH 6 INCREMENT BY 1;
CREATE SEQUENCE SEQ_DB_LEITURA_CONSUMO START WITH 6 INCREMENT BY 1;
CREATE SEQUENCE SEQ_DB_ALERTA START WITH 6 INCREMENT BY 1;

-- COMMIT FINAL
COMMIT;