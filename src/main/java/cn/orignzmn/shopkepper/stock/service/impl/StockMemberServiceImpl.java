package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.StockMemberDao;
import cn.orignzmn.shopkepper.stock.model.StockMember;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.service.StockMemberService;
@Service
public class StockMemberServiceImpl implements StockMemberService {

	@Autowired
	StockMemberDao stockMemberDao;
	@Override
	public List<StockMember> getData(PagVo pv) {
		// TODO Auto-generated method stub
		return stockMemberDao.getData(pv);
	}

	@Override
	public Integer total() {
		// TODO Auto-generated method stub
		return stockMemberDao.total();
	}

	@Override
	public void save(StockMember member) {
		// TODO Auto-generated method stub
		stockMemberDao.save(member);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		stockMemberDao.delete(id);
	}

}
