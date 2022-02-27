package com.njcit.controller;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.UserTrainingService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mirko on 2017/4/25.
 */
@Controller
@RequestMapping(value = "/hr", produces = "text/html;charset=UTF-8")
public class UserTrainController {
    @Autowired
    @Qualifier("userTrainingService")
    UserTrainingService userTrainingService;

    @ResponseBody
    @RequestMapping(value = "/getUserTraining.action", method = RequestMethod.POST)
    public  String getUserTraining(HttpServletRequest request) throws IOException {



        TemplateModelBean templateModelBean = (TemplateModelBean) GsonUtil.JsonToObject(getRequestPostStr(request),TemplateModelBean.class);

        System.out.println(userTrainingService.getUserTraining(templateModelBean));
        return  userTrainingService.getUserTraining(templateModelBean);


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
