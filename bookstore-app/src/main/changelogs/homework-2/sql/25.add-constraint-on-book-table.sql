ALTER TABLE books
ADD CONSTRAINT price_positive CHECK (price > 0);