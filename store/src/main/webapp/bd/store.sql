/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2016-03-28 9:23:26                           */
/*==============================================================*/
/*==============================================================*/
/* SECUENCIAS                                           */
/*==============================================================*/	  
CREATE SEQUENCE seq_fab_pedido_cab
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_pedido_cab
  OWNER TO postgres;

  CREATE SEQUENCE seq_fab_pedido_det
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_pedido_det
  OWNER TO postgres;
  
 CREATE SEQUENCE seq_fab_catalogo
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_catalogo
  OWNER TO postgres;
 
 CREATE SEQUENCE seq_fab_catalogoitems
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_catalogoitems
  OWNER TO postgres;

  CREATE SEQUENCE seq_fab_horario
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_horario
  OWNER TO postgres;
    
CREATE SEQUENCE seq_fab_horario_nodisponible
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_horario_nodisponible
  OWNER TO postgres;

  
CREATE SEQUENCE seq_fab_producto_fotos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_fab_producto_fotos
  OWNER TO postgres;
  
/*==============================================================*/
/* Table: FAB_CATALOGO                                          */
/*==============================================================*/
create table FAB_CATALOGO (
   CAT_ID               INT4                 not null DEFAULT nextval('seq_fab_catalogo'::regclass),
   CAT_NOMBRE           VARCHAR(200)         null,
   CAT_VALOR            CHAR                 null,
   constraint PK_FAB_CATALOGO primary key (CAT_ID)
);

/*==============================================================*/
/* Table: FAB_CATALOGOITEMS                                     */
/*==============================================================*/
create table FAB_CATALOGOITEMS (
   CATI_ID              INT4                 not null DEFAULT nextval('seq_fab_catalogoitems'::regclass),
   CAT_ID               INT4                 null,
   CATI_ID_PADRE        INT4                 null,
   CATI_NOMBRE          VARCHAR(100)         null,
   CATI_IMAGEN          VARCHAR(200)         null,
   CATI_ESTADO          CHAR                 null,
   constraint PK_FAB_CATALOGOITEMS primary key (CATI_ID)
);

/*==============================================================*/
/* Table: FAB_DIAS                                              */
/*==============================================================*/
create table FAB_DIAS (
   DIA_ID               INT4                 not null,
   DIA_NOMBRE           VARCHAR(20)          null,
   DIA_ESTADO           CHAR                 null,
   constraint PK_FAB_DIAS primary key (DIA_ID)
);

/*==============================================================*/
/* Table: FAB_HORARIO                                           */
/*==============================================================*/
create table FAB_HORARIO (
   HOR_ID               INT4                 not null DEFAULT nextval('seq_fab_horario'::regclass),
   PRO_ID               VARCHAR(20)          null,
   DIA_ID               INT4                 null,
   HOR_HORA_INICIO      TIME                 null,
   HOR_HORA_FINAL       TIME                 null,
   HOR_ESTADO           CHAR                 null,
   constraint PK_FAB_HORARIO primary key (HOR_ID)
);

/*==============================================================*/
/* Table: FAB_HORARIO_NODISPONIBLE                              */
/*==============================================================*/
create table FAB_HORARIO_NODISPONIBLE (
   HORN_ID              INT4                 not null DEFAULT nextval('seq_fab_horario_nodisponible'::regclass),
   PRO_ID               VARCHAR(20)          null,
   HORNODIS_DIA         DATE                 null,
   HORNODIS_HINICIAL    TIME                 null,
   HORNODIS_HFINAL      TIME                 null,
   HORNODIS_ESTADO      CHAR                 null,
   constraint PK_FAB_HORARIO_NODISPONIBLE primary key (HORN_ID)
);

/*==============================================================*/
/* Table: FAB_PEDIDO_CAB                                        */
/*==============================================================*/
create table FAB_PEDIDO_CAB (
   PEDC_ID              INT4                 not null DEFAULT nextval('seq_fab_pedido_cab'::regclass),
   USR_ID               VARCHAR(20)          null,
   PEDC_FECHA           DATE                 null,
   PEDC_FECHA_APROBACION TIMESTAMP            null,
   PEDC_ARCHIVO_PAGO    VARCHAR(255)         null,
   PEDC_OBSERVACION     VARCHAR(255)         null,
   PEDC_ESTADO          CHAR                 null,
   constraint PK_FAB_PEDIDO_CAB primary key (PEDC_ID)
);

/*==============================================================*/
/* Table: FAB_PEDIDO_DET                                        */
/*==============================================================*/
create table FAB_PEDIDO_DET (
   PEDD_ID              INT4                 not null DEFAULT nextval('seq_fab_pedido_det'::regclass),
   PEDC_ID              INT4                 null,
   PRO_ID               VARCHAR(20)          null,
   PEDD_CANTIDAD        INT4                 null,
   PEDD_COSTO           NUMERIC              null,
   PEDD_PRECIO          NUMERIC              null,
   PEDD_IMPUESTO        NUMERIC              null,
   PEDD_DESCUENTO       NUMERIC              null,
   constraint PK_FAB_PEDIDO_DET primary key (PEDD_ID)
);

/*==============================================================*/
/* Table: FAB_PRODUCTO                                          */
/*==============================================================*/
create table FAB_PRODUCTO (
   PRO_ID               VARCHAR(20)          not null,
   CATI_ID              INT4                 null,
   PRO_CODIGO_BARRAS    VARCHAR(100)         null,
   PRO_TIPO             CHAR                 null,
   PRO_NOMBRE           VARCHAR(200)         null,
   PRO_DESCRIPCION      VARCHAR(255)         null,
   PRO_COSTO            NUMERIC              null,
   PRO_PRECIO           NUMERIC              null,
   PRO_STOCK            INT4                 null,
   PRO_ESTADO           CHAR                 null,
   PRO_ESTADO_FUNCIONAL CHAR                 null,
   constraint PK_FAB_PRODUCTO primary key (PRO_ID)
);

/*==============================================================*/
/* Table: FAB_PRODUCTO_FOTOS                                    */
/*==============================================================*/
create table FAB_PRODUCTO_FOTOS (
   PROF_ID              INT4                 not null DEFAULT nextval('seq_fab_producto_fotos'::regclass),
   PRO_ID               VARCHAR(20)          null,
   PROF_NOMBRE          VARCHAR(200)         null,
   PROF_DIRECCION       VARCHAR(200)         null,
   PROF_ESTADO          CHAR                 null,
    constraint PK_FAB_PRODUCTO_FOTOS primary key (PROF_ID)
);

/*==============================================================*/
/* Table: FAB_USUARIO                                           */
/*==============================================================*/
create table FAB_USUARIO (
   USR_ID               VARCHAR(20)          not null,
   USR_NOMBRE           VARCHAR(100)         null,
   USR_APELLIDO         VARCHAR(100)         null,
   USR_CORREO           VARCHAR(200)         null,
   USR_PASSWORD         VARCHAR(200)         null,
   USR_DIRECCION        VARCHAR(255)         null,
   USR_FECHA_NACIMIENTO DATE                 null,
   USR_TELEFONO         VARCHAR(20)          null,
   USR_ESTADO           CHAR                 null,
   USR_ESTADO_FUNCIONAL CHAR                 null,
   USR_TIPO_USUARIO     CHAR                 null,
   constraint PK_FAB_USUARIO primary key (USR_ID)
);

alter table FAB_CATALOGOITEMS
   add constraint FK_FAB_CATA_REFERENCE_FAB_CATA foreign key (CAT_ID)
      references FAB_CATALOGO (CAT_ID)
      on delete restrict on update restrict;

alter table FAB_HORARIO
   add constraint FK_FAB_HORA_REFERENCE_FAB_PROD foreign key (PRO_ID)
      references FAB_PRODUCTO (PRO_ID)
      on delete restrict on update restrict;

alter table FAB_HORARIO
   add constraint FK_FAB_HORA_REFERENCE_FAB_DIAS foreign key (DIA_ID)
      references FAB_DIAS (DIA_ID)
      on delete restrict on update restrict;
      
alter table FAB_HORARIO_NODISPONIBLE
   add constraint FK_FAB_HORA_REFERENCE_FAB_PROD foreign key (PRO_ID)
      references FAB_PRODUCTO (PRO_ID)
      on delete restrict on update restrict;

alter table FAB_PEDIDO_CAB
   add constraint FK_FAB_PEDI_REFERENCE_FAB_USUA foreign key (USR_ID)
      references FAB_USUARIO (USR_ID)
      on delete restrict on update restrict;

alter table FAB_PEDIDO_DET
   add constraint FK_FAB_PEDI_REFERENCE_FAB_PROD foreign key (PRO_ID)
      references FAB_PRODUCTO (PRO_ID)
      on delete restrict on update restrict;

alter table FAB_PEDIDO_DET
   add constraint FK_FAB_PEDI_REFERENCE_FAB_PEDI foreign key (PEDC_ID)
      references FAB_PEDIDO_CAB (PEDC_ID)
      on delete restrict on update restrict;

alter table FAB_PRODUCTO
   add constraint FK_FAB_PROD_REFERENCE_FAB_CATA foreign key (CATI_ID)
      references FAB_CATALOGOITEMS (CATI_ID)
      on delete restrict on update restrict;

alter table FAB_PRODUCTO_FOTOS
   add constraint FK_FAB_PROD_REFERENCE_FAB_PROD foreign key (PRO_ID)
      references FAB_PRODUCTO (PRO_ID)
      on delete restrict on update restrict;