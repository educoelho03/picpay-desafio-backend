package tech.ecoelho.picpay.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.ecoelho.picpay.backend.exceptions.PicPayException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e){
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var fieldError = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate");
        pb.setProperty("invalid-params", fieldError);

        return pb;
    }

    private record InvalidParam(String fieldName, String reason){}
}
