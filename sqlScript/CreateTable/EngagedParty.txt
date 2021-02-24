-- Table: public.engaged_party

-- DROP TABLE public.engaged_party;

CREATE TABLE public.engaged_party
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT engaged_party_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.engaged_party
    OWNER to postgres;