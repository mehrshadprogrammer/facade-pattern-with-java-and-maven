package org.example;

import org.example.entity.Product;
import org.example.entity.User;
import org.example.util.ApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext.getInstance().getDatabaseUtil().getConnection();
        setUserForRegister();
        findByUsernameAndPassword();
        showAllProduct();
        insertProduct();
        ApplicationContext.getInstance().getDatabaseUtil().getConnection().close();
    }

    private static void insertProduct() throws SQLException {
        String name = ApplicationContext.getInstance().getIntSc().nextLine();
        int count = ApplicationContext.getInstance().getIntSc().nextInt();
        double price = ApplicationContext.getInstance().getIntSc().nextDouble();
        ApplicationContext.getInstance().getProductRepository().insertProduct(new Product(name, price, count));
    }


    private static void setUserForRegister() throws SQLException {
        String username = ApplicationContext.getInstance().getIntSc().nextLine();
        String password = ApplicationContext.getInstance().getIntSc().nextLine();
        User user = new User(username, password);
        ApplicationContext.getInstance().getUserRepository().insertUser(
                user
        );
    }
    private static List<Product> showAllProduct() throws SQLException {
        return ApplicationContext.getInstance().getProductRepository().showAllProducts();
    }

    private static void findByUsernameAndPassword() throws SQLException {
        String username = ApplicationContext.getInstance().getIntSc().nextLine();
        String password =ApplicationContext.getInstance().getIntSc().nextLine();
        boolean checkedExitOrNotExitWithUsernameAndPassword = ApplicationContext.getInstance().getUserRepository().checkUser(username, password);
        if (checkedExitOrNotExitWithUsernameAndPassword){
            System.out.println("exist");
        }else {
            System.out.println("oh username and password is wrong");
        }
    }
}