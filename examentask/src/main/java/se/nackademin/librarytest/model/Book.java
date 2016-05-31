/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.model;
import static com.jayway.restassured.RestAssured.*;

//import static com.jayway.restassured.RestAssured.delete;
//import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

/**
 * @author testautomatisering
 */
public class Book {
    private static final String BASE_URL="http://localhost:8080/librarytest-rest/";
    
    private Integer id;
    private String title;
    private Object author;
    private String description;
    private String isbn;
    private String publicationDate;
    private Integer nbrPages;
    private Integer totalNbrCopies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDatePublished() {
        return publicationDate;
    }

    public void setDatePublished(String datePublished) {
        this.publicationDate = datePublished;
    }

    public Integer getNbrAvailable() {
        return totalNbrCopies;
    }

    public void setNbrAvailable(Integer nbrAvailable) {
        this.totalNbrCopies = nbrAvailable;
    }   
       
    public Integer getNbOfPage() {
        return nbrPages;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbrPages = nbOfPage;
    }

    public Response getBooksOfAuthor(Integer authorId) {
        ///books/byauthor/{author_id}
        String resourceName = "books/byauthor/"+authorId;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName);//.prettyPeek();
        return response;
    }

    public Response getBooksAuthors(Integer bookId) {
        ///books/{book_id}/authors
        String resourceName = "books/"+bookId;
        Response response = given().accept(ContentType.JSON).get(BASE_URL+resourceName+"/authors");//.prettyPeek();
        return response;
    }
    
    public Response postBook(String booksTemplate) {
        String resourseName="books";
        Response postResponse = given().contentType(ContentType.JSON).body(booksTemplate).post(BASE_URL + resourseName);
        //System.out.println("Status code: " + postResponse.getStatusCode());
        return postResponse;            
    }

    public Response putBook(String booksTemplate) {
        String resourseName="books";
        Response putResponse = given().contentType(ContentType.JSON).body(booksTemplate).put(BASE_URL + resourseName);
        //System.out.println("Status code: " + putResponse.getStatusCode());
        return putResponse;            
    }

    public Response getBook(int id) {
        String resourseName="books/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        //System.out.println("Status code: " + getResponse.getStatusCode());
        return getResponse;               
    
    }
    
    //public String createBooksTemplate(String booksDescriptionTemplate, String booksIsbnTemplate, Integer booksnbOfPageTemplate, String booksTitleTemplate, Integer booksIdTemplate, String authorTemplate) {
    public String prepareTemplateForNewBook(String booksDescriptionTemplate, String booksIsbnTemplate, Integer booksnbOfPageTemplate, String booksPublicationDate, String booksTitleTemplate, Integer booksTotalNbrCopies, Integer booksIdTemplate, String authorTemplate) {    
        String templateTitle;
        if (booksDescriptionTemplate.equalsIgnoreCase("")) description = (UUID.randomUUID().toString()).substring(0,8); 
        else setDescription(booksDescriptionTemplate);

        if (booksIsbnTemplate.equalsIgnoreCase("")) isbn = (UUID.randomUUID().toString()).substring(0,8);
        else setIsbn(booksIsbnTemplate);
        
        if (booksnbOfPageTemplate == 0) setNbOfPage((Integer)new Random().nextInt(5000)); 
        else setNbOfPage(booksnbOfPageTemplate); 
        
        if (booksPublicationDate.equalsIgnoreCase("")) publicationDate = (UUID.randomUUID().toString()).substring(0,8); 
        else setDatePublished(booksPublicationDate); 

        if (booksTitleTemplate.equalsIgnoreCase("-")) templateTitle = "";
         else {
            if (booksTitleTemplate.equalsIgnoreCase("")) title = (UUID.randomUUID().toString()).substring(0,8);
            else setTitle(booksTitleTemplate);   
            templateTitle = "    \"title\": \"%s\"" + ",\n";
        }
 
        
        if (booksTotalNbrCopies == 0) setNbrAvailable((Integer)new Random().nextInt(100));
        else {
            if (booksTotalNbrCopies == 9999) setNbrAvailable(0);
            else      
                setNbrAvailable(booksTotalNbrCopies);
        }

        if (booksIdTemplate == 0) setId((Integer)new Random().nextInt(5000)); 
        else setId(booksIdTemplate); 
        
        String postBodyTemplate = ""+
                "\"book\":\n" +
                "  {\n" +
                "    \"description\": \"%s\",\n" +
                "    \"isbn\": \"%s\",\n" +
                "    \"nbrPages\": %s,\n" +
                "    \"publicationDate\": \"%s\",\n" +
                templateTitle +
                "    \"totalNbrCopies\": %s,\n" +
                "    \"id\": %s\n" +
                "    "+authorTemplate+
                "  }\n";
        
        //String postBody = String.format(postBodyTemplate, description, isbn, ""+getNbOfPage(), title, ""+getId());
        String postBody = String.format(postBodyTemplate, description, isbn, ""+getNbOfPage(), publicationDate, title, totalNbrCopies, ""+getId());
        return postBody;   
    }
    
    public String createBooksTemplate(String booksDescriptionTemplate, String booksIsbnTemplate, Integer booksnbOfPageTemplate, String booksPublicationDate, String booksTitleTemplate, Integer booksTotalNbrCopies, Integer booksIdTemplate, String authorTemplate) {
        String temp = prepareTemplateForNewBook(booksDescriptionTemplate, booksIsbnTemplate, booksnbOfPageTemplate, booksPublicationDate, booksTitleTemplate, booksTotalNbrCopies, booksIdTemplate, authorTemplate);
        String postBody = ""+"{\n" +
                temp+
                "\n" +
                "}";
        //String postBody = String.format(postBodyTemplate, firstName, ""+id);
        return postBody;        
   }

    public Response getAllBooks() {
        String resourseName="books";      
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourseName);//.prettyPeek();
        return getResponse;   
    }
    
    public Response deleteBook(int id) {
        String deleteResourseName = "books/"+id;
        Response deleteResponse = delete(BASE_URL + deleteResourseName);
        return deleteResponse;
            
    } 
    
    public Response putNewAuthors(String authorsTemplate, Integer booksId) {
        //books/{book_id}/authors
        Response putResponse = given().contentType(ContentType.JSON).body(authorsTemplate).put(BASE_URL+"books/"+booksId+"/authors"); 
        //System.out.println("Status code: " + putResponse.getStatusCode());
        return putResponse;        

    }
    
}
