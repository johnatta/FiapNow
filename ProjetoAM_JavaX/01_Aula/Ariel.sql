create table tab_audit_DML(
nom_tabela varchar2(30),
nom_usuario varchar2(30),
dat_evento date,
tip_evento varchar2(30)
);


CREATE OR REPLACE TRIGGER TRG_AUDIT_CLIENTE
BEFORE INSERT OR UPDATE OR DELETE ON LOC_CLIENTE
DECLARE V_TEXTO VARCHAR2(100);
BEGIN
IF INSERTING THEN
INSERT INTO TAB_AUDIT_DML VALUES ( 'LOC_CLIENTE', USER, SYSDATE,
'INSERT');
ELSIF UPDATING THEN
INSERT INTO TAB_AUDIT_DML VALUES ( 'LOC_CLIENTE', USER, SYSDATE,
'UPDATE');
ELSE
INSERT INTO TAB_AUDIT_DML VALUES ( 'LOC_CLIENTE', USER, SYSDATE,
'DELETE');
END IF;
END;

----------------------------------------------------------------

INSERT INTO LOC_CLIENTE (CD_CLIENTE,NM_CLIENTE,TELEFONE,TP_CLIENTE) VALUES
(10001, 'Yuri Xulambs da Silva Sauro', 25448555,'F');
INSERT INTO LOC_CLIENTE (CD_CLIENTE,NM_CLIENTE,TELEFONE,TP_CLIENTE) VALUES
(10002, 'Giulia Santos', 31774488,'F');
UPDATE LOC_CLIENTE SET NR_ESTRELAS = 4 WHERE CD_CLIENTE > 10000;

ROLLBACK;


----------------------------------------------------------------

insert into loc_funcionario values ( 22, 'Gustavo Lopes', 	to_date('15122008','ddmmyyyy'), null , 08352851488, 22541, 21, 50,2,3);
ROLLBACK;

CREATE OR REPLACE TRIGGER TRG_X
BEFORE INSERT ON LOC_FUNCIONARIO
BEGIN
  prc_grv_log_audit('LOC_FUNCIONARIO','INSERT');
END;

CREATE OR REPLACE PROCEDURE prc_grv_log_audit(p_table varchar2, p_command varchar2) AS
PRAGMA autonomous_transaction;
BEGIN
  INSERT INTO TAB_AUDIT_DML VALUES (p_table,USER,SYSDATE,p_command);
  COMMIT;
END;