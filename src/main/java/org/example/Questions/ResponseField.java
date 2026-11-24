package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Devuelve el valor (como String) de un campo del JSON de la ultima respuesta. */
public class ResponseField implements Question<String> {
    private final String field;

    private ResponseField(String field) {
        this.field = field;
    }

    public static Question<String> of(String field) {
        return new ResponseField(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        Object value = SerenityRest.lastResponse().jsonPath().get(field);
        return value == null ? "" : value.toString();
    }
}
