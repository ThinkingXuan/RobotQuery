package com.njcit.robotquery.view.chatUI.DefaultViewHodler;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.PostBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.TrainBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.data.BindView;
import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.DefaultMessageViewHolder;
import com.njcit.robotquery.view.chatUI.commons.models.IMessage;
import com.njcit.robotquery.view.chatUI.messages.MessagesListAdapter;
import com.njcit.robotquery.view.chatUI.messages.MessagesListStyle;

/**
 * Created by youxuan on 2017/4/3 0003.
 */

public class ImageMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {

    public static final String TAG = "ImageMessageViewHolder";
    private LinearLayout mLinearContent;

    public ImageMessageViewHolder(View itemView) {
        super(itemView);
        mLinearContent = (LinearLayout) itemView.findViewById(R.id.Linear_content);
    }

    @Override
    public void onBind(MESSAGE message) {

        MessagesListFixtures.Message msg = (MessagesListFixtures.Message) message;
        View view = null;

        switch (msg.getObjType()) {
            case Constants.TEMPLATE_EMPLOYYEE:
                EmployeeBean employeeBean = (EmployeeBean) msg.getObj();
                view = BindView.bindEmployeeView(mLinearContent.getContext(), employeeBean);
                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_ATTENDANCE:
                AttendanceBean attendanceBean = (AttendanceBean) msg.getObj();
                view = BindView.bindAttendanceView(mLinearContent.getContext(), attendanceBean);

                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_POST:
                PostBean postBean = (PostBean) msg.getObj();
                view = BindView.bindPostView(mLinearContent.getContext(), postBean);
                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_TRAIN:
                TrainBean trainBean = (TrainBean) msg.getObj();
                view = BindView.bindTrainView(mLinearContent.getContext(), trainBean);
                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_CHECK:
                CheckBean checkBean = (CheckBean) msg.getObj();
                view = BindView.bindCheckView(mLinearContent.getContext(), checkBean);
                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_ORDER:
                Orders orders = (Orders) msg.getObj();
                view = BindView.bindOrderView(mLinearContent.getContext(), orders);
                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            case Constants.TEMPLATE_PRODUCT:
                Products products = (Products) msg.getObj();
                view = BindView.bindProductView(mLinearContent.getContext(), products);

                if (view != null) {
                    mLinearContent.removeAllViews();
                    mLinearContent.addView(view);
                }
                break;
            default:
                view = null;

        }

    }

    @Override
    public void applyStyle(MessagesListStyle style) {

    }
}