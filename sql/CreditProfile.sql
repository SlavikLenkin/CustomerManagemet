-- Table: public.credit_profile

-- DROP TABLE public.credit_profile;

CREATE TABLE public.credit_profile
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    credit_profile_date timestamp with time zone,
    credit_score integer,
    start_date_time timestamp with time zone,
    end_date_time timestamp with time zone,
    customer_id character varying COLLATE pg_catalog."default",
    credit_risk_rating integer,
    CONSTRAINT credit_profile_pkey PRIMARY KEY (id),
    CONSTRAINT credit_profile_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.credit_profile
    OWNER to postgres;