ALTER TABLE reviews
ADD CONSTRAINT rating_check CHECK (rating >= 0 AND rating <= 5);
