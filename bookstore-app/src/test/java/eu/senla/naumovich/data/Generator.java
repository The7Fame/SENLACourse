package eu.senla.naumovich.data;

import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.dto.publisher.PublisherDto;
import eu.senla.naumovich.dto.review.ReviewCreateDto;
import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserUpdateDto;
import eu.senla.naumovich.entities.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collections;

@UtilityClass
public class Generator {
    private final static Long ID = 4L;
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
                .city(NAME+NAME)
                .street(NAME+NAME)
                .index(111111)
                .publisher(createPublisherDto())
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
        PublisherDto publisherDto = PublisherDto.builder().publisherName(NAME).id(1L).build();
        return BookDto.builder()
                .id(ID)
                .title(NAME)
                .isbn(NAME)
                .genre("POETRY")
                .price(new BigDecimal("10.00"))
                .authors(Collections.singletonList(createAuthorDto()))
                .publisher(publisherDto)
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
                .rating(5L)
                .book(createBookDto())
                .user(createUserDto())
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
                .privileges(Collections.singletonList(createPrivilegeDto()))
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
                .email(NAME+"@mail.com")
                .balance(BigDecimal.valueOf(100))
                .role(createRoleDto())
                .build();
    }

    public UserDto updateUserDto() {
        UserDto user = createUserDto();
        user.setName(UPDATE_NAME);
        user.setPassword(UPDATE_NAME);
        return user;
    }

    public CreatePromotionGenreDto createPromotionGenreDto(){
        return CreatePromotionGenreDto.builder()
                .genreId(1)
                .percent(BigDecimal.valueOf(10.00))
                .promotionName("SomePromotionGenre")
                .build();
    }

    public CreatePromotionAuthorDto createPromotionAuthorDto(){
        return CreatePromotionAuthorDto.builder()
                .authorName("Boris")
                .percent(BigDecimal.valueOf(10.00))
                .promotionName("SomePromotionAuthor")
                .build();
    }

    public ReviewCreateDto reviewCreateDto(){
        return ReviewCreateDto.builder()
                .text("Some text")
                .rating(3L)
                .build();
    }

    public UserReplenishBalanceDto userReplenishBalanceDto(){
        return UserReplenishBalanceDto.builder()
                .balance(BigDecimal.valueOf(100))
                .build();
    }

    public UserUpdateDto userUpdateDto(){
        return UserUpdateDto.builder()
                .surname(UPDATE_NAME)
                .name(UPDATE_NAME)
                .build();
    }
}
