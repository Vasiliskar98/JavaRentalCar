/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import static rentalproject.Rentalproject.carList;

public class Functions {

    static Scanner input = new Scanner(System.in);

    public static Reservation makeReservation(ArrayList<Bike> bikes, ArrayList<Car> cars, ArrayList<Locations> places) {
        String number = "0";
        String choice = "0";

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        int i, choicenumeric, choicenumericplace;
        int code;
        double price, priceperhour;
        String currentDate, pickupDate, deliveryDate;
        String vehicleType, pickupPlace, deliveryplace, idnumber, drivinglicense, creditcard, paychoice, paymethod;
        int age;
        LocalDateTime myDateObj, pickDateObj, deliveryDateObj;
        Reservation reservation;
        while (number != "10") {
            System.out.println("1.  ΑΥΤΟΚΙΝΗΤΟ \n2.  ΜΗΧΑΝΗ\n3.  ΕΞΟΔΟΣ\n");
            number = input.nextLine();

            switch (number) {
                case "1":

                    do {
                        vehicleType = "CAR";
                        for (i = 0; i < cars.size(); i++) {
                            System.out.printf("CAR  %d  :Price:%.1f$ (hourly)\tFuel:%s\tEngineVolume:%d\tHorsepower:%d\tLuggage capacity:%d\tSeats:%d\tDoors:%d\tWheel size:%d\n", i + 1, cars.get(i).getPrice(), cars.get(i).getFuel(), cars.get(i).getEnginevolume(), cars.get(i).getHorsepower(), cars.get(i).getLuggagecapacity(), cars.get(i).getSeats(), cars.get(i).getDoors(), cars.get(i).getWheelsize());
                        }
                        choice = input.nextLine();
                        try {
                            choicenumeric = Integer.parseInt(choice);
                        } catch (Exception e) {
                            choicenumeric = -1;
                        }
                    } while (choicenumeric <= 0 || choicenumeric > cars.size());

                    System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΛΑΒΗΣ\n");
                    pickupPlace = PickPlace(places);//εδω διαλεγει το μερος,επιστρεφει στρινγκ διευθυνσης

                    System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΔΟΣΗΣ\n");
                    deliveryplace = PickPlace(places);

                    myDateObj = LocalDateTime.now();
                    currentDate = myDateObj.format(myFormatObj);
                    System.out.println(currentDate);
                    System.out.println("ΒΑΛΤΕ ΗΜΕΡΟΜΗΝΙΑ ΠΑΡΑΛΑΒΗΣ");

                    pickDateObj = dateMaker(myDateObj);

                    pickupDate = pickDateObj.format(myFormatObj);

                    System.out.println("\nΒΑΛΤΕ ΗΜΕΡΟΜΗΝΙΑ ΕΠΙΣΤΡΟΦΗΣ");
                    deliveryDateObj = dateMaker(pickDateObj);
                    deliveryDate = deliveryDateObj.format(myFormatObj);

                    price = cars.get(choicenumeric - 1).getPrice() * diffCalculator(pickDateObj, deliveryDateObj);
                    priceperhour = cars.get(choicenumeric - 1).getPrice();
                    System.out.println("\nΒΑΛΤΕ AΡΙΘΜΟ ΤΑΥΤΟΤΗΤΑΣ");
                    idnumber = input.nextLine();
                    System.out.println("\nΒΑΛΤΕ AΡΙΘΜΟ ΔΙΠΛΩΜΑΤΟΣ");
                    drivinglicense = input.nextLine();

                    do {//εδω ελεγχουμε αν η ηλικια ειναι απο 15 μεχρι 150.αν ειναι διαφορετικη τιμη δεν φευγει απο το while
                        System.out.println("\nΒΑΛΤΕ ΗΛΙΚΙΑ");
                        try {//το try catch ειναι για να μην επιτρεψουμε ενα σφαλμα να διακοψει το προγραμμα.Αν το input του χρηστη δεν ειναι string τοτε θα βγαλει error 
                            age = Integer.parseInt(input.nextLine());
                        } catch (Exception e) {//To error θα μας παει εδω.
                            age = -1;
                        }
                    } while (age < 15 || age > 150);
                    System.out.println("\nΠΛΗΡΩΜΗ ΜΕ:\n1.ΚΑΡΤΑ\n2.ΜΕΤΡΗΤΑ\n");
                    paychoice = input.nextLine();

                    if (paychoice.equals("1")) {
                        paymethod = "CREDIT CARD";
                        System.out.println("\nΠΑΡΑΚΑΛΩ ΒΑΛΤΕ ΤΟΝ ΑΡΙΘΜΟ ΚΑΡΤΑΣ ΣΑΣ:");
                        creditcard = input.nextLine();
                    } else {
                        paymethod = "CASH";
                        creditcard = null;
                    }
                    code = ThreadLocalRandom.current().nextInt(100000, 999999);//με rand βαζουμε εναν κωδικο απο 100000 εως 999999
                    System.out.printf("ΚΩΔΙΚΟΣ:%d\nΣΥΝΟΛΙΚΗ ΤΙΜΗ:%f\nΤΡΟΠΟΣ ΠΛΗΡΩΜΗΣ:%s\nΜΕΡΟΣ ΩΡΑ ΠΑΡΑΛΑΒΗΣ %s\t%s\nΜΕΡΟΣ ΩΡΑ ΕΠΙΣΤΡΟΦΗΣ %s\t%s\n"
                            + "\nΣΤΟΙΧΕΙΑ ΟΧΗΜΑΤΟΣ\n"
                            + "%s\tFuel:%s\tEngineVolume:%d\tHorsepower:%d\tLuggage capacity:%d\tSeats:%d\tDoors:%d\tWheel size:%d\n"
                            + "\nΣΤΟΙΧΕΙΑ ΠΕΛΑΤΗ\n"
                            + "ΤΑΥΤΟΤΗΤΑ:%s\nΔΙΠΛΩΜΑ:%s\nΗΛΙΚΙΑ:%d\n",
                            code, price, paymethod, pickupPlace, pickupDate, deliveryplace, deliveryDate,
                            vehicleType, cars.get(choicenumeric - 1).getFuel(), cars.get(choicenumeric - 1).getEnginevolume(), cars.get(choicenumeric - 1).getHorsepower(), cars.get(choicenumeric - 1).getLuggagecapacity(), cars.get(choicenumeric - 1).getSeats(), cars.get(choicenumeric - 1).getDoors(), cars.get(choicenumeric - 1).getWheelsize(),
                            idnumber, drivinglicense, age);

                    reservation = new Reservation(Integer.toString(code),price, priceperhour, vehicleType, currentDate, pickupDate, deliveryDate, pickupPlace, deliveryplace, idnumber, drivinglicense, age, creditcard, paymethod);
                    return reservation;

                case "2"://Το ιδιο με το 1 αλλα μηχανακια
                    do {
                        vehicleType = "BIKE";
                        for (i = 0; i < bikes.size(); i++) {
                            System.out.printf("BIKE  %d  :Price:%.1f$ (hourly)\tFuel:%s\tEngineVolume:%d\tHorsepower:%d\tWheel size:%d\n", i + 1, bikes.get(i).getPrice(), bikes.get(i).getFuel(), bikes.get(i).getEnginevolume(), bikes.get(i).getHorsepower(), bikes.get(i).getWheelsize());
                        }
                        choice = input.nextLine();
                        try {
                            choicenumeric = Integer.parseInt(choice);
                        } catch (Exception e) {
                            choicenumeric = -1;
                        }
                    } while (choicenumeric <= 0 || choicenumeric > bikes.size());

                    System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΛΑΒΗΣ\n");
                    pickupPlace = PickPlace(places);

                    System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΔΟΣΗΣ\n");
                    deliveryplace = PickPlace(places);

                    myDateObj = LocalDateTime.now();
                    currentDate = myDateObj.format(myFormatObj);
                    System.out.println(currentDate);
                    System.out.println("ΒΑΛΤΕ ΗΜΕΡΟΜΗΝΙΑ ΠΑΡΑΛΑΒΗΣ");

                    pickDateObj = dateMaker(myDateObj);

                    pickupDate = pickDateObj.format(myFormatObj);

                    System.out.println("\nΒΑΛΤΕ ΗΜΕΡΟΜΗΝΙΑ ΕΠΙΣΤΡΟΦΗΣ");
                    deliveryDateObj = dateMaker(pickDateObj);
                    deliveryDate = deliveryDateObj.format(myFormatObj);

                    price = bikes.get(choicenumeric - 1).getPrice() * diffCalculator(pickDateObj, deliveryDateObj);
                    priceperhour = cars.get(choicenumeric - 1).getPrice();
                    System.out.println("\nΒΑΛΤΕ AΡΙΘΜΟ ΤΑΥΤΟΤΗΤΑΣ");
                    idnumber = input.nextLine();
                    System.out.println("\nΒΑΛΤΕ AΡΙΘΜΟ ΔΙΠΛΩΜΑΤΟΣ");
                    drivinglicense = input.nextLine();
                    do {
                        System.out.println("\nΒΑΛΤΕ ΗΛΙΚΙΑ");
                        try {
                            age = Integer.parseInt(input.nextLine());
                        } catch (Exception e) {
                            age = -1;
                        }
                    } while (age < 15 || age > 150);

                    System.out.println("\nΠΛΗΡΩΜΗ ΜΕ:\n1.ΚΑΡΤΑ\n2.ΜΕΤΡΗΤΑ\n");
                    paychoice = input.nextLine();

                    if (paychoice.equals("1")) {
                        paymethod = "CREDIT CARD";
                        System.out.println("\nΠΑΡΑΚΑΛΩ ΒΑΛΤΕ ΤΟΝ ΑΡΙΘΜΟ ΚΑΡΤΑΣ ΣΑΣ:");
                        creditcard = input.nextLine();
                    } else {
                        paymethod = "CASH";
                        creditcard = null;
                    }
                    code = ThreadLocalRandom.current().nextInt(100000, 999999);
                    System.out.printf("ΚΩΔΙΚΟΣ:%d\nΣΥΝΟΛΙΚΗ ΤΙΜΗ:%.1f$\nΤΡΟΠΟΣ ΠΛΗΡΩΜΗΣ:%s\nΜΕΡΟΣ ΩΡΑ ΠΑΡΑΛΑΒΗΣ %s\t%s\nΜΕΡΟΣ ΩΡΑ ΕΠΙΣΤΡΟΦΗΣ %s\t%s\n"
                            + "\nΣΤΟΙΧΕΙΑ ΟΧΗΜΑΤΟΣ\n"
                            + "%s\tFuel:%s\tEngineVolume:%d\tHorsepower:%d\tWheel size:%d\n"
                            + "\nΣΤΟΙΧΕΙΑ ΠΕΛΑΤΗ\n"
                            + "ΤΑΥΤΟΤΗΤΑ:%s\nΔΙΠΛΩΜΑ:%s\nΗΛΙΚΙΑ:%d\n",
                            code, price, paymethod, pickupPlace, pickupDate, deliveryplace, deliveryDate,
                            vehicleType, bikes.get(choicenumeric - 1).getFuel(), bikes.get(choicenumeric - 1).getEnginevolume(), bikes.get(choicenumeric - 1).getHorsepower(), bikes.get(choicenumeric - 1).getWheelsize(),
                            idnumber, drivinglicense, age);

                    reservation = new Reservation(Integer.toString(code), price, priceperhour, vehicleType, currentDate, pickupDate, deliveryDate, pickupPlace, deliveryplace, idnumber, drivinglicense, age, creditcard, paymethod);
                    return reservation;

                case "3":
                    return null;
            }
        }
        return null;
    }
//επιστρεφει το μερος.
    public static String PickPlace(ArrayList<Locations> places) {
        //System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ");
        int i, choicenumericplace;
        String choice;
        do {
            for (i = 0; i < places.size(); i++) {
                System.out.printf("\n%d.%s\n", i + 1, places.get(i).getAddress());
            }
            choice = input.nextLine();
            try {//αν ο χρηστης δεν εχει βαλει αριθμο τοτε περνει τιμη -1 και ξανα μπαινει στην επαναληψη
                choicenumericplace = Integer.parseInt(choice);
            } catch (Exception e) {
                choicenumericplace = -1;
            }
        } while (choicenumericplace <= 0 || choicenumericplace > places.size());
        return places.get(choicenumericplace - 1).getAddress();
    }

    //μετατρεπει το String ημερομηνιας σε αντικειμενο LocalDateTime και το επιστρεφει.
    public static LocalDateTime StringtoDate(String date) {//Βοηθεια απο stackoverflow!
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime dateobj = LocalDateTime.parse(date, myFormatObj);

        return dateobj;

    }

    //εδω φτιαχνουμε την ημερομηνία.περνει σαν ορισμα μια ημερομηνια ετσι ωστε να ελεγθει αν ειναι μετα απο αυτην την ημερομηνια.
    public static LocalDateTime dateMaker(LocalDateTime date2) {
        String dt;
        int monthnum, daynum, yearnum, hourr, minute;
        LocalDateTime date1 = null;
        do {
            do {
                System.out.println("ΒΑΛΤΕ ΜΗΝΑ(1-12)");
                dt = input.nextLine();
                try {
                    monthnum = Integer.parseInt(dt);
                } catch (Exception e) {
                    monthnum = -1;
                }
            } while (monthnum < 0 || monthnum > 13);

            do {
                System.out.println("ΒΑΛΤΕ ΜΕΡΑ(1-30)");
                dt = input.nextLine();
                try {
                    daynum = Integer.parseInt(dt);
                } catch (Exception e) {
                    daynum = -1;
                }
            } while (daynum < 0 || daynum > 31);

            do {
                System.out.println("ΒΑΛΤΕ ΧΡΟΝΟ(2020 και μετα)");
                dt = input.nextLine();
                try {
                    yearnum = Integer.parseInt(dt);
                } catch (Exception e) {
                    yearnum = -1;
                }
            } while (yearnum < 2019 || yearnum > 2030);
            do {
                System.out.println("ΒΑΛΤΕ ΩΡΑ(0-24)");
                dt = input.nextLine();
                try {
                    hourr = Integer.parseInt(dt);
                } catch (Exception e) {
                    hourr = -1;
                }
            } while (hourr <= -1 || hourr > 25);

            do {
                System.out.println("ΒΑΛΤΕ ΛΕΠΤΑ(0-60)");
                dt = input.nextLine();
                try {
                    minute = Integer.parseInt(dt);
                } catch (Exception e) {
                    minute = -1;
                }
            } while (minute <= -1 || minute > 61);
            String finalformat, day, month, year, hour, mnt;
            if (daynum < 10) {//Ελεγχουμε αν οι τιμες ειναι μικροτερες του 10 τοτε να μπει ενα 0 μπροστα ωστε να γραφτει σωστα στο LocalDateTime
                day = "0" + daynum;
            } else {
                day = "" + daynum;
            }
            if (monthnum < 10) {
                month = "0" + monthnum;
            } else {
                month = "" + monthnum;
            }

            if (yearnum < 10) {
                year = "0" + yearnum;
            } else {
                year = "" + yearnum;
            }

            if (hourr < 10) {
                hour = "0" + hourr;
            } else {
                hour = "" + hourr;
            }

            if (minute < 10) {
                mnt = "0" + minute;
            } else {
                mnt = "" + minute;
            }

            finalformat = "" + day + "-" + month + "-" + year + " " + hour + ":" + mnt;

            // System.out.println(finalformat);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            date1 = LocalDateTime.parse(finalformat, myFormatObj);

            System.out.println("date 1:" + date2 + "\tdate 2:" + date1);
            if (date2.isAfter(date1)) {//ελεγχουμε αν η ημερομηνια που φτιαξαμε ειναι πιο μετα απο του ορισματος(δηλαδη να ειναι μεγαλυτερη απο πχ την σημερινη ή την παραλαβης
                System.out.println("Η ημερομηνία που βάλατε ειναι λαθος.Παρακαλω επαναλαβετε την διαδικασία");
            }
        } while (date2.isAfter(date1));//αν δεν ειναι τοτε η διαδικασια γινεται απ την αρχη

        return date1;
        // System.out.println(finalformat+"\t"+date1);  
    }

    public static long diffCalculator(LocalDateTime pickDateObj, LocalDateTime deliveryDateObj) {//Εδω βρισκεται η διαφορα δυο LocalDateTime
        //βοηθεια απο stackoverflow

        System.out.println(pickDateObj + " - " + deliveryDateObj);
        Duration diff = Duration.between(pickDateObj, deliveryDateObj);
        long diffHours = diff.toHours();//περνουμε την διαφορα σε ωρες! και την επιστρεφουμε
        System.out.println("ΔΙΑΦΟΡΑ " + diffHours + "ΩΡΕΣ");
        return diffHours;
    }

}
