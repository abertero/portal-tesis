package portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import portal.model.Garage;
import portal.model.SaleOrder;
import portal.model.Technician;
import portal.model.user.User;

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

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerUser() {
        return doUser(new User(), true);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView("userList");
        mv.addObject("users", User.findAll());
        return mv;
    }

    @RequestMapping(value = "user/{altKeyUser}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable String altKeyUser, @RequestParam(required = false, defaultValue = "false") Boolean canEdit) {
        return doUser(User.findByAltKey(altKeyUser), canEdit != null && canEdit);
    }

    @RequestMapping(value = "technician", method = RequestMethod.GET)
    public ModelAndView technicianList() {
        ModelAndView mv = new ModelAndView("technicianList");
        mv.addObject("technicians", Technician.findAll());
        return mv;
    }

    @RequestMapping(value = "manager/technician/{altKeyTechnician}", method = RequestMethod.GET)
    public ModelAndView technicianDetail(@PathVariable String altKeyTechnician, @RequestParam(required = false, defaultValue = "false") Boolean canEdit) {
        return doTechnician(Technician.findByAltKey(altKeyTechnician), canEdit != null && canEdit);
    }

    @RequestMapping(value = "garage", method = RequestMethod.GET)
    public ModelAndView garageList() {
        ModelAndView mv = new ModelAndView("garageList");
        mv.addObject("garages", Garage.findAll());
        return mv;
    }

    @RequestMapping(value = "garage/{altKeyGarage}", method = RequestMethod.GET)
    public ModelAndView garageDetail(@PathVariable String altKeyGarage, @RequestParam(required = false, defaultValue = "false") Boolean canEdit) {
        return doGarage(Garage.findByAltKey(altKeyGarage), canEdit);
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ModelAndView saleOrderList() {
        ModelAndView mv = new ModelAndView("saleOrderList");
        mv.addObject("salesOrder", SaleOrder.findAll());
        return mv;
    }

    @RequestMapping(value = "order/{altKeyOrder}", method = RequestMethod.GET)
    public ModelAndView saleOrderDetail(@PathVariable String altKeyOrder, @RequestParam(required = false, defaultValue = "false") Boolean canEdit) {
        return doSaleOrder(SaleOrder.findByAltKey(altKeyOrder), canEdit);
    }

    @RequestMapping(value = "parking", method = RequestMethod.GET)
    public ModelAndView parkingList() {
        ModelAndView mv = new ModelAndView("parkingList");
        return mv;
    }

    //<editor-fold desc="Model">
    private ModelAndView doUser(User user, boolean canEdit) {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("user", user);
        mv.addObject("canEdit", canEdit);
        return mv;
    }

    private ModelAndView doTechnician(Technician technician, boolean canEdit) {
        ModelAndView mv = new ModelAndView("technician");
        mv.addObject("technician", technician);
        mv.addObject("canEdit", canEdit);
        return mv;
    }

    private ModelAndView doGarage(Garage garage, boolean canEdit) {
        ModelAndView mv = new ModelAndView("garage");
        mv.addObject("garage", garage);
        mv.addObject("canEdit", canEdit);
        return mv;
    }

    private ModelAndView doSaleOrder(SaleOrder saleOrder, boolean canEdit) {
        ModelAndView mv = new ModelAndView("saleOrder");
        mv.addObject("saleOrder", saleOrder);
        mv.addObject("canEdit", canEdit);
        return mv;
    }
    //</editor-fold>

    //<editor-fold desc="Actions">
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveUser(@ModelAttribute User user, BindingResult errors) {
        user.validateUserForm(errors);
        if (errors.hasErrors()) {
            return doUser(user, true);
        }
        boolean result = user.save();
        if (result) {
            return mainMenu();
        }
        return registerUser();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password) {
        boolean validate = User.validate(username, password);
        if (validate) {
            return mainMenu();
        }
        return applicationIndex();
    }

    @RequestMapping(value = "loggout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loggout() {
        return applicationIndex();
    }
    //</editor-fold>
}
