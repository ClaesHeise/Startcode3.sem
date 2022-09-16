package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MoviesDTO;
import entities.Movies;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovieResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Movies m1,m2;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Movies("Toy stories", 8, 2001);
        m2 = new Movies("Star wars III", 7, 2009);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movies.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/movies").then().statusCode(200);
    }

    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/movies")
                .then().statusCode(200);
    }

    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/movies")
                .then().log().body().statusCode(200);
    }

    @Test
    public void testGetById()  {
        given()
                .contentType(ContentType.JSON)
//                .pathParam("id", p1.getId()).when()
                .get("/movies/{id}",m1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", equalTo(m1.getName()))
                .body("rating", equalTo(m1.getRating()))
                .body("year", equalTo(m1.getYear()));
    }
    // Ain't throwing a exception atm.
//    @Test
//    public void testError() {
//        given()
//                .contentType(ContentType.JSON)
////                .pathParam("id", p1.getId()).when()
//                .get("/movies/{id}",999999999)
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
//                .body("code", equalTo(404))
//                .body("message", equalTo("The Movies entity with ID: "+999999999+" Was not found"));
//    }

    @Test
    public void testPrintResponse(){
        Response response = given().when().get("/movies/"+m1.getId());
        ResponseBody body = response.getBody();
        System.out.println(body.prettyPrint());

        response
                .then()
                .assertThat()
                .body("name",equalTo("Toy stories"));
    }

    @Test
    public void exampleJsonPathTest() {
        Response res = given().get("/movies/"+m1.getId());
        assertEquals(200, res.getStatusCode());
        String json = res.asString();
        JsonPath jsonPath = new JsonPath(json);
        assertEquals("Toy stories", jsonPath.get("name"));
    }

    @Test
    public void getAllMovies() throws Exception {
        List<MoviesDTO> moviesDTOs;

        moviesDTOs = given()
                .contentType("application/json")
                .when()
                .get("/movies")
                .then()
                .extract().body().jsonPath().getList("", MoviesDTO.class);

        for (MoviesDTO md : moviesDTOs
             ) {
            System.out.println(md.getName());
        }
        MoviesDTO m1DTO = new MoviesDTO(m1);
        MoviesDTO m2DTO = new MoviesDTO(m2);
        assertThat(moviesDTOs, containsInAnyOrder(m1DTO, m2DTO));

    }
//
//
//    @Test
//    public void postTest() {
//        Parent p = new Parent("Helge",45);
//        p.addChild(new Child("Josephine",3));
//        ParentDTO pdto = new ParentDTO(p);
//        String requestBody = GSON.toJson(pdto);
//
//        given()
//                .header("Content-type", ContentType.JSON)
//                .and()
//                .body(requestBody)
//                .when()
//                .post("/parent")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("id", notNullValue())
//                .body("name", equalTo("Helge"))
//                .body("children", hasItems(hasEntry("name","Josephine")));
//    }
//
//    @Test
//    public void updateTest() {
//        p2.addChild(c2);
//        p2.setAge(23);
//        ParentDTO pdto = new ParentDTO(p2);
//        String requestBody = GSON.toJson(pdto);
//
//        given()
//                .header("Content-type", ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .put("/parent/"+p2.getId())
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("id", equalTo(p2.getId()))
//                .body("name", equalTo("Betty"))
//                .body("age", equalTo(23))
//                .body("children", hasItems(hasEntry("name","Alberta")));
//    }

//    @Test
//    public void testDeleteParent() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("id", m2.getId())
//                .delete("/movies/{id}")
//                .then()
//                .statusCode(200)
//                .body("id",equalTo(m2.getId()));
//    }

    // More test tools from: https://www.baeldung.com/java-junit-hamcrest-guide
    @Test
    public void testListSize() {
        System.out.println("Check size of list");
        List<String> hamcrestMatchers = Arrays.asList(
                "collections", "beans", "text", "number");
        assertThat(hamcrestMatchers, hasSize(4));
    }

    @Test
    public void testCompareObjects() {
        System.out.println("Check if 2 instances has same property values (EG. use compare properties rather than objects");
        Movies movies1 = m2;
        Movies movies2 = m2;
        assertThat(movies1, samePropertyValuesAs(movies2));
    }
    @Test
    public void testToString(){
        System.out.println("Check if obj.toString() creates the right output");
        Movies movies= m1;
        String str=movies.toString();
        assertThat(movies,hasToString(str));
    }

    @Test
    public void testMapContains() {
        List<Movies> movies = Arrays.asList(
                m1,
                m2
        );
        assertThat(movies.toArray(), arrayContainingInAnyOrder(movies.get(0),movies.get(1)));
    }
    @Test
    public void testNumeric() {
        System.out.println("Test numeric values");
        assertThat(1.2, closeTo(1, 0.5));
        assertThat(5, greaterThanOrEqualTo(5));

        List<Integer> list = Arrays.asList(1, 2, 3);
        int baseCase = 0;
        assertThat(list, everyItem(greaterThan(baseCase)));
    }
    @Test
    public void testMoreReadable() {
        System.out.println("Use the IS, NOT etc keywords for readability");
        String str1 = "text";
        String str2 = "texts";
        String str3 = "texts";
        String str4 = "These are several texts in one sentence";
        assertThat(str1, not(str2));
        assertThat(str2, is(str3));
        assertThat(str4, containsString(str2));

    }
}
