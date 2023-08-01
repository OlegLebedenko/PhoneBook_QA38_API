package helpers;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import static helpers.Helper.BASE_URI;

public interface TestBase {

    @BeforeMethod
    default void precondition(){

        RestAssured.baseURI = BASE_URI;
    }
}
