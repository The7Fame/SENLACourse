package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.entities.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    final List<Payment> payments = new ArrayList<>();
    @Override
    public List<Payment> getAll() {
        return payments;
    }

    @Override
    public Payment getById(Payment payment) {
        for(Payment p : payments){
            if(p.getId() == payment.getId()){
                return p;
            }
        }
        return null;
    }

    @Override
    public Payment update(Payment payment) {
        for(Payment p : payments){
            if(payment.getId() == p.getId()){
                p.setStatus(payment.getStatus());
                return p;
            }
        }
        return null;
    }

    @Override
    public Payment create(Payment payment) {
        payments.add(payment);
        return payment;
    }

    @Override
    public void delete(Payment payment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payment.getId() == payments.get(i).getId()) {
                payments.remove(i);
            }
        }
    }
}
