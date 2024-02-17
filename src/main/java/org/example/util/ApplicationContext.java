    package org.example.util;

    import org.example.repository.ProductRepo;
    import org.example.repository.UserRepo;
    import org.example.service.ProductService;
    import org.example.service.UserService;

    import java.sql.SQLException;
    import java.util.Scanner;

    public final class ApplicationContext {

        private static ApplicationContext instance;

        private DatabaseUtil databaseUtil;
        private Scanner intSc = new Scanner(System.in);
        private UserRepo userRepository;
        private ProductRepo productRepository;
        private UserService userService;
        private ProductService productService;

        private ApplicationContext() {
        }

        public static ApplicationContext getInstance() {
            if (instance == null) {
                instance = new ApplicationContext();
            }
            return instance;
        }

        public DatabaseUtil getDatabaseUtil() throws SQLException, ClassNotFoundException {
            if (databaseUtil == null){
                databaseUtil = new DatabaseUtil();
            }
            return databaseUtil;
        }

        public UserRepo getUserRepository() {
            if (userRepository == null){
                userRepository = new UserRepo(databaseUtil.getConnection());
            }
            return userRepository;
        }

        public ProductRepo getProductRepository() {
            if (productRepository == null)
                productRepository = new ProductRepo(databaseUtil.getConnection());
            return productRepository;
        }


        public UserService getUserService() {
            if (userService == null)
                userService = new UserService();
            return userService;
        }

        public ProductService getProductService() {
            if (productService == null)
                productService = new ProductService();
            return productService;
        }

        public Scanner getIntSc() {
            return intSc;
        }

    }
