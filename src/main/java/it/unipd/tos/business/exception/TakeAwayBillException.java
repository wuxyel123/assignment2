////////////////////////////////////////////////////////////////////
// Alessandro Discalzi 1169739
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception {
    public TakeAwayBillException(final String errMsg){
        super(errMsg);
    }
}