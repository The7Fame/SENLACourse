package eu.senla.naumovich.aspect;

import eu.senla.naumovich.connection_holder.ConnectionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Aspect
@Component
public class TransactionAspect {
    private final ConnectionHolder connectionHolder;

    public TransactionAspect(ConnectionHolder connectionHolder){
        this.connectionHolder = connectionHolder;
    }

    @Around("@annotation(eu.senla.naumovich.annotation.Transaction)")
    public Object executeTransaction(ProceedingJoinPoint joinPoint){
        Connection conn = null;
        try {
            conn = connectionHolder.getTransactionConn();
            Object res = joinPoint.proceed();
            connectionHolder.commit(conn);
            return res;
        }catch (RuntimeException e){
            connectionHolder.rollback(conn);
        }catch (Exception e){
            connectionHolder.commit(conn);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            connectionHolder.releaseConn();
        }
        return null;
    }
}
