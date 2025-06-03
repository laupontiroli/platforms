CREATE TABLE orders (
    id_order VARCHAR(50) NOT NULL,
    str_id_user VARCHAR(50) NOT NULL,
    dt_order TIMESTAMP NOT NULL,
    vl_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id_order)
);


CREATE TABLE items (
    id_item VARCHAR(50) NOT NULL,
    str_id_order VARCHAR(50) NOT NULL,
    str_id_product VARCHAR(50) NOT NULL,
    qt_item INT NOT NULL,
    vl_item DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (str_id_order) REFERENCES orders (id_order),
    CONSTRAINT pk_item PRIMARY KEY (id_item)
);