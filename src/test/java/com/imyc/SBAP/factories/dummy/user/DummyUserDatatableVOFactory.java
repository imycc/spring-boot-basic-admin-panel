package com.imyc.SBAP.factories.dummy.user;

import java.util.ArrayList;
import java.util.List;

import com.imyc.SBAP.Http.user.viewobject.UserDatatableVO;
import com.imyc.SBAP.Http.user.viewobject.datatable.UserRow;
import com.imyc.SBAP.factories.dummy.DummyFactory;

public class DummyUserDatatableVOFactory implements DummyFactory<UserDatatableVO>{

	private UserDatatableVO dummyUserDatatableVO;
	private List<UserRow> dummyUserRowList;
	
	public DummyUserDatatableVOFactory() {
		
		UserRow dummyUserRow = new DummyUserRowFactory().make();
		dummyUserRowList = new ArrayList<UserRow>();
		dummyUserRowList.add(dummyUserRow);
		
		dummyUserDatatableVO = new UserDatatableVO();
	}
	
	@Override
	public UserDatatableVO make() {
		
		dummyUserDatatableVO
			.setDraw(1)
			.setRecordsFiltered((long) 1) //dummyUserRowList only have 1 record;
			.setRecordsTotal((long) 1) //dummyUserRowList only have 1 record;
			.setData(dummyUserRowList);
		
		return dummyUserDatatableVO;
	}

}
