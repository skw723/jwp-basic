package next.model;

public class Result {
    private boolean isSuccess;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {

        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
