package se.nackademin.librarytest.model;

/**
 *
 * @author olesia
 */
public class Author {
    private String authorsName;
    private String Country;
    private String Biography;


    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    /**
     * @return the Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * @param Country the Country to set
     */
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * @return the Biography
     */
    public String getBiography() {
        return Biography;
    }

    /**
     * @param Biography the Biography to set
     */
    public void setBiography(String Biography) {
        this.Biography = Biography;
    }
    
    
}
