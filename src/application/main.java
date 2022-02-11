package application;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;



import java.time.DayOfWeek;

import java.util.Random;

import entities.employee;
import entities.typeOfEmployee.*;

public class main {
    
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        String path = "C:\\Users\\mathe\\Documents\\codigos java\\poo\\folhaDePagamento\\src\\application\\";
        List<employee> registerEmployee = new ArrayList<>();
        List<syndicate> registersyndicalist = new ArrayList<>();
        List<String> file = new ArrayList();
        List<String> list_type_PaySchedule = new ArrayList<String>();
        list_type_PaySchedule.add("MENSAL");
        list_type_PaySchedule.add("SEMANAL 2 SEX");
        list_type_PaySchedule.add("SEMANAL 1 SEX");
        List<PaySchedule> registerPaySchedule = new ArrayList<>();
        LocalDate today = LocalDate.now();
        

        // variable temporary
        int interfaceVariable = 0, numIdentification = 0, aux1, aux2, syndInt;
        String name, address, aux;
        char typeEmployee;
        employee hourly, comissioned, salaried;
        syndicate syndicalist;
        PaySchedule paySchedule;
        double aux4, priceHour, percentComission, salary, serviceTax, syndicateTax;
        boolean exit = true, aux_bool, aux_bool1, syndicate_bool;
        Set<Integer> numIdent = new HashSet<>();

        // ------------------------------------ INICIANDO EMPREGADOS COM
        // ARQUIVOS----------------------------
        file = File(path + "in_comissioned.txt");
        comissioned = new Comissioned((int) Integer.parseInt(file.get(0)), file.get(1), file.get(2),
                file.get(3).charAt(0), Boolean.valueOf(file.get(6)), (double) Integer.parseInt(file.get(4)),
                (double) Integer.parseInt(file.get(5)));
        registerEmployee.add(comissioned);
        paySchedule = new PaySchedule((int) Integer.parseInt(file.get(0)), "SEMANAL 2 SEX");
        registerPaySchedule.add(paySchedule);

        file = File(path + "in_hourly.txt");
        hourly = new Hourly((int) Integer.parseInt(file.get(0)), file.get(1), file.get(2), file.get(3).charAt(0),
                Boolean.valueOf(file.get(5)),
                Integer.parseInt(file.get(4)));
        registerEmployee.add(hourly);
        syndicalist = new syndicate(7, (int) Integer.parseInt(file.get(0)), file.get(1), 150.00);
        registersyndicalist.add(syndicalist);
        hourly.setTaxSyn(150.00);
        paySchedule = new PaySchedule((int) Integer.parseInt(file.get(0)), "SEMANAL 1 SEX");
        registerPaySchedule.add(paySchedule);

        file = File(path + "in_salaried.txt");
        salaried = new Salaried((int) Integer.parseInt(file.get(0)), file.get(1), file.get(2), file.get(3).charAt(0),
                Boolean.valueOf(file.get(5)),
                Integer.parseInt(file.get(4)));

        registerEmployee.add(salaried);
        salaried.setTaxSyn(50.00);
        syndicalist = new syndicate(8, (int) Integer.parseInt(file.get(0)), file.get(1), 50.00);
        registersyndicalist.add(syndicalist);
        paySchedule = new PaySchedule((int) Integer.parseInt(file.get(0)), "MENSAL");
        registerPaySchedule.add(paySchedule);

        // ------------------------------------------------------------------------------------------------------------------
        while (exit) {
            numIdentification = VerifyNumIdent(numIdent); // começa o loop sempre com um numero de idenficação diferente
            // interface
            System.out.println("--------------------Menu--------------------");
            System.out.println("1 - Add employee");// Adição de um empregado
            System.out.println("2 - Remove employee");// Remoção de um empregado
            System.out.println("3 - Launch a point card");
            System.out.println("4 - Launch a sell result");
            System.out.println("5 - Launch service tax");
            System.out.println("6 - Change employee details");
            System.out.println("7 - Run payroll");
            System.out.println("8 - Print all employee");
            System.out.println("9 - pay schedule ");
            System.out.println("10 - add pay schedule ");

            System.out.print("choose your option:");
            interfaceVariable = input.nextInt();
            while (interfaceVariable < 1 && interfaceVariable > 11) {
                System.out.println("number invalid");
            }

            switch (interfaceVariable) {
                case 1:// - Add employee
                       // read variable
                    aux = input.nextLine();
                    System.out.print("write the name:");
                    name = input.nextLine();
                    System.out.print("write the address:");
                    address = input.nextLine();
                    System.out.println("this employee it's associate of the syndicate? ");
                    System.out.println("0 - False");
                    System.out.println("1 - True");
                    syndInt = input.nextInt();
                    syndicate_bool = syndInt == 1 ? true : false;
                    System.out.print("choose the type of employee:");
                    System.out.println("Possibles values Are:\n C to comissed\n H to hourly\n S to salaried");
                    typeEmployee = input.next(".").charAt(0);

                    while (typeEmployee != 'C' && typeEmployee != 'H' && typeEmployee != 'S') {
                        System.out.println("Value of the typeEmployee it's wrong");
                        System.out.println("Possibles values Are:\n C to comissed\n H to hourly\n S to salaried");
                        typeEmployee = input.next(".").charAt(0);

                    }
                    if (typeEmployee == 'C') {
                        System.out.print("Percent comission:");
                        percentComission = input.nextDouble();
                        System.out.print("value salary fixed:");
                        salary = input.nextDouble();

                        comissioned = new Comissioned(numIdentification, name, address, typeEmployee, syndicate_bool,
                                percentComission,
                                salary);
                        registerEmployee.add(comissioned);// adiciona funcionario ao registro
                        paySchedule = new PaySchedule(numIdentification, "SEMANAL 2 SEX");
                        registerPaySchedule.add(paySchedule);

                    } else if (typeEmployee == 'H') {
                        System.out.println("Hour Price:");
                        priceHour = input.nextDouble();

                        hourly = new Hourly(numIdentification, name, address, typeEmployee, syndicate_bool, priceHour);
                        registerEmployee.add(hourly);
                        paySchedule = new PaySchedule(numIdentification, "SEMANAL 1 SEX");
                        registerPaySchedule.add(paySchedule);

                    } else {
                        System.out.println("Salary value:");
                        salary = input.nextDouble();

                        salaried = new Salaried(numIdentification, name, address, typeEmployee, syndicate_bool, salary);
                        registerEmployee.add(salaried);
                        paySchedule = new PaySchedule(numIdentification, "MENSAL");
                        registerPaySchedule.add(paySchedule);

                    }
                    if (syndicate_bool) {
                        System.out.println("value syndicate tax:");
                        syndicateTax = input.nextDouble();
                        aux1 = VerifyNumIdent(numIdent);
                        syndicalist = new syndicate(aux1, numIdentification, name, syndicateTax);
                        registersyndicalist.add(syndicalist);
                        for (employee i : registerEmployee) {
                            if (i.getNumIdentification() == numIdentification) {
                                i.setTaxSyn(syndicateTax);
                            }

                        }

                    }

                    break;

                case 2:// - Remove employee
                    System.out.println("Choose the id for exclude register:");
                    aux_bool = false;
                    aux1 = printAllEmployee(registerEmployee);
                    if (aux1 == 0) { // verifica se ha itens pra remover
                        break;
                    }
                    System.out.print("Id:");
                    aux1 = input.nextInt();
                    for (employee interablEmployee : registerEmployee) {
                        if (interablEmployee.getNumIdentification() == aux1) {
                            registerEmployee.remove(interablEmployee);
                            aux_bool = true;
                            break;
                        }

                    }
                    for (PaySchedule interable : registerPaySchedule) {
                        if (interable.getId() == aux1) {
                            registerPaySchedule.remove(interable);
                            break;
                        }

                    }
                    for (syndicate interaSyndicate : registersyndicalist) {
                        if (interaSyndicate.getOldId() == aux1) {
                            registersyndicalist.remove(interaSyndicate);
                            break;
                        }

                    }
                    if (!aux_bool) {
                        System.out.println("ID not exist");

                    }

                    break;
                case 3:// - Launch a point card
                    aux_bool = false;
                    for (employee interable : registerEmployee) {
                        if (interable.getTypeEmployee() == 'H') {
                            System.out.println(interable.toString());
                            aux_bool = true;
                        }
                    }
                    if (aux_bool) {
                        aux_bool1 = false;
                        System.out.println("choose employee hourly:");
                        System.out.print("\tID:");
                        aux1 = input.nextInt();
                        System.out.print("\thours worked:");
                        aux2 = input.nextInt();
                        for (employee interablEmployee : registerEmployee) {
                            if (interablEmployee.getNumIdentification() == aux1) {
                                ((Hourly) interablEmployee).launchCardPoint(aux2);
                                aux_bool1 = true;
                                break;
                            }

                        }
                        if (!aux_bool1) {
                            System.out.println("ID not exist");

                        }

                    } else {
                        System.out.println("Add employee of type HOURLY before");
                    }

                    break;

                case 4:// - Launch a sell result
                    aux_bool = false;
                    for (employee interable : registerEmployee) {
                        if (interable.getTypeEmployee() == 'C') {
                            System.out.println(interable.toString());
                            aux_bool = true;
                        }
                    }
                    if (aux_bool) {
                        aux_bool1 = false;
                        System.out.println("choose employee Comissioned:");
                        System.out.print("\tID:");
                        aux1 = input.nextInt();
                        System.out.println("Write sell date");
                        aux = getdate(input);
                        System.out.println("Sell value:");
                        aux4 = input.nextDouble();
                        while (!aux_bool1) {
                            for (employee interablEmployee : registerEmployee) {
                                if (interablEmployee.getNumIdentification() == aux1
                                        && interablEmployee.getTypeEmployee() == 'C') {
                                    ((Comissioned) interablEmployee).addSellRegister(aux, aux4);

                                    aux_bool1 = true;
                                    break;
                                }

                            }
                            if (!aux_bool1) {
                                System.out.println("ID not exist.Choose another id:");
                                System.out.print("\tID:");
                                aux1 = input.nextInt();

                            }
                        }

                    } else {
                        System.out.println("Add employee of type COMISSIONED before");
                    }

                    break;
                case 5:// - Launch service tax
                    for (syndicate interator : registersyndicalist) {
                        System.out.println(interator.toString());

                    }

                    System.out.println("ID Employee:");
                    aux1 = input.nextInt();
                    aux_bool = true;
                    for (syndicate interator : registersyndicalist) {
                        if (interator.getOldId() == aux1) {
                            System.out.println("Value tax service:");
                            aux4 = input.nextDouble();
                            interator.setServiceTax(aux4);
                            for (employee interatoreEmployee : registerEmployee) {
                                if (interatoreEmployee.getNumIdentification() == aux1) {
                                    interatoreEmployee.setTaxService(interator.getServiceTax());
                                }

                            }
                            aux_bool = false;
                            break;
                        }

                    }
                    if (aux_bool) {
                        System.out.println("ID NOT EXIST");
                        break;
                    }

                    break;
                case 6:// - Change employee details
                    aux1 = printAllEmployee(registerEmployee);
                    if (aux1 == 0) { // verifica se ha itens
                        System.out.println("There aren't itens");
                        break;
                    }
                    System.out.println("Write ID:");
                    aux1 = input.nextInt();
                    for (employee i : registerEmployee) {
                        if (i.getNumIdentification() == aux1) {
                            if (i.getTypeEmployee() == 'C') {
                                aux = input.nextLine();
                                System.out.print("write the name:");
                                name = input.nextLine();
                                System.out.print("write the address:");
                                address = input.nextLine();
                                System.out.print("Percent comission:");
                                percentComission = input.nextDouble();
                                System.out.print("value salary fixed:");
                                salary = input.nextDouble();
                                ((Comissioned) i).change(name, address, salary, percentComission);

                            } else if (i.getTypeEmployee() == 'H') {
                                aux = input.nextLine();
                                System.out.print("write the name:");
                                name = input.nextLine();
                                System.out.print("write the address:");
                                address = input.nextLine();
                                System.out.println("Hour Price:");
                                priceHour = input.nextDouble();

                                ((Hourly) i).change(name, address, priceHour);

                            } else {
                                aux = input.nextLine();
                                System.out.print("write the name:");
                                name = input.nextLine();
                                System.out.print("write the address:");
                                address = input.nextLine();
                                System.out.println("Salary value:");
                                salary = input.nextDouble();
                                ((Salaried) i).change(name, address, salary);

                            }
                        }
                    }
                    break;
                case 7:// - Run payroll
                    int weekOfMonth = Calendar.WEEK_OF_MONTH;
                    int dayOfMonth = today.getDayOfMonth();
                    int restDayOfMonth = today.lengthOfMonth() - dayOfMonth;
                    String dayOfWeek = getDayOfWeek(today.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")).toString());

                    // String[] stringSeparate = i.getPaySchedule().split(" ");
                    // int tam = stringSeparate.length;
                    // stringSeparate.hashCode();

                    
                    // MENSAL
                    if ((restDayOfMonth == 0 && (dayOfWeek != "SAB" && dayOfWeek != "DOM")
                            || (restDayOfMonth == 2 && dayOfWeek.equals("SEX")))) {

                        for (PaySchedule i : registerPaySchedule) {
                            if (i.getPaySchedule().equals("MENSAL")) {
                                System.out.println(i.id + " foi pago");
                            }

                        }

                    }
                    // MENSAL $

                    for (PaySchedule i : registerPaySchedule) {
                        if (dayOfWeek.equals("SEX")) {
                            if (i.paySchedule.equals("SEMANAL 1 SEX")
                                    || i.paySchedule.equals("MENSAL " + String.valueOf(dayOfMonth + 1))
                                    || i.paySchedule.equals("MENSAL " + String.valueOf(dayOfMonth + 2))
                                    || i.paySchedule.equals("MENSAL " + String.valueOf(dayOfMonth))) {
                                System.out.println(i.id + " foi  pago");

                            }
                            if ((weekOfMonth == 2 || weekOfMonth == 4) && i.paySchedule.equals("SEMANAL 2 SEX")) {
                                System.out.println(i.id + " foi pago");
                            }
                        } else if (dayOfWeek.equals("SAB") || dayOfWeek.equals("DOM")) {
                            System.out.println("there isn't payment on the non-commercial day");
                        } else {
                            if (i.paySchedule.equals("MENSAL " + String.valueOf(dayOfMonth))
                                    || i.paySchedule.equals("SEMANAL 1 " + String.valueOf(dayOfWeek))) {
                                System.out.println(i.id + " foi pago");

                            }
                            if ((weekOfMonth == 2 || weekOfMonth == 4)
                                    && i.paySchedule.equals("SEMANAL 2 " + String.valueOf(dayOfWeek))) {
                                System.out.println(i.id + " foi pago");
                            }

                        }
                    }

                    break;
                case 8:// - Print all employee
                    printAllEmployee(registerEmployee);

                    break;
                case 9:// - pay schedule

                    System.out.println("choose the id for associate the pay schedule:");
                    aux_bool = false;
                    aux1 = printAllEmployee(registerEmployee);
                    if (aux1 == 0) { // verifica se ha itens
                        break;
                    }
                    System.out.print("Id:");
                    aux1 = input.nextInt();
                    for (String i : list_type_PaySchedule) {
                        System.out.println(list_type_PaySchedule.indexOf(i) + " - " + i);
                    }
                    System.out.print("Chosse the type of pay schedule:");
                    aux2 = input.nextInt();
                    for (employee i : registerEmployee) {
                        if (i.getNumIdentification() == aux1) {
                            i.setPaySchedule(list_type_PaySchedule.get(aux2));
                        }

                    }
                    for (PaySchedule i : registerPaySchedule) {
                        if (i.getId() == aux1) {
                            i.setPaySchedule(list_type_PaySchedule.get(aux2));
                        }

                    }

                    break;
                case 10:// - add pay schedule

                    System.out.println("Options");
                    System.out.println("0 - MENSAL");
                    System.out.println("1 - SEMANAL 1");
                    System.out.println("2 - SEMANAL 2");
                    System.out.print("option:");
                    aux1 = input.nextInt();
                    aux = "0";
                    PaySchedule pay_schedulele = new PaySchedule();
                    aux = pay_schedulele.AddPaySchedule(aux1);
                    aux_bool = true;
                    for (String i : list_type_PaySchedule) {
                        if ((i.compareTo(aux)) == 0) {
                            aux_bool = false;
                        }

                    }
                    if (aux_bool) {
                        list_type_PaySchedule.add(aux.toUpperCase());
                    }

                    break;
                case 11://
                    exit = false;
                default:
                    break;
            }
            Thread.sleep(2000);

        }
        input.close();
    }

    // roda a folha de pagamento
  

    static class PaySchedule {
        private int id;
        private String paySchedule;

        
        public PaySchedule() {
            
            
        }
        
        public PaySchedule(int id, String paySchedule) {
            this.id = id;
            this.paySchedule = paySchedule;

        }

        public int getId() {
            return id;
        }

        public String getPaySchedule() {
            return paySchedule;
        }

        public void setPaySchedule(String paySchedule) {
            this.paySchedule = paySchedule;
        }
        
        

        public String AddPaySchedule(int aux1){

            
            int aux2;
            String aux ="erro";
            Scanner inputs = new Scanner(System.in);

            switch (aux1) {
                case 0:
                    System.out.println("Day of Month to receive:");
                    System.out.println("WORNING: choose numbers between 1 and 28");
                    System.out.print("option:");
                    aux2 = inputs.nextInt();
                    aux = "MENSAL " + String.valueOf(aux2);

                    break;

                case 1:
                    System.out.println("Day of Month to receive:");
                    System.out.print("SEG\nTER\nQUA\nQUI\nSEX\n");
                    System.out.print("option:");
                    inputs.nextLine();
                    aux = inputs.nextLine();
                    aux = "SEMANAL 1 " + aux;

                    break;

                case 2:
                    System.out.println("Day of Month to receive:");
                    System.out.print("segunda\nterça\nquarta\nquinta\nsexta\n");
                    System.out.print("option:");
                    inputs.nextLine();
                    aux = inputs.nextLine();
                    aux = "SEMANAL 2 " + aux;

                    break;

            }
           
            return aux;
            
        }
    }

    public static int printAllEmployee(List<employee> register) {
        if (register.size() == 0) {
            System.out.println("--------------------------------------------");
            System.out.println("list is empty:");
            return 0;
        }
        for (employee iterable_element : register) {
            System.out.println("--------------------------------------------");
            System.out.println(iterable_element.toString());

        }
        return 1;

    }

    public static int VerifyNumIdent(Set<Integer> numIdent) {
        Random gerador = new Random();
        int aux = 100001;
        int number = gerador.nextInt(aux);

        while (!numIdent.add(number) && numIdent.size() < aux) {
            number = gerador.nextInt(aux);

        }
        return number;

    }

    public static List File(String path) {
        List<String> file = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                file.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        }

        return file;
    }

    private static String getdate(final Scanner scan) {
        String month = null, day = null, year = null, date = null;
        while (true) {

            scan.nextLine();
            System.out.print("\tMonth: ");
            month = scan.nextLine();
            if (!(Integer.parseInt(month) <= 12 && Integer.parseInt(month) > 1)) {
                System.out.println("Incorrect month value");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("\tDay: ");
            day = scan.nextLine();
            if (!(Integer.parseInt(day) <= 31 && Integer.parseInt(day) >= 1)) {
                System.out.println("Incorrect day value");
                continue;
            } else if (Integer.parseInt(month) == 2 && Integer.parseInt(day) > 28) {
                System.out.println("wrong value for the month of february");
                continue;
            }
            break;

        }
        while (true) {
            System.out.print("\tYear: ");
            year = scan.nextLine();
            if (year.length() != 4) {
                System.out.println("value for year not accepted");
                continue;
            }
            break;

        }
        date = day + "/" + month + "/" + year;
        return date;

    }

    public static String getDayOfWeek(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        DayOfWeek dow = DayOfWeek.from(parser.parse(data));
        return dow.getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase();
    }

}
