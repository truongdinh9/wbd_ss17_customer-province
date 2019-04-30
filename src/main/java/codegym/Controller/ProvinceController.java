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
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @Autowired
    CustomerService customerService;
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView=new ModelAndView("listP","provinces",provinceService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("province", new Province());
        return "createP";
    }
    @PostMapping("/save")
    public ModelAndView save(Province province ){
        provinceService.save(province);
        ModelAndView modelAndView =new ModelAndView("redirect:/province");
        modelAndView.addObject("message","Saved province");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        Province province=provinceService.findByID(id);
        ModelAndView modelAndView=new ModelAndView("editP");
        modelAndView.addObject("province",province);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Province province){
        provinceService.save(province);
        ModelAndView modelAndView=new ModelAndView("redirect:/province");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message","Edited province successfully");
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model){
        Province province=provinceService.findByID(id);
        model.addAttribute("province",province);
        return "deleteP";
    }
    @PostMapping("/remove")
    public String remove(Province province, RedirectAttributes redirect){
        provinceService.remove(province.getId());
        redirect.addFlashAttribute("message","Deleted province successfully");
        return "redirect:/province";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model){
        Province province=provinceService.findByID(id);
        Iterable<Customer> customers=customerService.findAllByProvince(province);
        model.addAttribute("province",province);
        model.addAttribute("customers",customers);
        return "viewP";
    }


}
