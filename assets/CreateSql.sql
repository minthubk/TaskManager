CREATE TABLE
"Tasks"
(
	"_id" INTEGER PRIMARY KEY
	,"Name" TEXT
	,"DueDate" INTEGER
	,"CategoryId" INTEGER
	,"CreateDate" INTEGER
	,"IsComplete" INTEGER
)

CREATE TABLE
"Categories"
(
	"_id" INTEGER PRIMARY KEY
	,"Name" TEXT
)
CREATE TABLE
"Alarms"
(
	"_id" INTEGER PRIMARY KEY
	,"TaskId" NUMERIC
	,"AlarmDate" NUMERIC
	,"AlarmTime" NUMERIC
	,"AlarmSound" NUMERIC
)
INSERT INTO Categories VALUES(NULL, NULL);
SELECT rowid, *  FROM Categories ORDER BY rowid; 
UPDATE Categories SET Name='Work' WHERE rowid=1;
INSERT INTO Categories VALUES(NULL, NULL);
SELECT rowid, *  FROM Categories ORDER BY rowid; 
UPDATE Categories SET Name='Kids' WHERE rowid=2;
INSERT INTO Categories VALUES(NULL, NULL);
SELECT rowid, *  FROM Categories ORDER BY rowid; 
UPDATE Categories SET Name='Personal' WHERE rowid=3;

INSERT INTO Tasks VALUES(NULL, NULL, NULL, NULL, NULL, NULL);
SELECT rowid, *  FROM Tasks ORDER BY rowid; 
UPDATE Tasks SET Name='Test Task' WHERE rowid=1;
UPDATE Tasks SET DueDate='1324747116' WHERE rowid=1;
UPDATE Tasks SET CategoryId='1' WHERE rowid=1;
UPDATE Tasks SET CreateDate='1324649915' WHERE rowid=1;
UPDATE Tasks SET IsComplete='0' WHERE rowid=1;