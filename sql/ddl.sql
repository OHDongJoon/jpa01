
-- item 테이블 생성
CREATE TABLE Item (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(255),
                      price INT,
                      stockQuantity INT
);

-- Member 테이블 생성
CREATE TABLE Member (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255),
                        city VARCHAR(255),
                        street VARCHAR(255),
                        zipcode VARCHAR(255)
);

-- Order 테이블 생성
CREATE TABLE Orders (
                        id BIGINT PRIMARY KEY,
                        member_id BIGINT,
                        order_date TIMESTAMP,
                        status VARCHAR(255)
);

-- OrderItem 테이블 생성
CREATE TABLE OrderItem (
                            id BIGINT PRIMARY KEY,
                            order_id BIGINT,
                            item_id BIGINT,
                            order_price INT,
                            count INT
);