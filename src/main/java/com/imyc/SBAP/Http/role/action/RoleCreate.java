package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebCreateDataException;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleCreateRequester;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RoleCreate {

    private RoleCreateRequester roleCreateRequester;

    @Autowired
    public RoleCreate(@Qualifier("RoleDatatableProvider") RoleCreateRequester roleCreateRequester) {
        this.roleCreateRequester = roleCreateRequester;
    }

    @GetMapping("/role/create")
    public ModelAndView render() {

        RoleCreateVO roleCreateVO = roleCreateRequester.createResponse();

        RoleCreateDTO roleCreateDTO = new RoleCreateDTO();

        ModelAndView modelAndView = new ModelAndView("admin-panel/role/create");
        modelAndView.addObject("roleCreateVO", roleCreateVO);
        modelAndView.addObject("roleCreateDTO", roleCreateDTO);

        return modelAndView;
    }

    @PostMapping("/role/create")
    public String handle(@Valid @ModelAttribute RoleCreateDTO roleCreateDTO) throws WebCreateDataException {

        roleCreateRequester.createRequest(roleCreateDTO);

        return "redirect:/role?create=success";
    }

}
