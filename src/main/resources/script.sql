DROP table IF EXISTS eleves;
CREATE TABLE eleves (
     id INT NOT NULL AUTO_INCREMENT,
     prenom varchar(20) NOT NULL,
     nom varchar(20) NOT NULL, 
     datenaissance varchar(10) ,
   PRIMARY KEY (id)
   );
   commit ;