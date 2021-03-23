-- Table: public.customer

-- DROP TABLE public.customer;

CREATE TABLE public.customer
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    status character varying(100) COLLATE pg_catalog."default",
    status_reason character varying(100) COLLATE pg_catalog."default",
    start_date_time timestamp with time zone,
    end_date_time timestamp with time zone,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.customer
    OWNER to postgres;