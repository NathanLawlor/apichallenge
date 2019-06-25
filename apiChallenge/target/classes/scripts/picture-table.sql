CREATE table challengeapi.PICTURES (
	ID INT AUTO_INCREMENT,
    FILE_NAME VARCHAR(255) NOT NULL,
    UPLOAD_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    COUNTRY VARCHAR(50),
    CITY VARCHAR(50),
    COORDINATES VARCHAR(50),
	PRIMARY KEY (ID)
)

-- DROP table appcms.pictures 