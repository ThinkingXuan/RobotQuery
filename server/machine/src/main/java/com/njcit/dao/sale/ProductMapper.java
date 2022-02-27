package com.njcit.dao.sale;
import com.njcit.model.concrete_search_by_short_sql.sale.ProductSQL;
import com.njcit.model.sale.Product;

import java.util.List;


public interface ProductMapper {

   List<Product> selectProduct(ProductSQL productSQL);
}