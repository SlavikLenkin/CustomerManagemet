CREATE TABLE public.customer
(
    href            character varying(100) COLLATE pg_catalog."default",
    id              character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name            character varying(100) COLLATE pg_catalog."default",
    status          character varying(100) COLLATE pg_catalog."default",
    status_reason   character varying(100) COLLATE pg_catalog."default",
    start_date_time timestamp with time zone,
    end_date_time   timestamp with time zone,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
) TABLESPACE pg_default;

CREATE TABLE public.account
(
    href        character varying(100) COLLATE pg_catalog."default",
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name        character varying(100) COLLATE pg_catalog."default",
    description character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT accountref_pkey PRIMARY KEY (id),
    CONSTRAINT accountref_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to postgres;
	
	
CREATE TABLE public.agreement
(
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    href        character varying(100) COLLATE pg_catalog."default",
    name        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT agreement_pkey PRIMARY KEY (id),
    CONSTRAINT agreement_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.agreement
    OWNER to postgres;
	
	
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

CREATE TABLE public.credit_profile
(
    id                  character varying COLLATE pg_catalog."default" NOT NULL,
    credit_profile_date timestamp with time zone,
    credit_score        integer,
    start_date_time     timestamp with time zone,
    end_date_time       timestamp with time zone,
    customer_id         character varying COLLATE pg_catalog."default",
    credit_risk_rating  integer,
    CONSTRAINT credit_profile_pkey PRIMARY KEY (id),
    CONSTRAINT credit_profile_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.credit_profile
    OWNER to postgres;
	
CREATE TABLE public.contact_medium
(
    preferred       boolean                                             NOT NULL,
    medium_type     character varying(100) COLLATE pg_catalog."default" NOT NULL,
    start_date_time timestamp with time zone                            NOT NULL,
    end_date_time   timestamp with time zone                            NOT NULL,
    id              character varying(100) COLLATE pg_catalog."default" NOT NULL,
    customer_id     character varying COLLATE pg_catalog."default",
    CONSTRAINT contact_medium_pkey PRIMARY KEY (id),
    CONSTRAINT contact_medium_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.contact_medium
    OWNER to postgres;
	
CREATE TABLE public.engaged_party
(
    href        character varying(100) COLLATE pg_catalog."default",
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT engaged_party_pkey PRIMARY KEY (id),
    CONSTRAINT engaged_party_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.engaged_party
    OWNER to postgres;

CREATE TABLE public.medium_characteristic
(
    id                character varying COLLATE pg_catalog."default" NOT NULL,
    city              character varying COLLATE pg_catalog."default",
    contact_type      character varying COLLATE pg_catalog."default",
    country           character varying COLLATE pg_catalog."default",
    email_address     character varying COLLATE pg_catalog."default",
    fax_number        character varying COLLATE pg_catalog."default",
    phone_number      character varying COLLATE pg_catalog."default",
    post_code         character varying COLLATE pg_catalog."default",
    social_network_id character varying COLLATE pg_catalog."default",
    state_or_province character varying COLLATE pg_catalog."default",
    street_1          character varying COLLATE pg_catalog."default",
    street_2          character varying COLLATE pg_catalog."default",
    contact_medium_id character varying COLLATE pg_catalog."default",
    CONSTRAINT medium_characteristic_pkey PRIMARY KEY (id),
    CONSTRAINT medium_characteristic_fkey FOREIGN KEY (contact_medium_id)
        REFERENCES public.contact_medium (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.medium_characteristic
    OWNER to postgres;
	
CREATE TABLE public.payment_method
(
    href        character varying(100) COLLATE pg_catalog."default",
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT "paymentMethod_pkey" PRIMARY KEY (id),
    CONSTRAINT "paymentMethod_fkey" FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.payment_method
    OWNER to postgres;
	
CREATE TABLE public.related_party
(
    id          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    href        character varying(100) COLLATE pg_catalog."default",
    name        character varying(100) COLLATE pg_catalog."default",
    role        character varying(100) COLLATE pg_catalog."default",
    customer_id character varying COLLATE pg_catalog."default",
    CONSTRAINT related_party_pkey PRIMARY KEY (id),
    CONSTRAINT related_party_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
) TABLESPACE pg_default;

ALTER TABLE public.related_party
    OWNER to postgres;