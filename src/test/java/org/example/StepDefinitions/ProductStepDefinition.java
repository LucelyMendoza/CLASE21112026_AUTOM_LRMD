package org.example.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseField;
import org.example.Tasks.GetProductById;
import org.example.Tasks.PostProduct;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

public class ProductStepDefinition {

    private static final String BASE_URL = "https://dummyjson.com";

    @Given("el {actor} establece el endpoint de productos")
    public void elActorEstableceElEndpointDeProductos(Actor actor) {
        actor.whoCan(CallAnApi.at(BASE_URL));
    }

    @When("el actor consulta el producto con id {string}")
    public void elActorConsultaElProductoConId(String id) {
        theActorInTheSpotlight().attemptsTo(GetProductById.withId(id));
    }

    @When("el actor crea un producto con el {string} {string} {string}")
    public void elActorCreaUnProductoConEl(String title, String price, String category) {
        theActorInTheSpotlight().attemptsTo(PostProduct.fromData(title, price, category));
    }

    @Then("el campo {string} de la respuesta debe ser {string}")
    public void elCampoDeLaRespuestaDebeSer(String field, String expected) {
        theActorInTheSpotlight().should(
                seeThat("el campo " + field, ResponseField.of(field), equalTo(expected)));
    }

    @Then("el campo {string} de la respuesta no debe estar vacio")
    public void elCampoDeLaRespuestaNoDebeEstarVacio(String field) {
        theActorInTheSpotlight().should(
                seeThat("el campo " + field + " no vacio", ResponseField.of(field), not(equalTo(""))));
    }

    @Then("el campo {string} de la respuesta debe contener {string}")
    public void elCampoDeLaRespuestaDebeContener(String field, String text) {
        theActorInTheSpotlight().should(
                seeThat("el campo " + field + " contiene texto", ResponseField.of(field), containsString(text)));
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
