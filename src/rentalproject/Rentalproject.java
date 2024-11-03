/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import static rentalproject.Functions.PickPlace;
import static rentalproject.Functions.dateMaker;
import static rentalproject.Functions.diffCalculator;

public class Rentalproject {

    /*Εδω γινεται η αρχικοποιηση ολων των λιστών.
    Η bikeList αποθηκευει μεσα της object τυπου bike
    carList αποθηκευει μεσα της object τυπου car
    locationList αποθηκευει μεσα της object τυπου location
    reservationList αποθηκευει μεσα της object τυπου reservation
     */
 /*Με την ενοια static εννοουμε οτι η λιστα ειναι διαθέσιμη οπουδηποτε στην κλαση*/
    public static ArrayList<Bike> bikeList = new ArrayList<>();
    public static ArrayList<Car> carList = new ArrayList<>();
    public static ArrayList<Locations> locationList = new ArrayList<>();
    public static ArrayList<Reservation> reservationList = new ArrayList<>();

    public static void main(String[] args) {

        /*Εδω περναμε τις πληροφοριες απο το αρχειο μεσα στις λιστες. 
        Δημιουργουμε τα νεα αντικειμενα και τα περναμε επιτόπου στην λιστα με το .add*/
        locationList.add(new Locations(1, "ΚΑΤΑΣΤΗΜΑ", "Ανθέων 32,ΤΚ23456"));
        locationList.add(new Locations(2, "ΚΑΤΑΣΤΗΜΑ", "Σωκράτους 58,ΤΚ13426"));
        locationList.add(new Locations(3, "ΣΗΜΕΙΟ", "Παπαδιαμαντοπούλου 20,ΤΚ23872"));
        locationList.add(new Locations(4, "ΣΗΜΕΙΟ", "Παπάγου 3,ΤΚ33421"));

        bikeList.add(new Bike("ΒΕΝΖΙΝΗ", 105, 15, 998, 3.1));
        bikeList.add(new Bike("ΗΛΕΚΤΡΙΚΟ", 147, 15, 982, 2.4));
        bikeList.add(new Bike("ΒΕΝΖΙΝΗ", 55, 14, 115, 1.4));
        bikeList.add(new Bike("ΒΕΝΖΙΝΗ", 85, 16, 478, 2.9));
        bikeList.add(new Bike("ΗΛΕΚΤΡΙΚΟ", 180, 14, 950, 2.8));

        carList.add(new Car(5, 4, 5, "ΒΕΝΖΙΝΗ", 156, 17, 1499, 3.5));
        carList.add(new Car(2, 2, 3, "ΒΕΝΖΙΝΗ", 89, 15, 1125, 2.6));
        carList.add(new Car(7, 4, 8, "ΒΕΝΖΙΝΗ", 200, 18, 2600, 4.7));
        System.out.println("ΚΑΛΩΣ ΟΡΙΣΑΤΕ ΣΤΗΝ ΕΦΑΡΜΟΓΗ ΕΝΟΙΚΙΑΣΗΣ ΟΧΗΜΑΤΩΝ *JAVIS rent a motor*\n\n");

        Scanner input = new Scanner(System.in);
        String menunumber = "0";
        while (menunumber != "4") {//To while δεν θα τελειωσει αν θα πληκτρολογιθει 4 (δεν προκειται ομως να συμβει εφοσον στο case 4 διακοπτεται το προγραμμα(exit))
            System.out.println("1.  ΔΗΜΙΟΥΡΓΙΑ ΚΡΑΤΗΣΗΣ \n2.  ΑΛΛΑΓΗ ΚΡΑΤΗΣΗΣ\n3.  ΕΜΦΑΝΙΣΗ ΚΡΑΤΗΣΕΩΝ\n4.  ΕΞΟΔΟΣ\n");

            /* Εδω δεχομαστε το input του χρηστη*/
            menunumber = input.nextLine();
            /*Σε περιπτωση που ειναι 1 τοτε μπαινει στο case 1 οπου υλοποιειται η δημιουργια κρατησης , παρομοια και στο 2 , στο 3 κτλ*/
            switch (menunumber) {
                case "1":
                    /*Εδω ενα reservation επιστρεφεται απο το makeReservation της κλασης functions,και αποθηκέυεται στην λιστα reservationList*/
                    try{//το try catch ειναι για να μην επιτρεψουμε ενα σφαλμα να διακοψει το προγραμμα.Αν το makeReservation γυρισει null τοτε πιανει το ερορ
                    reservationList.add(Functions.makeReservation(bikeList, carList, locationList));
                    }catch(Exception e){
                        
                    }
                    //με το break βγαινει απο το switch.
                    break;
                case "2":
                    //Εδω γινεται η αλλαγη κρατησης
                    System.out.printf("\n Πληκτρολογίστε τον αριθμο κρατησής σας\t");
                    
                    menunumber = input.nextLine();
                    //Περναμε το κωδικο που μας εδωσε ο χρηστης στην μεθοδο updatereservation,η οποια υλοποιει οποιοδηποτε αλλαγη
                    Updatereservation(menunumber);


                    break;
                case "3":
                    
                    //Eδω εμφανιζονται ολα τα reservation απο το reservationList.
                    for (int i = 0; i < reservationList.size(); i++) {
                        System.out.println(reservationList.get(i).toString());
                    }
                    break;
                case "4":
                    return;
            }

        }
    }

    public static void Updatereservation(String code) {
        Scanner input = new Scanner(System.in);
        String currentDate, newpickupdateString, newdeliverydateString;
        String pickupPlace, deliveryplace;
        Double priceperhour, price;
        
        //Ο DateTimeFormatter οριζει το πως θα ειναι το string της ημερομηνίας μας. Στην περιπτωση μας ειναι dd-MM-yyyy HH:mm
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime newpickupdate, pickupdate, deliverydate, newdeliverydate, currentdifdate;
        long diff;
        //currentDate = myDateObj.format(myFormatObj);
        int exists = -1, res = -1;
        String choice = "0";
        LocalDateTime myDateObj = LocalDateTime.now();
        //Παιρνω την σημερινή ημερομηνια με την localdatetime.now , την μετατρεπω σε string τυπου dd-MM-yyyy HH:mm
        currentDate = myDateObj.format(myFormatObj);
        //Μετα αυτο το String το ξανα μετατρεπω σε localdatetime object . Αυτο το εκανα επειδη μετα θα χρησιμοποιηθει για αφαιρεση.
        currentdifdate = Functions.StringtoDate(currentDate);//το stringtodate μετατρεπει ενα string ημερομηνιας σε object localdateTime
        for (int i = 0; i < reservationList.size(); i++) {//εδω ψαχνει να βρει το reservation με κωδικο code
            if (reservationList.get(i).getCode().equals(code)) {//αν βρεθει τοτε το exist γινεται 1 και το res=i (νουμερο του object)
                exists = 1;
                res = i;
            }
        }
        if (exists == 1) {//αν το exist ειναι 1 τοτε μπορει να κανει τις αλλαγες παραγγελιας.

            while (choice != "3") {
                System.out.printf("1. Αλλαγη στοιχείων παραλαβης/παραδοσης\n2. Aκυρωση παραγγελίας\n3. Επιστροφη στο κεντρικο μενου\n");
                choice = input.nextLine();
                switch (choice) {
                    case "1":
                        pickupdate = Functions.StringtoDate(reservationList.get(res).getPickupDate());//περνουμε την ηεμρα παραλαβης
                        System.out.println(pickupdate);
                        diff = Functions.diffCalculator(currentdifdate, pickupdate);//βρισκουμε την διαφορα σε ωρες της τωρινης ημερομηνιας με της παραλαβης
                        System.out.println(diff);
                        if (diff < 25) {//αν η διαφορα ειναι κατω απο 25 ωρες τοτε η αλλαγη δεν ειναι δυνατη
                            System.out.printf("\n\nΗ αλλαγη στοιχείων παραλαβής δεν ειναι δυνατη %d ωρες πριν την ωρα παραλαβης\n", diff);
                            return;
                        } else {
                            System.out.printf("Νεα ωρα παραλαβής\n");
                            newpickupdate = Functions.dateMaker(myDateObj);//το datemaker φτιαχνει μια ημερομηνia string
                            newpickupdateString = newpickupdate.format(myFormatObj);

                            System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΛΑΒΗΣ\n");
                            pickupPlace = Functions.PickPlace(locationList);

                            System.out.printf("Νεα ωρα παραδοσης\n");
                            newdeliverydate = Functions.dateMaker(newpickupdate);
                            newdeliverydateString = newpickupdate.format(myFormatObj);

                            System.out.printf("ΒΑΛΤΕ TΟΠΟΘΕΣΙΑ ΠΑΡΑΔΟΣΗΣ\n");
                            deliveryplace = Functions.PickPlace(locationList);

                            priceperhour = reservationList.get(res).getPriceperhour();//επειδη αλλαζει η ημερομηνια αλλζει και το κοστος.
                            price = priceperhour * Functions.diffCalculator(newpickupdate, newdeliverydate);

                            reservationList.get(res).setDeliveryDate(newdeliverydateString);
                            reservationList.get(res).setDeliveryplace(deliveryplace);
                            reservationList.get(res).setPickupDate(newpickupdateString);
                            reservationList.get(res).setPickupPlace(pickupPlace);
                            reservationList.get(res).setPrice(price);
                            System.out.printf("\n\nΝεα τιμη = %.1f$ Ημερομηνία παραλαβής %s\nΤοποθεσια παραλαβής :%s\nΗμερομηνια παραδοσης:%s\nΤοποθεσια παραδοσης:%s\n", price, newpickupdateString, pickupPlace, newdeliverydateString, deliveryplace);
                        }

                        return;
                    case "2":
                        pickupdate = Functions.StringtoDate(reservationList.get(res).getPickupDate());
                        System.out.println(pickupdate);
                        diff = Functions.diffCalculator(currentdifdate, pickupdate);
                        System.out.println(diff);

                        if (diff <= 48) {//αν η διαφορα ωρας ειναι <48 τοτε η ακυρωση εχει χρεωση 0.3 της τιμης
                            
                            double fine = reservationList.get(res).getPrice() * 0.3;
                            System.out.println("Λογω ακύρωσης κρατησης εντος 48 ωρών απο την παραλαβη υπαρχει χρέωση 30% της αρχικής.\nΠροστιμο :" + fine);

                        }
                        System.out.printf("Κρατηση με κωδικο %s ακυρώθηκε \n", reservationList.get(res).getCode());
                        reservationList.remove(res);//αφερουμε το αντικειμενο απο την λιστα 

                        return;

                    case "3":
                        return;
                }
            }

        } else {
            System.out.println("Η κρατηση αυτη δεν υπαρχει!");

        }

    }
}
