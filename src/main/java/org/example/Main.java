package org.example;

import com.mysql.cj.log.Log;
import org.example.entity.Product;
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

        ApplicationContext.getInstance().getProductRepository().insertProduct(
                ApplicationContext.getInstance()
                        .getProductService().create(name, price, count)
        );
    }


    private static void setUserForRegister() throws SQLException {
        System.out.println("enter username");
        String username = ApplicationContext.getInstance().getIntSc().nextLine();
        System.out.println("enter password");
        String password = ApplicationContext.getInstance().getIntSc().nextLine();
        ApplicationContext.getInstance().getUserRepository().insertUser(
                ApplicationContext.getInstance().getUserService()
                        .createUser(username, password)
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
            System.out.println("oh username and password is wrong!!!");
        }
    }
}