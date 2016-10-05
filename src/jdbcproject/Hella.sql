CREATE TABLE WritingGroup
  (
  GroupName   VARCHAR(30)   NOT NULL,
  HeadWriter  VARCHAR(30)   NOT NULL,
  YearFormed  DATE          NOT NULL,
  Subject     VARCHAR(30) ,
  CONSTRAINT pk_WritingGroup PRIMARY KEY (GroupName));
  


CREATE TABLE Publishers
  (
  PublisherName      CHAR(30)  NOT NULL,
  PublisherAddress   CHAR(30)          ,
  PublisherPhone     CHAR(30)          ,
  PublisherEmail     CHAR(30)          ,
  CONSTRAINT pk_publishers PRIMARY KEY (PublisherName ));

CREATE TABLE Book
  (
  GroupName       VARCHAR(30)      NOT NULL,
  BookTitle          CHAR(30)      NOT NULL,
  PublisherName      CHAR(30)      NOT NULL,
  YearPublished      DATE                  , 
  NumberPages        INTEGER               ,
  CONSTRAINT pk_Book PRIMARY KEY (GroupName ,BookTitle,PublisherName),
  CONSTRAINT Book_fk FOREIGN KEY (GroupName) 
  REFERENCES WritingGroup (GroupName),
  CONSTRAINT BookPublisher_fk FOREIGN KEY (PublisherName)
  REFERENCES Publishers (PublisherName));


INSERT INTO WRITINGGROUP VALUES('HellaHardWriters','DankMemes','1/2/2014','Philosophy');
INSERT INTO WRITINGGROUP VALUES('I Want To Write','Sensei','2/2/2010','Fan-Fiction');
INSERT INTO WRITINGGROUP VALUES('The Feel Train','Senpai','3/4/2015','Fiction');
INSERT INTO WRITINGGROUP VALUES('I Tried So Hard','CrashCourse','1/2/2014','TryHards');

INSERT INTO Publishers VALUES('Core Dump Books','1234 North Ave','1234567890','abc@yahoo.com');
INSERT INTO Publishers VALUES('My Life for Books','1234 East Ave','123456789','def@yahoo.com');
INSERT INTO Publishers VALUES('I Want Out','1234 West Ave','12345678','ghi@yahoo.com');
INSERT INTO Publishers VALUES('WEEBS','1234 South Ave','1234567','jkl@yahoo.com');

INSERT INTO Book VALUES('HellaHardWriters','Sometimes the Rain','WEEBS','1/1/2000',420);
INSERT INTO Book VALUES('HellaHardWriters','Sometimes I cry','WEEBS','1/1/2000',420);
INSERT INTO Book VALUES('HellaHardWriters','Engene is a Beach','WEEBS','1/1/2000',420);
INSERT INTO Book VALUES('HellaHardWriters','RIP','WEEBS','1/1/2000',420);
INSERT INTO Book VALUES('HellaHardWriters','WHAT','WEEBS','1/1/2000',420);