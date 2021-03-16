-- Table: public.medium_characteristic

-- DROP TABLE public.medium_characteristic;

CREATE TABLE public.medium_characteristic
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default",
    contact_type character varying COLLATE pg_catalog."default",
    country character varying COLLATE pg_catalog."default",
    email_address character varying COLLATE pg_catalog."default",
    fax_number character varying COLLATE pg_catalog."default",
    phone_number character varying COLLATE pg_catalog."default",
    post_code character varying COLLATE pg_catalog."default",
    social_network_id character varying COLLATE pg_catalog."default",
    state_or_province character varying COLLATE pg_catalog."default",
    street_1 character varying COLLATE pg_catalog."default",
    street_2 character varying COLLATE pg_catalog."default",
    CONSTRAINT medium_characteristic_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.medium_characteristic
    OWNER to postgres;