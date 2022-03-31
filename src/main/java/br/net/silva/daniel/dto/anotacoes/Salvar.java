package br.net.silva.daniel.dto.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Salvar {
    int linha();
    int coluna();
    String titulo();
    int tituloPosicao();
}
