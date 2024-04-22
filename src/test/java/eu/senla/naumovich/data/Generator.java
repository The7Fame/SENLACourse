package eu.senla.naumovich.data;

import eu.senla.naumovich.entities.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.Date;

@UtilityClass
public class Generator {
    private final static Long ID = 3L;
    private final static String NAME = "NAME";
    private final static String UPDATE_NAME = "UPDATE_NAME";

    public Role createRole() {
        return Role.builder()
                .id(ID)
                .roleName(NAME)
                .build();
    }

    public Role updateRole() {
        Role role = createRole();
        role.setRoleName(UPDATE_NAME);
        return role;
    }

    public Privilege createPrivilege() {
        return Privilege.builder()
                .id(ID)
                .privilegeName(NAME)
                .build();
    }

    public Privilege updatePrivilege() {
        Privilege privilege = createPrivilege();
        privilege.setPrivilegeName(UPDATE_NAME);
        return privilege;
    }

    public User createUser() {
        return User.builder()
                .id(ID)
                .name(NAME)
                .surname(NAME)
                .email(NAME)
                .build();
    }

    public User updateUser() {
        User user = createUser();
        user.setName(UPDATE_NAME);
        return user;
    }

    public Author createAuthor() {
        return Author.builder()
                .id(ID)
                .name(NAME)
                .surname(NAME)
                .build();
    }

    public Author updateAuthor() {
        Author author = createAuthor();
        author.setName(UPDATE_NAME);
        return author;
    }

    public Publisher createPublisher() {
        return Publisher.builder()
                .id(ID)
                .publisherName(NAME)
                .build();
    }

    public Publisher updatePublisher() {
        Publisher publisher = createPublisher();
        publisher.setPublisherName(UPDATE_NAME);
        return publisher;
    }

    public Address createAddress() {
        return Address.builder()
                .id(ID)
                .city(NAME)
                .street(NAME)
                .index(111111)
                .build();
    }

    public Address updateAddress() {
        Address address = createAddress();
        address.setCity(UPDATE_NAME);
        return address;
    }

    public Book createBook() {
        return Book.builder()
                .id(ID)
                .title(NAME)
                .isbn(NAME)
                .price(new BigDecimal("10.00"))
                .build();
    }

    public Book updateBook() {
        Book book = createBook();
        book.setTitle(UPDATE_NAME);
        return book;
    }

    public Promotion createPromotion() {
        return Promotion.builder()
                .id(ID)
                .percent(new BigDecimal("10.00"))
                .promotionName(NAME)
                .build();
    }

    public Promotion updatePromotion() {
        Promotion promotion = createPromotion();
        promotion.setPromotionName(UPDATE_NAME);
        return promotion;

    }

    public Review createReview() {
        return Review.builder()
                .id(ID)
                .text(NAME)
                .build();
    }

    public Review updateReview() {
        Review review = createReview();
        review.setText(UPDATE_NAME);
        return review;
    }

    public Order createOrder() {
        return Order.builder()
                .id(ID)
                .orderDate(Date.valueOf("2024-04-15"))
                .totalPrice(new BigDecimal("30.00"))
                .build();
    }

    public Order updateOrder() {
        Order order = createOrder();
        order.setTotalPrice(new BigDecimal("20.00"));
        return order;
    }

    public Payment createPayment() {
        return Payment.builder()
                .id(ID)
                .status(false)
                .build();
    }

    public Payment updatePayment() {
        Payment payment = createPayment();
        payment.setStatus(true);
        return payment;
    }
}
