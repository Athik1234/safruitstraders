package com.cs5500.FreshMart.controller;

import com.cs5500.FreshMart.exception.ItemNotExistException;
import com.cs5500.FreshMart.exception.OrderNotFinishedException;
import com.cs5500.FreshMart.exception.PasswordNotMatchException;
import com.cs5500.FreshMart.exception.UserAlreadyExistException;
import com.cs5500.FreshMart.exception.UserNotExistException;
import com.cs5500.FreshMart.model.Comment;
import com.cs5500.FreshMart.model.Item;
import com.cs5500.FreshMart.model.Order;
import com.cs5500.FreshMart.model.Greengrocer;
import com.cs5500.FreshMart.model.GreengrocerInfo;
import com.cs5500.FreshMart.service.OrderServiceImpl;
import com.cs5500.FreshMart.service.GreengrocerServiceImpl;
import com.cs5500.FreshMart.service.SearchEngineServiceImpl;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greengrocer")
public class GreengrocerController {

  private final GreengrocerServiceImpl greengrocerService;
  private final OrderServiceImpl orderService;
  private final SearchEngineServiceImpl searchEngineService;

  @Autowired
  public GreengrocerController(GreengrocerServiceImpl greengrocerService,
      OrderServiceImpl orderService, SearchEngineServiceImpl searchEngineService) {
    this.greengrocerService = greengrocerService;
    this.orderService = orderService;
    this.searchEngineService = searchEngineService;
  }

  @GetMapping(path = "/all")
  public List<Greengrocer> getAllGreengrocers() {
    return greengrocerService.getUsers();
  }

  @GetMapping(path = "/search/" + "{query}")
  public List<Greengrocer> SearchGreengrocers(@PathVariable("query") String query) {
    List<Greengrocer> res = new ArrayList<>();
    List<String> ids = searchEngineService.searchGreengrocer(query);
    if (ids != null) {
      for (String id : ids) {
        if (greengrocerService.getUser(id).isPresent()) {
          res.add(greengrocerService.getUser(id).get());
        }
      }
    }
    return res;
  }

  @GetMapping(path = "{id}")
  public Greengrocer getGreengrocerById(@PathVariable("id") String id)
      throws UserNotExistException {
    return greengrocerService.getUser(id)
        .orElseThrow(() -> new UserNotExistException("User doesn't exist"));
  }

  @PostMapping(path = "/login")
  public Greengrocer loginGreengrocer(@RequestBody String jsonUser)
      throws UserNotExistException, PasswordNotMatchException {

    JSONObject user = new JSONObject(jsonUser);
    String userName = user.getString("userName");
    String password = user.getString("password");
    Optional<Greengrocer> greengrocer = greengrocerService.getUserByName(userName);
    if (greengrocer.isEmpty()) {
      throw new UserNotExistException("User doesn't exist");
    }
    if (!greengrocerService.passwordMatch(greengrocer.get().getId(), password)) {
      throw new PasswordNotMatchException("Password doesn't match");
    }
    return greengrocer.get();
  }

  @PostMapping(path = "/register")
  public Greengrocer registerGreengrocer(@RequestBody String jsonUser)
      throws UserAlreadyExistException {

    JSONObject user = new JSONObject(jsonUser);
    String userName = user.getString("userName");
    String password = user.getString("password");
    String phoneNumber = user.getString("phoneNumber");
    String address = user.getString("address");
    String city = user.getString("city");
    String state = user.getString("state");
    String zip = user.getString("zip");
    Greengrocer greengrocer = greengrocerService
        .addUser(userName, password, phoneNumber, address, city, state, zip);
    if (greengrocer == null) {
      throw new UserAlreadyExistException("User already exists, please login");
    }
    return greengrocer;
  }

  @PostMapping(path = "/logout")
  public int logoutGreengrocer() {
    System.out.println("logout the user");
    return 1;
  }

  @GetMapping(path = "/myActiveOrders/" + "{id}")
  public List<Order> getActiveOrders(@PathVariable("id") String id)
      throws UserNotExistException {
    if (greengrocerService.getUser(id).isEmpty()) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    return orderService.greengrocerGetActiveOrders(id);
  }

  @GetMapping(path = "/myOrderHistory/" + "{id}")
  public List<Order> getOrderHistory(@PathVariable("id") String id)
      throws UserNotExistException {
    if (greengrocerService.getUser(id).isEmpty()) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    return orderService.greengrocerFindPastOrders(id);
  }

  @GetMapping(path = "/list/" + "{id}")
  public List<Item> getlist(@PathVariable("id") String id)
      throws UserNotExistException {
    if (greengrocerService.getUser(id).isEmpty()) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    return greengrocerService.getAllitems(id);
  }

  @PostMapping(path = "/addTolist")
  public int addItemTolist(@RequestBody String jsonItem)
      throws UserNotExistException {
    JSONObject item = new JSONObject(jsonItem);
    String greengrocerId = item.getString("greengrocerId");
    String itemName = item.getString("itemName");
    String imageUrl = item.getString("imageUrl");
    double price = item.getDouble("price");
    Item newItem = new Item(itemName, price, imageUrl);
    int res = greengrocerService.addItem(greengrocerId, newItem);
    if (res == -1) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    // handle search engine
    searchEngineService.addGreengrocer(itemName, greengrocerId);
    return res;
  }

  @PostMapping(path = "/removeItem")
  public int removeItemFromlist(@RequestBody String jsonItem)
      throws UserNotExistException, ItemNotExistException {
    JSONObject item = new JSONObject(jsonItem);
    String greengrocerId = item.getString("greengrocerId");
    Object itemObject = item.getJSONObject("item");
    Gson gson = new Gson();
    Item newItem = gson.fromJson(itemObject.toString(), Item.class);
    int res = greengrocerService.removeItem(greengrocerId, newItem);
    if (res == -1) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    if (res == 0) {
      throw new ItemNotExistException("The given item doesn't exist");
    }
    // handle search engine
    searchEngineService.removeGreengrocer(newItem.getItemName(), greengrocerId);
    return res;
  }

  @GetMapping(path = "/information/" + "{id}")
  public GreengrocerInfo getGreengrocerInformation(@PathVariable("id") String id)
      throws UserNotExistException {
    if (greengrocerService.getInformation(id) != null) {
      return greengrocerService.getInformation(id);
    }
    throw new UserNotExistException("The given greengrocer doesn't exist");
  }

  @PostMapping(path = "/information")
  public int updateGreengrocerInformation(@RequestBody String jsonInfo)
      throws UserNotExistException {
    JSONObject object = new JSONObject(jsonInfo);
    String greengrocerId = object.getString("greengrocerId");
    boolean open = object.getBoolean("status");
    String name = object.getString("name");
    String description = object.getString("description");
    String imageUrl = object.getString("imageUrl");
    String tag1 = object.getString("tag1");
    String tag2 = object.getString("tag2");
    String tag3 = object.getString("tag3");
    GreengrocerInfo newInfo = new GreengrocerInfo(open, name, description, imageUrl, tag1, tag2,
        tag3);
    // handle search engine
    GreengrocerInfo oldInfo = greengrocerService.getInformation(greengrocerId);
    if (oldInfo != null) {
      searchEngineService.eraseInfo(oldInfo, greengrocerId);
    }
    searchEngineService.updateInfo(newInfo, greengrocerId);
    int res = greengrocerService.updateInfo(greengrocerId, newInfo);
    if (res == -1) {
      throw new UserNotExistException("The given greengrocer doesn't exist");
    }
    return res;
  }

  @DeleteMapping(path = "{id}")
  public int deleterGreengrocer(@PathVariable("id") String id)
      throws UserNotExistException, OrderNotFinishedException {
    if (orderService.greengrocerGetActiveOrders(id).size() != 0) {
      throw new OrderNotFinishedException("You still have active orders, please finish them first");
    }
    // handle search engine
    GreengrocerInfo oldInfo = greengrocerService.getInformation(id);
    if (oldInfo != null) {
      searchEngineService.eraseInfo(oldInfo, id);
    }
    List<Item> items = greengrocerService.getAllitems(id);
    if (items != null) {
      searchEngineService.eraseItems(items, id);
    }
    int res = greengrocerService.deleteUser(id);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist");
    }
    return res;
  }

  @PostMapping(path = "/resetPassword")
  public int resetPassword(@RequestBody String jsonPassword)
      throws UserNotExistException, PasswordNotMatchException {
    JSONObject object = new JSONObject(jsonPassword);
    String id = object.getString("id");
    String oldPassword = object.getString("oldPassword");
    String newPassword = object.getString("newPassword");
    int res = greengrocerService.updatePassword(id, oldPassword, newPassword);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist");
    }
    if (res == 0) {
      throw new PasswordNotMatchException("Password doesn't match");
    }
    return res;
  }

  @PostMapping(path = "/resetPhone")
  public int resetPhoneNumber(@RequestBody String jsonPhone)
      throws UserNotExistException {
    JSONObject object = new JSONObject(jsonPhone);
    String id = object.getString("id");
    String phoneNumber = object.getString("phoneNumber");
    int res = greengrocerService.updatePhoneNumber(id, phoneNumber);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist");
    }
    return res;
  }

  @PostMapping(path = "/resetAddress")
  public int resetAddress(@RequestBody String jsonAddress)
      throws UserNotExistException {
    JSONObject object = new JSONObject(jsonAddress);
    String id = object.getString("id");
    String address = object.getString("address");
    String city = object.getString("city");
    String state = object.getString("state");
    String zip = object.getString("zip");
    int res = greengrocerService.updateAddress(id, address, city, state, zip);
    if (res == -1) {
      throw new UserNotExistException("User doesn't exist");
    }
    return res;
  }

  @GetMapping(path = "/getComments/" + "{id}")
  public List<Comment> findCommentsByGreengrocer(@PathVariable("id") String id)
      throws UserNotExistException {
    Optional<Greengrocer> greengrocerOptional = greengrocerService.getUser(id);
    if (greengrocerOptional.isEmpty()) throw new UserNotExistException("User doesn't exist");
    return orderService.greengrocerGetComments(id);
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({UserNotExistException.class, PasswordNotMatchException.class,
      UserAlreadyExistException.class, ItemNotExistException.class,
      OrderNotFinishedException.class})
  public String handleException(Exception e) {
    return e.getMessage();
  }
}
