
CREATE TABLE contacts (
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  name varchar(25) DEFAULT NULL,
  surname varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE mail (
  id_contacts bigint unsigned,
  email varchar(25) DEFAULT NULL primary key
  
);
ALTER TABLE mail
ADD CONSTRAINT mail_contact_fk1 FOREIGN KEY (id_contacts) REFERENCES contacts(id);


CREATE TABLE phone (
  id_contacts bigint unsigned,
  number varchar(25) DEFAULT NULL primary key
  
);
ALTER TABLE phone
ADD CONSTRAINT phone_contact_fk1 FOREIGN KEY (id_contacts) REFERENCES contacts(id);



INSERT INTO contacts (name,surname) VALUES
('Giuseppe', 'Schiano'),
('Franci', 'Petri'),
('Annalisa', 'Rossi'),
('Andrea', 'Verdi');

INSERT INTO mail (id_contacts,email) VALUES
(1, 'aaa@mail.it'),
(2, 'bbb@mail.it'),
(3, 'parmigiana@mail.it'),
(4, 'burrata@mail.it'),
(4, 'provola@mail.it');

INSERT INTO phone (id_contacts,number) VALUES
(1, '33311111'),
(2, '33322222'),
(3, '33333333'),
(4, '33344444');


SELECT c.id, c.name, c.surname, m.email, p.number FROM contacts c inner join mail m on c.id=m.id_contacts inner join phone p on c.id=p.id_contacts;
