package codegym.Controller;

import codegym.model.Customer;
import codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired CustomerService customerService;
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView=new ModelAndView("index","customers",customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @PostMapping("/save")
    public ModelAndView save(Customer customer ){
        customerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("redirect:/customer");
        modelAndView.addObject("message","Saved customer");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable long id){
        Customer customer=customerService.findById(id);
        ModelAndView modelAndView=new ModelAndView("edit");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView=new ModelAndView("redirect:/customer");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message","Edited customer successfully");
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model){
        Customer customer=customerService.findById(id);
        model.addAttribute("customer",customer);
        return "delete";
    }
    @PostMapping("/remove")
    public String remove(Customer customer, RedirectAttributes redirect){
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("message","Deleted customer successfully");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable long id, Model model){
        Customer customer=customerService.findById(id);
        model.addAttribute("customer",customer);
        return "view";
    }

}
