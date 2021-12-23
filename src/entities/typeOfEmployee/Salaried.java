package entities.typeOfEmployee;

import entities.employee;

public class Salaried extends employee {
    private double salary;

    public Salaried(int numIdentification, String name, String address, char typeEmployee, boolean sindicate,
            double salary) {
        super(numIdentification, name, address, typeEmployee, sindicate);
        this.salary = salary;
        this.paySchedule = "MENSAL";
    }

    private void calcSalary() {
        this.salary = this.salary - (this.taxService + this.taxSyndicate);
    }

    public void change(String name, String address, double salary) {
        super.set(name, address);
        this.salary = salary;

    }

    @Override
    public String toString() {
        calcSalary();
        return "Id: " + this.numIdentification +
                ", Name: " + this.name +
                ", Adress: " + this.address +
                ", Total salary:" + this.salary +
                ", Tax Service:" + this.taxService +
                ", Tax Syndicate:" + this.taxSyndicate +
                ", Pay Schedule:" + this.paySchedule;
    }

}
