package com.njcit.service.impl;

import com.njcit.dao.sale.ProductMapper;
import com.njcit.dao.sale.SupplierMapper;
import com.njcit.dao.sale.TypeMapper;
import com.njcit.model.JsonModel.chartbean.sale.ProductModel;
import com.njcit.model.JsonModel.sale.ProductJsonModel;
import com.njcit.model.concrete_search_by_short_sql.sale.ProductSQL;
import com.njcit.model.sale.Product;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.ProductService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/12.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductMapper productMapper;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    SupplierMapper supplierMapper;

    @Override
    public String getProductWayByProduct(TemplateModelBean templateModelBean) {

        ProductSQL productSQL = new ProductSQL();
        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("product_id");
        String sql2 = (String) map.get("product_name");
        String sql3 = (String) map.get("supplier_id");
        String sql4 = (String) map.get("type_id");
        String sql5 = (String) map.get("per_count");
        String sql6 = (String) map.get("price");
        String sql7 = (String) map.get("storage_count");


        if (!(sql1 == null || sql1.equals(""))) {
            productSQL.setProduct_id(sql1);
        }

        if (!(sql2 == null || sql2.equals(""))) {
            productSQL.setProduct_name(sql2);
        }
        if (!(sql3 == null || sql3.equals(""))) {
            productSQL.setSupplier_id(sql3);
        }
        if (!(sql4 == null || sql4.equals(""))) {
            productSQL.setType_id(sql4);
        }
        if (!(sql5 == null || sql5.equals(""))) {
            productSQL.setPer_count(sql5);
        }
        if (!(sql6 == null || sql6.equals(""))) {
            productSQL.setPrice(sql6);
        }
        if (!(sql7 == null || sql7.equals(""))) {
            productSQL.setStorage_count(sql7);
        }
        List<Product> products = productMapper.selectProduct(productSQL);

        for(Product product:products){

            String typeId = product.getTypeId();
            product.setTypeId(typeMapper.getTypeNameByTypeId(typeId));

        }

        for(Product product:products){

            String supplierId =product.getSupplierId();

            product.setSupplierId(supplierMapper.getSupplierCompanyBySupplierId(supplierId));

        }



        ProductJsonModel productJsonModel = new ProductJsonModel();
        productJsonModel.setMessage("获取成功");
        productJsonModel.setCode("1");
        productJsonModel.setObject(products);


        String response = GsonUtil.ObjectToJson(productJsonModel,productJsonModel.getClass());
        return  response;
    }
}
