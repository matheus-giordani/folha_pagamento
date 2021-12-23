package entities.typeOfEmployee;

import java.util.ArrayList;
import java.util.List;

import entities.employee;

public class Comissioned extends employee {
    private double comission, percentComission, salaryFixed, totalSalary;

    // register of the sell
    private class infoSell {
        private String date;
        private double sellValue;

        public infoSell() {

        }

        public void setvalue(String date, double sellValue) {
            this.date = date;
            this.sellValue = sellValue;
        }

        public double getSellValue() {
            return sellValue;
        }

    }

    private infoSell registerAux = new infoSell();
    // variavel que cria registro pra armazenar os dados das vendas
    private List<infoSell> register = new ArrayList<infoSell>();

    // constructor
    public Comissioned(int numIdentification, String name, String address, char typeEmployee, boolean sindicate,
            double percentComission, double salaryFixed) {
        super(numIdentification, name, address, typeEmployee, sindicate);
        this.percentComission = percentComission;
        this.salaryFixed = salaryFixed;
        this.paySchedule = "SEMANAL 2 SEX";
    }

    // add sell information in the variable "register"
    public void addSellRegister(String date, double sellValue) {
        this.registerAux.setvalue(date, sellValue);
        this.register.add(registerAux);
        valueSalary();

    }

    public List<infoSell> getRegister() {
        return this.register;

    }

    private void valueSalary() {
        double sumSell = 0.0;

        for (infoSell infoSell : register) {
            sumSell = +infoSell.getSellValue();
        }
        this.comission = sumSell * this.percentComission;

        this.totalSalary = this.comission + this.salaryFixed - this.taxService - this.taxSyndicate;
    }

    public void change(String name, String address, double salaryFixed, double percentComission) {
        super.set(name, address);
        this.salaryFixed = salaryFixed;
        this.percentComission = percentComission;
    }

    @Override
    public String toString() {
        return "Id: " + this.numIdentification +
                ", Name: " + this.name +
                ", Adress: " + this.address +
                ", Comission: " + this.comission +
                ", Total salary:" + this.totalSalary +
                ", Tax Service:" + this.taxService +
                ", Tax Syndicate:" + this.taxSyndicate +
                ", Pay Schedule:" + this.paySchedule;
    }

}