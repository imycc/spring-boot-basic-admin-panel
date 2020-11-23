package com.imyc.SBAP.Http.role.action;

import com.imyc.SBAP.Exception.web.WebDeleteDataException;
import com.imyc.SBAP.Http.role.service.requester.contracts.RoleDeleteRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleDelete {
    private RoleDeleteRequester roleDeleteRequester;

    @Autowired
    public RoleDelete(@Qualifier("RoleDatatableProvider") RoleDeleteRequester roleDeleteRequester) {
        this.roleDeleteRequester = roleDeleteRequester;
    }

    @PostMapping("/role/delete/{id}")
    public String handle(@PathVariable(value="id") int id) throws WebDeleteDataException {

        roleDeleteRequester.deleteRequest(id);

        return "redirect:/role?delete=success";
    }
}
