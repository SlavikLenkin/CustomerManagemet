-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    description character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT accountref_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to postgres;