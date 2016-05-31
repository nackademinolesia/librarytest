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
public class User {
    private static final String BASE_URL="http://localhost:8080/librarytest-rest/";    
    private String userDisplayName;
    private String usersPassword;
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private String userEmail;
    private String usersRole;
    private Integer id;

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUsersPassword() {
        return usersPassword;
    }

    public void setUsersPassword(String usersPassword) {
        this.usersPassword = usersPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUsersRole() {
        return usersRole;
    }

   public void setUsersRole(String usersRole) {
        this.usersRole = usersRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Response getUser(int id) {
        String resourseName="users/"+id;
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL+resourseName);
        return getResponse;               

    }

    public String prepareOneNewUser (String displayName, String firstName, boolean passwordNeed, String roleTemplate, Integer usersIdTemplate, boolean needId) {
        String templateForDN = "";
        String templateForFN;
        String templateForP = "";
        String templateR = "";
        String templateForId;
        
        setUserFirstName((UUID.randomUUID().toString()).substring(0,8));
        setUserLastName((UUID.randomUUID().toString()).substring(0,8));
        setUserPhone("34567889");
        
        if (displayName.equals("-")) templateForDN = "";
        else {
            if (displayName.equals("")) setUserDisplayName((UUID.randomUUID().toString()).substring(0,8));
            else setUserDisplayName(displayName);
            templateForDN = "    \"displayName\": " + "\"" + userDisplayName + "\"" + ",\n";
        } 
  
        if (firstName.equals("")) setUserFirstName((UUID.randomUUID().toString()).substring(0,8));
        else setUserFirstName(firstName);
         templateForFN = "    \"firstName\": " + "\"" + userFirstName + "\"" + ",\n"; 
        
        if (passwordNeed == true) {
            setUsersPassword((UUID.randomUUID().toString()).substring(0,8));
               
            templateForP = "    \"password\": " + "\"" + usersPassword + "\""+",\n";
        } 

        if (roleTemplate.equals("")) templateR = "";
        else {
            if (roleTemplate.equals("LOANER")) templateR = "    \"role\": \"LOANER\"" + "\n";
            if (roleTemplate.equals("LIBRARIAN")) templateR = "    \"role\": \"LIBRARIAN\"" + "\n";
            //give an exception
        }
        setUserEmail(userDisplayName+"@exampel.com");

        if (usersIdTemplate == 0) setId((Integer)new Random().nextInt(5000)); 
        else setId(usersIdTemplate); 

                
        if (needId == true) templateForId = "    \"id\": "+ ""+getId()+",\n";
        else templateForId = "\n";
        String postBodyTemplate = ""+
                "\"user\": \n" +
                "  {\n" +
                templateForDN +
                "    \"email\": \"%s\"" + ",\n"+                
                templateForFN +                
                "    \"lastName\": \"%s\"" + ",\n"+ 
                templateForP +
                "    \"phone\": \"%s\"" + ",\n"+
                templateForId +
                templateR +
                "}";
        
        String postBody = String.format(postBodyTemplate, userEmail, userLastName, userPhone);
        return postBody; 
        
        
    }

    public String prepareTemplateForNewUser (String displayName, String firstName, boolean passwordNeed, String roleTemplate, Integer usersIdTemplate, boolean needId) {
        String temp = prepareOneNewUser(displayName, firstName, passwordNeed, roleTemplate, usersIdTemplate, needId);
        String postBodyTemplate = ""+"{\n" +
                temp+
                "\n" +
                "}";
        return postBodyTemplate;        
    }   
    
    public Response getAllUsers() {
        String resourseName="users";      
        Response getResponse = given().accept(ContentType.JSON).get(BASE_URL + resourseName);//.prettyPeek();
        return getResponse;   
    }
    
    public Response postUser(String userTemplate) {
        String resourseName="users";
        Response postResponse = given().contentType(ContentType.JSON).body(userTemplate).post(BASE_URL + resourseName);
        //System.out.println("Status code: " + postResponse.getStatusCode());
        return postResponse;            
    }
    
    public Response putUser(String bodyTemplate) {
        String resourseName="users";
        Response putResponse = given().contentType(ContentType.JSON).body(bodyTemplate).put(BASE_URL+resourseName);
        //System.out.println("Status code: " + putResponse.getStatusCode());
        return putResponse;        

    }
    
    public Response deleteUser(int id) {
        String deleteResourseName = "users/"+id;
        Response deleteResponse = delete(BASE_URL + deleteResourseName);
        return deleteResponse;
            
    } 
    
    
}
