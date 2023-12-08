package com.booking.service;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.service.PrintService;
import com.booking.service.ValidationService;

public class ReservationService {
    public static void createReservation(List<Person> personList,String regexPatternCustomer,String regexPatternEmployee,String regexPatternService,String regexPatternYesno,List<Service>serviceList,List<Reservation>reservationsList){

        List <Service> reservationServiceList = new ArrayList<>();
        boolean addListCondition = true;

        PrintService.showAllCustomer(personList);
        String customerID = ValidationService.inputStringFunction(regexPatternCustomer,"Silahkan Masukkan Customer ID ", "Bukan Customer ID");
        Person customerById = getCustomerByCustomerId(personList, customerID);

        PrintService.showAllEmployee(personList);
        String employeeID = ValidationService.inputStringFunction(regexPatternEmployee,"Silahkan Masukkan Employee Id ", "Bukan Employee ID");
        Person employeeById = getEmployeeByEmployeeid(personList, employeeID);
        PrintService.showAllService(serviceList);

        if(customerById != null && employeeById != null){
            while(addListCondition){
            String serviceId = ValidationService.inputStringFunction(regexPatternService,"Silahkan Masukkan service Id ", "Bukan Services ID");
            Service service = getServiceByServiceId(serviceList, serviceId);
            if(service != null){
                if(!reservationServiceList.contains(service)){
                reservationServiceList.add(service);
                }else{
                System.out.println("service sudah ada ");
                }
            }else{
                System.out.println("servis tidak ditemukan");
            }
            //melakukan cek ke dalam service list 
            String pilihan = ValidationService.inputStringFunction(regexPatternYesno, "apakah ada lagi ya/tidak ", "pilihan ya/tidak");
            if (pilihan.equalsIgnoreCase("tidak")){
                addListCondition = false;
            }
        }
        }
        
        Reservation customerOrder = new Reservation("Rsv-0" + (reservationsList.size() + 1),(Customer) customerById,(Employee) employeeById,reservationServiceList,"In Progress");

        System.out.println("Reservation object created: " + customerOrder.getReservationId());

        reservationsList.add(customerOrder);


    }

    public static Person getCustomerByCustomerId(List<Person> personList,String customerId){
        Person getCustomer = personList.stream()
            .filter(person ->
                    person.getId().equalsIgnoreCase(customerId))
            .findFirst()
            .orElse(null);

    if (getCustomer == null) {
        System.out.println("Customer not found for ID: " + customerId);
    }
    return getCustomer;
    }
    public static Person getEmployeeByEmployeeid(List<Person> personList,String employeeId){
        Person getEmployee = personList.stream()
                            .filter(person -> person.getId()
                            .equalsIgnoreCase(employeeId)&& person instanceof Employee)
                            .findFirst()
                            .orElse(null);
        if (getEmployee == null) {
        System.out.println("Employee not found for ID: " + employeeId);
    }
        return getEmployee;
    }
    public static Service getServiceByServiceId(List<Service>serviceList,String serviceId){
        Service service = serviceList.stream()
                        .filter(serviceData -> serviceData.getServiceId().equalsIgnoreCase(serviceId)).
                        findAny().
                        orElse(null);
        
        if (service == null) {
        System.out.println("Service not found for ID: " + service);
        }
        return service;
        
    }
    public static Reservation getReservationByReservationId(List<Reservation>reservationsList,String reservationId){
        Reservation customerOrder = reservationsList.stream()
                        .filter(serviceData -> serviceData.getReservationId().equalsIgnoreCase(reservationId)).
                        findAny().
                        orElse(null);

        if (customerOrder == null) {
        System.out.println("Service not found for ID: " + reservationId);
        }
        return customerOrder;
    }

    public static void editReservationWorkstage(List<Reservation>listReservations,String regexPatternReservation,String regexPatterFinish){

        PrintService.showRecentReservation(listReservations);
        String setWorkstageStatus = ValidationService.inputStringFunction(regexPatterFinish, "input status : ", "bukan status untuk workstage, status hanya Finish Dan Cancel");
        String reservationId = ValidationService.inputStringFunction(regexPatternReservation, "masukan reservation id ", "bukan reservation id");
        Reservation updateReservation =  getReservationByReservationId(listReservations, reservationId);
        updateReservation.setWorkstage(setWorkstageStatus);
        PrintService.showAllReservation(listReservations);
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
