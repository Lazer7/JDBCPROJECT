CREATE TABLE WritingGroup
  (
  GroupName   VARCHAR(30)   NOT NULL,
  HeadWriter  VARCHAR(30)   NOT NULL,
  YearFormed  DATE          NOT NULL,
  Subject     VARCHAR(30) ,
  CONSTRAINT pk_WritingGroup PRIMARY KEY (GroupName));
  
CREATE TABLE Book
  (
  GroupName       VARCHAR(30)      NOT NULL,
  BookTitle          CHAR(30)      NOT NULL,
  PublisherName      CHAR(30)      NOT NULL,
  YearPublished      DATE                  , 
  NumberPages        INTEGER               ,
  CONSTRAINT pk_Book PRIMARY KEY (GroupName ,BookTitle),
  CONSTRAINT Book_fk FOREIGN KEY (GroupName)
  REFERENCES WritingGroup (GroupName));

CREATE TABLE Publishers
  (
  PublisherName      CHAR(30)  NOT NULL,
  PublisherAddress   CHAR(30)          ,
  PublisherPhone     CHAR(30)          ,
  PublisherEmail     CHAR(30)          ,
  CONSTRAINT pk_publishers PRIMARY KEY (PublisherName ));


CREATE TABLE authors
  (
  au_id    CHAR(3)     NOT NULL,
  au_fname VARCHAR(15) NOT NULL,
  au_lname VARCHAR(15) NOT NULL,
  phone    VARCHAR(12)         ,
  address  VARCHAR(20)         ,
  city     VARCHAR(15)         ,
  state    CHAR(2)             ,
  zip      CHAR(5)             ,
  CONSTRAINT pk_authors PRIMARY KEY (au_id)
  );

INSERT INTO authors VALUES('A01','Sarah','Buchman','718-496-7223',
  '75 West 205 St','Bronx','NY','10468');
INSERT INTO authors VALUES('A02','Wendy','Heydemark','303-986-7020',
  '2922 Baseline Rd','Boulder','CO','80303');
INSERT INTO authors VALUES('A03','Hallie','Hull','415-549-4278',
  '3800 Waldo Ave, #14F','San Francisco','CA','94123');
INSERT INTO authors VALUES('A04','Klee','Hull','415-549-4278',
  '3800 Waldo Ave, #14F','San Francisco','CA','94123');
INSERT INTO authors VALUES('A05','Christian','Kells','212-771-4680',
  '114 Horatio St','New York','NY','10014');
INSERT INTO authors VALUES('A06','','Kellsey','650-836-7128',
  '390 Serra Mall','Palo Alto','CA','94305');
INSERT INTO authors VALUES('A07','Paddy','O''Furniture','941-925-0752',
  '1442 Main St','Sarasota','FL','34236');

INSERT INTO WRITINGGROUP VALUES('HellaHardWriters','DankMemes','1/2/2014','Philosophy');

INSERT INTO Publishers VALUES('Core Dump Books','1234 North Ave','1234567890','Philosophy');

select * from publishers;