package ui;

import database.InMemoryDatabase;
import system.Category;
import system.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProductAccountingSystem {
    private static InMemoryDatabase database = new InMemoryDatabase();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Добро пожаловать в систему учета продуктов.");

        boolean appStatus = true;

        while(appStatus) {
            System.out.println("Выберите любой из пунктов меню:");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Найти продукт по ID");
            System.out.println("3. Вывести весь список продуктов");
            System.out.println("4. Удалить продукт по ID");
            System.out.println("5. Вывести список продуктов в определенной категории");
            System.out.println("6. Изменить информацию о продукте");
            System.out.println("7. Установить скидку на товары определенной категории");
            System.out.println("8. Завершить работу приложения");
            System.out.print("Выберите действие: ");

            int actionID = scanner.nextInt();

            switch(actionID) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    findProductByID();
                    break;
                case 3:
                    showAllProducts();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    getProductsInCategory();
                    break;
                case 6:
                    changeProductInformation();
                    break;
                case 7:
                    setDiscountInCategory();
                    break;
                case 8:
                    appStatus = false;
                    break;
            }
        }
    }

    private static void setDiscountInCategory() {
        byte _categoryID = 0;
        while(!(_categoryID > 0 && _categoryID < 3)) {
            System.out.println("Список категорий:");
            System.out.println("1. Фрукты");
            System.out.println("2. Овощи");
            System.out.print("Выберите категорию продуктов: ");
            _categoryID = scanner.nextByte();
        }

        List<Product> productsByCategory = null;

        switch(_categoryID) {
            case 1:
                productsByCategory = database.getProductsByCategory(Category.FRUIT);
                break;
            case 2:
                productsByCategory = database.getProductsByCategory(Category.VEGETABLE);
                break;
            default:
                System.out.println("Вы указали некорректную категорию!");
                return;
        }

        System.out.println("Укажите скидку:");
        BigDecimal discount = scanner.nextBigDecimal();

        for(Product prod : productsByCategory) {
            prod.setProductDiscount(discount);
        }

        System.out.println("Вы успешно установили скидку на товары категории #" + _categoryID);
    }

    private static void changeProductInformation() {
        System.out.print("Введите пожалуйста ID продукта: ");
        long productID = scanner.nextLong();

        Product productFound = null;
        byte actionID = -1;

        while(productFound == null || !(actionID >= 1 && actionID <= 3)) {
            productFound = database.getProductByID(productID);
            System.out.println(productFound.toString());

            System.out.println("Выберите пункт, который желаете изменить:");
            System.out.println("1. Название продукта");
            System.out.println("2. Стоимость продукта");
            System.out.println("3. Описание продукта");
            System.out.println("4. Скидку на продукт");

            actionID = scanner.nextByte();

            switch(actionID) {
                case 1:
                    System.out.println("Введите новое значение:");
                    productFound.setProductName(scanner.next());
                    break;
                case 2:
                    System.out.println("Введите новое значение:");
                    productFound.setProductPrice(scanner.nextBigDecimal());
                    break;
                case 3:
                    System.out.println("Введите новое значение:");
                    productFound.setProductDescription(scanner.next());
                    break;
                case 4:
                    System.out.println("Введите новое значение:");
                    productFound.setProductDiscount(scanner.nextBigDecimal());
                    break;
            }
        }
    }

    private static void getProductsInCategory() {
        byte _categoryID = 0;
        while(!(_categoryID > 0 && _categoryID < 3)) {
            System.out.println("Список категорий:");
            System.out.println("1. Фрукты");
            System.out.println("2. Овощи");
            System.out.print("Выберите категорию продукта: ");
            _categoryID = scanner.nextByte();
        }

        List<Product> productsByCategory = null;

        switch(_categoryID) {
            case 1:
                productsByCategory = database.getProductsByCategory(Category.FRUIT);
                break;
            case 2:
                productsByCategory = database.getProductsByCategory(Category.VEGETABLE);
                break;
        }

        for(Product prod : productsByCategory) {
            System.out.println(prod.toString());
        }
    }

    private static void removeProduct() {
        long productId = scanner.nextLong();
        database.removeProductByID(productId);
        System.out.println("[Информация] Вы успешно удалили продукт (id: " + productId + ").");
    }

    private static void showAllProducts() {
        List<Product> productList = database.getAllProducts();

        for(Product prod : productList) {
            System.out.println(prod.toString());
        }
    }

    private static void findProductByID() {
        System.out.print("Введите пожалуйста ID продукта: ");
        long productID = scanner.nextLong();

        Product productFound = database.getProductByID(productID);
        System.out.println(productFound.toString());
    }

    private static void addProduct() {
        System.out.print("Введите название продукта (пример: Яблоко): ");
        String productName = scanner.next();

        System.out.print("Укажите стоимость продукта (пример: 100.00): ");
        BigDecimal productCost = scanner.nextBigDecimal();

        byte categoryID = 0;
        Category productCategory = null;

        while(!(categoryID > 0 && categoryID < 3)) {
            System.out.println("Список категорий:");
            System.out.println("1. Фрукты");
            System.out.println("2. Овощи");
            System.out.print("Выберите категорию продукта: ");
            categoryID = scanner.nextByte();
        }

        switch(categoryID) {
            case 1:
                productCategory = Category.FRUIT;
                break;
            case 2:
                productCategory = Category.VEGETABLE;
                break;
        }

        System.out.print("Укажите скидку в % (если нет, напишите: 0): ");
        BigDecimal productDiscount = new BigDecimal(scanner.nextByte() / 100);

        System.out.print("Укажите описание продукта (если нет, напишите: \"-\"): ");
        String productDescription = scanner.next();

        Product product = new Product(productName, productCost, productCategory, productDiscount, productDescription);
        database.add(product);

        System.out.println("[Информация] Продукт был успешно добавлен!");
        System.out.println("ID продукта: " + product.getProductID());
    }
}
