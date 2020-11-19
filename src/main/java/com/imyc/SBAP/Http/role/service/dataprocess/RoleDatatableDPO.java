package com.imyc.SBAP.Http.role.service.dataprocess;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.Http.role.dao.Roles;
import com.imyc.SBAP.Http.role.dao.repository.RoleRepository;
import com.imyc.SBAP.Http.role.dao.repository.RoleSpecification;
import com.imyc.SBAP.Http.role.viewobject.RoleDatatableVO;
import com.imyc.SBAP.Http.role.viewobject.datatable.RoleRow;
import com.imyc.SBAP.config.repositroy.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleDatatableDPO {
    private RoleRepository roleRepo;

    @Autowired
    public RoleDatatableDPO(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
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
        Page<Roles> allRole = roleRepo.findAll(Specification.where(spec1), pageRequest);

        for(Roles role : allRole) {
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
}
