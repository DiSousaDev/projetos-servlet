package br.dev.diego.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logging(InvocationContext invocationContext) throws Exception {
        log.info("******* entrando antes de invocar metodo: [" + invocationContext.getMethod().getName() +
                "] da classe: " + invocationContext.getMethod().getDeclaringClass());
        Object proceed = invocationContext.proceed();
        log.info("******* saindo da invocação do método: [" + invocationContext.getMethod().getName() + "]");
        return proceed;
    }

}
