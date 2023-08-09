
CREATE TABLE if not exists BENEFICIARIES (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(250) NOT NULL,
                                surname VARCHAR(250) NOT NULL

);

CREATE TABLE if not exists ACCOUNTS (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          account_number INT NOT NULL,
                          pin_code VARCHAR(4),
                          balance DECIMAL,
                          beneficiary_id INT,
                          foreign key (beneficiary_id) references BENEFICIARIES (id)


);

INSERT INTO BENEFICIARIES (name, surname) VALUES
                                              ('Alex', 'Markis'),
                                              ('Sergej', 'Formin'),
                                              ('Nail', 'Sadikov');

INSERT INTO ACCOUNTS (account_number, pin_code, balance, beneficiary_id)
VALUES (1234567891, '1234', 15000, 2),
       (1234567800, '1011', 2701, 3),
       (1234567892, '2012', 2702, 3),
       (1234567893, '3013', 2703, 3),
       (1234567894, '4014', 2704, 3),
       (1234567895, '5015', 2705, 3),
       (0987654321,'0000', 40000,1);