SELECT * FROM payments p
JOIN orders o ON p.order_id = o.id;
