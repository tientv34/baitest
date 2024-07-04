package tientv.assingmentsummer.exception;

//Custom exception
public class CustomExceptionHandler extends Exception {
    private String code;
    private String message;

    public CustomExceptionHandler(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomExceptionHandler(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setError(String message) {
        this.message = message;
    }
}
