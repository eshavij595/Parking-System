package Controller;

import java.util.Random;
import java.util.regex.Pattern;

import Data.User;
import Data.User.UserType;
import Model.UserModel;
import Controller.ControllerResult.Status;

public class AuthenticationController extends AbstractController {
    private static AuthenticationController authenticationControllerInstance = null;

    public static AuthenticationController getInstance() {
        if (authenticationControllerInstance == null) {
            authenticationControllerInstance = new AuthenticationController();
        }
        return authenticationControllerInstance;
    }

    private AuthenticationController() {
        listen(UserModel.getInstance());
    }

    public ControllerResult login(String username, String password) {
        User user;
        UserModel userModel = UserModel.getInstance();
        synchronized (userModel) {
            userModel.load();
            user = userModel.users.stream().filter((e) -> (e.username.equals(username) && e.password.equals(password)))
                    .findAny().orElse(null);
        }
        if (user == null) {
            return new ControllerResult(user, Status.INVALID_CREDENTIAL);
        } else {
            userContext = user;
            return new ControllerResult(user, Status.OK);
        }
    }

    public ControllerResult register(String name, String email, String username, String password, UserType userType) {
        Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        UserModel userModel = UserModel.getInstance();
        if (emailPattern.matcher(email).find()) {
            synchronized (userModel) {
                userModel.load();
                if (userModel.users.stream().anyMatch((e) -> (e.username.equals(username) || e.email.equals(email)))) {
                    return new ControllerResult(null, Status.USERNAME_EXISTED);
                }
                User u = new User(userModel.users.size(), name, email, username, password, userType);
                if (userModel.add(u)) {
                    return new ControllerResult(u, Status.OK);
                }
            }
            return new ControllerResult(null, Status.ADD_FAILED);
        }
        return new ControllerResult(null, Status.INVALID_EMAIL);
    }

    public ControllerResult createManager(String name, String email, String username) {
        if (userContext.userType.equals(UserType.SUPER_MANAGER)) {
            Random r = new Random();
            char[] pw = new char[8];
            for (int k = 0; k < 8; ++k) {
                pw[k] = (char) ('!' + r.nextInt(94));
            }
            UserModel userModel = UserModel.getInstance();
            synchronized (userModel) {
                userModel.load();
                User u = new User(userModel.users.size(), name, email, username, new String(pw), UserType.MANAGER);
                if (userModel.users.stream().anyMatch((e) -> (e.username.equals(username) || e.email.equals(email)))) {
                    return new ControllerResult(null, Status.USERNAME_EXISTED);
                }
                if (userModel.add(u)) {
                    return new ControllerResult(u, Status.OK);
                }
            }
            return new ControllerResult(null, Status.ADD_FAILED);
        }
        return new ControllerResult(null, Status.UNAUTHORIZED);
    }

    public ControllerResult deleteManager(User manager) {
        if (userContext.userType.equals(UserType.SUPER_MANAGER)) {
            if (manager.userType.equals(UserType.MANAGER)) {
                if (UserModel.getInstance().delete(manager)) {
                    return new ControllerResult(null, Status.OK);
                }
                return new ControllerResult(null, Status.DELETE_FAILED);
            }
            return new ControllerResult(null, Status.USER_IS_NOT_MANAGER);
        }
        return new ControllerResult(null, Status.UNAUTHORIZED);
    }

    public ControllerResult getUserProfile() {
        if (userContext == null) {
            return new ControllerResult(null, Status.UNAUTHORIZED);
        }
        return new ControllerResult(userContext, Status.OK);
    }
}
