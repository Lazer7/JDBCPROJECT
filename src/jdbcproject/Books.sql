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


INSERT INTO WRITINGGROUP VALUES('HellaHardWriters'      ,'Harambe'          ,'1/2/2014'  ,'Non-Fiction');
INSERT INTO WRITINGGROUP VALUES('I Want To Write'       ,'Sarah-Chan'       ,'2/2/2010'  ,'Fan-Fiction');
INSERT INTO WRITINGGROUP VALUES('The Feel Train'        ,'Senpai Kun'       ,'3/4/2015'  ,'Fiction');
INSERT INTO WRITINGGROUP VALUES('I Tried So Hard'       ,'Alex Tol'         ,'1/2/2014'  ,'Philosophy');
INSERT INTO WRITINGGROUP VALUES('WEEBS'                 ,'Jimmy Chao'       ,'1/2/2014'  ,'Fiction');
INSERT INTO WRITINGGROUP VALUES('Classified Info'       ,'Donald Trump'     ,'10/16/2016','Fiction');


INSERT INTO Publishers VALUES('Core Dump Books'  ,'1234 North Ave' ,'1212121212','abc@yahoo.com');
INSERT INTO Publishers VALUES('My Life for Books','1234 East Ave'  ,'3434343434','def@yahoo.com');
INSERT INTO Publishers VALUES('I Want Out'       ,'1234 West Ave'  ,'5656565656','ghi@yahoo.com');
INSERT INTO Publishers VALUES('Shueisha'         ,'1234 South Ave' ,'7878787878','jkl@yahoo.com');

INSERT INTO Book VALUES('I Want To Write'       ,'Green Eggs and Ham'                 ,'My Life for Books','1/18/1997'  ,46);
INSERT INTO Book VALUES('HellaHardWriters'      ,'Sometimes the Rain'                 ,'I Want Out'       ,'12/1/2005'  ,282);
INSERT INTO Book VALUES('I Want To Write'       ,'Sometimes I cry'                    ,'My Life for Books','4/3/2012'   ,520);
INSERT INTO Book VALUES('HellaHardWriters'      ,'I Was Told There Was Cake'          ,'Core Dump Books'  ,'9/5/2000'   ,420);
INSERT INTO Book VALUES('The Feel Train'        ,'Do Androids Dream of Sheep'         ,'Core Dump Books'  ,'1/18/2007'  ,328);
INSERT INTO Book VALUES('I Tried So Hard'       ,'The Man Without Qualities'          ,'I Want Out'       ,'11/1/2004'  ,274);
INSERT INTO Book VALUES('WEEBS'                 ,'Nisekoi'                            ,'Shueisha'         ,'1/8/2011'   ,341);
INSERT INTO Book VALUES('WEEBS'                 ,'Clannad'                            ,'Shueisha'         ,'4/28/2004'  ,328);
INSERT INTO Book VALUES('Classified Info'       ,'Hillary Clinton Email'              ,'My Life for Books','10/16/2016' ,69);

