package me.aikin.mockbean;


import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

class AboutControllerTest extends ApiBaseTestSetup {
    @Test
    void should_can_get_about() {
        given().
        when().
                get("/api/about").
        then().
                body(equalTo("just try"));

    }
}
