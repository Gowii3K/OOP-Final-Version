package com.oop.cwk.Service;

import com.oop.cwk.Model.Customer;
import com.oop.cwk.Model.Ticket;
import com.oop.cwk.Model.TicketPool;
import com.oop.cwk.Model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Represents Service operations related to the ticketPool
 */
@Service
public class TicketPoolService {

    TicketPool ticketPool;

    /**
     * Constructs a ticketPoolService by autowiring the ticketPool
     * @param ticketPool=ticketPool for the event
     */
    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
        System.out.println("Ticket Pool Service Created");
    }

    //logger to log transactions related to the ticketPool
    private static final Logger logger = Logger.getLogger(TicketPoolService.class.getName());
    //list of log messages
    private  final List<String> logs = new ArrayList<>();

    //locks to protect critical section
    private final Lock lock = new ReentrantLock();
    //condition to check if the ticketPool is not full
    private final Condition notFull = lock.newCondition();
    //condition to check if the ticketPool is not empty
    private final Condition notEmpty = lock.newCondition();
    //condition to check if the ticketPool is not stopped
    private final Condition notStopped = lock.newCondition();
    //volatile boolean to represent if the operations are stopped
    private static volatile Boolean isStopped = false;

    //getters
    public static Boolean getIsStopped() {
        return isStopped;
    }

    public List<String> getLogs() {
        return logs;
    }

    //resume ticketPool operations
    public void resume(){
        //acquire the lock before attempting to stop operations
        lock.lock();
        try {
            isStopped = false;
            notStopped.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    //stop ticketPool operations
    public void stopTicketPool() {
        System.out.println("Stopping Ticket Pool");
        isStopped = true;
    }

    //check if ticketPool is stopped
    public void checkStopped() throws InterruptedException {
        while (isStopped){
            notStopped.await();
            ticketPool.getAvailableTickets().clear();
        }
    }


    /**
     * Adds ticket to the ticketPool
     * @param vendor=instance of the vendor that tries to access the method
     */
    public void addTicket( Vendor vendor) {
        //lock the critical section
        lock.lock();
        int vendorId= vendor.getVendorId();
        try {
            //keep running until there are tickets left to be added
            while (ticketPool.getTotalTickets()!=0) {
                //check to see whether user has stopped the ticketPool
                checkStopped();
                //wait if ticketPool has reached maximum capacity
                if (ticketPool.getAvailableTickets().size() == ticketPool.getMaximumTicketCapacity()) {
                    notFull.await();
                }
                //add a ticket to the ticketPool
                else {
                    Ticket ticket = new Ticket(ticketPool.getCurrentTicket());
                    ticketPool.getAvailableTickets().offer(ticket);
                    vendor.ticketSold(ticketPool.getCurrentTicket());
                    logger.info("Ticket No "+ticket.getId()+ "Added By Vendor" + vendorId+ " Current size is "+ticketPool.getAvailableTickets().size());
                    logs.add("Ticket No "+ticket.getId()+ "Added By Vendor" + vendorId);
                    //decrease total tickets
                    ticketPool.decrementTotalTickets();
                    //increase current tickets to tract current ticket number being added
                    ticketPool.incrementCurrentTicket();
                    //signal to waiting threads that ticketPool is no longer empty
                    notEmpty.signalAll();
                    return;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("Error occurred when adding tickets");
            }
        finally {
            lock.unlock();
        }
    }


    /**
     * Removes ticket from the TicketPool
     * @param customer=instance of the customer trying to purchase a ticket
     */
    public void removeTicket( Customer customer)  {
        //lock the critical section
        lock.lock();
        int customerId = customer.getCustomerId();
        try {
            //keep running until there are no more tickets in the ticket pool and no more tickets to be added to the pool
            while (ticketPool.getTotalTickets() != 0 || !ticketPool.getAvailableTickets().isEmpty()) {
                //check to see whether user has stopped the ticketPool
                checkStopped();
                //wait if ticketPool is empty but there are still tickets to be added
                if (ticketPool.getAvailableTickets().isEmpty() && ticketPool.getTotalTickets() !=0) {
                    notEmpty.await();
                }
                //remove a ticket from the ticketPool
                else {
                    Ticket ticket = ticketPool.getAvailableTickets().poll();
                    customer.ticketBought(ticket.getId());
                    logger.info("Ticket No "+ticket.getId()+ "Purchased By Customer" + customerId+ " Current size is "+ticketPool.getAvailableTickets().size());
                    logs.add("Ticket No "+ticket.getId()+ "Purchased By Customer" + customerId);
                    //signal to waiting threads that the ticketPool is no longer full
                    notFull.signalAll();
                    return;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error occurred when removing tickets");
        }
        finally {
            lock.unlock();
        }
    }
}



