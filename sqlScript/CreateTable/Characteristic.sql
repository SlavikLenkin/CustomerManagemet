-- Table: public.characteristic

-- DROP TABLE public.characteristic;

CREATE TABLE public.characteristic
(
    name character varying(100) COLLATE pg_catalog."default",
    value character varying(100) COLLATE pg_catalog."default",
    value_type character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT charecterictic_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.characteristic
    OWNER to postgres;