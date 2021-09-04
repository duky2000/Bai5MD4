package Controller;

import Model.Customer;
import Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class CustomerController {
    private String upLoadFile = "C:\\Users\\DELL\\IdeaProjects\\CSDL&ORM\\src\\main\\webapp\\WEB-INF\\file\\";
    @Autowired
    CustomerService customerService;

    @RequestMapping("/customer")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list",customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String getCreate(){
       return "create";

    }
    @GetMapping("/edit")
    public ModelAndView getEdit(@RequestParam int index) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer", customerService.list.get(index));
        return modelAndView;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam int index){
        customerService.delete(index);
        return "redirect:/customer";
    }
    @PostMapping("/edit")
    public String editPost(@RequestParam("upFileImg") MultipartFile upFileImg, @ModelAttribute Customer customer) {
        String nameImg = upFileImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upFileImg.getBytes(), new File(upLoadFile + nameImg));
            customer.setImg(nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }

        customerService.edit(customer);
        return "redirect:/customer";
    }

    @PostMapping("/create")
    public String create(@RequestParam MultipartFile upImg, @ModelAttribute Customer customer) {
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(upLoadFile + nameImg));
            customer.setImg(nameImg);
        } catch (IOException e) {
            System.err.println("err upload file");
        }

        customerService.add(customer);
        return "redirect:/customer";
    }



}
