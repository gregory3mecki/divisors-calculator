package com.gregory3mecki.core.util.rest;

import com.gregory3mecki.core.util.exception.StatusCodeException;
import com.gregory3mecki.rest.api.entity.ErrorDTO;
import com.gregory3mecki.rest.api.entity.ResponseDTO;
import io.quarkus.arc.Priority;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

@RestExceptionHandler
@Priority(10)
@Interceptor
public class RestExceptionHandlerInterceptor {

    @AroundInvoke
    Object process(final InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (StatusCodeException exception) {
            final ResponseDTO response = new ResponseDTO();
            response.setError(buildRestError(exception));
            return Response
                    .status(exception.getStatusCode())
                    .entity(response)
                    .build();
        } catch (Throwable exception) {
            final ResponseDTO response = new ResponseDTO();
            response.setError(buildError(exception));
            return Response
                    .status(500)
                    .entity(response)
                    .build();
        }
    }

    private ErrorDTO buildRestError(StatusCodeException exception) {
        final ErrorDTO error = new ErrorDTO();
        error.setMessage(exception.getExceptionMessage());
        error.setDetails(exception.getDetails());
        return error;
    }

    private ErrorDTO buildError(Throwable exception) {
        final ErrorDTO error = new ErrorDTO();
        error.setMessage("Server problem.");
        error.setDetails(exception.getMessage());
        return error;
    }

}
