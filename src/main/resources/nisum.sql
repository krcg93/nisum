--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Homebrew)
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS nisum;
--
-- Name: nisum; Type: DATABASE; Schema: -; Owner: kevinchilito
--

CREATE DATABASE nisum WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';


ALTER DATABASE nisum OWNER TO kevinchilito;

\connect nisum

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: nisum; Type: SCHEMA; Schema: -; Owner: kevinchilito
--

CREATE SCHEMA nisum;


ALTER SCHEMA nisum OWNER TO kevinchilito;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: phone; Type: TABLE; Schema: nisum; Owner: kevinchilito
--

CREATE TABLE nisum.phone (
    id integer NOT NULL,
    number character(15),
    citycode character(3),
    contrycode character(3),
    userid bigint NOT NULL
);


ALTER TABLE nisum.phone OWNER TO kevinchilito;

--
-- Name: phone_id_seq; Type: SEQUENCE; Schema: nisum; Owner: kevinchilito
--

ALTER TABLE nisum.phone ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME nisum.phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user; Type: TABLE; Schema: nisum; Owner: kevinchilito
--

CREATE TABLE nisum."user" (
    id bigint NOT NULL,
    name character(30) NOT NULL,
    email character(100) NOT NULL,
    password character(100) NOT NULL,
    created character(50),
    modified character(50),
    lastlogin character(50),
    token character(100),
    isactive boolean
);


ALTER TABLE nisum."user" OWNER TO kevinchilito;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: nisum; Owner: kevinchilito
--

ALTER TABLE nisum."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME nisum.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: phone phone_pkey; Type: CONSTRAINT; Schema: nisum; Owner: kevinchilito
--

ALTER TABLE ONLY nisum.phone
    ADD CONSTRAINT phone_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: nisum; Owner: kevinchilito
--

ALTER TABLE ONLY nisum."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: phone FK_UserPhone; Type: FK CONSTRAINT; Schema: nisum; Owner: kevinchilito
--

ALTER TABLE ONLY nisum.phone
    ADD CONSTRAINT "FK_UserPhone" FOREIGN KEY (userid) REFERENCES nisum."user"(id);


--
-- PostgreSQL database dump complete
--

