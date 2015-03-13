package br.ufes.scap.nucleo.aplicacao;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.METHOD}) // anotação para métodos
public @interface ProfessorRestricted {

}
