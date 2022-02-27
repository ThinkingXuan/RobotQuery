package com.njcit.robotquery.constant;

/**
 * Created by youxuan on 2017/3/25 0025.
 */

public class Constants {


    public static final String ARGS = "args";

    //每页加载的数量
    public static final int PER_PAGE_COUNT = 10;
    // 聊天数据
    public static String CHAT_DATA = "chat";
    //每日推荐数据
    public static String DAY_RECOMMED_DATA = "DayRecommedData";
    //订阅数据
    public static final String SUBSCRIBE_DATA = "SubscribeData";

    /**
     * -------------------------用户登录信息--------------------------
     */

    public static final String USERID = "userId";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String RULE = "rule";
    public static final String LOGINSTATE = "loginState"; //0未登录,1已登录
    public static final String TEMPLATE_IS_FINISH = "template_is_finish";//0未完成，1、完成

    public static final String ROLE_BOSS = "老板";
    public static final String ROLE_BOSS_ID = "2"; // 权限id与服务器对应

    public static final String ROLE_DEPARTMENT_MANAGER = "人事部经理";
    public static final String ROLE_DEPARTMENT_MANAGER_IdD = "3";

    public static final String ROLE_SALAS_MANAGER = "销售部经理";
    public static final String ROLE_SALAS_MANAGER_ID = "4";

    public static final String ROLE_COMMON_EMPLOYEE = "普通员工";
    public static final String ROLE_COMMON_EMPLOYEE_ID = "5";

    /***-----------------------------------------------------*/

    public static final String MODEL_POSTOIN = "model_position";

    /**
     * ------------------------------------------------
     */
    public static final String ACTIVIIY_BUNDLE_KEY_TYPE = "type";
    public static final String ACTIVIIY_BUNDLE_KEY_OBJ = "message";


    /***-------------------------------------------------**/

    public static final String NO = "不限";
    public static final String TEMPLATE_EMPLOYYEE = "employee";
    public static final String ZH_EMPLOYYEE = "员工查询";
    public static final String TEMPLATE_EMPLOYEE_IS_FINISH = "template_employee_is_finish";
    public static final String EMPOLYEE_ID = "user_id";
    public static final String EMPLOYEE_AGE = "user_age";
    public static final String EMPLOYEE_GENDER = "user_gender";
    public static final String EMPLOYEE_ACOUNT = "count";
    public static final String EMPLOYEE_USERNAME = "user_username";
    public static final String EMPLOYEE_SALARY = "user_salary";
    public static final String EMPLOYEE_POSTION = "user_position";
    public static final String EMPLOYEE_WORK_INFO = "user_work_info";
    public static final String EMPLOYEE_WORK_TIME = "user_work_time";
    public static final String EMPLOYEE_SCHOOL = "user_school";
    public static final String EMPLOYEE_DEPARTMENT = "user_department";
    public static final String EMPLOYEE_HOMETOWN = "user_hometown";
    public static final String EMPLOYEE_EDUCATION = "user_education";
    public static final String EMPLOYEE_BIRTHDAY = "user_birthday";


    //考勤
    public static final String TEMPLATE_ATTENDANCE = "attendance";
    public static final String ZH_ATTENDANCE = "考勤查询";
    public static final String TEMPLATE_ATTENDANCE_IS_FINISH = "template_attendance_is_finish";
    public static final String ATTENDANCE_DATE = "attendance_date";

    //招聘
    public static final String TEMPLATE_POST = "post";
    public static final String ZH_POST = "招聘培训";
    public static final String TEMPLATE_POST_IS_FINISH = "template_post_is_finish";

    public static final String POST = "";

    //员工培训

    public static final String TEMPLATE_TRAIN = "train";
    public static final String ZH_TRAIN = "员工培训";
    public static final String TEMPLATE_TRAIN_IS_FINISH = "template_check_is_finish";

    //绩效考核
    public static final String TEMPLATE_CHECK = "check";
    public static final String ZH_CHECK = "绩效考核";
    public static final String CHECK_USERID = "user_id";
    public static final String TEMPLATE_CHECK_IS_FINISH = "template_check_is_finish";

    //薪资查询
    public static final String TEMPLATE_SALARY = "salary";
    public static final String ZH_SALARY = "薪资查询";
    public static final String TEMPLATE_SALARY_IS_FINISH = "template_salary_is_finish";

    //销售量查询
    public static final String TEMPLATE_SALESACOUNT = "salesAcount";

    //产品
    public static final String TEMPLATE_PRODUCT = "product";
    public static final String ZH_PRODUCT = "产品查询";
    public static final String TEMPLATE_PROUDUT_IS_FINISH = "template_salary_is_finish";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_TYPE = "type_id";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_STORAGECOUNT = "storage_count";

    //订单
    public static final String TEMPLATE_ORDER = "order";
    public static final String ZH_ORDER = "订单查询";
    public static final String TEMPLATE_ORDER_IS_FINISH = "template_order_is_finish";
    public static final String ORDER_NUMBER = "orderId";
    public static final String ORDER_USER = "ownerName";
    public static final String ORDER_START_DATE = "orderDate";
    public static final String ORDER_END_DATE = "getOrderDate";

    //销售量
    public static final String TEMPLATE_SALAS = "salas";
    public static final String TEMPLATE_SALAS_IS_FINISH = "template_salas_is_finish";
    public static final String SALAS_PRODUCTNAME = "salas_productName";
    public static final String SALAS_PRODUCTTYPE = "salas_productType";
    public static final String SALAS_QUARTER = "salas_quarter";              //销量的季度


    //消息处理类型 普通消息  模板消息  上下文消息
    public static final String MESSAGE_NORMAL = "NormalMessage";
    public static final String MESSAGE_TEMPLATE = "TemplateMessage";
    public static final String MESSAGE_CONTEXT = "ContextMessage";

}

