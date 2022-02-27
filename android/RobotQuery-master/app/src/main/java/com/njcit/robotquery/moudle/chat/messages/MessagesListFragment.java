package com.njcit.robotquery.moudle.chat.messages;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.njcit.robotquery.MainActivity;
import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseApplication;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.ObjType;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.ChatMessage;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.PostBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.TrainBean;
import com.njcit.robotquery.bean.server.chartbean.LineBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.DataCache;
import com.njcit.robotquery.databinding.ActivityMessagesListLayoutBinding;
import com.njcit.robotquery.http.ApiManage;
import com.njcit.robotquery.adapter.listener.RequestImpl;
import com.njcit.robotquery.moudle.chat.ChatDateModel;
import com.njcit.robotquery.moudle.chat.ChatSamplesListAdapter;
import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;
import com.njcit.robotquery.moudle.datadetail.DataDetailActivity;
import com.njcit.robotquery.moudle.modelconfigure.ModelListFragment;
import com.njcit.robotquery.util.AlertUtil;
import com.njcit.robotquery.util.CommonUtil;
import com.njcit.robotquery.data.DataUtil;
import com.njcit.robotquery.util.JsonParser;
import com.njcit.robotquery.util.MenuUtil;
import com.njcit.robotquery.util.RxServiceUtil;
import com.njcit.robotquery.util.SendMessageUtil;
import com.njcit.robotquery.util.ShareUtil;
import com.njcit.robotquery.util.SharedPreUtil;
import com.njcit.robotquery.util.SoftInputUtil;
import com.njcit.robotquery.util.UserStatusUtil;
import com.njcit.robotquery.view.chatUI.commons.ImageLoader;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.OnLoadMoreListener;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.OnMessageClickListener;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.OnMessageLongClickListener;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.SelectionListener;
import com.njcit.robotquery.view.chatUI.commons.models.IMessage;
import com.njcit.robotquery.view.chatUI.messages.MessageInput;
import com.njcit.robotquery.view.chatUI.messages.MessagesListAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.njcit.robotquery.moudle.chat.ChatSamplesListAdapter.ChatSample.Type.CUSTOM_VIEW_HOLDER;

public class MessagesListFragment extends BaseFragment<ActivityMessagesListLayoutBinding> implements
        SelectionListener,
        OnMessageClickListener,
        OnMessageLongClickListener {

    private static final String ARG_TYPE = "type";
    private static final String TAG = "MessagesListActivity";
    private MessagesListAdapter<MessagesListFixtures.Message> adapter;     //RecyclerView的适配器

    private int selectionCount;
    private Toolbar toolbarActivity;
    private Menu menu;
    private ChatSamplesListAdapter.ChatSample.Type type;

    private int mPage = 1;             //当前页
    private DataCache mACache;
    private ChatMessage mChatMessage;
    private ChatDateModel mModel;
    private String userId = "1454739828";

    private String str_model_query;

    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private SharedPreferences mSharedPreferences;
    int ret = 0; // 函数调用返回值
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    private List<MessagesListFixtures.Message> messagesCache;
    private TemplateBean templateBean;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onCreate: +MessagesListActivity");
        type = CUSTOM_VIEW_HOLDER;

        Log.d(TAG, "onCreate: 初始化缓存");
        mACache = new DataCache(getContext());
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);

        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getActivity(), mInitListener);
        mSharedPreferences = getContext().getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);
        initView();
        initData();


        //发送消息回调
        inputMessage_Nostatus();
    }

    @Override
    public int setContent() {
        return R.layout.activity_messages_list_layout;
    }


    private void initView() {
        showContentView();
        setHasOptionsMenu(true); //设置开启Fragment的menu
        initMessagesAdapter();

        //初始化
        MessagesListFixtures.Message message =  SendMessageUtil.getMessage(1,"欢迎使用机器人小蜜","");
        updateData_Start(message,true,true);

    }

    private void initData() {

        // 获取用户的userId;
        userId = UserStatusUtil.getUserInfo(getContext(), Constants.USERID);

        messagesCache = mACache.getCache(); //从缓存中获取数据
//        Log.d(TAG, "initData: "+messagesCache.size());
//        Log.d(TAG, "initData: "+messagesCache.get(messagesCache.size()-1).getView());
        mPage = getCurrentPage();
        if (messagesCache != null && messagesCache.size() > 0) {
            showContentView();      //显示页面
            setAdapter();

        } else {

            loadChatHistory();
        }
    }

    //从缓存中加载的数据
    private void setAdapter() {
        adapter.addToEnd(messagesCache, true);
    }

    private void loadChatHistory() {
        mModel = new ChatDateModel();
        mModel.setData(userId, mPage, Constants.PER_PAGE_COUNT);
        Log.d(TAG, "loadChatHistory: " + mModel);
        mModel.getChatHistory(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                mChatMessage = (ChatMessage) object;

                if (null != mChatMessage && null != mChatMessage.getObject() && mChatMessage.getObject().size() > 0) {

                    List<MessagesListFixtures.Message> datas = DataUtil.getMessage(mChatMessage);
                    Log.d(TAG, "loadSuccess: " + mChatMessage.getObject().toString());
                    adapter.addToEnd(datas, true);
                    if (mPage <= mACache.getPage()) {
                        mACache.addCache(datas);
                    }
                    mPage++;
                }
            }


            @Override
            public void loadFailed() {

            }

            @Override
            public void addSubscription(Subscription subscription) {
                //   addSubscription(subscription);
            }
        });
    }


    //用户发消息。加载机器人的回复消息
    private void loadRobotMessage(String user_username, String content) {

        Subscription observable = ApiManage.getInstence().getChatService().getChatMessage(user_username, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChatMessage>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(ChatMessage chatMessage) {
                        Log.d(TAG, "onNext: " + chatMessage.toString());
                        MessagesListFixtures.Message message = DataUtil.messageConveter(chatMessage);
                        adapter.addToStart(message, true);
                        mACache.addCache(message);      //加入缓存

                    }
                });

        addSubscription(observable);
    }

    private void initMessagesAdapter() {

        //机器人头像
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //Glide.with(MessagesListActivity.this).load(url).into(imageView);
            }
        };

        //根据布局去加载相应的数据,两个ViewHolder绑定不同的消息。
        if (type == CUSTOM_VIEW_HOLDER) {
            MessagesListAdapter.HoldersConfig holdersConfig = new MessagesListAdapter.HoldersConfig();
            holdersConfig.setIncoming(CustomIncomingMessageViewHolder.class, R.layout.item_custom_holder_incoming_message);
            holdersConfig.setOutcoming(CustomOutcomingMessageViewHolder.class, R.layout.item_custom_holder_outcoming_message);
            adapter = new MessagesListAdapter<>("0", holdersConfig, imageLoader);

            adapter.setOnMessageClickListener(this);
            adapter.setOnMessageLongClickListener(this);

            //点击消息回调
            //adapter.enableSelectionMode(this);     //打开长按信息删除菜单
        } else {
            adapter = new MessagesListAdapter<>("1", imageLoader);

        }

      //  adapter.addToStart(new MessagesListFixtures.Message(), false);

        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadChatHistory();

            }
        });

        mBindingView.messagesList.setAdapter(adapter);


        //滑动RecyclerView隐藏软键盘
        mBindingView.messagesList.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (Math.abs(dy) > 20) {
                    //向下滑動
                    if (SoftInputUtil.isSoftShowing(getActivity())) {
                        SoftInputUtil.hideSoftInput(getActivity());
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    //得到真正的页数，因为有缓存的存在,所有从网络加载聊天历史,并不是从第一页加载的
    private int getCurrentPage() {

        if (null != messagesCache && messagesCache.size() > 0) {

            int cureentPage = messagesCache.size() / 10;
            if (cureentPage < 1) {
                return 1;
            } else {
                return cureentPage + 1;
            }
        } else {
            return 1;
        }

    }

    //加载更多消息。
    private void loadMessages() {
        new Handler().postDelayed(new Runnable() { //imitation of internet connection
            @Override
            public void run() {
                ArrayList<MessagesListFixtures.Message> messages = MessagesListFixtures.getMessages();
                adapter.addToEnd(messages, true);
            }
        }, 500);


    }


    public static void open(Activity activity, ChatSamplesListAdapter.ChatSample.Type type) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(ARG_TYPE, type);
        activity.startActivity(intent);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        String rule = SharedPreUtil.getString(BaseApplication.getInstance(), Constants.RULE, "");
        int meunId = MenuUtil.getMenuID(rule);
        if (meunId!=0){
            inflater.inflate(meunId, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final MessagesListFixtures.Message user_message;

        switch (item.getItemId()) {

            case R.id.menu_employee_query:
                user_message = SendMessageUtil.getMessage(0, "员工查询", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_EMPLOYYEE);
                break;
            case R.id.menu_tendence:
                user_message = SendMessageUtil.getMessage(0, "出勤查询", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_ATTENDANCE);
                break;
            case R.id.menu_post:
                user_message = SendMessageUtil.getMessage(0, "招聘查询", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_POST);
                break;
            case R.id.menu_trains:
                user_message = SendMessageUtil.getMessage(0, "员工培训", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_TRAIN);
                break;
            case R.id.menu_check:

                user_message = SendMessageUtil.getMessage(0, "绩效考核", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_CHECK);
                break;
            case R.id.menu_product:
                user_message = SendMessageUtil.getMessage(0, "产品查询", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_PRODUCT);
                break;
            case R.id.menu_order:
                user_message = SendMessageUtil.getMessage(0, "订单查询", "");
                updateData_Start(user_message, true, true);
                inputMessageToServer(Constants.TEMPLATE_ORDER);
                break;
            case R.id.menu_template_configure:
                ModelListFragment fragment = new ModelListFragment();
                MainActivity.newInstance().switchFragment(fragment, "模板列表");
                break;

        }
        return true;
    }


    @Override
    public void onSelectionChanged(int count) {
        this.selectionCount = count;
        menu.findItem(R.id.action_delete).setVisible(count > 0);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mACache.commit();
    }

    //显示长按菜单
    private void showMenuDialog(final MessagesListFixtures.Message message) {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());

        builder.setItems(CommonUtil.getArray(R.array.recycer_item_menu), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(message.getText());
                        AlertUtil.show(getActivity(), "提醒", "复制成功");
                        break;
                    case 1:
                        adapter.delete(message);
                        break;
                    case 2:
                        ShareUtil.Share(getActivity(), message);
                        break;
                    case 3:
                        AlertUtil.show(getActivity(), "提醒", "成功");
                        break;
                }
            }
        });
        builder.show();
    }

    //更新适配器数据
    private void updateData_Start(MessagesListFixtures.Message message, boolean isScroll, boolean isCache) {
        //发送服务端
        if (message != null) {
            adapter.addToStart(message, isScroll);
        }
        if (isCache) {
            mACache.addCache(message);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    //消息Item点击回调
    @Override
    public void onMessageClick(IMessage message) {
        if (!(message.getUser().getId()).equals("0")) {
            toMessagePresent((MessagesListFixtures.Message) message);
        }
    }

    //消息Item长按回调
    @Override
    public void onMessageLongClick(IMessage message) {
        showMenuDialog((MessagesListFixtures.Message) message);
    }


    //消息发送监听 带状态,不带状态

    public void switchInputListenerStatus() {
        inputMessage_Nostatus();
    }

    public void inputMessageToServer(final String type) {

        TemplateBean templateBean = (TemplateBean) SharedPreUtil.getObject(BaseApplication.getInstance(), type);
        Subscription subscription = RxServiceUtil.getInstance().getQueryObservable(templateBean, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MessagesListFixtures.Message message = SendMessageUtil.getMessage(1,"查询信息不存在","");
                        updateData_Start(message,true,true);

                    }

                    @Override
                    public void onNext(Object o) {
                        MessagesListFixtures.Message robot_message;
                        switch (type) {
                            case Constants.TEMPLATE_EMPLOYYEE:
                                EmployeeBean employeeBean = (EmployeeBean) o;
                                if (employeeBean.getCode().equals("1")&&employeeBean.getObject().size()>0) {

                                    robot_message = SendMessageUtil.getMessage(4, "", "", employeeBean, Constants.TEMPLATE_EMPLOYYEE);
                                    adapter.addToStart(robot_message, true);
                                    mACache.addCache(robot_message);
                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case Constants.TEMPLATE_ATTENDANCE:
                                AttendanceBean attendance = (AttendanceBean) o;
                                if (attendance.getCode().equals("1") &&attendance.getObject().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", attendance, Constants.TEMPLATE_ATTENDANCE);
                                    updateData_Start(robot_message, true, true);

                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case Constants.TEMPLATE_CHECK:
                                CheckBean checkBean = (CheckBean) o;
                                if (checkBean.getCode().equals("1") && checkBean.getObject().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", checkBean, Constants.TEMPLATE_CHECK);

                                    Log.d(TAG, "onNext: " + checkBean.toString());
                                    updateData_Start(robot_message, true, true);

                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case Constants.TEMPLATE_POST:

                                PostBean postBean = (PostBean) o;
                                if (postBean.getCode().equals("1")&&postBean.getObject().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", postBean, Constants.TEMPLATE_POST);
                                    updateData_Start(robot_message, true, true);

                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case Constants.TEMPLATE_TRAIN:
                                TrainBean trainBean = (TrainBean) o;
                                if (trainBean.getCode().equals("1")&&trainBean.getObject().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", trainBean, Constants.TEMPLATE_TRAIN);
                                    updateData_Start(robot_message, true, true);

                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case Constants.TEMPLATE_PRODUCT:
                                Products products = (Products) o;
                                if (products.getCode().equals("1")&&products.getProList().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", products, Constants.TEMPLATE_PRODUCT);
                                    updateData_Start(robot_message, true, true);
                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();

                                }
                                break;
                            case Constants.TEMPLATE_ORDER:
                                Orders orders = (Orders) o;
                                if (orders.getCode().equals("1")&&orders.getOrderList().size()>1) {
                                    robot_message = SendMessageUtil.getMessage(4, "", "", orders, Constants.TEMPLATE_ORDER);
                                    updateData_Start(robot_message, true, true);

                                } else {
                                    Toast.makeText(getActivity(), "查询信息不存在", Toast.LENGTH_SHORT).show();

                                }
                                break;
                        }
                    }
                });

        addSubscription(subscription);

    }

    public void inputMessage_status(final int status) {

        mBindingView.input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                MessagesListFixtures.Message message = new MessagesListFixtures.Message(input.toString());
                message.setIsRobot(0);
                adapter.addToStart(message, true);
                mACache.addCache(message);        //加入缓存

                Subscription subscription = RxServiceUtil.getInstance().getQuery(userId, input.toString(), status)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Object o) {
                                MessagesListFixtures.Message robot_message = null;

                                if (status == ObjType.EMPLOYEE.ordinal()) {
                                    Employee e = (Employee) o;
                                    if (e.getCode().equals("1")) {
                                        robot_message = SendMessageUtil.getMessage(4, "<font color=\"bule\">查询成功,点击查看</font>", "", e, Constants.TEMPLATE_EMPLOYYEE);

                                        //绑定消息视图
                                        //  View view = BindView.bindEmployeeView(MessagesListActivity.this, e);
                                        //robot_message.setView(view);

                                        // switchInputListenerStatus();

                                    } else if (e.getCode().equals("0")) {
                                        robot_message = SendMessageUtil.getMessage(1, e.getMessage() + ",请重新输入", "");
                                    }
                                    updateData_Start(robot_message, true, true);
                                }

                                if (status == ObjType.ORDER.ordinal()) {
                                    Orders orders = (Orders) o;
                                    if (orders.getCode().equals("1")) {
                                        robot_message = SendMessageUtil.getMessage(1, "<font color=\"bule\">查询成功,点击查看</font>", "", orders, "");
                                        switchInputListenerStatus();

                                    } else if (orders.getCode().equals("0")) {
                                        robot_message = SendMessageUtil.getMessage(1, orders.getMessage() + ",请重新输入", "");

                                    }
                                    updateData_Start(robot_message, true, true);
                                }

                                if (status == ObjType.PRODUCT.ordinal()) {
                                    Products products = (Products) o;

                                    if (products.getCode().equals("1")) {
                                        robot_message = SendMessageUtil.getMessage(1, "<font color=\"bule\">查询成功,点击查看</font>", "", products, "");
                                        switchInputListenerStatus();

                                    } else if (products.getCode().equals("0")) {
                                        robot_message = SendMessageUtil.getMessage(1, products.getMessage() + ",请重新输入", "");

                                    }
                                    updateData_Start(robot_message, true, true);
                                }
                                if (status == ObjType.SALE.ordinal()) {
                                    LineBean lineBean = (LineBean) o;

                                    if (lineBean.getCode().equals("1")) {
                                        robot_message = SendMessageUtil.getMessage(1, "<font color=\"bule\">查询成功,点击查看</font>", "", lineBean, "");
                                        switchInputListenerStatus();
                                    } else if (lineBean.getCode().equals("0")) {
                                        robot_message = SendMessageUtil.getMessage(1, lineBean.getMessage() + ",请重新输入", "");

                                    }
                                    updateData_Start(robot_message, true, true);
                                }

                            }
                        });

                addSubscription(subscription);
                return true;

            }

            @Override
            public boolean onVoiceInput(View view) {
                return false;
            }
        });
    }

    public void inputMessage_Nostatus() {

        mBindingView.input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                MessagesListFixtures.Message message = SendMessageUtil.getMessage(0, input.toString(), "");
                updateData_Start(message, true, true);

                switch (input.toString()) {
                    case Constants.ZH_EMPLOYYEE:
                        inputMessageToServer(Constants.TEMPLATE_EMPLOYYEE);
                        break;
                    case Constants.ZH_CHECK:
                        inputMessageToServer(Constants.TEMPLATE_CHECK);
                        break;
                    case Constants.ZH_ATTENDANCE:
                        inputMessageToServer(Constants.TEMPLATE_ATTENDANCE);
                        break;
                    case Constants.ZH_TRAIN:
                        inputMessageToServer(Constants.TEMPLATE_TRAIN);
                        break;
                    case Constants.ZH_POST:
                        inputMessageToServer(Constants.TEMPLATE_POST);

                        break;
                    case Constants.ZH_PRODUCT:
                        inputMessageToServer(Constants.TEMPLATE_PRODUCT);

                        break;
                    case Constants.ZH_ORDER:
                        inputMessageToServer(Constants.TEMPLATE_ORDER);

                        break;
                    default:
                        loadRobotMessage("1454739828", input.toString());


                }

                return true;
            }

            @Override
            public boolean onVoiceInput(View view) {

                // 移动数据分析，收集开始听写事件
                FlowerCollector.onEvent(getActivity(), "iat_recognize");

//                mResultText.setText(null);// 清空显示内容
//                mIatResults.clear();
                // 设置参数
                setParam();
                boolean isShowDialog = mSharedPreferences.getBoolean(
                        getString(R.string.pref_key_iat_show), true);
                if (isShowDialog) {
                    // 显示听写对话框
                    mIatDialog.setListener(mRecognizerDialogListener);
                    mIatDialog.show();
                    mBindingView.input.setMessageVoiceInputText("点击 取消");
                    Toast.makeText(getActivity(), getString(R.string.text_begin), Toast.LENGTH_SHORT).show();
                } else {
                    // 不显示听写对话框
                    ret = mIat.startListening(mRecognizerListener);
                    if (ret != ErrorCode.SUCCESS) {
                        mBindingView.input.setMessageVoiceInputText("点击 说话");
                        Toast.makeText(getActivity(), "听写失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "请开始说话…", Toast.LENGTH_SHORT).show();
                    }
                }

                mIatDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mBindingView.input.setMessageVoiceInputText("点击 说话");
                    }
                });
                return false;
            }
        });
    }


    //消息展示页面
    private void toMessagePresent(MessagesListFixtures.Message message) {

        Intent intent = new Intent(getContext(), DataDetailActivity.class);
        intent.putExtra(Constants.ACTIVIIY_BUNDLE_KEY_TYPE, message.getObjType());
        intent.putExtra(Constants.ACTIVIIY_BUNDLE_KEY_OBJ, (Serializable) message.getObj());

        startActivity(intent);
    }

    /**
     * 参数设置
     *
     * @param
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "1"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }


    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(getActivity(), "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }


    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            Toast.makeText(getActivity(), "开始说话", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            Toast.makeText(getActivity(), error.getPlainDescription(true), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            Toast.makeText(getActivity(), "结束说话", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            printResult(results);

            if (isLast) {
                // TODO 最后的结果
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            Toast.makeText(getActivity(), "当前正在说话，音量大小：" + volume, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "返回音频数据：" + data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };
    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            printResult(results);
        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            Toast.makeText(getActivity(), error.getErrorDescription(), Toast.LENGTH_SHORT).show();
        }

    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        if (resultBuffer.length() > 0 && !resultBuffer.toString().equals("")) {
            mBindingView.input.showInput(resultBuffer.toString());
        }
    }
}
