package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostProduct implements Task {
    private final String title, price, category;

    public PostProduct(String title, String price, String category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public static Performable fromData(String title, String price, String category) {
        return instrumented(PostProduct.class, title, price, category);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/products/add")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept", "application/json")
                                .body("""
                                        {
                                            "title": "%s",
                                            "price": %s,
                                            "category": "%s"
                                        }""".formatted(title, price, category))
                                .log().all())
        );
        SerenityRest.lastResponse().body().prettyPrint();
    }
}
