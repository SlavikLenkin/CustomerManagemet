-- Table: public.contact_medium

-- DROP TABLE public.contact_medium;

CREATE TABLE public.contact_medium
(
    preferred boolean NOT NULL,
    medium_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    start_date_time timestamp with time zone NOT NULL,
    end_date_time timestamp with time zone NOT NULL,
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT contact_medium_pkey PRIMARY KEY (id),
    CONSTRAINT contact_medium_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.contact_medium
    OWNER to postgres;