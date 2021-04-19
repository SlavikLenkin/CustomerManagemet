-- Table: public.engaged_party

-- DROP TABLE public.engaged_party;

CREATE TABLE public.engaged_party
(
    href        character varying(100) COLLATE pg_catalog."default",
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT engaged_party_pkey PRIMARY KEY (id),
    CONSTRAINT engaged_party_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.engaged_party
    OWNER to postgres;