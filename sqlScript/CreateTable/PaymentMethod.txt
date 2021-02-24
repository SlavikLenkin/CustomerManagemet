-- Table: public.payment_method

-- DROP TABLE public.payment_method;

CREATE TABLE public.payment_method
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT "paymentMethod_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.payment_method
    OWNER to postgres;