package eu.senla.naumovich.data;

import eu.senla.naumovich.dto.*;
import eu.senla.naumovich.entities.*;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.units.qual.N;

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
                .password(NAME)
                .email(NAME)
                .build();
    }

    public User updateUser() {
        User user = createUser();
        user.setName(UPDATE_NAME);
        user.setPassword(UPDATE_NAME);
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

    public AddressDto createAddressDto() {
        return AddressDto.builder()
                .id(ID)
                .city(NAME)
                .street(NAME)
                .index(111111)
                .build();
    }

    public AddressDto updateAddressDto() {
        AddressDto address = createAddressDto();
        address.setCity(UPDATE_NAME);
        return address;
    }

    public AuthorDto createAuthorDto() {
        return AuthorDto.builder()
                .id(ID)
                .name(NAME)
                .surname(NAME)
                .build();
    }

    public AuthorDto updateAuthorDto() {
        AuthorDto author = createAuthorDto();
        author.setName(UPDATE_NAME);
        return author;
    }

    public BookDto createBookDto() {
        return BookDto.builder()
                .id(ID)
                .title(NAME)
                .isbn(NAME)
                .price(new BigDecimal("10.00"))
                .build();
    }

    public BookDto updateBookDto() {
        BookDto book = createBookDto();
        book.setTitle(UPDATE_NAME);
        return book;
    }

    public OrderDto createOrderDto() {
        return OrderDto.builder()
                .id(ID)
                .orderDate(Date.valueOf("2024-04-15"))
                .totalPrice(new BigDecimal("30.00"))
                .build();
    }

    public OrderDto updateOrderDto() {
        OrderDto order = createOrderDto();
        order.setTotalPrice(new BigDecimal("20.00"));
        return order;
    }

    public PaymentDto createPaymentDto() {
        return PaymentDto.builder()
                .id(ID)
                .status(false)
                .build();
    }

    public PaymentDto updatePaymentDto() {
        PaymentDto payment = createPaymentDto();
        payment.setStatus(true);
        return payment;
    }

    public PrivilegeDto createPrivilegeDto() {
        return PrivilegeDto.builder()
                .id(ID)
                .privilegeName(NAME)
                .build();
    }

    public PrivilegeDto updatePrivilegeDto() {
        PrivilegeDto privilege = createPrivilegeDto();
        privilege.setPrivilegeName(UPDATE_NAME);
        return privilege;
    }

    public PromotionDto createPromotionDto() {
        return PromotionDto.builder()
                .id(ID)
                .percent(new BigDecimal("10.00"))
                .promotionName(NAME)
                .build();
    }

    public PromotionDto updatePromotionDto() {
        PromotionDto promotion = createPromotionDto();
        promotion.setPromotionName(UPDATE_NAME);
        return promotion;
    }

    public PublisherDto createPublisherDto() {
        return PublisherDto.builder()
                .id(ID)
                .publisherName(NAME)
                .build();
    }

    public PublisherDto updatePublisherDto() {
        PublisherDto publisher = createPublisherDto();
        publisher.setPublisherName(UPDATE_NAME);
        return publisher;
    }

    public ReviewDto createReviewDto() {
        return ReviewDto.builder()
                .id(ID)
                .text(NAME)
                .build();
    }

    public ReviewDto updateReviewDto() {
        ReviewDto review = createReviewDto();
        review.setText(UPDATE_NAME);
        return review;
    }

    public RoleDto createRoleDto() {
        return RoleDto.builder()
                .id(ID)
                .roleName(NAME)
                .build();
    }

    public RoleDto updateRoleDto() {
        RoleDto role = createRoleDto();
        role.setRoleName(UPDATE_NAME);
        return role;
    }

    public UserDto createUserDto() {
        return UserDto.builder()
                .id(ID)
                .name(NAME)
                .surname(NAME)
                .password(NAME)
                .email(NAME)
                .build();
    }

    public UserDto updateUserDto() {
        UserDto user = createUserDto();
        user.setName(UPDATE_NAME);
        user.setPassword(UPDATE_NAME);
        return user;
    }
}
