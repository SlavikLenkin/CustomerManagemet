-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    description character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT accountref_pkey PRIMARY KEY (id),
    CONSTRAINT accountref_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to postgres;