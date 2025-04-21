package br.com.fiap.coletas.advice;

import br.com.fiap.coletas.exceptions.CooperativaException;

import br.com.fiap.coletas.exceptions.EmpresaException;
import br.com.fiap.coletas.exceptions.SolicitacaoException;
import br.com.fiap.coletas.repository.EmpresaRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {

        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for(FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmpresaException.class)
    public Map<String, String> handleEmpresaNotFound(EmpresaException ex) {
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("Erro", ex.getMessage());
        return mapaErro;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SolicitacaoException.class)
    public Map<String, String> handleSolicitacaoNotFound(SolicitacaoException ex) {
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("Erro", ex.getMessage());
        return mapaErro;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        Throwable cause = ex.getCause();
        String message = cause.getMessage();
        if (cause instanceof ConstraintViolationException) {
            errorMap.put("Erro", "Um registro com esse email já existe no Banco de Dados.");
        } else {
            if (message.contains("FK_COOPERATIVA") && message.contains("ORA-02292")) {
                errorMap.put("Erro", "Não é possível excluir a COOPERATIVA, pois existe uma Solicitação de Coleta cadastrada em aberto no Banco de Dados");
            } else if (message.contains("FK_EMPRESA") && message.contains("ORA-02292")) {
                errorMap.put("Erro", "Não é possível excluir a EMPRESA, pois existe uma Solicitação de Coleta cadastrada em aberto no Banco de Dados");
            } else if (message.contains("FK_COOPERATIVA") && message.contains("ORA-02291")) {
                errorMap.put("Erro", "Não existe COOPERATIVA cadastrada com esse ID");
            } else if (message.contains("FK_EMPRESA") && message.contains("ORA-02291")) {
                errorMap.put("Erro", "Não existe EMPRESA cadastrada com esse ID");
            }
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CooperativaException.class)
    public Map<String, String> handleCooperativaNotFound () {
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("Erro", "Não existe cooperativa cadastrada com esse ID!");
        return mapaErro;
    }
}