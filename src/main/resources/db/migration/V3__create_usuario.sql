CREATE TABLE DB_USUARIO (
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    login VARCHAR2(100) NOT NULL UNIQUE,
    senha VARCHAR2(255) NOT NULL,
    role VARCHAR2(50) DEFAULT 'USER'
);

INSERT INTO DB_USUARIO (login, senha, role) VALUES ('Allef', '12345', 'ADMIN')