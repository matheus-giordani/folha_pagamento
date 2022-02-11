package entities.typeOfEmployee;


import java.util.ArrayList;
import java.util.List;


import entities.employee;

public class Hourly extends employee {

    private final double DEFAULT_WORKING_HOUR = 8;
    private final double TAX_EXTRA_HOUR = 1.5;
    private double priceHour, totalSalary = 0;
    private List<Double> bankHour = new ArrayList<>();

    public Hourly(int numIdentification, String name, String address, char typeEmployee, boolean sindicate,
            double priceHour) {
        super(numIdentification, name, address, typeEmployee, sindicate);
        this.priceHour = priceHour;
        this.paySchedule = "SEMANAL 1 SEX";

    }

    public void launchCardPoint(double cardTime) {
        this.bankHour.add(cardTime);
        calcSalary();

    }

    private void calcSalary() {
        double extraHour;
        for (Double horaDay : bankHour) {
            if (horaDay > this.DEFAULT_WORKING_HOUR) {
                extraHour = horaDay - this.DEFAULT_WORKING_HOUR;
            } else {
                extraHour = 0;
            }
            this.totalSalary += (DEFAULT_WORKING_HOUR * this.priceHour) + (extraHour * TAX_EXTRA_HOUR) - this.taxService
                    - taxSyndicate;
        }
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void change(String name, String address, double priceHour) {
        super.set(name, address);
        this.priceHour = priceHour;
    }

    @Override
    public String toString() {
        return "Id: " + this.numIdentification +
                ", Name: " + this.name +
                ", Adress: " + this.address +
                ", Total salary:" + this.totalSalary +
                ", Tax Service:" + this.taxService +
                ", Tax Syndicate:" + this.taxSyndicate +
                ", Pay Schedule:" + this.paySchedule;
    }

}
