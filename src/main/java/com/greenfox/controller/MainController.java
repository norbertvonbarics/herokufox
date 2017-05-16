package com.greenfox.controller;

import com.greenfox.Fox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  List<String> foods = new ArrayList<>(Arrays.asList("fish", "duck", "his tail", "dogfood"));
  List<String> drinks = new ArrayList<>(Arrays.asList("water", "his piss", "pond", "coke"));
  List<String> tricksToLearn = new ArrayList<>(Arrays.asList("jump", "hide", "bite", "run"));


  @Autowired
  private Fox mrGreen;

  @RequestMapping("/tamagotchi")
  public String tamagotchiControl(Model model) {
    model.addAttribute("tricks", mrGreen.getTricks());
    model.addAttribute("name", mrGreen.getName());
    model.addAttribute("food", mrGreen.getFood());
    model.addAttribute("drink", mrGreen.getDrink());
    model.addAttribute("trickSize", mrGreen.getTricks().size());
    return "index";
  }

  @RequestMapping("/nutritionStore")
  public String nutritionStore(Model model) {
    model.addAttribute("foods", foods);
    model.addAttribute("drinks", drinks);
    return "nutritionStore";
  }

  @RequestMapping("/nutritionStoreAdd")
  public String nutritionStoreAdd(@RequestParam("food") String food,
      @RequestParam("drink") String drink) {
    mrGreen.setFood(food);
    mrGreen.setDrink(drink);
    return "redirect:/nutritionStore";
  }

  @RequestMapping("/trickview")
  public String trickview(Model model) {
    model.addAttribute("tricksToLearn", tricksToLearn);
    return "trickview";
  }

  @RequestMapping("/addTricks")
  public String addTricks(@RequestParam("trick") String trick) {
    mrGreen.addTrick(trick);
    tricksToLearn.remove(trick);
    return "redirect:/trickview";
  }

}