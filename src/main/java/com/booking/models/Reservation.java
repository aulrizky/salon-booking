package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    private static final double SILVER_MEMBERSHIP = 0.05;
    private static final double GOLD_MEMBERSHIP = 0.10;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        calculateReservationPrice();
        this.workstage = workstage;
    };
    private double calculateTotalPrice(){
        // double sum = services.stream()
        //             .mapToDouble(Service::getPrice)
        //             .sum();
        // return sum;
        double total = 0;
        for (Service service : services) {
            total = total + service.getPrice();
            
        }
        return total;
    }
    private double discountCustomerMember(){
        String membership = customer.getMember().getMembershipName();
        if(membership.equalsIgnoreCase("silver")){
            return SILVER_MEMBERSHIP;
        }else if (membership.equalsIgnoreCase("gold")){
            return GOLD_MEMBERSHIP;
        }else{
            return 0;
        }
    }
    private void calculateReservationPrice(){
        double totalPrice = calculateTotalPrice();
        double discountMember =  discountCustomerMember();
        double discountPrice = totalPrice - (totalPrice * discountMember);
        setReservationPrice(discountPrice);
    }
}
