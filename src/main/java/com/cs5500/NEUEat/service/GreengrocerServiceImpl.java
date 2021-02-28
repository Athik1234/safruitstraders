package com.cs5500.FreshMart.service;

import com.cs5500.FreshMart.model.Comment;
import com.cs5500.FreshMart.model.Item;
import com.cs5500.FreshMart.model.Greengrocer;
import com.cs5500.FreshMart.model.GreengrocerInfo;
import com.cs5500.FreshMart.repository.GreengrocerRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreengrocerServiceImpl implements GreengrocerService, UserService<Greengrocer> {

  @Autowired
  GreengrocerRepository greengrocerRepository;
  PasswordService passwordService = new PasswordService();


  @Override
  public int addItem(String id, Item item) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      Set<Item> set;
      if (greengrocer.get().getList() == null) {
        set = new HashSet<>();
      } else {
        set = new HashSet<>(greengrocer.get().getList());
      }
      set.add(item);
      greengrocer.get().setList(new ArrayList<>(set));
      greengrocerRepository.save(greengrocer.get());

      System.out.println("Add the item");
      return 1;
    }
    System.out.println("Can't add the item");
    return -1;
  }

  @Override
  public int removeItem(String id, Item item) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      List<Item> temp = greengrocer.get().getList();
      if (temp.contains(item)) {
        temp.remove(item);
        greengrocer.get().setList(temp);
        greengrocerRepository.save(greengrocer.get());

        System.out.println("Remove the item");
        return 1;
      } else {
        System.out.println("Item not in the list");
        return 0;
      }
    }
    System.out.println("Can't remove the item");
    return -1;
  }

  @Override
  public List<Item> getAllitems(String id) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    System.out.println("Get all itemes from greengrocer: " + id);
    return greengrocer.map(Greengrocer::getList).orElse(null);
  }

  @Override
  public GreengrocerInfo getInformation(String id) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      System.out.println("Get the greengrocer information");
      if (greengrocer.get().getInformation() == null) {
        return new GreengrocerInfo();
      } else {
        return greengrocer.get().getInformation();
      }
    }
    return null;
  }

  @Override
  public int updateInfo(String id, GreengrocerInfo info) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {

      greengrocer.get().setInformation(info);
      greengrocerRepository.save(greengrocer.get());
      System.out.println("Update the information");
      return 1;
    }
    System.out.println("Can't update the information");
    return -1;
  }

  @Override
  public Greengrocer addUser(String userName, String password, String phoneNumber, String address,
      String city, String state, String zip) {
    if (this.getUserIdByName(userName) == null) {
      String newPassword = passwordService.generatePassword(password);
      Greengrocer greengrocer = new Greengrocer(userName, newPassword, phoneNumber, address, city,
          state,
          zip);
      greengrocerRepository.insert(greengrocer);
      System.out.println("Greengrocer added to the database");
      return greengrocer;
    }
    System.out.println("Greengrocer can't be added to the database");
    return null;
  }

  @Override
  public int deleteUser(String id) {
    if (this.getUser(id).isPresent()) {
      greengrocerRepository.deleteById(id);
      System.out.println("Greengrocer deleted from the database");
      return 1;
    }
    System.out.println("Greengrocer can't be deleted from the database");
    return -1;
  }

  @Override
  public Optional<Greengrocer> getUser(String id) {
    if (id != null) {
      return greengrocerRepository.findById(id);
    }
    return Optional.empty();
  }

  @Override
  public String getUserIdByName(String userName) {
    List<Greengrocer> greengrocers = this.getUsers();
    for (Greengrocer greengrocer : greengrocers) {
      if (greengrocer.getUserName().equals(userName)) {
        return greengrocer.getId();
      }
    }
    System.out.println("Given userName doesn't found in greengrocer database");
    return null;
  }

  @Override
  public Optional<Greengrocer> getUserByName(String userName) {
    return this.getUser(getUserIdByName(userName));
  }

  @Override
  public List<Greengrocer> getUsers() {
    return greengrocerRepository.findAll();
  }

  @Override
  public boolean passwordMatch(String id, String password) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    return greengrocer.isPresent() && passwordService
        .passwordMatch(password, greengrocer.get().getPassword());
  }

  @Override
  public int updatePassword(String id, String oldPassword, String newPassword) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      if (this.passwordMatch(id, oldPassword)) {
        greengrocer.get().setPassword(passwordService.generatePassword(newPassword));
        greengrocerRepository.save(greengrocer.get());
        System.out.println("Update the password");
        return 1;
      } else {
        System.out.println("Password doesn't match");
        return 0;
      }
    }
    System.out.println("Can't update the password");
    return -1;
  }

  @Override
  public int updatePhoneNumber(String id, String newNumber) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      greengrocer.get().setPhoneNumber(newNumber);
      greengrocerRepository.save(greengrocer.get());
      System.out.println("Update the phone number");
      return 1;
    }
    System.out.println("Can't update the phone number");
    return -1;
  }

  @Override
  public int updateAddress(String id, String address, String city, String state,
      String zip) {
    Optional<Greengrocer> greengrocer = this.getUser(id);
    if (greengrocer.isPresent()) {
      greengrocer.get().setAddress(address);
      greengrocer.get().setCity(city);
      greengrocer.get().setState(state);
      greengrocer.get().setZip(zip);
      greengrocerRepository.save(greengrocer.get());
      System.out.println("Update the address");
      return 1;
    }
    System.out.println("Can't update the address");
    return -1;
  }
}
