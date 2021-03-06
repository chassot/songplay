
CREATE TABLE album(
    id		INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    name	VARCHAR(50),
    author 	VARCHAR(50),
    year  	INTEGER
);

CREATE TABLE track (
    id        INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
    trackname VARCHAR(50),
    album_ID    INTEGER,  
    FOREIGN KEY (album_ID) REFERENCES album(id)
);
COMMIT;
