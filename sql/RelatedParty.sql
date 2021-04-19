-- Table: public.related_party

-- DROP TABLE public.related_party;

CREATE TABLE public.related_party
(
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    href        character varying(100) COLLATE pg_catalog."default",
    name        character varying(100) COLLATE pg_catalog."default",
    role        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT related_party_pkey PRIMARY KEY (id),
    CONSTRAINT related_party_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.related_party
    OWNER to postgres;