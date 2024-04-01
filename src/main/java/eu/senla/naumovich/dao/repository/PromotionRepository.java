package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Promotion;

import java.util.List;

public interface PromotionRepository {
    List<Promotion> getAll();

    Promotion getById(Promotion promotion);

    Promotion update(Promotion promotion);

    Promotion create(Promotion promotion);

    void delete(Promotion promotion);
}
