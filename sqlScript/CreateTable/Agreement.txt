-- Table: public.agreement

-- DROP TABLE public.agreement;

CREATE TABLE public.agreement
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    href character varying(100) COLLATE pg_catalog."default",
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT agreement_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.agreement
    OWNER to postgres;