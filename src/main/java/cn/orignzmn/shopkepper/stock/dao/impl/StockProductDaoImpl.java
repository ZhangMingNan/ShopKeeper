package cn.orignzmn.shopkepper.stock.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.api.model.DayReport;
import cn.orignzmn.shopkepper.api.model.SizeTotal;
import cn.orignzmn.shopkepper.api.model.StockInfo;
import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.dao.StockProductDao;
import cn.orignzmn.shopkepper.stock.model.ProductSize;
import cn.orignzmn.shopkepper.stock.model.ProductType;
import cn.orignzmn.shopkepper.stock.model.StockIn;
import cn.orignzmn.shopkepper.stock.model.StockInDetail;
import cn.orignzmn.shopkepper.stock.model.StockOut;
import cn.orignzmn.shopkepper.stock.model.StockOutDetail;
import cn.orignzmn.shopkepper.stock.model.StockProduct;
import cn.orignzmn.shopkepper.stock.model.StockType;
import cn.orignzmn.shopkepper.stock.model.TypeTotal;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.model.vo.ProductNoVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductDgVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductTotalVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockReprotVO;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
@Repository
public class StockProductDaoImpl  implements StockProductDao{
	private static final	String updateStockProductSql = "UPDATE `stock_stock_product` SET `type`=?, `product_no`=?, `size`=?,`bar_code`=? WHERE (`id`=?)";
	private static  final String findAllSql = "SELECT"
			+ "(SELECT ID FROM STOCK_PRODUCT_TYPE PT WHERE SP.TYPE = PT.ID),"
			+ "(SELECT TYPE FROM STOCK_PRODUCT_TYPE PT WHERE SP.TYPE = PT.ID),"
			+"SP.BAR_CODE,"
			+ "sp.product_no,"
			+ "(select pno.no from stock_product_no pno where pno.id = sp.product_no),"
			+ "SP.COLOR,"
			+ "(select pno.selling_price from stock_product_no pno where pno.id = sp.product_no ),"
			+ "(SELECT ID FROM STOCK_PRODUCT_SIZE PS WHERE SP.SIZE = PS.ID),"
			+ "(SELECT SIZE FROM STOCK_PRODUCT_SIZE PS WHERE SP.SIZE = PS.ID),"
			+ "SP.ID,"
			+ "SP.DATE"
			+ " FROM STOCK_STOCK_PRODUCT SP";
	private static final String findAllTypeSql = "SELECT id,type FROM stock_product_type";



	private static final String findAllSizeSql = "select id,size from stock_product_size";
	private static final String deleteStockProductSql  = "delete from stock_stock_product where id = ? ";
	private static final String createStockProductSql  = "insert into stock_stock_product("
			+ "type,"
			+ "product_no,"
			+ "size, "
			+ "date,"
			+ "bar_code) values(?,?,?,?,UPPER(?))";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<StockProduct> findAll(StockProductDgVO pv) {
		List<Object> params = Lists.newArrayListWithCapacity(3);
		StringBuilder sb = createSql(findAllSql,pv, params);
		return jdbcTemplate.query(sb.toString(),new StockProductMapper(),params.toArray());
	}

	private StringBuilder createSql(String baseSql,StockProductDgVO pv, List<Object> params) {
		StringBuilder sb = createTotalSql(baseSql, pv, params);
		sb.append( " limit ?,?");
		params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
		params.add(pv.getRows());
		return sb;
	}

	private StringBuilder createTotalSql(String baseSql, StockProductDgVO pv,
			List<Object> params) {
		StringBuilder sb = new StringBuilder(baseSql);
		sb.append(" where 1=1 and sp.status = 0 ");
		if(pv.isNotNull()){
			if(pv.getDate()!=null){
				sb.append(" and DATE(sp.date) = DATE(?)");
				params.add(pv.getDate());
			}
			if(pv.getTypeId()!=null){
				sb.append(" and  sp.type = ?");
				params.add(pv.getTypeId());
			}
			if(StringUtils.isNotBlank(pv.getProductNo())){
				sb.append(" and  sp.PRODUCT_NO = ?");
				params.add(pv.getProductNo());
			}
			if(StringUtils.isNotBlank(pv.getSort())){        
				String sort = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,pv.getSort()); 
				sb.append(" order by SP."+sort+" "+pv.getOrder());
			}
		}
		return sb;
	}
	private static final class StockProductMapper implements RowMapper<StockProduct> {
		public StockProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			StockProduct product = new StockProduct();
			int columnIndex = 1;
			product.getProductType().setId(rs.getLong(columnIndex++));
			product.getProductType().setType(rs.getString(columnIndex++));
			product.setBarCode(rs.getString(columnIndex++));
			product.getProductNo().setId(rs.getLong(columnIndex++));
			product.getProductNo().setNo(rs.getString(columnIndex++));
			product.setColor(rs.getString(columnIndex++));
			product.setSellingPrice(rs.getFloat(columnIndex++));
			product.getProductSize().setId(rs.getLong(columnIndex++));
			product.getProductSize().setSize(rs.getString(columnIndex++));
			product.setId(rs.getLong(columnIndex++));
			product.setDate(rs.getDate(columnIndex++));
			return product;
		}
	}
	@Override
	public List<ProductType> findAllType() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(findAllTypeSql, new BeanPropertyRowMapper<ProductType>(ProductType.class));
	}

	@Override
	public List<ProductSize> findAllSize() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(findAllSizeSql, new BeanPropertyRowMapper<ProductSize>(ProductSize.class));
	}
	@Override
	public void createStockProduct(final StockProduct stockProduct) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(createStockProductSql, new String[]{"id"});
				int count = 1;
				psst.setLong(count++, stockProduct.getProductType().getId());
				psst.setLong(count++, stockProduct.getProductNo().getId());
				psst.setLong(count++, stockProduct.getProductSize().getId());
				psst.setTimestamp(count++, new Timestamp(System.currentTimeMillis()));
				psst.setString(count++,stockProduct.getBarCode());
				return psst;
			}
		});
	}
	@Override
	public void deleteStockProduct(Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(deleteStockProductSql,id);
	}
	@Override
	public void updateStockProduct(final StockProduct stockProduct) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(updateStockProductSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int parameterIndex =1;
				ps.setLong(parameterIndex++,stockProduct.getProductType().getId());
				ps.setLong(parameterIndex++,stockProduct.getProductNo().getId());
				ps.setLong(parameterIndex++, stockProduct.getProductSize().getId());
				ps.setString(parameterIndex++, stockProduct.getBarCode());
				ps.setLong(parameterIndex++, stockProduct.getId());
			}
		});
	}
	@Override
	public StockProduct getByBarCode(String barCode) {
		// TODO Auto-generated method stub
		String sql =findAllSql +  " where SP.BAR_CODE = ? and status = 0"; //获取带销售
		List<StockProduct> splist=jdbcTemplate.query(sql,new StockProductMapper(),barCode);	
		if(splist.size()!=0){
			StockProduct sp = splist.get(0);
			return sp;
		}
		return null;
	}
	@Override
	public Integer total(StockProductDgVO pv) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from stock_stock_product sp";     
		List<Object> params = Lists.newArrayListWithCapacity(3);
		StringBuilder sb = createTotalSql(sql, pv, params);

		return jdbcTemplate.queryForObject(sb.toString(),Integer.class,params.toArray());
	}
	@Override
	public List<ProductNoVO> allProductNo() {
		// TODO Auto-generated method stub
		String allProductNoSql = "select id,no from stock_product_no";
		return jdbcTemplate.query(allProductNoSql, new BeanPropertyRowMapper<>(ProductNoVO.class));
	}

	final static String getStockInfoSql ="SELECT spn.no as productNo,\n" +
			"COUNT(ssp.id) stockNumber ,\n" +
			"ssp.product_no productNoId,\n" +
			"(SELECT spn.selling_price from stock_product_no spn where spn.id = ssp.product_no) sellingPrice,\n" +
			"(SELECT spn.product_icon_url from stock_product_no spn where spn.id = ssp.product_no) productIconUrl \n" +
			"from stock_stock_product ssp ,stock_product_no spn  where ssp.product_no = spn.id  and ssp.status = 0  ";

	final static	String getSizeByProNoSql = "select (SELECT sps.size FROM stock_product_size sps where ssp.size = sps.id) sizeLabel,count(ssp.product_no) number from stock_stock_product ssp where ssp.product_no = ? and ssp.status = 0  group by sizeLabel ";

	@Override
	public List<StockInfo> getStockInfo(StockReprotVO pv) {

		List<Object> params = Lists.newArrayList();
		StringBuilder sb = new StringBuilder(getStockInfoSql);

		if(StringUtils.isNotEmpty(pv.getShortNo())){
			//根据货号获取货号id
			sb.append("and spn.short_no = ? ");
			params.add(pv.getShortNo());
		}
		if(pv.getType()!=null){
			//根据货号获取货号id
			sb.append("and spn.type = ? ");
			params.add(pv.getType());
		}
		sb.append(" GROUP BY spn.no");
		if(pv.getPage()!=null&&pv.getRows()!=null){
			sb.append(" limit ?,? ");
			params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
			params.add(pv.getRows());
		}
		List<StockInfo> list= jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(StockInfo.class),params.toArray());
		for (StockInfo si:list ){
			List<SizeTotal> sizeList = jdbcTemplate.query(getSizeByProNoSql, new BeanPropertyRowMapper<>(SizeTotal.class),si.getProductNoId());
			si.setSizeList(sizeList);
		}
		return list;
	}

	static final String getStockInfoTotalSql = "SELECT count(DISTINCT ssp.product_no) FROM"
			+ " stock_product_no spn , stock_stock_product ssp where spn.id = ssp.product_no and ssp.status=0  ";
	
	@Override
	public Integer getStockInfoTotal(StockReprotVO pv){

		List<Object> params = Lists.newArrayList();
		StringBuilder sb = new StringBuilder(getStockInfoTotalSql);
		if (StringUtils.isNotEmpty(pv.getShortNo())){
			sb.append("and spn.short_no= ?");
			params.add(pv.getShortNo());
		}
		if(pv.getType()!=null){
			//根据货号获取货号id
			sb.append("and spn.type = ? ");
			params.add(pv.getType());
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class,params.toArray());
	}
	@Override
	public DayReport dayReport(String date) {

		// TODO Auto-generated method stub
		String sql =  "SELECT\n" +
				"stock_day_report.id,\n" +
				"stock_day_report.volumen,\n" +
				"stock_day_report.saleroom,\n" +
				"stock_day_report.profit,\n" +
				"stock_day_report.date\n" +
				"FROM\n" +
				"stock_day_report WHERE ? = DATE(date)";
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<DayReport>(DayReport.class),date);
	}
	static final	String getNoIdSql = "select id from stock_product_no where no = ?";
	@Override
	public Long getNoId(String productNo) {
		// TODO Auto-generated method stub

		Long id = null;
		try {
			id = jdbcTemplate.queryForObject(getNoIdSql, Long.class,productNo);
		} catch (EmptyResultDataAccessException e) {}
		return id;
	}

	@Override
	public Long getSizeId(String size) {
		// TODO Auto-generated method stub
		String sql = "select id from stock_product_size where size = ?";
		Long id = null;
		try {
			id = jdbcTemplate.queryForObject(sql, Long.class,size);
		} catch (EmptyResultDataAccessException e) {}
		return id;
	}

	@Override
	public void stockOut(List<StockProductVO> list) {
		String stockOutSql = "update stock_stock_product set status='1' where id = ? ";
		// TODO Auto-generated method stub
		for(StockProductVO spvo:list){
			try {
				Long id =jdbcTemplate.queryForObject("SELECT id FROM stock_stock_product WHERE bar_code = UPPER(?) and status  = 0 LIMIT 0,1", Long.class,spvo.getBarCode());	
				if(id !=null){
					jdbcTemplate.update(stockOutSql, id);
				}else{ 
					System.out.println("没有找到:"+spvo.getBarCode());
				}
			} catch (DataAccessException e) {}
		}
	}

	@Override
	public List<TypeTotal> groupByType(PagVo pv){
		String sql = "SELECT spt.type,COUNT(ssp.id) total FROM stock_product_type spt LEFT JOIN stock_stock_product ssp   ON ssp.type = spt.id and ssp.status = 0 GROUP BY spt.id";
		StringBuilder sb = new StringBuilder(sql);
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<TypeTotal>(TypeTotal.class));
	}

	@Override
	public Integer groupByTypeTotal() {
		// TODO Auto-generated method stubø
		return jdbcTemplate.queryForObject("select count(*) from stock_product_type", Integer.class);
	}
	@Override
	public Integer stockInTotal() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(*) from stock_stock_in ", Integer.class);
	}
	@Override
	public List<StockIn> stockInList(PagVo pv){
		String sql = "SELECT ssi.id,ssi.date ,ssi.total,(select spt.type from stock_product_type spt where spt.id = ssi.type_id) as type FROM stock_stock_in ssi order by ssi.date desc";
		StringBuilder sb = new StringBuilder(sql);
		List<Object> params = Lists.newArrayListWithCapacity(2);
		if(pv.getPage()!=null&&pv.getRows()!=null){
			sb.append(" limit ?,? ");
			params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
			params.add(pv.getRows());
		}
		return jdbcTemplate.query(sb.toString(), new StockInRowMapper(),params.toArray());
	}
	
	@Override
	public Integer stockOutTotal() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(*) from stock_stock_out ", Integer.class);
	}
	@Override
	public List<StockOut> stockOutList(PagVo pv){
		String sql = "SELECT sso.id,sso.date ,sso.total,(select sst.type from stock_stockout_type sst where sst.id = sso.type_id)  type FROM stock_stock_out sso order by sso.date desc";
		StringBuilder sb = new StringBuilder(sql);
		List<Object> params = Lists.newArrayListWithCapacity(2);
		if(pv.getPage()!=null&&pv.getRows()!=null){
			sb.append(" limit ?,? ");
			params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
			params.add(pv.getRows());
		}
		return jdbcTemplate.query(sb.toString(), new StockOutRowMapper(),params.toArray());
	}
	private static final class StockOutRowMapper implements RowMapper<StockOut> {
		@Override
		public StockOut mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			StockOut sin = new StockOut();
	    	int columnIndex = 1;
	    	sin.setId(rs.getLong(columnIndex++));
	    	sin.setDate(rs.getDate(columnIndex++));
	    	sin.setTotal(rs.getInt(columnIndex++));
	    	sin.setType(rs.getString(columnIndex++));
			return sin;
		}
	}
	private static final class StockInRowMapper implements RowMapper<StockIn> {
		@Override
		public StockIn mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			StockIn sin = new StockIn();
	    	int columnIndex = 1;
	    	sin.setId(rs.getLong(columnIndex++));
	    	sin.setDate(rs.getDate(columnIndex++));
	    	sin.setTotal(rs.getInt(columnIndex++));
	    	sin.setType(rs.getString(columnIndex++));
			return sin;
		}
	}
	
	@Override
	public List<StockInDetail> stockInDetailById(Long id) {
		// TODO Auto-generated method stub
		String stockInDetailByIdSql = "select id,no,size_list as sizeList,stock_in_id,total from stock_stock_in_detail where stock_in_id = ?";
		return jdbcTemplate.query(stockInDetailByIdSql, new BeanPropertyRowMapper<StockInDetail>(StockInDetail.class),id) ;
	}

	@Override
	public Long saveStockIn(final StockIn sin) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String saveStockInSql = "insert into `stock_stock_in` (`date`,`total`,`type_id`)values(now(),?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(saveStockInSql, new String[]{"id"});
				int count = 1;
				psst.setInt(count++,sin.getTotal());
				psst.setLong(count++, sin.getTypeId());
				return psst;
			}
		},keyHolder);
        return keyHolder.getKey().longValue();
	}
	@Override
	public void saveStockInDetail(Long id, List<StockProductTotalVO> sptvo) {
		// TODO Auto-generated method stub
		String sql = "insert into `stock_stock_in_detail` (`no`,`size_list`,`stock_in_id`,total)values(?,?,?,?)";
		List<Object[]> batchArgs  = Lists.newArrayList();
		for(StockProductTotalVO spvo:sptvo){
			batchArgs.add(new Object[]{spvo.getProductNo(),spvo.getSizeDetail(),id,spvo.getTotal()});
		}
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Override
	public List<StockOutDetail> stockOutDetailById(Long id) {
		String stockInDetailByIdSql = "select id,no,size_list as sizeList,stock_out_id,total from stock_stock_out_detail where stock_out_id = ?";
		return jdbcTemplate.query(stockInDetailByIdSql, new BeanPropertyRowMapper<StockOutDetail>(StockOutDetail.class),id) ;
	}
	@Override
	public void saveStockOutDetail(Long id, List<StockProductTotalVO> sptvo) {
		// TODO Auto-generated method stub
		String sql = "insert into `stock_stock_out_detail` (`no`,`size_list`,`stock_out_id`,total)values(?,?,?,?)";
		List<Object[]> batchArgs  = Lists.newArrayList();
		for(StockProductTotalVO spvo:sptvo){
			batchArgs.add(new Object[]{spvo.getProductNo(),spvo.getSizeDetail(),id,spvo.getTotal()});
		}
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	@Override
	public Long saveStockOut(final StockOut sout,final Long typeId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String saveStockOutSql = "insert into `stock_stock_out` (`date`,`total`,type_id)values(now(),?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(saveStockOutSql, new String[]{"id"});
				int count = 1;
				psst.setInt(count++,sout.getTotal());
				psst.setLong(count++, typeId);
				return psst;
			}
		},keyHolder);
        return keyHolder.getKey().longValue();
	}
	@Override
	public List<StockType> allStockType(){
		String sql = "select id,type from stock_stockout_type";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StockType.class));
	}

	@Override
	public List<ProductNoVO> allShortProductNo() {
		// TODO Auto-generated method stub
		String allShortProductNoSql = "select DISTINCT short_no as shortNo from stock_product_no";
		return jdbcTemplate.query(allShortProductNoSql, new BeanPropertyRowMapper<>(ProductNoVO.class));
	}
static final String noTreeByShotNoSql = "select id as id, short_no as shortNo,no as no from stock_product_no where available=1 and short_no = ?";
	@Override
	public List<ProductNoVO> noTreeByShotNo(List<String> noList) {
		// TODO Auto-generated method stub
		List<ProductNoVO> list = Lists.newArrayList();
		for(String shortNo:noList){
			list.addAll(jdbcTemplate.query(noTreeByShotNoSql, new BeanPropertyRowMapper<>(ProductNoVO.class),shortNo));
		}
		return list;
	}

	@Override
	public List<StockInfo> getStockInfoNoId(List<String> noIds) {
		StringBuilder sb = new StringBuilder(getStockInfoSql+"and spn.id=? GROUP BY spn.no");
		List<StockInfo> list = Lists.newArrayList();
		for(String id : noIds){
			List<StockInfo> infoList= jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(StockInfo.class),id);
			for (StockInfo si:infoList ){
				List<SizeTotal> sizeList = jdbcTemplate.query(getSizeByProNoSql, new BeanPropertyRowMapper<>(SizeTotal.class),si.getProductNoId());
				si.setSizeList(sizeList);
			}
			list.addAll(infoList);
	 }
		return list;
	}
}