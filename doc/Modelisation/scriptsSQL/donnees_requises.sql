
INSERT INTO typeprop (id_typeprop, typeprop) VALUES
(1, 'string'),
(2, 'integer'),
(3, 'boolean'),
(4, 'date'),
(5, 'mdp'),
(6, 'email'),
(7, 'bigint'),
(8, 'double')
;

INSERT INTO propriete (nom, id_typeprop, principal, supprimable, modifiable, nbrvalmin, nbrvalmax, taillevalmin, taillevalmax) VALUES
('login', 1, true, false, false, 1, 64, 3, 32),
('mdp', 5, true, false, true, 1, 64, 6, 32),
('mail', 6, true, true, true, 1, 64, 6, 128)
;

INSERT INTO typechiffrage (id_typechiffrage, typechiffrage) VALUES
(1, 'MD5'),
(2, 'SHA-1'),
(3, 'SHA-128'),
(4, 'SHA-256'),
(5, 'SHA-384'),
(6, 'SHA-512')
;