package portal.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import portal.config.ApplicationContants;
import portal.model.SaleOrder;
import portal.model.SaleOrderLog;
import portal.model.SaleOrderStatus;
import portal.model.Technician;
import portal.model.user.Role;
import portal.model.user.User;
import portal.model.views.MachineShopListView;
import portal.model.views.SaleOrderHeaderView;
import portal.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class ApplicationController {

    //<editor-fold desc="Views">
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView applicationIndex(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "redirect:menu", null);
        return mv;
    }

    private ModelAndView doMenu(HttpServletRequest request, String view, String backUrl) {
        ModelAndView mv = new ModelAndView("home");
        if (request != null) {
            String username = SessionUtils.getProperty(request, ApplicationContants.SESSION_USERNAME);
            if (!StringUtils.isEmpty(username)) {

                mv = new ModelAndView(view);
                mv.addObject("user", StringUtils.isNotEmpty(username) ? User.findByUsername(username) : new User());
                mv.addObject("usrRol", User.findByUsername(username));
                mv.addObject("backUrl", backUrl);
            }
        }
        return mv;
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView mainMenu(HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "mainMenu", null);
        return mv;
    }

    @RequestMapping(value = "registerUser", method = RequestMethod.GET)
    public ModelAndView registerUser(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doUser(new User(), true, backUrl, request);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView userList(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "userList", backUrl);
        mv.addObject("users", User.findAll());
        return mv;
    }

    @RequestMapping(value = "user/{altKeyUser}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable String altKeyUser, @RequestParam(required = false, defaultValue = "false") Boolean canEdit,
                                   @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doUser(User.findByAltKey(altKeyUser), canEdit != null && canEdit, backUrl, request);
    }

    @RequestMapping(value = "registerTechnician", method = RequestMethod.GET)
    public ModelAndView registerTechnician(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doTechnician(new Technician(), true, backUrl, request);
    }

    @RequestMapping(value = "technician", method = RequestMethod.GET)
    public ModelAndView technicianList(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "technicianList", backUrl);
        mv.addObject("technicians", Technician.findAll());
        return mv;
    }

    @RequestMapping(value = "technician/{altKeyTechnician}", method = RequestMethod.GET)
    public ModelAndView technicianDetail(@PathVariable String altKeyTechnician, @RequestParam(required = false, defaultValue = "false") Boolean canEdit,
                                         @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doTechnician(Technician.findByAltKey(altKeyTechnician), canEdit != null && canEdit, backUrl, request);
    }

    @RequestMapping(value = "machineShop", method = RequestMethod.GET)
    public ModelAndView machineShopList(@RequestParam(required = false, defaultValue = "0") Integer currentPage, @RequestParam(required = false) Integer numberOfPages,
                                        @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "machineShopList", backUrl);
        mv.addObject("machineShops", MachineShopListView.findAll(currentPage));
        mv.addObject("currentPage", currentPage);
        mv.addObject("numberOfPages", numberOfPages != null ? numberOfPages : MachineShopListView.findNumberOfPages());
        return mv;
    }

    @RequestMapping(value = "parking", method = RequestMethod.GET)
    public ModelAndView parkingLotList(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doMenu(request, "parkingLot", backUrl);
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ModelAndView saleOrderList(@RequestParam(required = false, defaultValue = "0") Integer currentPage, @RequestParam(required = false) Integer numberOfPages,
                                      @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "saleOrderList", backUrl);
        mv.addObject("salesOrder", SaleOrderHeaderView.findAll(currentPage));
        mv.addObject("currentPage", currentPage);
        mv.addObject("numberOfPages", numberOfPages != null ? numberOfPages : SaleOrderHeaderView.findNumberOfPages());
        return mv;
    }

    @RequestMapping(value = "order/{docNum}", method = RequestMethod.GET)
    public ModelAndView saleOrderDetail(@PathVariable Long docNum, @RequestParam(required = false, defaultValue = "false") Boolean canEdit,
                                        @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doSaleOrder(SaleOrder.findByDocNum(docNum, true), canEdit, backUrl, request);
    }

    @RequestMapping(value = "reports", method = RequestMethod.GET)
    public ModelAndView reports(@RequestParam(required = false) String backUrl, HttpServletRequest request) {
        return doMenu(request, "reports", backUrl);
    }
    //</editor-fold>

    //<editor-fold desc="Model">
    private ModelAndView doUser(User user, boolean canEdit, String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "user", backUrl);
        mv.addObject("user", user);
        mv.addObject("roles", Role.findAll());
        mv.addObject("canEdit", canEdit);
        return mv;
    }

    private ModelAndView doTechnician(Technician technician, boolean canEdit, String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "technician", backUrl);
        mv.addObject("technician", technician);
        mv.addObject("canEdit", canEdit);
        return mv;
    }

    private ModelAndView doSaleOrder(SaleOrder saleOrder, boolean canEdit, String backUrl, HttpServletRequest request) {
        ModelAndView mv = doMenu(request, "saleOrder", backUrl);
        mv.addObject("saleOrder", saleOrder);
        mv.addObject("canEdit", canEdit);
        mv.addObject("status", SaleOrderStatus.findAll());
        mv.addObject("history", SaleOrderLog.findBySaleOrder(saleOrder.getId()));
        return mv;
    }

    @RequestMapping(value = "findTechnicianByCode", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    List<Technician> findByCode(@RequestParam String code) {
        return Technician.findByCode(code);
    }
    //</editor-fold>

    //<editor-fold desc="Actions">
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveUser(@ModelAttribute User user, BindingResult errors,
                                 @RequestParam(required = false) String backUrl, @RequestParam(required = false) Long[] idRoles, HttpServletRequest request) {
        user.validateUserForm(errors);
        if (errors.hasErrors()) {
            return doUser(user, true, backUrl, request);
        }
        User userDB;
        if (user.getId() != null) {
            userDB = User.findById(user.getId());
            userDB.getData(user);
        } else {
            userDB = user;
        }
        boolean result = userDB.save();
        if (result) {
            //saleOrderDB.saveWithTechnicians(idTechnicians);
            userDB.saveWithRoles(idRoles);
            SessionUtils.addProperty(request, ApplicationContants.SESSION_USERNAME, user.getUsername());
            ModelAndView mv = new ModelAndView();
            mv.setView(new RedirectView(StringUtils.defaultString(backUrl, request.getContextPath() + "/user"), false));
//            return new ModelAndView("redirect:" + StringUtils.defaultString(backUrl, "user"));
            return mv;
        }
        return new ModelAndView("redirect:registerUser");
    }

    @RequestMapping(value = "saveTechnician", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveTechnician(@ModelAttribute Technician technician, BindingResult errors,
                                       @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        technician.validateTechnicianForm(errors);
        if (errors.hasErrors()) {
            return doTechnician(technician, true, backUrl, request);
        }
        Technician technicianDB;
        if (technician.getId() != null) {
            technicianDB = Technician.findById(technician.getId());
            technicianDB.getData(technician);
        } else {
            technicianDB = technician;
        }
        boolean result = technicianDB.save();
        if (result) {
            ModelAndView mv = new ModelAndView();
            mv.setView(new RedirectView(StringUtils.defaultString(backUrl, request.getContextPath() + "/technician"), false));
//            return new ModelAndView("redirect:" + StringUtils.defaultString(backUrl, "technician"));
            return mv;
        }
        return new ModelAndView("redirect:registerTechnician");
    }

    @RequestMapping(value = "saveOrder", method = RequestMethod.POST)
    @Transactional
    public ModelAndView saveOrder(@ModelAttribute SaleOrder saleOrder, BindingResult errors, @RequestParam(required = false) Long[] idTechnicians, @RequestParam(required = false, defaultValue = "0.0") Double comission,
                                  @RequestParam(required = false) String backUrl, HttpServletRequest request) {
        saleOrder.validateSaleOrderForm(errors);
        if (errors.hasErrors()) {
            return doSaleOrder(saleOrder, true, backUrl, request);
        }
        SaleOrder saleOrderDB;
        if (saleOrder.getId() != null) {
            saleOrderDB = SaleOrder.findById(saleOrder.getId());
        } else {
            saleOrderDB = saleOrder;
        }
        saleOrderDB.getData(saleOrder);
        String username = SessionUtils.getProperty(request, ApplicationContants.SESSION_USERNAME);
        boolean result = saleOrderDB.saveWithTechnicians(idTechnicians, comission, username);
        if (result) {
            ModelAndView mv = new ModelAndView();
            mv.setView(new RedirectView(StringUtils.defaultString(backUrl, request.getContextPath() + "/order"), false));
//            return new ModelAndView("redirect:" + request.getContextPath() + StringUtils.defaultString(backUrl, "order"));
            return mv;
        }
        return doSaleOrder(saleOrder, true, backUrl, request);
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
