-- Table: public.characteristic

-- DROP TABLE public.characteristic;

CREATE TABLE public.characteristic
(
    name        character varying(100) COLLATE pg_catalog."default",
    value       character varying(100) COLLATE pg_catalog."default",
    value_type  character varying(100) COLLATE pg_catalog."default",
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT charecterictic_pkey PRIMARY KEY (id),
    CONSTRAINT charecterictic_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.characteristic
    OWNER to postgres;