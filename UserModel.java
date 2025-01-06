package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import Data.User;

public class UserModel extends AbstractModel {
    public Collection<User> users = new ArrayList<User>();
    private static UserModel userModelInstance = null;
    private static List<String> userFieldsNamesLists = Arrays.asList("name", "email", "username", "password",
            "userType");

    public static UserModel getInstance() {
        if (userModelInstance == null) {
            userModelInstance = new UserModel();
        }
        return userModelInstance;
    }

    private UserModel() {
    }

    @Override
    public boolean add(Object value) {
        if (value instanceof User) {
            try {
                synchronized (this) {
                    AbstractModel.loadDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                    users.add((User) value);
                    AbstractModel.updateDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                }
                firePropertyChange("add User", null, value);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(Object oldValue, Object newValue) {
        if (newValue instanceof User && oldValue instanceof User) {
            try {
                synchronized (this) {
                    AbstractModel.loadDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                    users.remove(oldValue);
                    users.add((User) newValue);
                    AbstractModel.updateDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                }
                firePropertyChange("update User", oldValue, newValue);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean load() {
        try {
            synchronized (this) {
                AbstractModel.loadDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Object value) {
        if (value instanceof User) {
            try {
                synchronized (this) {
                    AbstractModel.loadDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                    users.remove(value);
                    AbstractModel.updateDB(User.class, users, userFieldsNamesLists, "../../DataBase/Users.csv");
                }
                firePropertyChange("delete User", value, null);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

}
