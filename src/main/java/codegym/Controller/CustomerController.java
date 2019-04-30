package codegym.Controller;

import codegym.model.Customer;
import codegym.model.Province;
import codegym.service.CustomerService;
import codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }
    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView;
        modelAndView=new ModelAndView("index","customers",customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView =new ModelAndView("create") ;
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        customerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("redirect:/customer");
        redirectAttributes.addFlashAttribute("message","Saved customer");
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
    public ModelAndView edit(Customer customer, RedirectAttributes redirect){
        customerService.save(customer);
        ModelAndView modelAndView=new ModelAndView("redirect:/customer");
        modelAndView.addObject("customer", customer);
        redirect.addFlashAttribute("message","Edited customer successfully");
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
