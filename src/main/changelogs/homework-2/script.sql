INSERT INTO privileges (privilege_name)
VALUES
('privilege1'),
('privilege2');

INSERT INTO roles (role_name)
VALUES
('role1'),
('role2');


INSERT INTO users (name, surname, email, role_id)
VALUES
('name1', 'surname1', '1@example.com', 1),
('name2', 'surname2', '2@example.com', 2);

INSERT INTO authors (name, surname)
VALUES
('name1', 'surname1'),
('name2', 'surname2');

INSERT INTO promotions (promotion_name, percent)
VALUES
('name1', 10.00),
('name2', 10.00);

INSERT INTO genres (genre_name)
VALUES
('name1'),
('name2');

INSERT INTO publishers (publisher_name)
VALUES
('name1'),
('name2');

INSERT INTO addresses (city, street, index, publisher_id)
VALUES
('city1', 'street1', 000000, 1),
('city2', 'street2', 000000, 2);

INSERT INTO roles_privileges (role_id, privilege_id)
VALUES
(1, 1),
(2, 2);

INSERT INTO books (title, price, isbn, genre_id, publisher_id)
VALUES
('title1', 10.00, 1231231231231,1,1),
('text2', 10.00,3213213213213,2,2);

INSERT INTO reviews (text, rating, user_id, book_id)
VALUES
('text1', 3,1,1),
('text2', 3,2,2);

INSERT INTO authors_books (author_id, book_id)
VALUES
(1,1),
(2,2);

INSERT INTO books_promotions (book_id, promotion_id)
VALUES
(1,1),
(2,2);

INSERT INTO carts (user_id, book_id)
VALUES
(1,1),
(2,2);

INSERT INTO orders (order_date, total_price, user_id)
VALUES
(NOW(), 10.00, 1),
(NOW(), 10.00, 2);

INSERT INTO payments (status, payment_date, total_price, user_id, order_id)
VALUES
(true, NOW(), 10.00, 1, 1),
(true, NOW(), 10.00, 1, 2);
