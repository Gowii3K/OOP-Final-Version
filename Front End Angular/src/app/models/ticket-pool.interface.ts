export interface TicketPool {
    totalTickets: number;
    maximumTicketCapacity: number;
    availableTickets: Ticket[];
    currentTicket: number;
}

export interface Customer {
    customerId: number;
    boughtTickets: number[];
}

export interface Vendor {
    vendorId: number;
    soldTickets: number[];
}


export interface Ticket{
    id:number;
}