
CREATE TABLE public.typechiffrage (
                id_typechiffrage INTEGER NOT NULL,
                typechiffrage VARCHAR(16) NOT NULL,
                CONSTRAINT typechiffrage_pk PRIMARY KEY (id_typechiffrage)
);


CREATE SEQUENCE public.valeurdouble_id_valeurtypee_seq;

CREATE TABLE public.valeurdouble (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurdouble_id_valeurtypee_seq'),
                valeur DOUBLE PRECISION NOT NULL,
                CONSTRAINT valeurdouble_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurdouble_id_valeurtypee_seq OWNED BY public.valeurdouble.id_valeurtypee;

CREATE SEQUENCE public.valeurboolean_id_valeurtypee_seq;

CREATE TABLE public.valeurboolean (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurboolean_id_valeurtypee_seq'),
                valeur BOOLEAN NOT NULL,
                CONSTRAINT valeurboolean_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurboolean_id_valeurtypee_seq OWNED BY public.valeurboolean.id_valeurtypee;

CREATE SEQUENCE public.valeurmdp_id_valeurtypee_seq;

CREATE TABLE public.valeurmdp (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurmdp_id_valeurtypee_seq'),
                valeur VARCHAR(512) NOT NULL,
                salt VARCHAR NOT NULL,
                id_typechiffrage INTEGER NOT NULL,
                CONSTRAINT valeurmdp_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurmdp_id_valeurtypee_seq OWNED BY public.valeurmdp.id_valeurtypee;

CREATE SEQUENCE public.valeurdate_id_valeurtypee_seq;

CREATE TABLE public.valeurdate (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurdate_id_valeurtypee_seq'),
                valeur DATE NOT NULL,
                CONSTRAINT valeurdate_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurdate_id_valeurtypee_seq OWNED BY public.valeurdate.id_valeurtypee;

CREATE SEQUENCE public.valeurtypee_id_valeurtypee_seq_3;

CREATE TABLE public.valeurbigint (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurtypee_id_valeurtypee_seq_3'),
                valeur BIGINT NOT NULL,
                CONSTRAINT valeurbigint_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurtypee_id_valeurtypee_seq_3 OWNED BY public.valeurbigint.id_valeurtypee;

CREATE SEQUENCE public.valeurinteger_id_valeurtypee_seq;

CREATE TABLE public.valeurinteger (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurinteger_id_valeurtypee_seq'),
                valeur INTEGER NOT NULL,
                CONSTRAINT valeurinteger_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurinteger_id_valeurtypee_seq OWNED BY public.valeurinteger.id_valeurtypee;

CREATE SEQUENCE public.valeurstring_id_valeurtypee_seq;

CREATE TABLE public.valeurstring (
                id_valeurtypee BIGINT NOT NULL DEFAULT nextval('public.valeurstring_id_valeurtypee_seq'),
                valeur VARCHAR(128) NOT NULL,
                CONSTRAINT valeurstring_pk PRIMARY KEY (id_valeurtypee)
);


ALTER SEQUENCE public.valeurstring_id_valeurtypee_seq OWNED BY public.valeurstring.id_valeurtypee;

CREATE TABLE public.typefermeture (
                id_typefermeture INTEGER NOT NULL,
                typefermeture VARCHAR(32) NOT NULL,
                CONSTRAINT typefermeture_pk PRIMARY KEY (id_typefermeture)
);


CREATE SEQUENCE public.domaine_id_domaine_seq;

CREATE TABLE public.domaine (
                id_domaine BIGINT NOT NULL DEFAULT nextval('public.domaine_id_domaine_seq'),
                ip VARCHAR(32) NOT NULL,
                CONSTRAINT domaine_pk PRIMARY KEY (id_domaine)
);


ALTER SEQUENCE public.domaine_id_domaine_seq OWNED BY public.domaine.id_domaine;

CREATE TABLE public.typeprop (
                id_typeprop INTEGER NOT NULL,
                typeprop VARCHAR(32) NOT NULL,
                CONSTRAINT typeprop_pk PRIMARY KEY (id_typeprop)
);


CREATE SEQUENCE public.propriete_id_propriete_seq;

CREATE TABLE public.propriete (
                id_propriete BIGINT NOT NULL DEFAULT nextval('public.propriete_id_propriete_seq'),
                supprimable BOOLEAN NOT NULL,
                modifiable BOOLEAN NOT NULL,
                nom VARCHAR(64) NOT NULL,
                nbrvalmin INTEGER NOT NULL,
                nbrvalmax INTEGER NOT NULL,
                taillevalmin INTEGER NOT NULL,
                taillevalmax INTEGER NOT NULL,
                id_typeprop INTEGER NOT NULL,
                principal BOOLEAN DEFAULT FALSE NOT NULL,
                CONSTRAINT propriete_pk PRIMARY KEY (id_propriete)
);


ALTER SEQUENCE public.propriete_id_propriete_seq OWNED BY public.propriete.id_propriete;

CREATE SEQUENCE public.user_1_id_user_seq;

CREATE TABLE public.user_1 (
                id_user BIGINT NOT NULL DEFAULT nextval('public.user_1_id_user_seq'),
                dateinscription DATE NOT NULL,
                datederniereconnexion DATE,
                nbrconnexion BIGINT DEFAULT 0 NOT NULL,
                CONSTRAINT user_1_pk PRIMARY KEY (id_user)
);


ALTER SEQUENCE public.user_1_id_user_seq OWNED BY public.user_1.id_user;

CREATE SEQUENCE public.valeur_id_valeur_seq;

CREATE TABLE public.valeur (
                id_valeur BIGINT NOT NULL DEFAULT nextval('public.valeur_id_valeur_seq'),
                id_propriete BIGINT NOT NULL,
                principale BOOLEAN DEFAULT FALSE NOT NULL,
                id_user BIGINT NOT NULL,
                id_valeurtypee BIGINT NOT NULL,
                CONSTRAINT valeur_pk PRIMARY KEY (id_valeur)
);


ALTER SEQUENCE public.valeur_id_valeur_seq OWNED BY public.valeur.id_valeur;

CREATE TABLE public.visibilite (
                id_valeur BIGINT NOT NULL,
                id_domaine BIGINT NOT NULL,
                CONSTRAINT visibilite_pk PRIMARY KEY (id_valeur, id_domaine)
);


CREATE SEQUENCE public.session_id_session_seq;

CREATE TABLE public.session (
                id_session BIGINT NOT NULL DEFAULT nextval('public.session_id_session_seq'),
                id_user BIGINT NOT NULL,
                id_domaine BIGINT NOT NULL,
                dateOuverture DATE NOT NULL,
                dateFermeture DATE NOT NULL,
                id_typefermeture INTEGER NOT NULL,
                CONSTRAINT session_pk PRIMARY KEY (id_session)
);


ALTER SEQUENCE public.session_id_session_seq OWNED BY public.session.id_session;

CREATE TABLE public.sessionprop (
                id_session BIGINT NOT NULL,
                id_propriete BIGINT NOT NULL,
                demande BOOLEAN NOT NULL,
                accepte BOOLEAN NOT NULL,
                CONSTRAINT sessionprop_pk PRIMARY KEY (id_session, id_propriete)
);


ALTER TABLE public.valeurmdp ADD CONSTRAINT typechiffrage_valeurmdp_fk
FOREIGN KEY (id_typechiffrage)
REFERENCES public.typechiffrage (id_typechiffrage)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.session ADD CONSTRAINT typefermeture_session_fk
FOREIGN KEY (id_typefermeture)
REFERENCES public.typefermeture (id_typefermeture)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.visibilite ADD CONSTRAINT domaine_visibilite_fk
FOREIGN KEY (id_domaine)
REFERENCES public.domaine (id_domaine)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.session ADD CONSTRAINT domaine_session_fk
FOREIGN KEY (id_domaine)
REFERENCES public.domaine (id_domaine)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.propriete ADD CONSTRAINT typeprop_propriete_fk
FOREIGN KEY (id_typeprop)
REFERENCES public.typeprop (id_typeprop)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.valeur ADD CONSTRAINT propriete_valeur_fk
FOREIGN KEY (id_propriete)
REFERENCES public.propriete (id_propriete)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sessionprop ADD CONSTRAINT propriete_sessionprop_fk
FOREIGN KEY (id_propriete)
REFERENCES public.propriete (id_propriete)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.session ADD CONSTRAINT user_session_fk
FOREIGN KEY (id_user)
REFERENCES public.user_1 (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.valeur ADD CONSTRAINT user_1_valeur_fk
FOREIGN KEY (id_user)
REFERENCES public.user_1 (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.visibilite ADD CONSTRAINT valeur_visibilite_fk
FOREIGN KEY (id_valeur)
REFERENCES public.valeur (id_valeur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sessionprop ADD CONSTRAINT session_sessionprop_fk
FOREIGN KEY (id_session)
REFERENCES public.session (id_session)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
