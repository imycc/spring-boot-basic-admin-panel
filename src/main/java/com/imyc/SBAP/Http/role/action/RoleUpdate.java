package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebPageNotFoundException;
import com.imyc.SBAP.Exception.web.WebUpdateDataException;
import com.imyc.SBAP.Http.role.dto.RoleUpdateDTO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleUpdateRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RoleUpdate {

    private RoleUpdateRequester roleUpdateRequester;

    @Autowired
    public RoleUpdate (@Qualifier("RoleDatatableProvider") RoleUpdateRequester roleUpdateRequester) {
        this.roleUpdateRequester = roleUpdateRequester;
    }

    @GetMapping("/role/update/{id}")
    public ModelAndView render(@PathVariable(value="id") int id) throws WebPageNotFoundException {

        RoleUpdateVO roleUpdateVO = roleUpdateRequester.updateResponse(id);

        RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO();

        ModelAndView modelAndView = new ModelAndView("admin-panel/role/update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("roleUpdateVO", roleUpdateVO);
        modelAndView.addObject("roleUpdateDTO", roleUpdateDTO);

        return modelAndView;
    }

    @PostMapping("/role/update/{id}")
    public String handle(@Valid @ModelAttribute RoleUpdateDTO roleUpdateDTO, @PathVariable(value="id") int id) throws WebUpdateDataException {

        roleUpdateRequester.updateRequest(roleUpdateDTO, id);

        return "redirect:/role?update=success";
    }

}
