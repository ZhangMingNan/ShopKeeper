package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StockMember;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;

public interface StockMemberDao {

	List<StockMember> getData(PagVo pv);

	Integer total();

	void save(StockMember member);

	void delete(Long id);

}
