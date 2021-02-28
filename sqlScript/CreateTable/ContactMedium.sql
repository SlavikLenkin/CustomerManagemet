-- Table: public.contact_medium

-- DROP TABLE public.contact_medium;

CREATE TABLE public.contact_medium
(
    preferred boolean NOT NULL,
    medium_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    start_date_time timestamp with time zone NOT NULL,
    end_date_time timestamp with time zone NOT NULL,
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT contact_medium_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.contact_medium
    OWNER to postgres;