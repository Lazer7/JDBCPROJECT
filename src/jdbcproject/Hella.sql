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

INSERT INTO WRITINGGROUP VALUES('HellaHardWriters','DankMemes','1-2-2014','Philosophy');