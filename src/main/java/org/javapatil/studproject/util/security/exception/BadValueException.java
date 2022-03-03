/**
 * *********************************************
 * Date         Name                         
 * *********************************************
 * 11-Dec-2019   Sachin Patil aka JavaPatil  
 */

package org.javapatil.studproject.util.security.exception;

public class BadValueException extends RuntimeException {

    private String fieldName;
    private String reason;

    public BadValueException(String message, String fieldName, String reason) {
        super(message);
        this.fieldName = fieldName;
        this.reason = reason;
    }

    public BadValueException(String fieldName, String reason) {
        super("Bad Value");
        this.fieldName = fieldName;
        this.reason = reason;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getReason() {
        return reason;
    }
}
