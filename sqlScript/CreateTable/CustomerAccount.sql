-- Table: public.customer_account

-- DROP TABLE public.customer_account;

CREATE TABLE public.customer_account
(
    customer_id character varying COLLATE pg_catalog."default" NOT NULL,
    account_id character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customer_accont_pkey PRIMARY KEY (customer_id, account_id),
    CONSTRAINT customer_account_account_id_key UNIQUE (account_id),
    CONSTRAINT customer_accont_account_fkey FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT customer_accont_customer_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.customer_account
    OWNER to postgres;