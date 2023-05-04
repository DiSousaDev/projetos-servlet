package br.dev.diego.interceptors;

import br.dev.diego.config.MySqlConn;
import br.dev.diego.services.exceptions.ServerJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionInterceptor {

    @Inject
    @MySqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext ctx) throws Exception {

        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }

        try {
            log.info(">>>>> Iniciando transação método: [" + ctx.getMethod().getName() +
                    "] Classe: [" + ctx.getMethod().getDeclaringClass());
            Object result = ctx.proceed();
            conn.commit();
            log.info("xxxxx Realizando commit e finalixando transação método: [" + ctx.getMethod().getName() +
                    "] Classe: [" + ctx.getMethod().getDeclaringClass());
            return result;
        } catch (ServerJdbcException e) {
            conn.rollback();
            throw e;
        }
    }

}
