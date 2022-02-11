package entities.typeOfEmployee;


public class syndicate {
    private String name;
    private int id, oldId;
    private double serviceTax = 0.0, syndicateTax;

    public syndicate(int id, int oldId, String name, double syndicateTax) {
        this.id = id;
        this.oldId = oldId;
        this.name = name;
        this.syndicateTax = syndicateTax;
    }

    public int getOldId() {
        return oldId;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = +serviceTax;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public double getSyndicateTax() {
        return syndicateTax;
    }

    @Override
    public String toString() {
        return "Id: " + this.id +
                "IdEmployee: " + this.oldId +
                ", Name: " + this.name +
                ", Tax service: " + this.serviceTax +
                ", tax syndicate:" + this.serviceTax;
    }
}
