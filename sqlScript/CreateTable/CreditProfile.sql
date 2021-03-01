-- Table: public.credit_profile

-- DROP TABLE public.credit_profile;

CREATE TABLE public.credit_profile
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    credit_profile_date timestamp with time zone,
    credit_risck_raiting integer,
    credit_score integer,
    start_date_time timestamp with time zone,
    end_date_time timestamp with time zone,
    CONSTRAINT credit_profile_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.credit_profile
    OWNER to postgres;