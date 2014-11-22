package portal.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import portal.config.ApplicationContants;
import portal.model.Garage;
import portal.model.SaleOrder;
import portal.model.Technician;
import portal.model.user.User;
import portal.model.views.SaleOrderHeaderView;
import portal.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView applicationIndex() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    private ModelAndView doMenu(HttpServletRequest request, String view) {
        ModelAndView mv = new ModelAndView(view);
        String username = SessionUtils.getProperty(request, ApplicationContants.SESSION_USERNAME);
        mv.addObject("user", StringUtils.isNotEmpty(username) ? User.findByUsername(username) : new User());
        return mv;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView mainMenu(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "mainMenu");
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
    public ModelAndView saleOrderList(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "saleOrderList");
        mv.addObject("salesOrder", SaleOrderHeaderView.findAll(1));
        return mv;
    }

    @RequestMapping(value = "order/{docNum}", method = RequestMethod.GET)
    public ModelAndView saleOrderDetail(@PathVariable Long docNum, @RequestParam(required = false, defaultValue = "false") Boolean canEdit, HttpServletRequest request) {
        return doSaleOrder(SaleOrder.findByDocNum(docNum), canEdit, request);
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

    private ModelAndView doSaleOrder(SaleOrder saleOrder, boolean canEdit, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "saleOrder");
        mv.addObject("saleOrder", saleOrder);
        mv.addObject("canEdit", canEdit);
        return mv;
    }
    //</editor-fold>

    //<editor-fold desc="Actions">
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveUser(@ModelAttribute User user, BindingResult errors, HttpServletRequest request) {
        user.validateUserForm(errors);
        if (errors.hasErrors()) {
            return doUser(user, true);
        }
        boolean result = user.save();
        if (result) {
            SessionUtils.addProperty(request, ApplicationContants.SESSION_USERNAME, user.getUsername());
            return new ModelAndView("redirect:menu");
        }
        return registerUser();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password,
                              HttpServletRequest request) {
        boolean validate = User.validate(username, password);
        if (validate) {
            SessionUtils.addProperty(request, ApplicationContants.SESSION_USERNAME, username);
            return new ModelAndView("redirect:menu");
        }
        return applicationIndex();
    }

    @RequestMapping(value = "loggout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loggout(HttpServletRequest request) {
        SessionUtils.removeProperty(request, ApplicationContants.SESSION_USERNAME);
        return applicationIndex();
    }
    //</editor-fold>
}
