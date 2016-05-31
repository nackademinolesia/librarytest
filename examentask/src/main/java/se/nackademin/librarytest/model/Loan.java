package se.nackademin.librarytest.model;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;

/**
 *
 * @author olesia
 */
public class Loan {
    private static final String BASE_URL="http://localhost:8080/librarytest-rest/";
    private Integer id;
    private String book;
    private String author;
    private String dateBorrowed;
    private String dateDue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }  
    
    public Response getAllLoans() {
        String resourseName="loans";      
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourseName);//.prettyPeek();
        return getResponse;   
    }

    public Response getLoan(int id) {
        String resourseName="loans/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        return getResponse;               
    
    }

    public Response getLoansOfUser(int id) {
        String resourseName="loans/ofuser/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        return getResponse;               
    
    }

    public Response getLoansOfBook(int id) {
        String resourseName="loans/ofbook/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        return getResponse;               
    
    }

    public Response getLoansOfUserOfBook(int idBook, int idUser) {
        ///loans/ofuser/{user_id}/ofbook/{book_id}
        String resourseName="loans/ofuser/"+idUser+"/ofbook/"+idBook;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        return getResponse;               
    
    }
    
    public Response postLoan(String loanTemplate) {
        String resourseName="loans";
        Response postResponse = given().contentType(ContentType.JSON).body(loanTemplate).post(BASE_URL + resourseName);
        return postResponse;            
    }

    public Response putLoan(String loanTemplate) {
        String resourseName="loans";
        Response putResponse = given().contentType(ContentType.JSON).body(loanTemplate).put(BASE_URL + resourseName);
        return putResponse;            
    }
    
    public Response deleteLoan(int id) {
        String deleteResourseName = "loans/"+id;
        Response deleteResponse = delete(BASE_URL + deleteResourseName);
        return deleteResponse;
            
    }    
    
    public String createLoansTemplate(String booksTemplate, String userTemplate, String dateBorrowedTemplate, String dateDueTemplate, Integer idTemplate) {
        //setDateBorrowed(dateBorrowedTemplate);
        //setDateDue(dateDueTemplate);
        String templateForId;
        String templateForDB;
        String templateForDD;
        if (idTemplate == 0) templateForId = "\n";
        else {
            if (idTemplate == 1) setId((Integer)new Random().nextInt(5000)); 
            else setId(idTemplate);
            templateForId = "\n" + "\n"+"    \"id\": %s,\n";
        }
        if(dateBorrowedTemplate.equals("")) templateForDB = "\n";
        else {
            setDateBorrowed(dateBorrowedTemplate);
            templateForDB = "    \"dateBorrowed\": \"%s\"" + ",\n";
        }
        
        if(dateDueTemplate.equals("")) templateForDD = "\n";
        else {
            setDateDue(dateDueTemplate);
            templateForDD = "    \"dateDue\": \"%s\"" + ",\n";
        }

        String postBodyTemplate = "{\n" +
        "  \"loan\": {\n" +
        booksTemplate + ",\n" +
        templateForId +
        templateForDB +
        templateForDD +
        userTemplate + "\n" +
        "  }\n" +
        "}";
        
        String postBody = String.format(postBodyTemplate, ""+getId(), getDateBorrowed(), getDateDue());   
        return postBody;
    }

}
