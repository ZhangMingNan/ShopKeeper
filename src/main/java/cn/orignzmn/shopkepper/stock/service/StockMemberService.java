package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StockMember;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;

public interface StockMemberService {

	List<StockMember> getData(PagVo pv);

	Integer total();

	void save(StockMember member);

	void delete(Long id);

}
