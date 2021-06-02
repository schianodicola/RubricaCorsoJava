CREATE TABLE contacts (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  name varchar(25) DEFAULT NULL,
  surname varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE mail (
  id_contacts bigint unsigned,
  email varchar(25) DEFAULT NULL
  
);
ALTER TABLE mail
ADD CONSTRAINT mail_contact_fk1 FOREIGN KEY (id_contacts) REFERENCES contacts(id);


CREATE TABLE phone (
  id_contacts bigint unsigned,
  number varchar(25) DEFAULT NULL,
  
  CONSTRAINT phone_contact_fk1 FOREIGN KEY (id_contacts) REFERENCES contacts(id)
  
);



INSERT INTO contacts (name,surname) VALUES
('Giuseppe', 'Schiano'),
('Franci', 'Petri'),
('Annalisa', 'Rossi'),
('Andrea', 'Verdi');

INSERT INTO mail (id_contacts,email) VALUES
(1, 'aaa@mail.it'),
(2, 'bbb@mail.it'),
(3, 'ccc@mail.it'),
(4, 'ddd@mail.it'),
(4, 'provola@mail.it');

INSERT INTO phone (id_contacts,number) VALUES
(1, '33311111'),
(2, '33322222'),
(3, '33333333'),
(4, '33344444');

