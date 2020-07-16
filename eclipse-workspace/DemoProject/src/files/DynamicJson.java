package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json").
		body(payload.Addbook(isbn,aisle)).
		post ("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		

		String DeleteBookresponse = given().header("Content-Type","application/json").
		body(payload.Deletebook(id)).
		post ("Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(DeleteBookresponse);
		String deletemessage = js1.get("msg");
		System.out.println(deletemessage);
		
		
	}
	
		
		
	
	
@DataProvider(name = "BooksData")
public Object[][] getData()
	{
	return new Object[][] {{"fguh","79666"},{"mvk7j","970970"},{"mjgj77","896986"}};
	}

}
