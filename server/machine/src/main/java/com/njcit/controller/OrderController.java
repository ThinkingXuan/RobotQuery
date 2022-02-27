package com.njcit.controller;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.OrderService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/sale", produces = "text/html;charset=UTF-8")
public class OrderController {

    @Autowired
    @Qualifier("orderService")
    OrderService orderService;


    @RequestMapping(value = "/getOrder.action", method = RequestMethod.POST)
    @ResponseBody
    public String getUserContactWay(HttpServletRequest request) throws IOException {
        TemplateModelBean templateModelBean = (TemplateModelBean) GsonUtil.JsonToObject(getRequestPostStr(request),TemplateModelBean.class);

        System.out.println(orderService.getOrder(templateModelBean));
        return  orderService.getOrder(templateModelBean);

    }


    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }

        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }



}


