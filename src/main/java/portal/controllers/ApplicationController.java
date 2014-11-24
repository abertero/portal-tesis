package portal.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import portal.config.ApplicationContants;
import portal.model.ParkingLot;
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
    public ModelAndView applicationIndex(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "redirect:menu");
        return mv;
    }

    private ModelAndView doMenu(HttpServletRequest request, String view) {
        ModelAndView mv = new ModelAndView("home");
        if (request != null) {
            String username = SessionUtils.getProperty(request, ApplicationContants.SESSION_USERNAME);
            if (!StringUtils.isEmpty(username)) {
                mv = new ModelAndView(view);
                mv.addObject("user", StringUtils.isNotEmpty(username) ? User.findByUsername(username) : new User());
            }
        }
        return mv;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView mainMenu(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "mainMenu");
        return mv;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerUser() {
        return doUser(new User(), true, false, null);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "userList");
        mv.addObject("users", User.findAll());
        return mv;
    }

    @RequestMapping(value = "user/{altKeyUser}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable String altKeyUser, @RequestParam(required = false, defaultValue = "false") Boolean canEdit, HttpServletRequest request) {
        return doUser(User.findByAltKey(altKeyUser), canEdit != null && canEdit, true, request);
    }

    @RequestMapping(value = "registerTechnician", method = RequestMethod.GET)
    public ModelAndView registerTechnician(HttpServletRequest request) {
        return doTechnician(new Technician(), true, request);
    }

    @RequestMapping(value = "technician", method = RequestMethod.GET)
    public ModelAndView technicianList(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "technicianList");
        mv.addObject("technicians", Technician.findAll());
        return mv;
    }

    @RequestMapping(value = "manager/technician/{altKeyTechnician}", method = RequestMethod.GET)
    public ModelAndView technicianDetail(@PathVariable String altKeyTechnician, @RequestParam(required = false, defaultValue = "false") Boolean canEdit, HttpServletRequest request) {
        return doTechnician(Technician.findByAltKey(altKeyTechnician), canEdit != null && canEdit, request);
    }

    @RequestMapping(value = "parkingLot", method = RequestMethod.GET)
    public ModelAndView parkingLotList(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "parkingLotList");
        mv.addObject("parkingLots", ParkingLot.findAll());
        return mv;
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ModelAndView saleOrderList(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "saleOrderList");
        mv.addObject("salesOrder", SaleOrderHeaderView.findAll(0));
        return mv;
    }

    @RequestMapping(value = "order/{docNum}", method = RequestMethod.GET)
    public ModelAndView saleOrderDetail(@PathVariable Long docNum, @RequestParam(required = false, defaultValue = "false") Boolean canEdit, HttpServletRequest request) {
        return doSaleOrder(SaleOrder.findByDocNum(docNum, true), canEdit, request);
    }

    //<editor-fold desc="Model">
    private ModelAndView doUser(User user, boolean canEdit, boolean withSession, HttpServletRequest request) {
        ModelAndView mv = withSession ? doMenu(request, "user") : new ModelAndView("user");
        mv.addObject("user", user);
        mv.addObject("canEdit", canEdit);
        mv.addObject("withSession", withSession);
        return mv;
    }

    private ModelAndView doTechnician(Technician technician, boolean canEdit, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "technician");
        mv.addObject("technician", technician);
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
    public ModelAndView saveUser(@ModelAttribute User user, BindingResult errors, @RequestParam(required = false, defaultValue = "true") Boolean withSession, HttpServletRequest request) {
        user.validateUserForm(errors);
        if (errors.hasErrors()) {
            return doUser(user, true, withSession != null && withSession, request);
        }
        boolean result = user.save();
        if (result) {
            SessionUtils.addProperty(request, ApplicationContants.SESSION_USERNAME, user.getUsername());
            return new ModelAndView("redirect:userLists");
        }
        return registerUser();
    }

    @RequestMapping(value = "saveTechnician", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveTechnician(@ModelAttribute Technician technician, BindingResult errors, HttpServletRequest request) {
        technician.validateUserForm(errors);
        if (errors.hasErrors()) {
            return doTechnician(technician, true, request);
        }
        boolean result = technician.save();
        if (result) {
            return new ModelAndView("redirect:technician");
        }
        return registerTechnician(null);
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
        return applicationIndex(null);
    }

    @RequestMapping(value = "loggout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loggout(HttpServletRequest request) {
        SessionUtils.removeProperty(request, ApplicationContants.SESSION_USERNAME);
        return applicationIndex(null);
    }
    //</editor-fold>
}
