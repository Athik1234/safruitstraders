//package com.cs5500.FreshMart;
//
//import com.cs5500.FreshMart.model.Comment;
//import com.cs5500.FreshMart.model.Customer;
//import com.cs5500.FreshMart.model.Dish;
//import com.cs5500.FreshMart.model.Driver;
//import com.cs5500.FreshMart.model.Order;
//import com.cs5500.FreshMart.model.Restaurant;
//import com.cs5500.FreshMart.model.RestaurantInfo;
//import com.cs5500.FreshMart.model.SearchEngine;
//import com.cs5500.FreshMart.repository.CustomerRepository;
//import com.cs5500.FreshMart.repository.DriverRepository;
//import com.cs5500.FreshMart.repository.OrderRepository;
//import com.cs5500.FreshMart.repository.RestaurantRepository;
//import com.cs5500.FreshMart.repository.SearchEngineRepository;
//import com.cs5500.FreshMart.service.PasswordService;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//// This class is used to refresh the database. It will delete all existing data and add the default ones
//// The default data contains one customer, one driver, two greengrocers, two finished orders. Each greengrocer contains three dishes.
//
//@SpringBootApplication
//public class LoadDatabase implements CommandLineRunner {
//  @Autowired
//  private CustomerRepository customerRepository;
//  @Autowired
//  private DriverRepository driverRepository;
//  @Autowired
//  private RestaurantRepository greengrocerRepository;
//  @Autowired
//  private OrderRepository orderRepository;
//  @Autowired
//  private SearchEngineRepository searchEngineRepository;
//  PasswordService passwordService = new PasswordService();
//
//  public static void main(String[] args) {
//    SpringApplication.run(LoadDatabase.class, args);
//  }
//
//  @Override
//  public void run(String... args) throws Exception {
//    customerRepository.deleteAll();
//    Customer customer1 = new Customer("tma", passwordService.generatePassword("12345"),
//        "7739973942", "1512 NW 63rd St", "Seattle", "WA", "98107");
//    customerRepository.save(customer1);
//
//    driverRepository.deleteAll();
//    Driver driver1 = new Driver("Bruce", passwordService.generatePassword("12345"), "5674243435",
//        "401 NE Northgate Way", "Seattle", "WA", "98125");
//    driverRepository.save(driver1);
//
//    RestaurantInfo greengrocerInfo1 = new RestaurantInfo();
//    greengrocerInfo1.setOpen(true);
//    greengrocerInfo1.setRestaurantName("Macdonald");
//    greengrocerInfo1.setDescription("We serve best fast food!");
//    greengrocerInfo1.setImageUrl(
//        "https://i.pinimg.com/originals/f4/4e/ec/f44eecf0fa921427f4a4669fb8f69115.png");
//    greengrocerInfo1.setTag1("fastfood");
//    greengrocerInfo1.setTag2("cheap");
//    greengrocerInfo1.setTag3("hamburger");
//
//    RestaurantInfo greengrocerInfo2 = new RestaurantInfo();
//    greengrocerInfo2.setOpen(true);
//    greengrocerInfo2.setRestaurantName("PizzaHut");
//    greengrocerInfo2.setDescription("We serve pizza and salad");
//    greengrocerInfo2.setImageUrl(
//        "https://yt3.ggpht.com/a/AATXAJwkXJs-JbKCe4ab7wNKGtzjHVuyingDGT7bzL5Okw=s900-c-k-c0xffffffff-no-rj-mo");
//    greengrocerInfo2.setTag1("italian");
//    greengrocerInfo2.setTag2("healthy");
//    greengrocerInfo2.setTag3("party");
//
//    RestaurantInfo greengrocerInfo3 = new RestaurantInfo();
//    greengrocerInfo3.setOpen(false);
//    greengrocerInfo3.setRestaurantName("Water Bar");
//    greengrocerInfo3.setDescription("We serve all kind of drinks");
//    greengrocerInfo3.setImageUrl(
//        "https://scx2.b-cdn.net/gfx/news/hires/2019/water.jpg"
//    );
//    greengrocerInfo3.setTag1("juice");
//    greengrocerInfo3.setTag2("beer");
//    greengrocerInfo3.setTag3("drink");
//
//    Dish dish1 = new Dish();
//    dish1.setDishName("Big Mac");
//    dish1.setImageUrl(
//        "https://www.mcdonalds.com/is/image/content/dam/usa/nfl/nutrition/items/regular/desktop/t-mcdonalds-Big-Mac.jpg");
//    dish1.setPrice(5);
//
//    Dish dish2 = new Dish();
//    dish2.setDishName("Fried Chicken");
//    dish2.setImageUrl("https://content.fortune.com/wp-content/uploads/2015/03/ap956240873949.jpg");
//    dish2.setPrice(4);
//
//    Dish dish3 = new Dish();
//    dish3.setDishName("Fries");
//    dish3
//        .setImageUrl("https://www.wired.com/wp-content/uploads/2014/06/qq_whatsinside_fries_f.jpg");
//    dish3.setPrice(3);
//
//    Dish dish4 = new Dish();
//    dish4.setDishName("Pizza");
//    dish4.setImageUrl(
//        "https://www.qsrmagazine.com/sites/default/files/styles/story_page/public/story/pizza-hut-turns-comeback-expert_0.jpg?itok=U_V-5YAD");
//    dish4.setPrice(22);
//
//    Dish dish5 = new Dish();
//    dish5.setDishName("Salad");
//    dish5.setImageUrl(
//        "https://www.howsweeteats.com/wp-content/uploads/2020/05/summer-salad-16-500x375.jpg");
//    dish5.setPrice(13);
//
//    Dish dish6 = new Dish();
//    dish6.setDishName("Soup");
//    dish6.setImageUrl(
//        "https://www.inspiredtaste.net/wp-content/uploads/2018/10/Homemade-Vegetable-Soup-Recipe-2-1200.jpg");
//    dish6.setPrice(7.5);
//
//    Dish dish7 = new Dish();
//    dish7.setDishName("Orange Juice");
//    dish7.setImageUrl("https://thumbs.dreamstime.com/b/fresh-orange-juice-vitamins-health-table-fruits-jucie-78350628.jpg");
//    dish7.setPrice(5);
//
//    List<Dish> dishes1 = new ArrayList<>();
//    dishes1.add(dish1);
//    dishes1.add(dish2);
//    dishes1.add(dish3);
//
//    List<Dish> dishes2 = new ArrayList<>();
//    dishes2.add(dish4);
//    dishes2.add(dish5);
//    dishes2.add(dish6);
//
//    List<Dish> dishes3 = new ArrayList<>();
//    dishes3.add(dish7);
//
//    greengrocerRepository.deleteAll();
//    Restaurant greengrocer1 = new Restaurant("mcd", passwordService.generatePassword("12345"),
//        "1234567890", "9000 Holman Rd NW", "Seattle", "WA", "98117", greengrocerInfo1, dishes1);
//    Restaurant greengrocer2 = new Restaurant("pizza", passwordService.generatePassword("12345"),
//        "1234567890", "23830 Hwy 99 Ste 118", "Edmonds", "WA", "98026", greengrocerInfo2, dishes2);
//    Restaurant greengrocer3 = new Restaurant("bar", passwordService.generatePassword("12345"), "1234567890", "309 N 36th St", "Seattle", "WA", "98103", greengrocerInfo3, dishes3);
//    greengrocerRepository.save(greengrocer1);
//    greengrocerRepository.save(greengrocer2);
//    greengrocerRepository.save(greengrocer3);
//
//    searchEngineRepository.deleteAll();
//    SearchEngine searchEngine = new SearchEngine();
//    String id1 = greengrocer1.getId();
//    searchEngine.add(greengrocerInfo1.getRestaurantName(), id1);
//    searchEngine.add(greengrocerInfo1.getTag1(), id1);
//    searchEngine.add(greengrocerInfo1.getTag2(), id1);
//    searchEngine.add(greengrocerInfo1.getTag3(), id1);
//    searchEngine.add(dish1.getDishName(), id1);
//    searchEngine.add(dish2.getDishName(), id1);
//    searchEngine.add(dish3.getDishName(), id1);
//    String id2 = greengrocer2.getId();
//    searchEngine.add(greengrocerInfo2.getRestaurantName(), id2);
//    searchEngine.add(greengrocerInfo2.getTag1(), id2);
//    searchEngine.add(greengrocerInfo2.getTag2(), id2);
//    searchEngine.add(greengrocerInfo2.getTag3(), id2);
//    searchEngine.add(dish4.getDishName(), id2);
//    searchEngine.add(dish5.getDishName(), id2);
//    searchEngine.add(dish6.getDishName(), id2);
//    String id3 = greengrocer3.getId();
//    searchEngine.add(greengrocerInfo3.getRestaurantName(), id3);
//    searchEngine.add(greengrocerInfo3.getTag1(), id3);
//    searchEngine.add(greengrocerInfo3.getTag2(), id3);
//    searchEngine.add(greengrocerInfo3.getTag3(), id3);
//    searchEngine.add(dish7.getDishName(), id3);
//    searchEngineRepository.save(searchEngine);
//
//    orderRepository.deleteAll();
//    Order order1 = new Order();
//    order1.setCustomerId(customer1.getId());
//    order1.setDriverId(driver1.getId());
//    order1.setRestaurantId(greengrocer1.getId());
//    order1.setStartTime(LocalDateTime.of(2020, 1, 1, 19, 30));
//    order1.setDelivery(true);
//    order1.setEndTime(LocalDateTime.of(2020, 1, 1, 20, 0));
//    order1.setContent(dishes1);
//    double price1 = 0;
//    for (Dish dish : dishes1) {
//      price1 += dish.getPrice();
//    }
//    order1.setPrice(price1);
//    Comment comment1 = new Comment(4, "very nice experience, the food is delicious");
//    order1.setComment(comment1);
//    Order order2 = new Order();
//    order2.setCustomerId(customer1.getId());
//    order2.setDriverId(driver1.getId());
//    order2.setRestaurantId(greengrocer2.getId());
//    order2.setStartTime(LocalDateTime.of(2020, 2, 3, 11, 25));
//    order2.setDelivery(true);
//    order2.setEndTime(LocalDateTime.of(2020, 2, 3, 13, 0));
//    order2.setContent(dishes2);
//    double price2 = 0;
//    for (Dish dish : dishes2) {
//      price2 += dish.getPrice();
//    }
//    order2.setPrice(price2);
//    Comment comment2 = new Comment(2, "the food is so expensive");
//    order2.setComment(comment2);
//    Order order3 = new Order();
//    order3.setCustomerId(customer1.getId());
//    order3.setDriverId(driver1.getId());
//    order3.setRestaurantId(greengrocer3.getId());
//    order3.setStartTime(LocalDateTime.of(2020, 1, 1, 19, 30));
//    order3.setDelivery(true);
//    order3.setEndTime(LocalDateTime.of(2020, 1, 1, 20, 0));
//    order3.setContent(dishes3);
//    double price3 = 0;
//    for (Dish dish : dishes3) {
//      price3 += dish.getPrice();
//    }
//    order3.setPrice(price3);
//    Order order4 = new Order();
//    order4.setCustomerId(customer1.getId());
//    order4.setDriverId(driver1.getId());
//    order4.setRestaurantId(greengrocer2.getId());
//    order4.setStartTime(LocalDateTime.of(2020, 2, 3, 11, 25));
//    order4.setDelivery(true);
//    order4.setEndTime(LocalDateTime.of(2020, 2, 3, 13, 0));
//    order4.setContent(dishes2);
//    double price4 = 0;
//    for (Dish dish : dishes2) {
//      price4 += dish.getPrice();
//    }
//    order4.setPrice(price4);
//    Comment comment3 = new Comment(5, "The delivery is very fast!!!");
//    order4.setComment(comment3);
//    orderRepository.save(order1);
//    orderRepository.save(order2);
//    orderRepository.save(order3);
//    orderRepository.save(order4);
//  }
//}
