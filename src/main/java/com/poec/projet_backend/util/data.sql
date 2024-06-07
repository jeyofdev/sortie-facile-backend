DROP TABLE IF EXISTS Department;
DROP TABLE IF EXISTS Region;
DROP TABLE IF EXISTS City;

INSERT INTO Region (id, name) VALUES
                                  (1, 'Auvergne-Rhône-Alpes'),
                                  (2, 'Bourgogne-Franche-Comté'),
                                  (3, 'Bretagne'),
                                  (4, 'Centre-Val de Loire'),
                                  (5, 'Corse'),
                                  (6, 'Grand Est'),
                                  (7, 'Hauts-de-France'),
                                  (8, 'Île-de-France'),
                                  (9, 'Normandie'),
                                  (10, 'Nouvelle-Aquitaine'),
                                  (11, 'Occitanie'),
                                  (12, 'Pays de la Loire'),
                                  (13, 'Provence-Alpes-Côte d''Azur');

-- Création de la table Department

-- Insertion des données dans la table Department
INSERT INTO Department (id, name, region_id) VALUES
                                                 (1, 'Ain', 1),
                                                 (2, 'Aisne', 7),
                                                 (3, 'Allier', 1),
                                                 (4, 'Alpes-de-Haute-Provence', 13),
                                                 (5, 'Hautes-Alpes', 13),
                                                 (6, 'Alpes-Maritimes', 13),
                                                 (7, 'Ardèche', 1),
                                                 (8, 'Ardennes', 6),
                                                 (9, 'Ariège', 11),
                                                 (10, 'Aube', 6),
                                                 (11, 'Aude', 11),
                                                 (12, 'Aveyron', 11),
                                                 (13, 'Bouches-du-Rhône', 13),
                                                 (14, 'Calvados', 9),
                                                 (15, 'Cantal', 1),
                                                 (16, 'Charente', 10),
                                                 (17, 'Charente-Maritime', 10),
                                                 (18, 'Cher', 4),
                                                 (19, 'Corrèze', 10),
                                                 (20, 'Corse-du-Sud', 5),
                                                 (21, 'Haute-Corse', 5),
                                                 (22, 'Côte-d''Or', 2),
                                                 (23, 'Côtes-d''Armor', 3),
                                                 (24, 'Creuse', 10),
                                                 (25, 'Dordogne', 10),
                                                 (26, 'Doubs', 2),
                                                 (27, 'Drôme', 1),
                                                 (28, 'Eure', 9),
                                                 (29, 'Eure-et-Loir', 4),
                                                 (30, 'Finistère', 3),
                                                 (31, 'Gard', 11),
                                                 (32, 'Haute-Garonne', 11),
                                                 (33, 'Gers', 11),
                                                 (34, 'Gironde', 10),
                                                 (35, 'Hérault', 11),
                                                 (36, 'Ille-et-Vilaine', 3),
                                                 (37, 'Indre', 4),
                                                 (38, 'Indre-et-Loire', 4),
                                                 (39, 'Isère', 1),
                                                 (40, 'Jura', 2),
                                                 (41, 'Landes', 10),
                                                 (42, 'Loir-et-Cher', 4),
                                                 (43, 'Loire', 1),
                                                 (44, 'Haute-Loire', 1),
                                                 (45, 'Loire-Atlantique', 12),
                                                 (46, 'Loiret', 4),
                                                 (47, 'Lot', 11),
                                                 (48, 'Lot-et-Garonne', 10),
                                                 (49, 'Lozère', 11),
                                                 (50, 'Maine-et-Loire', 12),
                                                 (51, 'Manche', 9),
                                                 (52, 'Marne', 6),
                                                 (53, 'Haute-Marne', 6),
                                                 (54, 'Mayenne', 12),
                                                 (55, 'Meurthe-et-Moselle', 6),
                                                 (56, 'Meuse', 6),
                                                 (57, 'Morbihan', 3),
                                                 (58, 'Moselle', 6),
                                                 (59, 'Nièvre', 2),
                                                 (60, 'Nord', 7),
                                                 (61, 'Oise', 7),
                                                 (62, 'Orne', 9),
                                                 (63, 'Pas-de-Calais', 7),
                                                 (64, 'Puy-de-Dôme', 1),
                                                 (65, 'Pyrénées-Atlantiques', 10),
                                                 (66, 'Hautes-Pyrénées', 11),
                                                 (67, 'Pyrénées-Orientales', 11),
                                                 (68, 'Bas-Rhin', 6),
                                                 (69, 'Haut-Rhin', 6),
                                                 (70, 'Rhône', 1),
                                                 (71, 'Haute-Saône', 2),
                                                 (72, 'Saône-et-Loire', 2),
                                                 (73, 'Sarthe', 12),
                                                 (74, 'Savoie', 1),
                                                 (75, 'Haute-Savoie', 1),
                                                 (76, 'Paris', 8),
                                                 (77, 'Seine-Maritime', 9),
                                                 (78, 'Seine-et-Marne', 8),
                                                 (79, 'Yvelines', 8),
                                                 (80, 'Deux-Sèvres', 10),
                                                 (81, 'Somme', 7),
                                                 (82, 'Tarn', 11),
                                                 (83, 'Tarn-et-Garonne', 11),
                                                 (84, 'Var', 13),
                                                 (85, 'Vaucluse', 13),
                                                 (86, 'Vendée', 12),
                                                 (87, 'Vienne', 10),
                                                 (88, 'Haute-Vienne', 10),
                                                 (89, 'Vosges', 6),
                                                 (90, 'Yonne', 2),
                                                 (91, 'Essonne', 8),
                                                 (92, 'Hauts-de-Seine', 8),
                                                 (93, 'Seine-Saint-Denis', 8),
                                                 (94, 'Val-de-Marne', 8),
                                                 (95, 'Val-d''Oise', 8);

-- Insertion des préfectures et sous-préfectures dans la table City
-- Insertion des préfectures et sous-préfectures dans la table City avec code postal
INSERT INTO City(id, postal_code, name, department_id) VALUES
                                                          (1, 01000, 'Bourg-en-Bresse', 1), -- Ain
                                                          (2, 01300, 'Belley', 1),
                                                          (3, 01170, 'Gex', 1),
                                                          (4, 01460, 'Nantua', 1),
                                                          (5, 02000, 'Laon', 2), -- Aisne
                                                          (6, 02400, 'Château-Thierry', 2),
                                                          (7, 02100, 'Saint-Quentin', 2),
                                                          (8, 02200, 'Soissons', 2),
                                                          (9, 02140, 'Vervins', 2),
                                                          (10, 03000, 'Moulins', 3), -- Allier
                                                          (11, 03100, 'Montluçon', 3),
                                                          (12, 03200, 'Vichy', 3),
                                                          (13, 04000, 'Digne-les-Bains', 4), -- Alpes-de-Haute-Provence
                                                          (14, 04400, 'Barcelonnette', 4),
                                                          (15, 04120, 'Castellane', 4),
                                                          (16, 04300, 'Forcalquier', 4),
                                                          (17, 05000, 'Gap', 5), -- Hautes-Alpes
                                                          (18, 05100, 'Briançon', 5),
                                                          (19, 06000, 'Nice', 6), -- Alpes-Maritimes
                                                          (20, 06130, 'Grasse', 6),
                                                          (21, 07000, 'Privas', 7), -- Ardèche
                                                          (22, 07110, 'Largentière', 7),
                                                          (23, 07300, 'Tournon-sur-Rhône', 7),
                                                          (24, 08000, 'Charleville-Mézières', 8), -- Ardennes
                                                          (25, 08300, 'Rethel', 8),
                                                          (26, 08200, 'Sedan', 8),
                                                          (27, 08400, 'Vouziers', 8),
                                                          (28, 09000, 'Foix', 9), -- Ariège
                                                          (29, 09100, 'Pamiers', 9),
                                                          (30, 09200, 'Saint-Girons', 9),
                                                          (31, 10000, 'Troyes', 10), -- Aube
                                                          (32, 10200, 'Bar-sur-Aube', 10),
                                                          (33, 10400, 'Nogent-sur-Seine', 10),
                                                          (34, 11000, 'Carcassonne', 11), -- Aude
                                                          (35, 11300, 'Limoux', 11),
                                                          (36, 11100, 'Narbonne', 11),
                                                          (37, 12000, 'Rodez', 12), -- Aveyron
                                                          (38, 12100, 'Millau', 12),
                                                          (39, 12200, 'Villefranche-de-Rouergue', 12),
                                                          (40, 13000, 'Marseille', 13), -- Bouches-du-Rhône
                                                          (41, 13100, 'Aix-en-Provence', 13),
                                                          (42, 13200, 'Arles', 13),
                                                          (43, 14000, 'Caen', 14), -- Calvados
                                                          (44, 14400, 'Bayeux', 14),
                                                          (45, 14100, 'Lisieux', 14),
                                                          (46, 14500, 'Vire', 14),
                                                          (47, 15000, 'Aurillac', 15), -- Cantal
                                                          (48, 15200, 'Mauriac', 15),
                                                          (49, 15100, 'Saint-Flour', 15),
                                                          (50, 16000, 'Angoulême', 16), -- Charente
                                                          (51, 16500, 'Confolens', 16),
                                                          (52, 16100, 'Cognac', 16),
                                                          (53, 17000, 'La Rochelle', 17), -- Charente-Maritime
                                                          (54, 17500, 'Jonzac', 17),
                                                          (55, 17300, 'Rochefort', 17),
                                                          (56, 17100, 'Saintes', 17),
                                                          (57, 17400, 'Saint-Jean-d''Angély', 17),
(58, 18000, 'Bourges', 18), -- Cher
(59, 18200, 'Saint-Amand-Montrond', 18),
(60, 18100, 'Vierzon', 18),
(61, 19000, 'Tulle', 19), -- Corrèze
(62, 19100, 'Brive-la-Gaillarde', 19),
(63, 19200, 'Ussel', 19),
(64, 20000, 'Ajaccio', 20), -- Corse-du-Sud
(65, 20100, 'Sartène', 20),
(66, 20200, 'Bastia', 21), -- Haute-Corse
(67, 20260, 'Calvi', 21),
(68, 20250, 'Corte', 21),
(69, 21000, 'Dijon', 22), -- Côte-d'Or
(70, 21200, 'Beaune', 22),
                                                           (71, 21500, 'Montbard', 22),
                                                           (72, 22000, 'Saint-Brieuc', 23), -- Côtes-d'Armor
                                                           (73, 22100, 'Dinan', 23),
                                                           (74, 22200, 'Guingamp', 23),
                                                           (75, 22300, 'Lannion', 23),
                                                           (76, 22440, 'Plérin', 23),
                                                           (77, 23000, 'Guéret', 24), -- Creuse
                                                           (78, 23200, 'Aubusson', 24),
                                                           (79, 24000, 'Périgueux', 25), -- Dordogne
                                                           (80, 24100, 'Bergerac', 25),
                                                           (81, 24300, 'Nontron', 25),
                                                           (82, 24200, 'Sarlat-la-Canéda', 25),
                                                           (83, 25000, 'Besançon', 26), -- Doubs
                                                           (84, 25200, 'Montbéliard', 26),
                                                           (85, 25300, 'Pontarlier', 26),
                                                           (86, 26000, 'Valence', 27), -- Drôme
                                                           (87, 26150, 'Die', 27),
                                                           (88, 26110, 'Nyons', 27),
                                                           (89, 27000, 'Évreux', 28), -- Eure
                                                           (90, 27700, 'Les Andelys', 28),
                                                           (91, 27300, 'Bernay', 28),
                                                           (92, 28000, 'Chartres', 29), -- Eure-et-Loir
                                                           (93, 28200, 'Châteaudun', 29),
                                                           (94, 28100, 'Dreux', 29),
                                                           (95, 28400, 'Nogent-le-Rotrou', 29),
                                                           (96, 29000, 'Quimper', 30), -- Finistère
                                                           (97, 29200, 'Brest', 30),
                                                           (98, 29150, 'Châteaulin', 30),
                                                           (99, 29600, 'Morlaix', 30),
                                                           (100, 30000, 'Nîmes', 31), -- Gard
                                                           (101, 30100, 'Alès', 31),
                                                           (102, 30120, 'Le Vigan', 31),
                                                           (103, 31000, 'Toulouse', 32), -- Haute-Garonne
                                                           (104, 31600, 'Muret', 32),
                                                           (105, 31800, 'Saint-Gaudens', 32),
                                                           (106, 32000, 'Auch', 33), -- Gers
                                                           (107, 32100, 'Condom', 33),
                                                           (108, 32300, 'Mirande', 33),
                                                           (109, 33000, 'Bordeaux', 34), -- Gironde
                                                           (110, 33120, 'Arcachon', 34),
                                                           (111, 33390, 'Blaye', 34),
                                                           (112, 33210, 'Langon', 34),
                                                           (113, 33340, 'Lesparre-Médoc', 34),
                                                           (114, 33500, 'Libourne', 34),
                                                           (115, 34000, 'Montpellier', 35), -- Hérault
                                                           (116, 34500, 'Béziers', 35),
                                                           (117, 34700, 'Lodève', 35),
                                                           (118, 35000, 'Rennes', 36), -- Ille-et-Vilaine
                                                           (119, 35300, 'Fougères', 36),
                                                           (120, 35400, 'Saint-Malo', 36),
                                                           (121, 35500, 'Vitré', 36),
                                                           (122, 36000, 'Châteauroux', 37), -- Indre
                                                           (123, 36100, 'Issoudun', 37),
                                                           (124, 36200, 'Le Blanc', 37),
                                                           (125, 37000, 'Tours', 38), -- Indre-et-Loire
                                                           (126, 37300, 'Chinon', 38),
                                                           (127, 37400, 'Loches', 38),
                                                           (128, 38000, 'Grenoble', 39), -- Isère
                                                           (129, 38200, 'Vienne', 39),
                                                           (130, 38100, 'La Tour-du-Pin', 39),
                                                           (131, 39000, 'Lons-le-Saunier', 40), -- Jura
                                                           (132, 39200, 'Saint-Claude', 40),
                                                           (133, 39100, 'Dole', 40),
                                                           (134, 40000, 'Mont-de-Marsan', 41), -- Landes
                                                           (135, 40100, 'Dax', 41),
                                                           (136, 40200, 'Mont-de-Marsan', 41),
                                                           (137, 41000, 'Blois', 42), -- Loir-et-Cher
                                                           (138, 41100, 'Vendôme', 42),
                                                           (139, 41200, 'Romorantin-Lanthenay', 42),
                                                           (140, 42000, 'Saint-Étienne', 43), -- Loire
                                                           (141, 42300, 'Roanne', 43),
                                                           (142, 42100, 'Montbrison', 43),
                                                           (143, 43000, 'Le Puy-en-Velay', 44), -- Haute-Loire
                                                           (144, 43200, 'Yssingeaux', 44),
                                                           (145, 43100, 'Brioude', 44),
                                                           (146, 44000, 'Nantes', 45), -- Loire-Atlantique
                                                           (147, 44200, 'Saint-Nazaire', 45),
                                                           (148, 44300, 'Châteaubriant', 45),
                                                           (149, 44400, 'Ancenis', 45),
                                                           (150, 45000, 'Orléans', 46), -- Loiret
                                                           (151, 45200, 'Montargis', 46),
                                                           (152, 45300, 'Pithiviers', 46),
                                                           (153, 46000, 'Cahors', 47), -- Lot
                                                           (154, 46100, 'Figeac', 47),
                                                           (155, 46200, 'Gourdon', 47),
                                                           (156, 47000, 'Agen', 48), -- Lot-et-Garonne
                                                           (157, 47200, 'Marmande', 48),
                                                           (158, 47100, 'Villeneuve-sur-Lot', 48),
                                                           (159, 47300, 'Nérac', 48),
                                                           (160, 48000, 'Mende', 49), -- Lozère
                                                           (161, 48400, 'Florac', 49),
                                                           (162, 49000, 'Angers', 50), -- Maine-et-Loire
                                                           (163, 49300, 'Cholet', 50),
                                                           (164, 49400, 'Saumur', 50),
                                                           (165, 53000, 'Laval', 51), -- Mayenne
                                                           (166, 53200, 'Château-Gontier', 51),
                                                           (167, 53100, 'Mayenne', 51),
                                                           (168, 51000, 'Châlons-en-Champagne', 52), -- Marne
                                                           (169, 51200, 'Épernay', 52),
                                                           (170, 51100, 'Reims', 52),
                                                           (171, 51800, 'Sainte-Menehould', 52),
                                                           (172, 51300, 'Vitry-le-François', 52),
                                                           (173, 52000, 'Chaumont', 53), -- Haute-Marne
                                                           (174, 52200, 'Langres', 53),
                                                           (175, 52100, 'Saint-Dizier', 53),
                                                           (176, 54000, 'Nancy', 54), -- Meurthe-et-Moselle
                                                           (177, 54150, 'Briey', 54),
                                                           (178, 54300, 'Lunéville', 54),
                                                           (179, 54200, 'Toul', 54),
                                                           (180, 55000, 'Bar-le-Duc', 55), -- Meuse
                                                           (181, 55200, 'Commercy', 55),
                                                           (182, 55100, 'Verdun', 55),
                                                           (183, 57000, 'Metz', 56), -- Moselle
                                                           (184, 57220, 'Boulay-Moselle', 56),
                                                           (185, 57130, 'Château-Salins', 56),
                                                           (186, 57600, 'Forbach', 56),
                                                           (187, 57400, 'Sarrebourg', 56),
                                                           (188, 57100, 'Thionville', 56),
                                                           (189, 56000, 'Vannes', 57), -- Morbihan
                                                           (190, 56100, 'Lorient', 57),
                                                           (191, 56300, 'Pontivy', 57),
                                                           (192, 35600, 'Redon', 57),
                                                           (193, 78000, 'Versailles', 78), -- Yvelines
                                                           (194, 78200, 'Mantes-la-Jolie', 78),
                                                           (195, 78120, 'Rambouillet', 78),
                                                           (196, 79000, 'Niort', 79), -- Deux-Sèvres
                                                           (197, 79300, 'Bressuire', 79),
                                                           (198, 79200, 'Parthenay', 79),
                                                           (199, 80000, 'Amiens', 80), -- Somme
                                                           (200, 80100, 'Abbeville', 80),
                                                           (201, 80200, 'Péronne', 80),
                                                           (202, 81000, 'Albi', 81), -- Tarn
                                                           (203, 81100, 'Castres', 81),
                                                           (204, 82000, 'Montauban', 82), -- Tarn-et-Garonne
                                                           (205, 82100, 'Castelsarrasin', 82),
                                                           (206, 83000, 'Toulon', 83), -- Var
                                                           (207, 83170, 'Brignoles', 83),
                                                           (208, 83300, 'Draguignan', 83),
                                                           (209, 84000, 'Avignon', 84), -- Vaucluse
                                                           (210, 84400, 'Apt', 84),
                                                           (211, 84200, 'Carpentras', 84),
                                                           (212, 85000, 'La Roche-sur-Yon', 85), -- Vendée
                                                           (213, 85200, 'Fontenay-le-Comte', 85),
                                                           (214, 85100, 'Les Sables-d''Olonne', 85),
                                                           (215, 86000, 'Poitiers', 86), -- Vienne
                                                           (216, 86100, 'Châtellerault', 86),
                                                           (217, 86500, 'Montmorillon', 86),
                                                           (218, 87000, 'Limoges', 87), -- Haute-Vienne
                                                           (219, 87300, 'Bellac', 87),
                                                           (220, 87600, 'Rochechouart', 87),
                                                           (221, 88000, 'Épinal', 88), -- Vosges
                                                           (222, 88300, 'Neufchâteau', 88),
                                                           (223, 88100, 'Saint-Dié-des-Vosges', 88),
                                                           (224, 89000, 'Auxerre', 89), -- Yonne
                                                           (225, 89200, 'Avallon', 89),
                                                           (226, 89100, 'Sens', 89),
                                                           (227, 92000, 'Nanterre', 92), -- Hauts-de-Seine
                                                           (228, 92160, 'Antony', 92),
                                                           (229, 92100, 'Boulogne-Billancourt', 92),
                                                           (230, 93000, 'Bobigny', 93), -- Seine-Saint-Denis
                                                           (231, 93340, 'Le Raincy', 93),
                                                           (232, 94000, 'Créteil', 94), -- Val-de-Marne
                                                           (233, 94240, 'L''Haÿ-les-Roses', 94),
(234, 95000, 'Pontoise', 95), -- Val-d\'Oise
(235, 95100, 'Argenteuil', 95),
                                                            (236, 95200, 'Sarcelles', 95);
