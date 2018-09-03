drop table document cascade;
drop table chemicalcompound_sentence cascade;
drop table sentence cascade;
drop table ocurrence cascade;

drop table hepatotoxicityterm_sentence cascade;
drop table cytochrome_sentence cascade;
drop table chemicalcompound_cytochrome_sentence cascade;
drop table chemicalcompound_hepatotoxicityterm_sentence cascade;


-- Table: chemical_compound

CREATE TABLE chemical_compound
(
   id SERIAL PRIMARY KEY,
   name varchar(255) NOT NULL,
   chemPlusId varchar(255),
   nameToStruct varchar(255),
   chebi varchar(255),
   casRegistryNumber varchar(255),
   pubChemCompundId varchar(255),
   pubChemSubstance varchar(255),
   inchi varchar,	  
   drugBankId varchar(255),	  
   humanMetabolomeId varchar(255),	  
   keggCompoundId varchar(255),	  
   meshSubstanceId varchar(255),	  
   nrDBIds varchar(255),
   smiles varchar (255)
);


-- Table: document

CREATE TABLE document
(
  id SERIAL PRIMARY KEY,
  type varchar(31) NOT NULL,
  fulltext varchar,
  score float NOT NULL,
  sourceId varchar(255) NOT NULL
);

-- Table: sentence

CREATE TABLE sentence
(
  id SERIAL PRIMARY KEY,
  text varchar,
  n_order integer,
  score float,
  document_id integer,
  sentenceId varchar(255) NOT NULL,
  CONSTRAINT fklt57de34kwq60icdvyapi1o8q FOREIGN KEY (document_id)
      REFERENCES document (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



-- Table: chemicalcompound_sentence

CREATE TABLE chemicalcompound_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score float NOT NULL,
  sentence_id integer,
  chemicalcompound_id integer,
  chemicalCompoundValueTypeFounded varchar(255) NOT NULL,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (chemicalcompound_id)
      REFERENCES chemical_compound (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION 
);

-- Table: ocurrence

CREATE TABLE ocurrence
(
  id SERIAL PRIMARY KEY,
  start int NOT NULL,
  n_end int NOT NULL,
  chemicalcompoundsentence_id integer,
  CONSTRAINT fk84umihqgfdgdssdf1dsdsdfb1g8mxo8qra3g FOREIGN KEY (chemicalcompoundsentence_id)
      REFERENCES chemicalcompound_sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION    
);

-- Table: hepatoxicityterm_sentence

CREATE TABLE hepatotoxicityterm_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score float NOT NULL,
  sentence_id integer,
  hepatotoxicityterm_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (hepatotoxicityterm_id)
      REFERENCES hepatotoxkeyword (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: cytochrome_sentence

CREATE TABLE cytochrome_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  cytochrome_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (cytochrome_id)
      REFERENCES cytochrome (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: marker_sentence

CREATE TABLE marker_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  marker_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (marker_id)
      REFERENCES marker (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: chemicalcompound_cytochrome_sentence

CREATE TABLE chemicalcompound_cytochrome_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  relationrule varchar(255) NOT NULL,
  chemicalcompound_id integer,
  cytochrome_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (cytochrome_id)
      REFERENCES cytochrome (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mx333o8qra3g FOREIGN KEY (chemicalcompound_id)
      REFERENCES chemical_compound (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: chemicalcompound_cytochrome_sentence

CREATE TABLE chemicalcompound_hepatotoxicityterm_sentence
(
  id SERIAL PRIMARY KEY,
  quantity integer NOT NULL,
  score real NOT NULL,
  sentence_id integer,
  relationrule varchar(255) NOT NULL,
  chemicalcompound_id integer,
  hepatotoxicityterm_id integer,
  CONSTRAINT fk84umihqwme1fb1g8mxo8qra3g FOREIGN KEY (sentence_id)
      REFERENCES sentence (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mxo8qra3g FOREIGN KEY (hepatotoxicityterm_id)
      REFERENCES hepatotoxkeyword (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk84umihqgfdgdf1fb1g8mx333o8qra3g FOREIGN KEY (chemicalcompound_id)
      REFERENCES compounddict (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);






