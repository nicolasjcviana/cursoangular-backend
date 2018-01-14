package com.algaworks.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.api.service.exception.PessoaInexistenteOuInativaException;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messages;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagem = messages.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String detalhe = ex.getCause() != null ? ex.getCause().getMessage() : ex.toString();
		return handleExceptionInternal(ex, Arrays.asList(new Erro(mensagem, detalhe)), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, criarErros(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagem = messages.getMessage("recurso.naoencontrado", null, LocaleContextHolder.getLocale());
		String detalhe = ex.getMessage();
		return handleExceptionInternal(ex, Arrays.asList(new Erro(mensagem, detalhe)), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String mensagem = messages.getMessage("recurso.operacaonaopermitida", null, LocaleContextHolder.getLocale());
		String detalhe = ex.getMessage();
		return handleExceptionInternal(ex, Arrays.asList(new Erro(mensagem, detalhe)), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criarErros(BindingResult result) {
		List<Erro> listaErros = new ArrayList<>();

		for (FieldError erro : result.getFieldErrors()) {
			String mensagem = messages.getMessage(erro, LocaleContextHolder.getLocale());
			listaErros.add(new Erro(mensagem, erro.toString()));
		}
		return listaErros;
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex, WebRequest request) {
		String mensagem = messages.getMessage("pessoa.pessoainexistenteouinativaexception", null, LocaleContextHolder.getLocale());
		String detalhe = ex.toString();
		return handleExceptionInternal(ex, Arrays.asList(new Erro(mensagem, detalhe)), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	

	private static class Erro {

		private String mensagem;
		private String detalhes;

		public Erro(String mensagem, String detalhes) {
			this.mensagem = mensagem;
			this.detalhes = detalhes;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getDetalhes() {
			return detalhes;
		}

		public void setDetalhes(String detalhes) {
			this.detalhes = detalhes;
		}

	}

}
