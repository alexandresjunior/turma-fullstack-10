package br.com.treina.recife.sgp.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.treina.recife.sgp.api.dto.ErroRespostaDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaDTO> tratarRecursoNaoEncontradoException(
        RecursoNaoEncontradoException ex, HttpServletRequest request) {
            ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(), 
                HttpStatus.NOT_FOUND.value(), 
                "Recurso Não Encontrado",
                ex.getMessage(),
                request.getRequestURI()
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroRespostaDTO> tratarRegraDeNegocioException(
        RegraDeNegocioException ex, HttpServletRequest request) {
            ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), 
                "Requisição Inválida", 
                ex.getMessage(), 
                request.getRequestURI()
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDTO> tratarErroGenerico(
        Exception ex, HttpServletRequest request) {
            ErroRespostaDTO erro = new ErroRespostaDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado no sistema.",
                request.getRequestURI()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

}
