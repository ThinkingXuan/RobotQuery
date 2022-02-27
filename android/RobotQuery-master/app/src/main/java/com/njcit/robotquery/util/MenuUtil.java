package com.njcit.robotquery.util;

import com.njcit.robotquery.R;
import com.njcit.robotquery.constant.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 2017/5/11 0011.
 */

public class MenuUtil {
    public static List<Integer> getMenuRes(String role){
        //要移除的Menu ID
        List<Integer> menuRes = new ArrayList<>();

        switch (role){
            case Constants.ROLE_BOSS:

                break;
            case Constants.ROLE_DEPARTMENT_MANAGER:
                menuRes.add(R.id.menu_order);
                menuRes.add(R.id.menu_sales_account);
                menuRes.add(R.id.menu_product);
//                menuRes.add(R.id.menu_logistics);
//                menuRes.add(R.id.menu_position);
//                menuRes.add(R.id.menu_work);
                break;
            case Constants.ROLE_SALAS_MANAGER:
                menuRes.add(R.id.menu_employee_query);
                menuRes.add(R.id.menu_tendence);
                menuRes.add(R.id.menu_post);
                menuRes.add(R.id.menu_trains);
                menuRes.add(R.id.menu_check);
//                menuRes.add(R.id.menu_position);
//                menuRes.add(R.id.menu_work);

                break;
            case Constants.ROLE_COMMON_EMPLOYEE:
                menuRes.add(R.id.menu_order);
                menuRes.add(R.id.menu_sales_account);
                menuRes.add(R.id.menu_product);
//                menuRes.add(R.id.menu_logistics);
//                menuRes.add(R.id.menu_position);
//                menuRes.add(R.id.menu_work);
                menuRes.add(R.id.menu_employee_query);
                menuRes.add(R.id.menu_tendence);
                menuRes.add(R.id.menu_post);
                menuRes.add(R.id.menu_trains);
                menuRes.add(R.id.menu_check);
                break;
        }

        return menuRes;
    }

    public static int getMenuID(String rule) {
        int i = 0;
        switch (rule){
            case Constants.ROLE_BOSS:
                i = R.menu.tempate_tag_menu;
                break;
            case Constants.ROLE_DEPARTMENT_MANAGER:
                i = R.menu.tempate_person_tag;
                break;
            case Constants.ROLE_SALAS_MANAGER:
               i = R.menu.template_sale_tag;

                break;
            case Constants.ROLE_COMMON_EMPLOYEE:
                i = R.menu.tempate_tag_menu;
                break;
        }

        return i;
    }
}
