drop database if exists spiritJava;
create database spiritJava;
use spiritJava;


#------------------------------------------------------------
# Table: Produit 
#------------------------------------------------------------

CREATE TABLE Produit(
        id_produit     Int (3)  Auto_increment  NOT NULL ,
        nom            Varchar (40) NOT NULL ,
        prix           Float NOT NULL ,
        tpsPreparation Varchar (10) NOT NULL,
		PRIMARY KEY (id_produit)
);

#------------------------------------------------------------
# Table: Commander
#------------------------------------------------------------

CREATE TABLE Commander
(
        id_produit      int (3)  NOT NULL,
         id_client     int (3)  NOT NULL,  
        PRIMARY KEY (id_produit,id_client),
        FOREIGN KEY (id_produit) REFERENCES Produit(id_produit),
        FOREIGN KEY (id_client) REFERENCES Client(id_client)
);

#------------------------------------------------------------
# Table: Preparer
#------------------------------------------------------------

CREATE TABLE Preparer(
        id_produit      int (3)  NOT NULL,
        id_cuisinier    int (3)  NOT NULL,  
        PRIMARY KEY (id_produit,id_cuisinier),
        FOREIGN KEY (id_produit) REFERENCES Produit(id_produit),
        FOREIGN KEY (id_cuisinier) REFERENCES Cuisinier(id_cuisinier)
);

#------------------------------------------------------------
# Table: Cuisinier
#------------------------------------------------------------

CREATE TABLE Cuisinier(
        id_cuisinier      Int (3) Auto_increment NOT NULL ,
        nom			     Varchar (20) NOT NULL ,
        prenom			 Varchar (20) NOT NULL ,
        adresse			 Varchar (200) NOT NULL ,
        tel			    Varchar (10) NOT NULL ,
        mail		    Varchar (60) NOT NULL,
		PRIMARY KEY (id_cuisinier)
);

#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        id_client       Int (3) Auto_increment  NOT NULL ,
        nom		      Varchar (30) NOT NULL ,
        prenom		   Varchar (30) NOT NULL ,
        adresse		  Varchar (200) NOT NULL ,
        tel		      Varchar (10) NOT NULL,
		nbCommandePassees 	INT NOT NULL,
		PRIMARY KEY (id_client)
);

#------------------------------------------------------------
# Table: USER
#------------------------------------------------------------

create table user ( 
	iduser int(3) not null auto_increment,
	email varchar(50), 
	mdp varchar(50),
	nom varchar(50), 
	prenom varchar(50), 
	droits enum("admin","user","autre"), 
	primary key (iduser)
);

#insertion
insert into cuisinier values (null,'Robert','Renaud','10 avenue du larynx','0150406071','r@gmail.com');
insert into cuisinier values (null,'Chnor','Alphonse','18 rue de la m�thamph�tamine','0118201061','a@gmail.com');
insert into cuisinier values (null,'Lahieu','Patrick','61 rue du vieux-port','0125639841','p@gmail.com');