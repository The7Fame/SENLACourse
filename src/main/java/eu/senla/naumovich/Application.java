package eu.senla.naumovich;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.senla.naumovich.configuration.ApplicationConfig;
import eu.senla.naumovich.controllers.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {
    public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        AddressController addressController = context.getBean(AddressController.class);
        addressRequest(addressController);
        AuthorController authorController = context.getBean(AuthorController.class);
        authorRequest(authorController);
        BookController bookController = context.getBean(BookController.class);
        bookRequest(bookController);
        CartController cartController = context.getBean(CartController.class);
        cartRequest(cartController);
        GenreController genreController = context.getBean(GenreController.class);
        genreRequest(genreController);
        OrderController orderController = context.getBean(OrderController.class);
        orderRequest(orderController);
        PaymentController paymentController = context.getBean(PaymentController.class);
        paymentRequest(paymentController);
        PrivilegeController privilegeController = context.getBean(PrivilegeController.class);
        privilegeRequest(privilegeController);
        PromotionController promotionController = context.getBean(PromotionController.class);
        promotionRequest(promotionController);
        PublisherController publisherController = context.getBean(PublisherController.class);
        publisherRequest(publisherController);
        ReviewController reviewController = context.getBean(ReviewController.class);
        reviewRequest(reviewController);
        RoleController roleController = context.getBean(RoleController.class);
        roleRequest(roleController);
        UserController userController = context.getBean(UserController.class);
        userRequest(userController);
    }
    public static void addressRequest(AddressController addressController) throws JsonProcessingException {
        String addressFirst = "{\"id\": 1, " +
                "\"street\": \"street1\", " +
                "\"index\": 123456}";
        String addressSecond = "{\"id\": 2, " +
                "\"street\": \"street2\", " +
                "\"index\": 123456}";
        String addressToUpdate = "{\"id\": 2, " +
                "\"street\": \"street2\", " +
                "\"index\": 123457}";
        System.out.println("Insert 2 records");
        addressController.create(addressFirst);
        addressController.create(addressSecond);
        System.out.println("All addresses");
        System.out.println(addressController.getAll());
        System.out.println("Get the first address");
        System.out.println(addressController.getById(addressFirst));
        System.out.println("Delete the first record");
        addressController.delete(addressFirst);
        System.out.println("Update index in the second record");
        System.out.println(addressController.update(addressToUpdate));
        System.out.println("All records");
        System.out.println(addressController.getAll());
    }
    public static void authorRequest(AuthorController authorController) throws JsonProcessingException {
        String authorFirst = "{\"id\": 1," +
                " \"name\": \"name1\"," +
                " \"surname\": \"surname1\"}";
        String authorSecond = "{\"id\": 2, " +
                "\"name\": \"name2\", " +
                "\"surname\": \"surname2\"}";
        String authorToUpdate = "{\"id\": 2, " +
                "\"name\": \"name2\", " +
                "\"surname\": \"updateSurname\"}";
        System.out.println("Insert 2 records");
        authorController.create(authorFirst);
        authorController.create(authorSecond);
        System.out.println("All authors");
        System.out.println(authorController.getAll());
        System.out.println("Get the first author");
        System.out.println(authorController.getById(authorFirst));
        System.out.println("Delete the first record");
        authorController.delete(authorFirst);
        System.out.println("Update surname in the second record");
        System.out.println(authorController.update(authorToUpdate));
        System.out.println("All records");
        System.out.println(authorController.getAll());
    }
    public static void bookRequest(BookController bookController) throws JsonProcessingException {
        String bookFirst = "{\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {"
                + "\"id\": 1,"
                + "\"genreName\": \"genre1\"},"
                + "\"publisher\": {"
                + "\"id\": 1,"
                + "\"publisherName\": \"publisher1\","
                + "\"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "}";
        String bookSecond = "{\"id\": 2,"
                + "\"title\": \"book2\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231231,"
                + "\"genre\": {"
                + "\"id\": 2,"
                + "\"genreName\": \"genre2\"},"
                + "\"publisher\": {"
                + "\"id\": 2,"
                + "\"publisherName\": \"publisher2\","
                + "\"address\": {\"id\": 2, \"street\": \"street2\", \"index\": 123456}}"
                + "}";
        String bookToUpdate = "{\"id\": 2,"
                + "\"title\": \"book2\","
                + "\"price\": 12.00,"
                + "\"isbn\": 1231231231231,"
                + "\"genre\": {"
                + "\"id\": 2,"
                + "\"genreName\": \"genre2\"},"
                + "\"publisher\": {"
                + "\"id\": 2,"
                + "\"publisherName\": \"publisher2\","
                + "\"address\": {\"id\": 2, \"street\": \"street2\", \"index\": 123456}}"
                + "}";
        System.out.println("Insert 2 records");
        bookController.create(bookFirst);
        bookController.create(bookSecond);
        System.out.println("All books");
        System.out.println(bookController.getAll());
        System.out.println("Get the first book");
        System.out.println(bookController.getById(bookFirst));
        System.out.println("Delete the first record");
        bookController.delete(bookFirst);
        System.out.println("Update surname in the second record");
        System.out.println(bookController.update(bookToUpdate));
        System.out.println("All records");
        System.out.println(bookController.getAll());
    }
    public static void cartRequest(CartController cartController) throws JsonProcessingException {
        String cartFirst = "{\"id\": 1,"
                + "\"book\":{\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {"
                + "\"id\": 1,"
                + "\"genreName\": \"genre1\"},"
                + "\"publisher\": {"
                + "\"id\": 1,"
                + "\"publisherName\": \"publisher1\","
                + "\"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "},"
                + "\"user\": {"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}}";
        String cartSecond = "{\"id\": 2,"
                + "\"book\":{\"id\": 2,"
                + "\"title\": \"book2\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231231,"
                + "\"genre\": {"
                + "\"id\": 2,"
                + "\"genreName\": \"genre2\"},"
                + "\"publisher\": {"
                + "\"id\": 2,"
                + "\"publisherName\": \"publisher2\","
                + "\"address\": {\"id\": 2, \"street\": \"street2\", \"index\": 123456}}"
                + "},"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        String cartToUpdate = "{\"id\": 2,"
                + "\"book\":{\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {"
                + "\"id\": 1,"
                + "\"genreName\": \"genre1\"},"
                + "\"publisher\": {"
                + "\"id\": 1,"
                + "\"publisherName\": \"publisher1\","
                + "\"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "},"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        System.out.println("Insert 2 records");
        cartController.create(cartFirst);
        cartController.create(cartSecond);
        System.out.println("All carts");
        System.out.println(cartController.getAll());
        System.out.println("Get the first сart");
        System.out.println(cartController.getById(cartFirst));
        System.out.println("Delete the first record");
        cartController.delete(cartFirst);
        System.out.println("Update index in the second record");
        System.out.println(cartController.update(cartToUpdate));
        System.out.println("All records");
        System.out.println(cartController.getAll());
    }
    public static void genreRequest(GenreController genreController) throws JsonProcessingException {
        String genreFirst = "{\"id\": 1, " +
                "\"genreName\": \"genre1\"" +
                "}";
        String genreSecond = "{\"id\": 2, " +
                "\"genreName\": \"genre2\"" +
                "}";
        String genreToUpdate = "{\"id\": 2, " +
                "\"genreName\": \"genreUpdateName\"" +
                "}";
        System.out.println("Insert 2 records");
        genreController.create(genreFirst);
        genreController.create(genreSecond);
        System.out.println("All genres");
        System.out.println(genreController.getAll());
        System.out.println("Get the first genre");
        System.out.println(genreController.getById(genreFirst));
        System.out.println("Delete the first record");
        genreController.delete(genreFirst);
        System.out.println("Update genreName in the second record");
        System.out.println(genreController.update(genreToUpdate));
        System.out.println("All records");
        System.out.println(genreController.getAll());
    }
    public static void orderRequest(OrderController orderController) throws JsonProcessingException {
        String orderFirst = "{\"id\": 1,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"user\": {"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}}";
        String orderSecond = "{\"id\": 2,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        String orderToUpdate = "{\"id\": 2,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 30.00,"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        System.out.println("Insert 2 records");
        orderController.create(orderFirst);
        orderController.create(orderSecond);
        System.out.println("All orders");
        System.out.println(orderController.getAll());
        System.out.println("Get the first order");
        System.out.println(orderController.getById(orderFirst));
        System.out.println("Delete the first record");
        orderController.delete(orderFirst);
        System.out.println("Update totalPrice in the second record");
        System.out.println(orderController.update(orderToUpdate));
        System.out.println("All records");
        System.out.println(orderController.getAll());
    }
    public static void paymentRequest(PaymentController paymentController) throws JsonProcessingException {
        String paymentFirst = "{\"id\": 1,"
                + "\"status\": \"false\","
                + "\"paymentDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"order\": {\"id\": 1,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"user\": {"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}},"
                + "\"user\": {"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}}";
        String paymentSecond = "{\"id\": 2,"
                + "\"status\": \"false\","
                + "\"paymentDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"order\": {\"id\": 2,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}},"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role2\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        String paymentToUpdate = "{\"id\": 2,"
                + "\"status\": \"true\","
                + "\"paymentDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"order\": {\"id\": 2,"
                + "\"orderDate\": \"01.04.2024\","
                + "\"totalPrice\": 20.00,"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role1\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}},"
                + "\"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role2\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}}";
        System.out.println("Insert 2 records");
        paymentController.create(paymentFirst);
        paymentController.create(paymentSecond);
        System.out.println("All payments");
        System.out.println(paymentController.getAll());
        System.out.println("Get the first payment");
        System.out.println(paymentController.getById(paymentFirst));
        System.out.println("Delete the first record");
        paymentController.delete(paymentFirst);
        System.out.println("Update status in the second record");
        System.out.println(paymentController.update(paymentToUpdate));
        System.out.println("All records");
        System.out.println(paymentController.getAll());
    }
    public static void privilegeRequest(PrivilegeController privilegeController) throws JsonProcessingException {
        String privilegeFirst = "{\"id\": 1, " +
                "\"privilegeName\": \"privilege1\"" +
                "}";
        String privilegeSecond = "{\"id\": 2, " +
                "\"privilegeName\": \"privilege2\"" +
                "}";
        String privilegeToUpdate = "{\"id\": 2, " +
                "\"privilegeName\": \"privilegeUpdateName\"" +
                "}";
        System.out.println("Insert 2 records");
        privilegeController.create(privilegeFirst);
        privilegeController.create(privilegeSecond);
        System.out.println("All privileges");
        System.out.println(privilegeController.getAll());
        System.out.println("Get the first privilege");
        System.out.println(privilegeController.getById(privilegeFirst));
        System.out.println("Delete the first record");
        privilegeController.delete(privilegeFirst);
        System.out.println("Update privilegeName in the second record");
        System.out.println(privilegeController.update(privilegeToUpdate));
        System.out.println("All records");
        System.out.println(privilegeController.getAll());
    }
    public static void promotionRequest(PromotionController promotionController) throws JsonProcessingException {
        String promotionFirst = "{\"id\": 1, \"promotionName\": \"promotion1\", \"percent\": 10.00}";
        String promotionSecond = "{\"id\": 2, \"promotionName\": \"promotion2\", \"percent\": 10.00}";
        String promotionToUpdate = "{\"id\": 2, \"promotionName\": \"updatePromotionName\", \"percent\": 10.00}";
        System.out.println("Insert 2 records");
        promotionController.create(promotionFirst);
        promotionController.create(promotionSecond);
        System.out.println("All promotions");
        System.out.println(promotionController.getAll());
        System.out.println("Get the first promotion");
        System.out.println(promotionController.getById(promotionFirst));
        System.out.println("Delete the first record");
        promotionController.delete(promotionFirst);
        System.out.println("Update promotionName in the second record");
        System.out.println(promotionController.update(promotionToUpdate));
        System.out.println("All records");
        System.out.println(promotionController.getAll());
    }
    public static void publisherRequest(PublisherController publisherController) throws JsonProcessingException {
        String publisherFirst = "{\"id\": 1, \"publisherName\": \"publisher1\", \"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}";
        String publisherSecond = "{\"id\": 2, \"publisherName\": \"publisher2\", \"address\": {\"id\": 2, \"street\": \"street2\", \"index\": 123456}}";
        String publisherToUpdate = "{\"id\": 2, \"publisherName\": \"updatePublisherName\", \"address\": {\"id\": 2, \"street\": \"street2\", \"index\": 123456}}";
        System.out.println("Insert 2 records");
        publisherController.create(publisherFirst);
        publisherController.create(publisherSecond);
        System.out.println("All publishers");
        System.out.println(publisherController.getAll());
        System.out.println("Get the first publisher");
        System.out.println(publisherController.getById(publisherFirst));
        System.out.println("Delete the first record");
        publisherController.delete(publisherFirst);
        System.out.println("Update publisherName in the second record");
        System.out.println(publisherController.update(publisherToUpdate));
        System.out.println("All records");
        System.out.println(publisherController.getAll());
    }
    public static void reviewRequest(ReviewController reviewController) throws JsonProcessingException {
        String reviewFirst = "{\"id\": 1, \"text\": \"text1\", \"rating\": 2, \"user\": {"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}, \"book\": {\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {\"id\": 1, \"genreName\": \"genre1\"},"
                + "\"publisher\": {\"id\": 1, \"publisherName\": \"publisher1\", \"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "}}";
        String reviewSecond = "{\"id\": 2, \"text\": \"text2\", \"rating\": 3, \"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role2\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}, \"book\": {\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {\"id\": 1, \"genreName\": \"genre1\"},"
                + "\"publisher\": {\"id\": 1, \"publisherName\": \"publisher1\", \"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "}}";
        String reviewToUpdate = "{\"id\": 2, \"text\": \"updateText\", \"rating\": 3, \"user\": {"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 2, \"roleName\": \"role2\", \"privileges\": [{\"id\": 2, \"privilegeName\": \"privilege2\"}]}"
                + "}, \"book\": {\"id\": 1,"
                + "\"title\": \"book1\","
                + "\"price\": 10.00,"
                + "\"isbn\": 1231231231233,"
                + "\"genre\": {\"id\": 1, \"genreName\": \"genre1\"},"
                + "\"publisher\": {\"id\": 1, \"publisherName\": \"publisher1\", \"address\": {\"id\": 1, \"street\": \"street1\", \"index\": 123456}}"
                + "}}";
        System.out.println("Insert 2 records");
        reviewController.create(reviewFirst);
        reviewController.create(reviewSecond);
        System.out.println("All reviews");
        System.out.println(reviewController.getAll());
        System.out.println("Get the first review");
        System.out.println(reviewController.getById(reviewFirst));
        System.out.println("Delete the first record");
        reviewController.delete(reviewFirst);
        System.out.println("Update text in the second record");
        System.out.println(reviewController.update(reviewToUpdate));
        System.out.println("All records");
        System.out.println(reviewController.getAll());
    }
    public static void roleRequest(RoleController roleController) throws JsonProcessingException {
        String roleFirst = "{\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}";
        String roleSecond = "{\"id\": 2, \"roleName\": \"role2\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}";
        String roleToUpdate = "{\"id\": 2, \"roleName\": \"updateRoleName\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}";
        System.out.println("Insert 2 records");
        roleController.create(roleFirst);
        roleController.create(roleSecond);
        System.out.println("All roles");
        System.out.println(roleController.getAll());
        System.out.println("Get the first role");
        System.out.println(roleController.getById(roleFirst));
        System.out.println("Delete the first record");
        roleController.delete(roleFirst);
        System.out.println("Update roleName in the second record");
        System.out.println(roleController.update(roleToUpdate));
        System.out.println("All records");
        System.out.println(roleController.getAll());
    }
    public static void userRequest(UserController userController) throws JsonProcessingException {
        String userFirst = "{"
                + "\"id\": 1,"
                + "\"name\": \"name1\","
                + "\"surname\": \"surname1\","
                + "\"email\": \"test1@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}";
        String userSecond = "{"
                + "\"id\": 2,"
                + "\"name\": \"name2\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}";
        String userToUpdate = "{"
                + "\"id\": 2,"
                + "\"name\": \"updateName\","
                + "\"surname\": \"surname2\","
                + "\"email\": \"test2@example.com\","
                + "\"role\": {\"id\": 1, \"roleName\": \"role1\", \"privileges\": [{\"id\": 1, \"privilegeName\": \"privilege1\"}]}"
                + "}";
        System.out.println("Insert 2 records");
        userController.create(userFirst);
        userController.create(userSecond);
        System.out.println("All users");
        System.out.println(userController.getAll());
        System.out.println("Get the first user");
        System.out.println(userController.getById(userFirst));
        System.out.println("Delete the first record");
        userController.delete(userFirst);
        System.out.println("Update name in the second record");
        System.out.println(userController.update(userToUpdate));
        System.out.println("All records");
        System.out.println(userController.getAll());
    }
}