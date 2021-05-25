package model;

/**
 * Models an outsourced Part.
 *
 * @author Eric Lofaso
 */
public class Outsourced extends Part {

    /**
     * Company Name for the Part.
     */
    private String CompanyName;

    /**
     * Constructor
     *
     * @param id Part ID.
     * @param name Part Name.
     * @param price Part Price.
     * @param stock Part Stock Level.
     * @param min Part Minimum Stock Level.
     * @param max Part Maximum Stock Level.
     * @param companyName Part Source Company Name.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        CompanyName = companyName;
    }

    /**
     * Getter for Company Name.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * Setter for Company Name
     *
     * @param companyName The company name.
     */
    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}
