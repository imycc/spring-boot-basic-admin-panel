package com.imyc.SBAP.Http.role.service.dataprocess;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.privilege.Privilege;
import com.imyc.SBAP.Http.privilege.dao.repository.PrivilegeRepository;
import com.imyc.SBAP.Http.privilege.viewobject.PrivilegeVO;
import com.imyc.SBAP.Http.role.action.RoleCreate;
import com.imyc.SBAP.Http.role.dao.Role;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.role.dao.repository.RoleSpecification;
import com.imyc.SBAP.Http.role.dto.RoleCreateDTO;
import com.imyc.SBAP.Http.role.viewobject.RoleCreateVO;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.role.viewobject.RoleVO;
import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import com.imyc.SBAP.Http.user.dao.User;
import com.imyc.SBAP.Http.user.viewobject.UserCreateVO;
import com.imyc.SBAP.config.repositroy.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleDatatableDPO {
    private RoleRepository roleRepo;
    private PrivilegeRepository privilegeRepo;

    @Autowired
    public RoleDatatableDPO(RoleRepository roleRepo, PrivilegeRepository privilegeRepo) {
        this.roleRepo = roleRepo;
        this.privilegeRepo = privilegeRepo;
    }

    // Index

    public RoleDatatableVO getRoleDatatableVO(DatatableServerSideConfig datatableServerSideConfig) {

        int draw = datatableServerSideConfig.getDraw();
        int start = datatableServerSideConfig.getStart();
        int length = datatableServerSideConfig.getLength();
        String keyword = datatableServerSideConfig.getKeyword();

        RoleDatatableVO roleDatatableVO = new RoleDatatableVO();
        List<RoleRow> roleRowList = new ArrayList<>();

        RoleSpecification spec1 = new RoleSpecification(new SearchCriteria("name", ":", keyword));

        Pageable pageRequest = PageRequest.of(start, length);
        Page<Role> allRole = roleRepo.findAll(Specification.where(spec1), pageRequest);

        for(Role role : allRole) {
            RoleRow roleRow = new RoleRow();
            roleRow
                    .setId(role.getId())
                    .setName(role.getName());

            roleRowList.add(roleRow);
        }

        roleDatatableVO
                .setDraw(draw)
                .setRecordsTotal(allRole.getTotalElements())
                .setRecordsFiltered(allRole.getTotalElements())
                .setData(roleRowList);

        return roleDatatableVO;
    }

    // Create
    public RoleCreateVO getPrivilegeList() {
        List<PrivilegeVO> privilegeVOList = getAllPrivilegeList();

        RoleCreateVO roleCreateVO = new RoleCreateVO();
        roleCreateVO.setPrivilegeList(privilegeVOList);

        return roleCreateVO;
    }

    public boolean roleCreate(RoleCreateDTO roleCreateDTO) {

        List<Integer> rawPrivilegeList = roleCreateDTO.getPrivileges();
        Set<Privilege> privilegeSet = new HashSet<>(privilegeRepo.findAllById(rawPrivilegeList));

        Role role = new Role();
        role
                .setName(roleCreateDTO.getName().toUpperCase())
                .setAdmin(roleCreateDTO.getAdmin())
                .setPrivileges(privilegeSet);

        roleRepo.save(role);

        return true;
    }

    // Other

    private List<PrivilegeVO> getAllPrivilegeList() {
        List<Privilege> privilegeList = privilegeRepo.findAll();

        List<PrivilegeVO> privilegeVOList = new ArrayList<>();
        for (Privilege privilege : privilegeList) {
            PrivilegeVO privilegeVO = new PrivilegeVO();
            privilegeVO
                    .setId(privilege.getId())
                    .setName(privilege.getName());

            privilegeVOList.add(privilegeVO);
        }

        return privilegeVOList;
    }

}
