create table transact (
    event_time date,
    event_type varchar(20),
    product_id varchar(20),
    category_id varchar(30),
    category_code varchar(100),
    brand varchar(100),
    price float,
    user_id varchar(100),
    user_session varchar(255)
)