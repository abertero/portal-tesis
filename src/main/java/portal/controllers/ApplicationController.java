package portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView applicationIndex() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView mainMenu() {
        ModelAndView mv = new ModelAndView("mainMenu");
        return mv;
    }

    @RequestMapping(value = "garage/{altKeyGarage}", method = RequestMethod.GET)
    public ModelAndView garageList(@PathVariable String altKeyGarage) {
        ModelAndView mv = new ModelAndView("garageList");
        return mv;
    }

    @RequestMapping(value = "order/{altKeyOrder}", method = RequestMethod.GET)
    public ModelAndView saleOrder(@PathVariable String altKeyOrder) {
        ModelAndView mv = new ModelAndView("saleOrder");
        return mv;
    }

    @RequestMapping(value = "technician", method = RequestMethod.GET)
    public ModelAndView technicianList() {
        ModelAndView mv = new ModelAndView("technicianList");
        return mv;
    }
}
