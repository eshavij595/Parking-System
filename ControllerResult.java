package Controller;

public class ControllerResult {
    public enum Status {
        OK,
        INVALID_CREDENTIAL,
        USERNAME_EXISTED,
        INVALID_EMAIL,
        ADD_FAILED,
        UPDATE_FAILED,
        DELETE_FAILED,
        UNAUTHORIZED,
        USER_IS_NOT_MANAGER
    }

    public final Object result;
    public final Status status;

    public ControllerResult(Object result, Status status) {
        this.result = result;
        this.status = status;
    }
}
