package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.sleep;
import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
//import static com.jayway.restassured.RestAssured.*;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.Loan;
import se.nackademin.librarytest.model.User;


/**
 *
 * @author olesia
 */
public class RestTest {
    private int sleepTime = 1000;

    @Test /*test #1*/
    public void testPostAuthor() {
        //Create a new author.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        System.out.println("test #1 Status code: " + response.getStatusCode());
        assertEquals("Was created a new author. Status code for testPostAuthor should be 201", 201, response.statusCode());
        sleep(sleepTime);
        
    }

    @Test /*test #1.1*/
    public void testPostAuthorNegativ1() {
        //400 The author's id was already in the database.
        Author author1 = new Author();
        Author author2 = new Author();
        Response response1 = author1.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response1.statusCode());
        int idAuthor1 = author1.getId();
        Response response2 = author2.postAuthor("", "", "", "", idAuthor1, true);
        System.out.println("test #1.1 Status code: " + response2.getStatusCode());
        assertEquals("The author's id was already in the database. Post response for testPostAuthorNegativ1 should have status code 400", 400, response2.getStatusCode());
        sleep(sleepTime);
    
    }
    
    @Test /*test #1.2*/
    public void testPostAuthorNegativ2() {
        //400 Try to Create a new author with no firatName.
        Author author = new Author();
        Response response = author.postAuthor("-", "", "", "", 0, true);
        System.out.println("test #1.2 Status code: " + response.getStatusCode());
        assertEquals("Try to Create a new author with no firatName. Status code for testPostAuthorNegativ2 should be 400", 400, response.statusCode());
        sleep(sleepTime);
        
    }

    @Test /*test #1.3*/
    public void testPostAuthorNegativ3() {
        ///400 Try to Create a new author with no lastName.
        Author author = new Author();
        Response response = author.postAuthor("", "-", "", "", 0, true);
        System.out.println("test #1.3 Status code: " + response.getStatusCode());
        assertEquals("Try to Create a new author with no lastName. Status code for testPostAuthorNegativ3 should be 400", 400, response.statusCode());
        sleep(sleepTime);
        
    }


    @Test /*test #2*/
    public void testPutAuthor() {
        //Update a author with new data.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response.statusCode());
        String authorsNameTemplate = "Avrora";
        Integer authorsIdTemplate = author.getId();
        author.setFirstName(authorsNameTemplate);
        String bodyResponse = author.prepareTemplateForNewAuthor(authorsNameTemplate, authorsNameTemplate, authorsNameTemplate, authorsNameTemplate,authorsIdTemplate, true);

        Response putResponse = author.putAuthor(bodyResponse);
        System.out.println("test #2 Status code: " + putResponse.statusCode());
        assertEquals("Update a author with new data. Status code for testPutAuthor should be 200", 200, putResponse.statusCode());
        sleep(sleepTime);
   
    }

    @Test /*test #2.1*/
    public void testPutAuthorNegativ1() {
        //404 The author was not found.
        Author author = new Author();
        String bodyResponse = author.prepareTemplateForNewAuthor("", "", "", "", 0, true);

        Response putResponse = author.putAuthor(bodyResponse);
        System.out.println("test #2.1 Status code: " + putResponse.statusCode());
        assertEquals("The author was not found. Status code for testPutAuthorNegativ1 should be 404", 404, putResponse.statusCode());
        sleep(sleepTime);
    
    }
    
    @Test /*test #2.2*/
    public void testPutAuthorNegativ2() {
        //400 The author had no first name.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response.statusCode());
        Integer authorsIdTemplate = author.getId();
        String bodyResponse = author.prepareTemplateForNewAuthor("-", "", "", "", authorsIdTemplate, true);

        Response putResponse = author.putAuthor(bodyResponse);
        System.out.println("test #2.2 Status code: " + putResponse.statusCode());
        assertEquals("The author had no first name. Status code for testPutAuthorNegativ2 should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
    
    }

    @Test /*test #2.3*/
    public void testPutAuthorNegativ3() {
        //400 The author had no last name.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response.statusCode());
        Integer authorsIdTemplate = author.getId();
        String bodyResponse = author.prepareTemplateForNewAuthor("", "-", "", "", authorsIdTemplate, true);

        Response putResponse = author.putAuthor(bodyResponse);
        System.out.println("test #2.3 Status code: " + putResponse.statusCode());
        assertEquals("The author had no last name. Status code for testPutAuthorNegativ3 should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
    
    }
       
    @Test /*test #3*/
    public void testGetAllAuthors() {
        //200 Get all the books from the database
        Author author = new Author();
        Response response = author.getAllAuthors();
        System.out.println("test #3 Status code: " + response.getStatusCode());
        assertEquals("Get all the books from the database. Status code for testGetAllAuthors should be 200", 200, response.statusCode());
        sleep(sleepTime);
    
    }   

    @Test /*test #4*/
    public void testGetAuthorById() {
        //200 Get the author with the specified id.
        Author author = new Author();

        Response response = author.getAuthor(1);
        System.out.println("test #4 Status code: " + response.getStatusCode());
        assertEquals("Get the author with the specified id. Status code for testGetAuthorById should be 200", 200, response.statusCode());
        sleep(sleepTime);
    
    }

    @Test /*test #4.1*/
      public void testGetAuthorByIdNegativ() {
        //404 Get the author with the specified id, if the author was not found.
        Author author = new Author();
        
        Response response = author.getAuthor(999999);
        System.out.println("test #4.1 Status code: " + response.getStatusCode());
        assertEquals("Get the author with the specified id, if the author was not found. Status code for testGetAuthorByIdNegativ should be 404 if the author was not found", 404, response.statusCode());
        sleep(sleepTime);
    
    }
    
    @Test /*test #5*/
       public void testDeleteAuthorById() {
        //201 Delete the author with the specified id.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response.statusCode());
        int t = author.getId();
        Response deleteResponse = author.deleteAuthor(t);
        assertEquals("Delete the author with the specified id. Delete method for testDeleteAuthorById should return 204", 204, deleteResponse.getStatusCode());
        System.out.println("test #5 Status code: " + deleteResponse.getStatusCode());
        sleep(sleepTime);
   
    }
      
    @Test /*test #5.1*/
       public void testDeleteAuthorByIdNegativ1() {
        //404 The author was not found.
        Author author = new Author();
        Response response = author.postAuthor("", "", "", "", 0, true);
        assertEquals("Status code should be 201", 201, response.statusCode());
        int t = author.getId();
        Response deleteResponse = author.deleteAuthor(t);
        assertEquals("Delete method should return 204", 204, deleteResponse.getStatusCode());
        deleteResponse = author.deleteAuthor(t);
        assertEquals("The author was not found. Delet method for testDeleteAuthorByIdNegativ1 should return 404", 404, deleteResponse.getStatusCode());
        System.out.println("test #5.1 Status code: " + deleteResponse.getStatusCode());
        sleep(sleepTime);
 
    }

    @Test /*test #5.2*/
       public void testDeleteAuthorByIdNegativ2() {
        //409 The author still has books in the database.
        Author author = new Author();
        Response deleteResponse = author.deleteAuthor(2);
        assertEquals("The author still has books in the database. TestDeleteAuthorByIdNegativ2 should return 409", 409, deleteResponse.getStatusCode());
        System.out.println("test #5.2 Status code: " + deleteResponse.getStatusCode());
        sleep(sleepTime);
       }
 
    @Test /*test #6*/
      public void testBookPost() {
        //201 book was added
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        System.out.println("Test #6 Status code: " + postResponse.getStatusCode());
        assertEquals("book was added. Post response for testBookPost should have status code 201", 201, postResponse.statusCode());
        sleep(sleepTime);
    
    }
      
   @Test /*test #6.1*/
      public void testBookPostNegativ1() {
        //400 The book's id was already in the database
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

        postResponse = book.postBook(bookTemplate);
        System.out.println("Test #6.1 Status code: " + postResponse.getStatusCode());
        assertEquals("The book's id was already in the database. Post response for testBookPostNegativ1 should have status code 400", 400, postResponse.getStatusCode());
        sleep(sleepTime);
    
    }

   @Test /*test #6.2*/
      public void testBookPostNegativ2() {
        //400 The book contained an author that had no id field set
        Author additionalAuthor = new Author();
        Response response = additionalAuthor.postAuthor("Astrid", "Lindgren", "", "", 0, false);
        assertEquals("Status code should be 201", 201, response.statusCode());
        
        String OneNewAuthor = additionalAuthor.prepareOneNewAuthor("Astrid", "Lindgren", "", "", 0, false);
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+OneNewAuthor+"\n");
        response = book.postBook(bookTemplate);
        System.out.println("test #6.2 Status code: " + response.getStatusCode());
        assertEquals("The book contained an author that had no id field set. Status code for testBookPostNegativ2 should be 400", 400, response.statusCode());   
        sleep(sleepTime);
        
    }
      
   @Test /*test #6.3*/
      public void testBookPostNegativ3() {
        //400 the book contained an author that didn't exist in the database.
        Author additionalAuthor = new Author();
        String authorTemplate = additionalAuthor.prepareOneNewAuthor("Agatha", "Christie", "", "", 0, true);  
        
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+authorTemplate+"\n");
        Response postResponse = book.postBook(bookTemplate);
        System.out.println("test #6.3 Status code: " + postResponse.getStatusCode());
        assertEquals("the book contained an author that didn't exist in the database. Status code for testBookPostNegativ3 should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
        
    }
      
   @Test /*test #6.4*/
      public void testBookPostNegativ4() {
        //400 The book had no title set.
        Author additionalAuthor = new Author();
        String authorTemplate = additionalAuthor.prepareOneNewAuthor("Boris", "Strugackiy", "", "", 0, true);  
        
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "-", 0, 0, ",\n"+authorTemplate+"\n");
        Response postResponse = book.postBook(bookTemplate);
        System.out.println("test #6.4 Status code: " + postResponse.getStatusCode());
        assertEquals("The book had no title set. Status code for testBookPostNegativ4 should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
        
    }
           
   @Test /*test #7*/
     public void testBookPut() {
      //200 The book was updated. 
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        Integer booksId = book.getId();
        String putBody = book.createBooksTemplate("Hej!Hej!", "11111111111", 111, "1111-11-11", "About me", 3, booksId, "");
        Response putResponse = book.putBook(putBody);
        System.out.println("test #7 Status code: " + putResponse.getStatusCode());
        assertEquals("The book was updated. Status code for testBookPut should be 200", 200, putResponse.statusCode());
        sleep(sleepTime);
        
      }
      
     @Test /*test #7.1*/
      public void testBookPutNegativ1() {
        //400 The book had no title set.
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        Integer booksId = book.getId();
        String putBody = book.createBooksTemplate("Hej!Hej!", "11111111111", 0, "1973-08-28", "-", 6, booksId, "");        
        Response putResponse = book.putBook(putBody);
        System.out.println("test #7.1 Status code: " + putResponse.getStatusCode());
        assertEquals("The book had no title set. Status code for testBookPutNegativ1 should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
          
      
      }
      
     @Test /*test #7.2*/
      public void testBookPutNegativ2() {
        //400 The book contained an author with no id field set. 
        Author additionalAuthor = new Author();
        String authorTemplate = additionalAuthor.prepareOneNewAuthor("", "", "", "", 0, false);          
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");        
        Response postResponse = book.postBook(bookTemplate);
        String putBody = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+authorTemplate+"\n");     
        Response putResponse = book.putBook(putBody);
        System.out.println("test #7.2 Status code: " + putResponse.getStatusCode());
        assertEquals("The book contained an author with no id field set. Status code for testBookPutNegativ2 should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
          
      }      

    
     @Test /*test #7.3*/
      public void testBookPutNegativ3() {
      //400 The book contained an author that didn't exist in the database.      
        Author author = new Author();
              
        String authorsTemplate = author.prepareOneNewAuthor("", "", "", "", 0, true);
        Integer authorsId = author.getId();
        String authorsFirstName = author.getFirstName();
        String authorsLastName = author.getLastName();
        String authorsBio = author.getBiography();
        String authorsCountry = author.getCountry();
        
        Response response = author.postAuthor(authorsFirstName, authorsLastName, authorsBio, authorsCountry, authorsId, true);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+authorsTemplate+"\n");
        Integer booksId = book.getId();
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());


        String authorsTemplate1 = author.prepareOneNewAuthor("", "", "", "", 0, false);

        String putBody = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), "1111-11-11", book.getTitle(), book.getNbrAvailable(), booksId, ",\n"+authorsTemplate1+"\n");
        Response putResponse = book.putBook(putBody);
        System.out.println("test #7.3 Status code: " + putResponse.statusCode());
        assertEquals("The book contained an author that didn't exist in the database. Status code for testBookPutNegativ3 should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
      
      }

   @Test /*test #7.4*/
      public void testBookPutNegativ4() {
      //404 The book was not found.
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response putResponse = book.putBook(bookTemplate);
        System.out.println("test #7.4 Status code: " + putResponse.statusCode());
        assertEquals("The book was not found. Status code for testBookPutNegativ4 should be 404", 404, putResponse.statusCode());
        sleep(sleepTime);
           
      }  


      
    @Test /*test #8*/
      public void testGetAllBooks() {
        //Get all the books from the database
        Book book = new Book();
        Response response = book.getAllBooks();
        System.out.println("test #8 Status code: " + response.getStatusCode());
        assertEquals("Get all the books from the database. Status code for testGetAllBooks should be 200", 200, response.statusCode());
        sleep(sleepTime);

    }

      
    @Test /*test #9*/
      public void testGetBookByNumber() {
        Book book = new Book();
       //200 Get book by number
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        int t = book.getId();
        Response response = book.getBook(t);
        System.out.println("test #9 Status code: " + response.getStatusCode());
        assertEquals("Got book by number. Status code for testGetBookByNumber should be 200", 200, response.statusCode());
        sleep(sleepTime);

    }

    @Test /*test #9.1*/
      public void testGetBookByNumberNegativ1() {
        //404 The book was not found.
        Book book = new Book();
        Response response = book.getBook(999999999);
        System.out.println("test #9.1 Status code: " + response.getStatusCode());
        assertEquals("The book was not found. Status code for testGetBookByNumberNegativ1 should be 404", 404, response.statusCode());
        sleep(sleepTime);

    } 
 
    @Test /*test #10*/
      public void testDeleteBookByNumber() {
      //204 The book was deleted.
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        int t = book.getId();
        Response deleteResponse = book.deleteBook(t);
        System.out.println("test #10 Status code: " + deleteResponse.getStatusCode());
        assertEquals("The book was deleted. testDeleteBookByNumber should return 204", 204, deleteResponse.getStatusCode());
        sleep(sleepTime);
        
      }
      
    @Test /*test #10.1*/
      public void testDeleteBookByNumberNegativ() {
      //404 The book was not found.
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        int t = book.getId();
        Response deleteResponse = book.deleteBook(t);
        assertEquals("Delet method should return 204", 204, deleteResponse.getStatusCode());
        Response getDeletedBookResponse = book.getBook(t);
        assertEquals("Fetching deleted book should return 404", 404, getDeletedBookResponse.getStatusCode());
        deleteResponse = book.deleteBook(t);
        System.out.println("test #10.1 Status code: " + deleteResponse.getStatusCode());        
        assertEquals("The book was not found. testDeleteBookByNumberNegativ should return 404", 404, deleteResponse.getStatusCode());        
        sleep(sleepTime);

      }

    @Test /*test #11*/
      public void testGetBooksOfAuthor() {
        //200 Get all the books of the asked author from the database.
        ///books/byauthor/{author_id}
        Book book = new Book();
        Response response = book.getBooksOfAuthor(2);
        System.out.println("test #11 Status code: " + response.getStatusCode());
        assertEquals("Get all the books of the asked author from the database. Status code for testGetBooksOfAuthor should be 200", 200, response.statusCode());
        sleep(sleepTime);
          
      }

    @Test /*test #11.1*/
      public void testGetBooksOfAuthorNegativ() {
        //200 STRANGE Try to get all the books of the asked author from the database, when there is no such author.
        ///books/byauthor/{author_id}
        Book book = new Book();
        Response response = book.getBooksOfAuthor(555555);
        System.out.println("test #11.1 Status code: " + response.getStatusCode());
        assertEquals("Try to get all the books of the asked author from the database, when there is no such author. Status code should be 200", 200, response.statusCode());
        sleep(sleepTime);
          
      }
      
    @Test /*test #12*/
     public void testGetAuthersOfBook() {
        //books/{book_id}/authors
        //200 Get the authors of the specified book.
        Book book = new Book();
        Response getResponse = book.getBooksAuthors(4);
        System.out.println("test #12 Status code: " + getResponse.getStatusCode());
        assertEquals("Get the authors of the specified book. Status code for testGetAuthersOfBook should be 200", 200, getResponse.statusCode());
        sleep(sleepTime);
          
      }
      
    @Test /*test #12.1*/
      public void testGetAuthersOfBookNegativ() {
        //books/{book_id}/authors
        //404 The book was not 
        Book book = new Book();
        Response getResponse = book.getBooksAuthors(99999);
        System.out.println("test #12.1 Status code: " + getResponse.getStatusCode());
        assertEquals("The book was not. Status code for testGetAuthersOfBookNegativ should be 404", 404, getResponse.statusCode());
        sleep(sleepTime);
         
      }
      
    @Test /*test #13*/
      public void testAddAutherToBook() {
        //200 Add an author to the specified book. 
        ///books/{book_id}/authors
        Author additionalAuthor = new Author();
        String authorsTemplate = additionalAuthor.prepareTemplateForNewAuthor("", "", "", "", 0, true);
        Response response = additionalAuthor.postAuthor(additionalAuthor.getFirstName(), additionalAuthor.getLastName(), additionalAuthor.getBiography(), additionalAuthor.getCountry(), additionalAuthor.getId(), true);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        response = additionalAuthor.postAdditionalAuthorToBook(authorsTemplate, 3);
        System.out.println("test #13 Status code: " + response.getStatusCode());
        assertEquals("Add an author to the specified book. Status code for testAddAutherToBook should be 200", 200, response.statusCode());
        sleep(sleepTime);

      }

    @Test /*test #13.1*/
     public void testAddAutherToBookNegativ1() {
        //400 The author did not have the id field set
        ///books/{book_id}/authors
        Author additionalAuthor = new Author();

        String authorsTemplate = additionalAuthor.prepareTemplateForNewAuthor("", "", "", "", 0, false);
        Response response = additionalAuthor.postAuthor(additionalAuthor.getFirstName(), additionalAuthor.getLastName(), additionalAuthor.getBiography(), additionalAuthor.getCountry(), additionalAuthor.getId(), false);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        response = additionalAuthor.postAdditionalAuthorToBook(authorsTemplate, 3);
        System.out.println("test #13.1 Status code: " + response.getStatusCode());
        assertEquals("The author did not have the id field set. Status code for testAddAutherToBookNegativ1 should be 400", 400, response.statusCode());
        sleep(sleepTime);

      }

    @Test /*test #13.2*/
      public void testAddAutherToBookNegativ2() {
        //400 The author was already an author of this book. 
        ///books/{book_id}/authors
        Author additionalAuthor = new Author();              
        String authorsTemplate = additionalAuthor.prepareTemplateForNewAuthor("", "", "", "", 0, true);
        Response response = additionalAuthor.postAuthor(additionalAuthor.getFirstName(), additionalAuthor.getLastName(), additionalAuthor.getBiography(), additionalAuthor.getCountry(), additionalAuthor.getId(), true);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        response = additionalAuthor.postAdditionalAuthorToBook(authorsTemplate, 3);
        assertEquals("Status code should be 200", 200, response.statusCode());
        response = additionalAuthor.postAdditionalAuthorToBook(authorsTemplate, 3);
        System.out.println("test #13.2 Status code: " + response.getStatusCode());
        assertEquals("The author was already an author of this book. Status code for testAddAutherToBookNegativ2 should be 400", 400, response.statusCode());
        sleep(sleepTime);
        
      }
      
    @Test /*test #13.3*/
      public void testAddAutherToBookNegativ3() {
        //404 The book was not found. 
        ///books/{book_id}/authors
        Author additionalAuthor = new Author();
        String authorsTemplate = additionalAuthor.prepareTemplateForNewAuthor("", "", "", "", 0, true);
        Response response = additionalAuthor.postAuthor(additionalAuthor.getFirstName(), additionalAuthor.getLastName(), additionalAuthor.getBiography(), additionalAuthor.getCountry(), additionalAuthor.getId(), true);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        response = additionalAuthor.postAdditionalAuthorToBook(authorsTemplate, 99999);
        System.out.println("test #13.3 Status code: " + response.getStatusCode());
        assertEquals("The book was not found. Status code for testAddAutherToBookNegativ3 should be 404", 404, response.statusCode());
        sleep(sleepTime);

      }
      
    @Test /*test #14*/
      public void testUpdateBooksAuthors() {
      ///books/{book_id}/authors
      //Update a book's list of authors with a new list of authors.
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, "");

        Integer booksId = book.getId();
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

        Author author = new Author();
        String authorsTemplate = author.prepareFullTemplateForAuthors("", "", "", "", 0, true);
        Response response = author.postAuthor(author.getFirstName(), author.getLastName(), author.getBiography(), author.getCountry(), author.getId(), true);
        assertEquals("Post response should have status code 201", 201, response.statusCode());
        Response putResponse = book.putNewAuthors(authorsTemplate, booksId);
        System.out.println("test #14 Status code: " + putResponse.getStatusCode());
        assertEquals("Update a book's list of authors with a new list of authors. Post response should have status code 200", 200, putResponse.statusCode());
        sleep(sleepTime);

      }
 
    @Test /*test #14.1*/
      public void testUpdateBooksAuthorsNegativ1() {
      ///books/{book_id}/authors
      //400 one of the authors was already an author of this book.
        Author author1 = new Author();
        Author author2 = new Author();
              
        String authors1Template = author1.prepareOneNewAuthor("", "", "", "", 0, true);
        Response response1 = author1.postAuthor(author1.getFirstName(), author1.getLastName(), author1.getBiography(), author1.getCountry(), author1.getId(), true);
        assertEquals("Post response should have status code 201", 201, response1.statusCode());
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+authors1Template+"\n");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        Response putResponse = book.putNewAuthors(authors1Template, book.getId());
        System.out.println("test #14.2 Status code: " + putResponse.getStatusCode());
        assertEquals("one of the authors was already an author of this book. Post response for UpdateBooksAuthorsNegativ1 should have status code 400", 400, putResponse.statusCode());     
        sleep(sleepTime);

      }

    @Test /*test #14.2*/
      public void testUpdateBooksAuthorsNegativ2() {
      ///books/{book_id}/authors
      //400 One of the authors did not have the id field set.
        Author author1 = new Author();
        Author author2 = new Author();
              
        String authors1Template = author1.prepareOneNewAuthor("", "", "", "", 0, true);
        String authors2Template = author2.prepareOneNewAuthor("", "", "", "", 0, false);
        Response response1 = author1.postAuthor(author1.getFirstName(), author1.getLastName(), author1.getBiography(), author1.getCountry(), author1.getId(), true);
        assertEquals("Post response should have status code 201", 201, response1.statusCode());
        
        Response response2 = author1.postAuthor(author2.getFirstName(), author2.getLastName(), author2.getBiography(), author2.getCountry(), author2.getId(), false);
        assertEquals("Post response should have status code 201", 201, response2.statusCode());
        
        Book book = new Book();
        String bookTemplate = book.createBooksTemplate("", "", 0, "1973-06-28", "", 0, 0, ",\n"+authors1Template+"\n");
        Response postResponse = book.postBook(bookTemplate);
        assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
        Response putResponse = book.putNewAuthors(authors2Template, book.getId());
        System.out.println("test #14.2 Status code: " + putResponse.getStatusCode());
        assertEquals("One of the authors did not have the id field set. Post response for UpdateBooksAuthorsNegativ2 should have status code 400", 400, putResponse.statusCode());     
        sleep(sleepTime);

      }
      
    @Test /*test #14.3*/
      public void testUpdateBooksAuthorsNegativ3() {
      ///books/{book_id}/authors
      //404 The book was not found.
        Author author = new Author();      
        Book book = new Book();
        String authorsTemplate = author.prepareFullTemplateForAuthors("", "", "", "", 0, true);
        Response response = author.postAuthor(author.getFirstName(), author.getLastName(), author.getBiography(), author.getCountry(), author.getId(), true);

        assertEquals("Post response should have status code 201", 201, response.statusCode());
        Response putResponse = book.putNewAuthors(authorsTemplate, 141414);
        System.out.println("test #14.3 Status code: " + putResponse.getStatusCode());
        assertEquals("The book was not found. Post response for UpdateBooksAuthorsNegativ3 should have status code 404", 404, putResponse.statusCode());     
        sleep(sleepTime);

      }  
      
    @Test /*test #15*/
      public void testGetAllUsers() { 
       //200 Get all users
        User user = new User();
        Response response = user.getAllUsers();
        System.out.println("test #15 Status code: " + response.getStatusCode());
        assertEquals("Get all users. Status code should be 200", 200, response.statusCode());
        sleep(sleepTime);
     
      }
 
    @Test /*test #16*/
      public void testPostUser() { 
       //201 The user was created.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, false);
        Response postResponse = user.postUser(userTemplate);
        System.out.println("test #16 Status code: " + postResponse.getStatusCode());
        assertEquals("The user was created. Status code should be 201", 201, postResponse.statusCode());
        sleep(sleepTime);
      
      }
 
    @Test /*test #16.1*/
      public void testPostUserNegativ1() { 
       //400 The user's id was already in the database.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("","", true, "LOANER", 0, false);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        postResponse = user.postUser(userTemplate);
        System.out.println("test #16.1 Status code: " + postResponse.getStatusCode());
        assertEquals("The user's id was already in the database. Status code should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
      
      }

    @Test /*test #16.2*/
      public void testPostUserNegativ2() { 
       //400 The user had no display name.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("-", "", true, "LOANER", 0, false);
        Response postResponse = user.postUser(userTemplate);
        System.out.println("test #16.2 Status code: " + postResponse.getStatusCode());
        assertEquals("The user had no display name. Status code should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
      
      }

    @Test /*test #16.3*/
      public void testPostUserNegativ3() { 
       //400 The user had no password.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", false, "LOANER", 0, false);
        Response postResponse = user.postUser(userTemplate);
        System.out.println("test #16.3 Status code: " + postResponse.getStatusCode());
        assertEquals("The user had no password. Status code should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
      
      }
      
    @Test /*test #16.4*/
      public void testPostUserNegativ4() { 
       //400 The user had no role.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "", 0, false);
        Response postResponse = user.postUser(userTemplate);
        System.out.println("test #16.4 Status code: " + postResponse.getStatusCode());
        assertEquals("The user had no role. Status code should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
      
      }

    @Test /*test #16.5*/
      public void testPostUserNegativ5() { 
       //400 The user's display name already exists.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, false);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userName = user.getUserDisplayName();
        userTemplate = user.prepareTemplateForNewUser(userName, "", true, "LOANER", 0, false);
        postResponse = user.postUser(userTemplate);
        System.out.println("test #16.5 Status code: " + postResponse.getStatusCode());
        assertEquals("The user's display name already exists. Status code should be 400", 400, postResponse.statusCode());
        sleep(sleepTime);
      
      }
      
    @Test /*test #17*/
    public void testPutUser() {
        //200 Update user with new data.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate1 = user.prepareTemplateForNewUser("", "Bella", true, "LOANER", user.getId(), true);
        Response putResponse = user.putUser(userTemplate1);
        System.out.println("test #17 Status code: " + putResponse.statusCode());
        assertEquals("Updated user with new data. Status code should be 200", 200, putResponse.statusCode());
        sleep(sleepTime);
   
    }
      
    @Test /*test #17.1*/
    public void testPutUserNegativ1() {
        //400 the user had no display name.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate1 = user.prepareTemplateForNewUser("-", "Bella", true, "LOANER", user.getId(), true);
        Response putResponse = user.putUser(userTemplate1);
        System.out.println("test #17.1 Status code: " + putResponse.statusCode());
        assertEquals("the user had no display name. Status code should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
   
    }

    @Test /*test #17.2*/
    public void testPutUserNegativ2() {
        //400 The user had no password.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate1 = user.prepareTemplateForNewUser("", "Karl", false, "LOANER", user.getId(), true);
        Response putResponse = user.putUser(userTemplate1);
        System.out.println("test #17.2 Status code: " + putResponse.statusCode());
        assertEquals("The user had no password. Status code should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
   
    }

    @Test /*test #17.3*/
    public void testPutUserNegativ3() {
        //400 The user had no role.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate1 = user.prepareTemplateForNewUser("", "Karl", true, "", user.getId(), true);
        Response putResponse = user.putUser(userTemplate1);
        System.out.println("test #17.3 Status code: " + putResponse.statusCode());
        assertEquals("The user had no role. Status code should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
   
    }

    @Test /*test #17.4*/
    public void testPutUserNegativ4() {
        //400 The user's display name already exists in another user.
        User user1 = new User();
        User user2 = new User();
        String userTemplate1 = user1.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user1.postUser(userTemplate1);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate2 = user2.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        postResponse = user2.postUser(userTemplate2);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        String userTemplate3 = user2.prepareTemplateForNewUser(user1.getUserDisplayName(), "Karl", true, "LOANER", user2.getId(), true);
        Response putResponse = user2.putUser(userTemplate3);
        System.out.println("test #17.4 Status code: " + putResponse.statusCode());
        assertEquals("The user's display name already exists in another user. Status code should be 400", 400, putResponse.statusCode());
        sleep(sleepTime);
   
    }
    
    @Test /*test #17.5*/
    public void testPutUserNegativ5() {
        //404 The user was not found.
        User user = new User();
        String userTemplate1 = user.prepareTemplateForNewUser("", "Karl", true, "LOANER", 222222222, true);
        Response putResponse = user.putUser(userTemplate1);
        System.out.println("test #17.5 Status code: " + putResponse.statusCode());
        assertEquals("The user was not found. Status code should be 404", 404, putResponse.statusCode());
        sleep(sleepTime);
  
    }
    
    @Test /*test #18*/
      public void testGetUserByNumber() {
        //200 The user was retrieved.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        int t = user.getId();
        Response response = user.getUser(t);
        System.out.println("test #18 Status code: " + response.getStatusCode());
        assertEquals("The user was retrieved. Status code should be 200", 200, response.statusCode());
        sleep(sleepTime);

    }

    @Test /*test #18.1*/
      public void testGetUserByNumberNegativ1() {
        //404 The user was not found.
        User user = new User();
        Response response = user.getUser(999999999);
        System.out.println("test #18.1 Status code: " + response.getStatusCode());
        assertEquals("The user was not found. Status code should be 404", 404, response.statusCode());
        sleep(sleepTime);

    } 
    
    @Test /*test #19*/
      public void testDeleteUserByNumber() {
      //204 The user was deleted.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        int t = user.getId();
        Response deleteResponse = user.deleteUser(t);
        System.out.println("test #19 Status code: " + deleteResponse.getStatusCode());
        assertEquals("The user was deleted. Delet method should return 204", 204, deleteResponse.getStatusCode());
        sleep(sleepTime);
        
      }
      
    @Test /*test #19.1*/
      public void testDeleteUserByNumberNegativ() {
      //404 The user was not found.
        User user = new User();
        String userTemplate = user.prepareTemplateForNewUser("", "", true, "LOANER", 0, true);
        Response postResponse = user.postUser(userTemplate);
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
        int t = user.getId();
        Response deleteResponse = user.deleteUser(t);
        assertEquals("Delet method should return 204", 204, deleteResponse.getStatusCode());
        Response getDeletedBookResponse = user.getUser(t);
        assertEquals("Fetching deleted book should return 404", 404, getDeletedBookResponse.getStatusCode());
        deleteResponse = user.deleteUser(t);
        System.out.println("test #19.1 Status code: " + deleteResponse.getStatusCode());        
        assertEquals("The user was not found. Delet method should return 404", 404, deleteResponse.getStatusCode());        
        sleep(sleepTime);

      }
 
    @Test /*test #20*/
      public void testGetAllLoans() {
      //200 Get all the loans from the database.
        Loan loan = new Loan();
        Response response = loan.getAllLoans();
        System.out.println("test #20 Status code: " + response.getStatusCode());
        assertEquals("Get all the loans from the database.. Status code should be 200", 200, response.statusCode());
        sleep(sleepTime);
      
      }

    @Test /*test #21*/
      public void testPostLoans() { 
          //201 The loan was created.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), book.getDatePublished(), book.getTitle(), book.getNbrAvailable(), book.getId(), "");
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 0);
           Response response = loan.postLoan(postTemplate);
           System.out.println("test #21 Status code: " + response.getStatusCode());
           assertEquals("The loan was created. Status code should be 201", 201, response.statusCode());           
           sleep(sleepTime);
      
      }

      
    @Test /*test #21.1*/
      public void testPostLoansNegativ1() { 
          //400 The user was not set or was not found in the database.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 0);
           Response response = loan.postLoan(postTemplate);
           System.out.println("test #21.1 Status code: " + response.getStatusCode());
           assertEquals("The user was not set or was not found in the database. Status code should be 400", 400, response.statusCode());
           sleep(sleepTime);
                
      }
     
    @Test /*test #21.2*/
      public void testPostLoansNegativ2() { 
          //400 The book was not set or was not found in the database.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 0);
           Response response = loan.postLoan(postTemplate);
           System.out.println("test #21.2 Status code: " + response.getStatusCode());
           assertEquals("The book was not set or was not found in the database. Status code should be 400", 400, response.statusCode());           
           sleep(sleepTime);
      
      }


/*    @Test /*test #21.3*///  DOSN'T WORK PROGRAMM. RETORN 201
/*      public void testPostLoansNegativ3() { 
          //400 The user had no role.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), book.getDatePublished(), book.getTitle(), book.getNbrAvailable(), book.getId(), "");
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           System.out.println("test #21.3 Status code: " + response.getStatusCode());
           assertEquals("Status code should be 400", 400, response.statusCode());           
      
      }
*/
/*  @Test /*test #22.3*///DOSN WORK BAG IN PROGRAM??
/*      public void testPostLoansNegativ3() { 
          //400 The user had no role.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), book.getDatePublished(), book.getTitle(), book.getNbrAvailable(), book.getId(), "");
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           String userDisplayTmp = user.getUserDisplayName();
           String firstNameTmp = user.getUserFirstName();
           Integer userIdTmp = user.getId();
        
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode());
           Integer idLoan = loan.getId();
           userTemplate = user.prepareOneNewUser(userDisplayTmp, firstNameTmp, true, "", userIdTmp, true);
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-12-15", idLoan);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.3 Status code: " + response.getStatusCode());
           assertEquals("The user had no role. Status code should be 400", 400, response.statusCode()); 
     
      }
*/     
 
    @Test /*test #21.4*/ 
      public void testPostLoansNegativ4() { 
          //409 There was already a loan present with the same book and user.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), book.getDatePublished(), book.getTitle(), book.getNbrAvailable(), book.getId(), "");
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           response = loan.postLoan(postTemplate);
           System.out.println("test #21.4 Status code: " + response.getStatusCode());
           assertEquals("There was already a loan present with the same book and user. Status code should be 409", 409, response.statusCode());
           sleep(sleepTime);
      
      }

  @Test /*test #21.5*/
      public void testPostLoansNegativ5() { 
          //409 There were not enough copies of the book available to create the loan.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = user.prepareTemplateForNewUser(user.getUserDisplayName(), user.getUserFirstName(), true, "LOANER", user.getId(), true);
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 9999, 0, "");
           book.setNbrAvailable(0);
           String bookTemplateR = book.createBooksTemplate(book.getDescription(), book.getIsbn(), book.getNbOfPage(), book.getDatePublished(), book.getTitle(), 9999, book.getId(), "");
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 0);
           Response response = loan.postLoan(postTemplate);
           System.out.println("test #21.5 Status code: " + response.getStatusCode());
           assertEquals("There were not enough copies of the book available to create the loan. Status code should be 409", 409, response.statusCode()); 
           sleep(sleepTime);
      
      }      

   @Test /*test #22*/ 
     public void testLoanPut() {
      //200 The loan was updated. 
          
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-06-05", "2016-12-15", idLoan);
           response = loan.putLoan(putTemplate);
           assertEquals("The loan was updated. Status code should be 200", 200, response.statusCode());
           sleep(sleepTime);
           
      }
  
      
 @Test /*test #22.1*/ 
     public void testLoanPutNegativ1() {
      //400 The user was not set or was not found in the database. 
          
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-12-15", idLoan);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.1 Status code: " + response.getStatusCode());
           assertEquals("The user was not set or was not found in the database. Status code should be 400", 400, response.statusCode()); 
           sleep(sleepTime);
           
      }

 @Test /*test #22.2*/ 
     public void testLoanPutNegativ2() {
      //400 The book was not set or was not found in the database. 
          
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-12-15", idLoan);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.2 Status code: " + response.getStatusCode());
           assertEquals("The book was not set or was not found in the database. Status code should be 400", 400, response.statusCode()); 
           sleep(sleepTime);
           
      }

 @Test /*test #22.3*/ 
     public void testLoanPutNegativ3() {
      //400 The loan had no id set.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           //bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-12-15", 0);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.3 Status code: " + response.getStatusCode());
           assertEquals("The loan had no id set. Status code should be 400", 400, response.statusCode()); 
           sleep(sleepTime);
           
      }
/*     
 @Test /*test #22.4*/ 
/*     public void testLoanPutNegativ4() {
      //400 The loan had no date borrowed set.//BAG IN PROGRAM WITHOUT DATA BORROW MAKES IT AUTOMATICALY FOR <dateBorrowed>1970-01-01</dateBorrowed>
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           //bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "", "2016-12-15", idLoan);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.4 Status code: " + response.getStatusCode());
           assertEquals("Status code should be 400", 400, response.statusCode()); 
      }
*/
/*@Test /*test #22.5*/ 
/*     public void testLoanPutNegativ4() {
      //400 The loan had no date due set.//BAG IN PROGRAM WITHOUT DATA BORROW MAKES IT AUTOMATICALY FOR <dateBorrowed>1970-01-01</dateBorrowed>
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           Integer idLoan = loan.getId();
           
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-12-15", "", idLoan);
           response = loan.putLoan(putTemplate);
           System.out.println("test #22.5 Status code: " + response.getStatusCode());
           assertEquals("Status code should be 400", 400, response.statusCode()); 
      }  
*/  
     
@Test /*test #22.6*/ 
     public void testLoanPutNegativ4() {
      //404 The loan was not found.
           Loan loan = new Loan();
           User user = new User();
           Book book = new Book();
          
           String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
           String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
           Response postResponse = user.postUser(userTemplateR);
           assertEquals("Status code should be 201", 201, postResponse.statusCode());           
           String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
           String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
           postResponse = book.postBook(bookTemplateR);
           assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
           
           String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 0);
           Response response = loan.postLoan(postTemplate);
           assertEquals("Status code should be 201", 201, response.statusCode()); 
           
           String putTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-06-05", "2016-12-15", 9999);
           response = loan.putLoan(putTemplate);
           assertEquals("The loan was not found. Status code should be 404", 404, response.statusCode()); 
           sleep(sleepTime);
           
      }  
     

    @Test /*test #23*/
    public void testGetLoanById() {
        //200 The loan was retrieved.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response response = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, response.statusCode()); 
       Integer idLoan = loan.getId();

       response = loan.getLoan(idLoan);
       System.out.println("test #23 Status code: " + response.getStatusCode());
       assertEquals("The loan was retrieved. Status code should be 200", 200, response.statusCode());
       sleep(sleepTime);
    
    }

    @Test /*test #23.1*/
      public void testGetLoanByIdNegativ() {
        //404 The loan was not found.
        Loan loan = new Loan();
        
        Response response = loan.getLoan(999999);
        System.out.println("test #23.1 Status code: " + response.getStatusCode());
        assertEquals("The loan was not found. Status code should be 404 if the author was not found", 404, response.statusCode());
        sleep(sleepTime);
   
    }
     
    @Test /*test #24*/
      public void testDeleteLoanByNumber() {
      //204 The loan was deleted.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response response = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, response.statusCode()); 
       Integer idLoan = loan.getId();
       Response deleteResponse = loan.deleteLoan(idLoan);
       System.out.println("test #24 Status code: " + deleteResponse.getStatusCode());
       assertEquals("The loan was deleted. Delet method should return 204", 204, deleteResponse.getStatusCode());
       sleep(sleepTime);
       
      }
      
    @Test /*test #24.1*/
      public void testDeleteLoanByNumberNegativ() {
      //404 The loan was not found.
        Loan loan = new Loan();
        Response deleteResponse = loan.deleteLoan(999999);
        System.out.println("test #24.1 Status code: " + deleteResponse.getStatusCode());        
        assertEquals("The loan was not found. Delet method should return 404", 404, deleteResponse.getStatusCode());
        sleep(sleepTime);

      }

    @Test /*test #25*/
      public void testGetLoansByUserId() {
      //loans/ofuser/{user_id}          
      //200 The loans were retrieved.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       Integer idUser = user.getId();
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response response = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, response.statusCode()); 
       Integer idLoan = loan.getId();
       Response getLoans = loan.getLoansOfUser(idUser);
       System.out.println("test #25 Status code: " + getLoans.getStatusCode());
       assertEquals("The loans were retrieved. GetLoansByUserId method should return 200", 200, getLoans.getStatusCode());
       sleep(sleepTime);

      }

    @Test /*test #25.1*/
      public void testGetLoansByUserIdNegativ1() {
      //loans/ofuser/{user_id}
      //404  The user was not found.
       Loan loan = new Loan();

       Response getLoans = loan.getLoansOfUser(999999);
       System.out.println("test #25.1 Status code: " + getLoans.getStatusCode());
       assertEquals("The user was not found. GetLoansByUserId method should return 404", 404, getLoans.getStatusCode());
       sleep(sleepTime);

      }
      
    @Test /*test #25.2*/
      public void testGetLoansByUserIdNegativ2() {
      ///loans/ofuser/{user_id}
      //404  The user was found but there were no loans of the user.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       assertEquals("Status code should be 201", 201, postResponse.statusCode()); 
       Integer idUser = user.getId();

       Response getLoans = loan.getLoansOfUser(idUser);
       System.out.println("test #25.2 Status code: " + getLoans.getStatusCode());
       assertEquals("The user was found but there were no loans of the user. GetLoansByUserId method should return 404", 404, getLoans.getStatusCode());
       sleep(sleepTime);

      }
      
    @Test /*test #26*/
      public void testGetLoansByBookId() {
      //loans/ofbook/{book_id}          
      //200 The loans were retrieved.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       Integer idBook = book.getId();
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response response = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, response.statusCode()); 
       Response getLoans = loan.getLoansOfBook(idBook);
       System.out.println("test #26 Status code: " + getLoans.getStatusCode());
       assertEquals("The loans were retrieved. GetLoansByBookId method should return 200", 200, getLoans.getStatusCode());
       sleep(sleepTime);

      }

    @Test /*test #26.1*/
      public void testGetLoansByBookIdNegativ1() {
      ///loans/ofbook/{book_id}
      //404  The book was not found.
       Loan loan = new Loan();

       Response getLoans = loan.getLoansOfBook(999999);
       System.out.println("test #26.1 Status code: " + getLoans.getStatusCode());
       assertEquals("The book was not found. GetLoansByBookId method should return 404", 404, getLoans.getStatusCode());
       sleep(sleepTime);

      }
      
    @Test /*test #26.2*/
      public void testGetLoansByBookIdNegativ2() {
      ///loans/ofbook/{book_id}
      //404  The book was found but there were no loans of the book.
       Loan loan = new Loan();
       Book book = new Book();

       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       Response postResponse = book.postBook(bookTemplateR);
       Integer idBook = book.getId();
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       Response getLoans = loan.getLoansOfBook(idBook);
       System.out.println("test #26.2 Status code: " + getLoans.getStatusCode());
       assertEquals("The book was found but there were no loans of the book. GetLoansByBookId method should return 404", 404, getLoans.getStatusCode());
       sleep(sleepTime);

      }

    @Test /*test #27*/
      public void testGetLoansOfUserOfBook() {
       ///loans/ofuser/{user_id}/ofbook/{book_id}
       //200 The loan was retrieved.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       Integer idUser = user.getId();
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       Integer idBook = book.getId();
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response responseLoans = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, responseLoans.statusCode()); 
       Response getLoans = loan.getLoansOfBook(idBook);
       assertEquals("The loans were retrieved. GetLoansByBookId method should return 200", 200, getLoans.getStatusCode());
       
       Response response = loan.getLoansOfUserOfBook(idBook, idUser);
       System.out.println("test #27 Status code: " + response.getStatusCode());
       assertEquals("The loans were retrieved. testGetLoansOfUserOfBook should return 200", 200, response.getStatusCode());
       sleep(sleepTime);
      }
      
    @Test /*test #27.1*/
      public void testGetLoansOfUserOfBookNegativ1() {
       ///loans/ofuser/{user_id}/ofbook/{book_id}
       //404 The book or user was not found.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       Integer idUser = user.getId();
       assertEquals("Status code should be 201", 201, postResponse.statusCode());
       
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       Integer idBook = book.getId();
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());

       String postTemplate = loan.createLoansTemplate(bookTemplate, userTemplate, "2016-05-05", "2016-05-15", 1);
       Response responseLoans = loan.postLoan(postTemplate);
       assertEquals("Status code should be 201", 201, responseLoans.statusCode()); 
       Response getLoans = loan.getLoansOfBook(idBook);
       assertEquals("The loans were retrieved. GetLoansByBookId method should return 200", 200, getLoans.getStatusCode());
//       userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       Response deleteResponse = user.deleteUser(idUser);
       assertEquals("The user was deleted. Delet method should return 204", 204, deleteResponse.getStatusCode());       
       Response response = loan.getLoansOfUserOfBook(idBook, idUser/*999999*/);
       System.out.println("test #27.1 Status code: " + response.getStatusCode());
       assertEquals("The book or user was not found. TestGetLoansOfUserOfBookNegativ1 should return 404", 404, response.getStatusCode());
       sleep(sleepTime);
      }
      
    @Test /*test #27.2*/
      public void testGetLoansOfUserOfBookNegativ2() {
       ///loans/ofuser/{user_id}/ofbook/{book_id}
       //404 The book and user were found but there were no loans of the book by the user.
       Loan loan = new Loan();
       User user = new User();
       Book book = new Book();

       String userTemplate = user.prepareOneNewUser("", "", true, "LOANER", 0, true);
       String userTemplateR = ""+"{\n" + userTemplate + "\n" + "}";
       Response postResponse = user.postUser(userTemplateR);
       Integer idUser = user.getId();
       assertEquals("Status code should be 201", 201, postResponse.statusCode());           
       String bookTemplate = book.prepareTemplateForNewBook("", "", 0, "2012-12-12", "", 0, 0, "");
       String bookTemplateR = ""+"{\n" + bookTemplate + "\n" + "}";
       postResponse = book.postBook(bookTemplateR);
       Integer idBook = book.getId();
       assertEquals("Post response should have status code 201", 201, postResponse.statusCode());
       Response response = loan.getLoansOfUserOfBook(idBook, idUser);
       System.out.println("test #27.2 Status code: " + response.getStatusCode());
       assertEquals("The book and user were found but there were no loans of the book by the user.testGetLoansOfUserOfBookNegativ2 should return 404", 404, response.getStatusCode());
       sleep(sleepTime);
      }

}
