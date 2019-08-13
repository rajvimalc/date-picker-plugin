package dev.vcs.jenkins.datepicker;

/**
 * @author Vimalraj Chandra Sekaran (vchandr4)
 * Email: vchandr4@mazdausa.com
 * Created on: 8/12/2019 7:49 PM
 */
public class PickerException extends RuntimeException {

    public PickerException() {
        super();
    }

    public PickerException(String message) {
        super(message);
    }

    public PickerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PickerException(Throwable cause) {
        super(cause);
    }
}
