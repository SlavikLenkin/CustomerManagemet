-- Table: public.payment_method

-- DROP TABLE public.payment_method;

CREATE TABLE public.payment_method
(
    href character varying(100) COLLATE pg_catalog."default",
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT "paymentMethod_pkey" PRIMARY KEY (id),
    CONSTRAINT "paymentMethod_fkey" FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.payment_method
    OWNER to postgres;