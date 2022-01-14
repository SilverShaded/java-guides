package com.springinaction.tacocloud.Controller;

import com.springinaction.tacocloud.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder",new TacoOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("tacoOrder") TacoOrder order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: "+order);
        return "redirect:/";
    }
}
