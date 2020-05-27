package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1.utils.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Iterator;

public class CustomValidationException extends Exception {
    private final MethodParameter parameter;
    private final BindingResult bindingResult;

    public CustomValidationException(MethodParameter parameter, BindingResult bindingResult) {
        this.parameter = parameter;
        this.bindingResult = bindingResult;
    }

    public CustomValidationException(MethodArgumentNotValidException e){
        this.parameter = e.getParameter();
        this.bindingResult = e.getBindingResult();
    }

    public MethodParameter getParameter() {
        return this.parameter;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }

    public String getMessage() {
        StringBuilder sb = (new StringBuilder("Validation failed"));

        sb.append(": ");
        Iterator var2 = this.bindingResult.getAllErrors().iterator();

        while(var2.hasNext()) {
            ObjectError error = (ObjectError)var2.next();
            try {
                String code = error.getCodes()[1];
                sb.append("[").append(code.substring(code.indexOf(".")+1)).append(" ").append(error.getDefaultMessage()).append("], ");
            }
            catch(NullPointerException e){
                sb.append("[").append(error).append("] ");
            }

        }

        return sb.toString();
    }
}
