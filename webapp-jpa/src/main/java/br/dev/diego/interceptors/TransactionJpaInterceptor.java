package br.dev.diego.interceptors;

import br.dev.diego.services.exceptions.ServerJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext ctx) throws Exception {

        try {
            log.info(">>>>> Iniciando transação método: [" + ctx.getMethod().getName() +
                    "] Classe: [" + ctx.getMethod().getDeclaringClass());
            em.getTransaction().begin();
            Object result = ctx.proceed();
            em.getTransaction().commit();
            log.info("xxxxx Realizando commit e finalixando transação método: [" + ctx.getMethod().getName() +
                    "] Classe: [" + ctx.getMethod().getDeclaringClass());
            return result;
        } catch (ServerJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
