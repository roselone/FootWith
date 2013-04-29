use footwith;

ALTER TABLE footwith.user ADD `like` varchar(200) NULL;
ALTER TABLE footwith.user ADD `sex` bit DEFAULT 1 NOT NULL;
ALTER TABLE footwith.user ADD `joinTime` timestamp DEFAULT current_timestamp NOT NULL;
ALTER TABLE footwith.user ADD `marks` varchar(200) NULL;

ALTER TABLE footwith.plan ADD `timestamp` timestamp DEFAULT current_timestamp NOT NULL;

ALTER TABLE footwith.record ADD `timestamp` timestamp DEFAULT current_timestamp NOT NULL;

ALTER TABLE footwith.journal ADD `timestamp` timestamp DEFAULT current_timestamp NOT NULL;

ALTER TABLE footwith.picture ADD `timestamp` timestamp DEFAULT current_timestamp NOT NULL;

