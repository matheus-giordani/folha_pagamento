package entities;



public abstract class employee { // transformando a classe em abstrata ela não podera mais ser instanciada apenas
                                 // suas subclasses
    protected String name, address, paySchedule;
    private char typeEmployee;
    private boolean syndicate;
    protected int numIdentification;
    protected double taxService = 0.0, taxSyndicate = 0.0;

    // implementar a geração automatica de numeros de identificação!

    public employee() {

    }

    public employee(int numIdentification, String name, String address, char typeEmployee, boolean syndicate) {
        this.numIdentification = numIdentification;
        this.name = name;
        this.address = address;
        this.typeEmployee = typeEmployee;
        this.syndicate = syndicate;

    }

    public int getNumIdentification() {
        return numIdentification;
    }

    public char getTypeEmployee() {
        return typeEmployee;
    }

    public void setPaySchedule(String paySchedule) {
        this.paySchedule = paySchedule;
    }

    public void setTaxService(double taxService) {
        this.taxService += taxService;

    }

    public void setTaxSyn(double taxSyndicate) {
        this.taxSyndicate = taxSyndicate;
    }

    protected void set(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
