package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.entities.Promotion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {
    final List<Promotion> promotions = new ArrayList<>();
    @Override
    public List<Promotion> getAll() {
        return promotions;
    }

    @Override
    public Promotion getById(Promotion promotion) {
        for(Promotion p : promotions){
            if(p.getId() == promotion.getId()){
                return p;
            }
        }
        return null;
    }

    @Override
    public Promotion update(Promotion promotion) {
        for(Promotion p : promotions){
            if(promotion.getId() == p.getId()){
                p.setPromotionName(promotion.getPromotionName());
                return p;
            }
        }
        return null;
    }

    @Override
    public Promotion create(Promotion promotion) {
        promotions.add(promotion);
        return promotion;
    }

    @Override
    public void delete(Promotion promotion) {
        for (int i = 0; i < promotions.size(); i++) {
            if (promotion.getId() == promotions.get(i).getId()) {
                promotions.remove(i);
            }
        }
    }
}
