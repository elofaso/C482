package model;

/**
 * Models an in-house Part.
 *
 * @author Eric Lofaso
 *
 */
public class InHouse extends Part{

    /**
     * The machine ID for the part.
     */
    private int machineId;

    /**
     * Constructor
     *
     * @param id Part Id.
     * @param name Part Name.
     * @param price Part Price.
     * @param stock Part Stock Level.
     * @param min Part Minimum Stock Level.
     * @param max Part Maximum Stock Level.
     * @param machineId Part Machine ID.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Getter for Machine ID
     *
     * @return The machine ID>
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Setter for Machine ID
     *
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
