package com.booking.service;

import java.util.List;
import java.util.stream.IntStream;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("In Progress")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, 
                reservation.getReservationId(), 
                reservation.getCustomer().getName(), 
                printServices(reservation.getServices()), reservation.getReservationPrice(), 
                reservation.getEmployee().getName(), 
                reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> personList){
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang" );
        System.out.println("+========================================================================================+");
        IntStream.range(0, personList.size())
                .filter(index -> personList.get(index) instanceof Customer)
                .forEach(index -> {
                    Customer customer = (Customer) personList.get(index);
                    System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                            index + 1,
                            customer.getId(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getMember().getMembershipName(),
                            customer.getWallet()
                    );
                });
    }

    public static void showAllEmployee(List<Person> personList){
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman" );
        System.out.println("+========================================================================================+");
        IntStream.range(0, personList.size())
                .filter(index -> personList.get(index) instanceof Employee)
                .forEach(index -> {
                    Employee customer = (Employee) personList.get(index);
                    System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                            index + 1,
                            customer.getId(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getExperience()
                    );
                });
    }
    public static void showAllService(List<Service> servcesList){
        System.out.printf("| %-4s | %-4s | %-11s | %-15s |\n",
                "No.", "ID", "Nama", "Harga" );
        System.out.println("+========================================================================================+");
        IntStream.range(0, servcesList.size())
                .forEach(index -> {
                    Service service  = servcesList.get(index);
                    System.out.printf("| %-4s | %-4s | %-11s | %-15s |\n",
                            index + 1,
                            service.getServiceId(),
                            service.getServiceName(),
                            service.getPrice()
                    );
                });
    }
    public static void showHistoryReservation(List<Reservation> reservationList){
        double sum = reservationList.stream()
                    .filter(reservation -> reservation.getWorkstage().equalsIgnoreCase("finish"))
                    .mapToDouble(Reservation::getReservationPrice)
                    .sum();
                
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama service", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        IntStream.range(0, reservationList.size())
                .filter(index -> reservationList.get(index).getWorkstage().equalsIgnoreCase("finish")||reservationList.get(index).getWorkstage().equalsIgnoreCase("cancel"))
                .forEach(index -> {
                    Reservation reservation = (Reservation) reservationList.get(index);
                    System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n"
                            ,
                            index + 1,
                            reservation.getReservationId(),
                            reservation.getCustomer().getName(),
                            printServices(reservation.getServices()),
                            reservation.getReservationPrice(),
                            reservation.getEmployee().getName(),
                            reservation.getWorkstage()
                    );
                });
        System.out.printf("| %-4s | %-4s |\n","total keuntungan ",sum);
                
    }
    public static void showAllReservation(List<Reservation> reservationList){
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama service", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        IntStream.range(0, reservationList.size())
                .forEach(index -> {
                    Reservation reservation = (Reservation) reservationList.get(index);
                    System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n"
                            ,
                            index + 1,
                            reservation.getReservationId(),
                            reservation.getCustomer().getName(),
                            printServices(reservation.getServices()),
                            reservation.getReservationPrice(),
                            reservation.getEmployee().getName(),
                            reservation.getWorkstage()
                    );
                });
    }
}
