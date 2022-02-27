package com.stfalcon.chatkit.DefaultViewHodler;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.commons.ViewHolder;
import com.stfalcon.chatkit.commons.chatinterface.DefaultMessageViewHolder;
import com.stfalcon.chatkit.messages.MessagesListStyle;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

public class DefaultDateHeaderViewHolder extends ViewHolder<Date>
        implements DefaultMessageViewHolder {

    protected TextView text;
    protected String dateFormat;

    public DefaultDateHeaderViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.messageText);
    }

    @Override
    public void onBind(Date date) {
        text.setText(DateFormatter.format(date, dateFormat));
    }

    @Override
    public void applyStyle(MessagesListStyle style) {
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getDateHeaderTextSize());
        text.setTextColor(style.getDateHeaderTextColor());
        text.setPadding(style.getDateHeaderPadding(), style.getDateHeaderPadding(),
                style.getDateHeaderPadding(), style.getDateHeaderPadding());
        dateFormat = style.getDateHeaderFormat();
        dateFormat = dateFormat == null ? DateFormatter.Template.STRING_MONTH.get() : dateFormat;
    }
}
