package kitchenpos;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.config.profile.Profile;
import kitchenpos.config.profile.TestProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.Map;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Conditional(TestProfile.class)
@ActiveProfiles(Profile.TEST)
@Sql("classpath:/db/migration/V1__Initialize_project_tables.sql")
public class AcceptanceTest {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void after() throws SQLException {
        databaseCleanup.truncate();
    }

    protected ExtractableResponse<Response> get(final String url) {
        return RestAssured.given().log().all()
                .when().get(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> get(final String url, final Object pathVariable) {
        return RestAssured.given().log().all()
                .when().get(url, pathVariable)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> get(final String url, final Map<String, ?> queryString) {
        return RestAssured.given().log().all()
                .queryParams(queryString)
                .when().get(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> get(final String url, final Object pathVariable, final Map<String, ?> queryString) {
        return RestAssured.given().log().all()
                .queryParams(queryString)
                .when().get(url, pathVariable)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> post(final String url, final Object body) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().post(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> post(final String url, final Object body, final Object pathVariable) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().post(url, pathVariable)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> put(final String url, final Object body) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().put(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> put(final String url, final String pathVariable) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> put(final String url, final Long pathVariable) {
        return this.put(url, String.valueOf(pathVariable));
    }

    protected ExtractableResponse<Response> put(final String url, final Object body, final Object pathVariable) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when().put(url, pathVariable)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> delete(final String url, final Object pathVariable) {
        return RestAssured.given().log().all()
                .when().delete(url, pathVariable)
                .then().log().all()
                .extract();
    }

}
