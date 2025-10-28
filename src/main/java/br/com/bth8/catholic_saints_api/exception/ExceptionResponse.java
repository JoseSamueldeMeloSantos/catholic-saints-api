package br.com.bth8.catholic_saints_api.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
