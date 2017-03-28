
-- Créé un user avec son login, mdp, et mail

INSERT INTO user_1(id_user, dateinscription, datederniereconnexion, nbrconnexion) VALUES
(1, '2017-03-25', null, 0)
;

INSERT INTO valeurstring(id_valeurtypee, valeur) VALUES
(1, 'chnapy'),
(2, 'mail@mail.com'),
(4, 'supprimable@mail.com')
;

--123456
INSERT INTO valeurmdp(id_valeurtypee, valeur, salt, id_typechiffrage) VALUES
(3, '3014f68750c513c98f518d144ba79445bb32a3fc501f70127f23e293257b89e2', '57.-91.-84.29.93.77.105.43.-98.7.116.-19.-89.126.-109.33', 4)
;

INSERT INTO valeur(id_valeur, id_propriete, principale, id_user, id_valeurtypee) VALUES
(1, 1, true, 1, 1),
(2, 3, true, 1, 2),
(3, 2, true, 1, 3)
(4, 3, false, 1, 4),
;

-- Créé des domaines

INSERT INTO domaine(id_domaine, ip) VALUES
(1, 'http://google.com'),
(2, 'http://youtube.com'),
(3, 'http://twitter.com'),
(4, 'http://richardhaddad.fr'),
(5, 'http://urlsuperlonguehistoiredetesterlesdebordementsdeconte.nu'),
(6, 'https://github.com')
;

-- Lie les domaines aux valeurs

INSERT INTO visibilite(id_valeur, id_domaine) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(3, 1),
(3, 2),
(3, 4),
(3, 6)
;
