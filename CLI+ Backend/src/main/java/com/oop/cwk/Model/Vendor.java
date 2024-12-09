package com.oop.cwk.Model;

import com.oop.cwk.Service.TicketPoolService;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vendor that adds tickets to the ticketPool
 */
public  class Vendor implements Runnable{

    //unique id for each vendor
    private final int vendorId;
    //time interval that a vendor should wait before attempting to release another ticket
    private final int releaseInterval;
    //service to handle ticketPool business logic
    private final TicketPoolService ticketPoolService;
    //common ticketPool shared among vendors and customers
    private final TicketPool ticketPool;
    //track the tickets put on sale by the vendor
    private final List<Integer> soldTickets=new ArrayList<>();


    //getter for vendorId
    public int getVendorId() {
        return vendorId;
    }
    //getter for soldTickets
    public List<Integer> getSoldTickets() {
        return soldTickets;
    }


    public Vendor(int releaseInterval, TicketPool ticketPool, int vendorId, TicketPoolService ticketPoolService){
        this.releaseInterval=releaseInterval;
        this.ticketPool=ticketPool;
        this.vendorId=vendorId;
        this.ticketPoolService = ticketPoolService;
    }

    /**
     * Adds id of ticket that was put on sale to the soldTickets array
     * @param ticketId=id of ticket that the vendor sold
     */
    public void ticketSold(Integer ticketId){
        soldTickets.add(ticketId);
    }


    /**
     * resets vendor object to initial state
     */
    public void resetVendor(){
        soldTickets.clear();
    }

    /**
     * Run Vendor thread to put tickets up on sale to the ticketPool as long as there are tickets left to be added
     */
    @Override
    public void run() {
        while (true){
            ticketPoolService.addTicket(this);
            try {
                Thread.sleep(releaseInterval* 1000L);
            } catch (InterruptedException e) {
                System.out.println("Error occurred when adding Tickets");
            }
            if (ticketPool.getTotalTickets() == 0) {
                System.out.println("All tickets added, Vendor " + vendorId + "Finished execution.. Terminating ");
                break;
            }
        }
    }


}