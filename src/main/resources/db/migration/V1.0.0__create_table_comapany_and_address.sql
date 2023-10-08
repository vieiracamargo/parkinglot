CREATE TABLE address (
                         id bigserial PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         city VARCHAR(255) NOT NULL,
                         state VARCHAR(255) NOT NULL,
                         country VARCHAR(255)NOT NULL,
                         zipCode VARCHAR(255) NOT NULL,
                         neighborhood VARCHAR(255) NOT NULL,
                         complement VARCHAR(255) NOT NULL
);


CREATE TABLE company (
                            id bigserial PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            cnpj VARCHAR(14) NOT NULL,
                            address_id bigserial REFERENCES ADDRESS(id),
                            phone VARCHAR(20) NOT NULL
);

