package se.nackademin.librarytest.model;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author olesia
 */
public class Author {
    private static final String BASE_URL="http://localhost:8080/librarytest-rest/";
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String country;
    private String biography;
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String Biography) {
        this.biography = Biography;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Response getAuthor(int id) {
        String resourceName = "authors/"+id;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);//.prettyPeek();
        return response;
    }   
    
    public Response getAllAuthors() {
        String resourseName="authors";      
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourseName);//.prettyPeek();
        return getResponse;   
    }

    public String prepareOneNewAuthor (String authorsFirstNameTemplate, String authorsLastNameTemplate, String biographyTemplate, String countryTemplate, Integer authorsIdTemplate, boolean needId) {
        String templateForId = "";
        String templateForFN;
        String templateForLN ;

        if (authorsFirstNameTemplate.equalsIgnoreCase("-")) templateForFN = "";
         else {
            if (authorsFirstNameTemplate.equalsIgnoreCase("")) firstName = (UUID.randomUUID().toString()).substring(0,8);
            else setFirstName(authorsFirstNameTemplate);   
            templateForFN = "    \"firstName\": \"%s\"" + ",\n";
        }

        if (authorsLastNameTemplate.equalsIgnoreCase("-")) templateForLN = "";
         else {
            if (authorsLastNameTemplate.equalsIgnoreCase("")) lastName = (UUID.randomUUID().toString()).substring(0,8);
            else setLastName(authorsLastNameTemplate);   
            templateForLN = "    \"lastName\": \"%s\"";// + ",\n";
        }
        
        if (authorsIdTemplate == 0) setId((Integer)new Random().nextInt(5000)); 
        else setId(authorsIdTemplate); 

        if (biographyTemplate.equalsIgnoreCase("")) biography = (UUID.randomUUID().toString()).substring(0,8);
        else setBiography(biographyTemplate);

        if (countryTemplate.equalsIgnoreCase("")) country = (UUID.randomUUID().toString()).substring(0,8);
        else setCountry(countryTemplate);
                
        if (needId == true) templateForId = ",\n" + "\n"+"    \"id\": %s\n";
        else templateForId = "\n";

        String postBodyTemplate = ""+
                "\"author\": \n" +
                "  {\n" +
                "    \"bio\": \"%s\"" + ",\n"+
                "    \"country\": \"%s\"" + ",\n"+                
                templateForFN +
                templateForLN +
                templateForId +
                "}";
        
        String postBody = String.format(postBodyTemplate, biography, country, firstName, lastName, ""+id);
        return postBody;          
    }
    
    public String prepareTemplateForNewAuthor (String authorsFirstNameTemplate, String authorsLastNameTemplate, String biographyTemplate, String countryTemplate, Integer authorsIdTemplate, boolean needId) {
        String temp = prepareOneNewAuthor(authorsFirstNameTemplate, authorsLastNameTemplate, biographyTemplate, countryTemplate, authorsIdTemplate, needId);
        String postBodyTemplate = ""+"{\n" +
                temp+
                "\n" +
                "}";
        String postBody = String.format(postBodyTemplate, firstName, ""+id);
        return postBody;        
    }
    
    public String prepareFullTemplateForAuthors (String authorsFirstNameTemplate, String authorsLastNameTemplate, String biographyTemplate, String countryTemplate, Integer authorsIdTemplate, boolean needId) {
        String temp = prepareTemplateForNewAuthor(authorsFirstNameTemplate, authorsLastNameTemplate, biographyTemplate, countryTemplate, authorsIdTemplate, needId);
        String postBodyTemplate = ""+"{\n" +
                "\"authors\":\n" +
                 temp+
                "\n" +
                "}";
        
        String postBody = String.format(postBodyTemplate, firstName, ""+id);
        return postBody;        
    
    }
    
    public Response postAuthor(String authorsFirstNameTemplate, String authorsLastNameTemplate, String biographyTemplate, String countryTemplate, Integer authorsIdTemplate, boolean needId) {
        String resourseName="authors";
        String postBody = prepareTemplateForNewAuthor(authorsFirstNameTemplate, authorsLastNameTemplate, biographyTemplate, countryTemplate, authorsIdTemplate, needId);
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(BASE_URL + resourseName);//.prettyPeek();
        //System.out.println("Status code: " + postResponse.getStatusCode());
        return postResponse;        

    }

    public Response putAuthor(String bodyTemplate) {
        String resourseName="authors";
        Response putResponse = given().contentType(ContentType.JSON).body(bodyTemplate).put(BASE_URL+resourseName);
        //System.out.println("Status code: " + putResponse.getStatusCode());
        return putResponse;        

    }

    public Response postAdditionalAuthorToBook(String authorsTemplate, Integer bookId) {
        Response postResponse = given().contentType(ContentType.JSON).body(authorsTemplate).post(BASE_URL + "books/" + bookId +"/authors");
        //System.out.println("Status code: " + postResponse.getStatusCode());
        return postResponse;        
     
    }
                 
    public Response deleteAuthor(int id) {
        String deleteResourseName = "authors/"+id;
        Response deleteResponse = delete(BASE_URL + deleteResourseName);
        return deleteResponse;
           
    }
    
    
}
