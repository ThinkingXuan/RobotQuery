package com.njcit.controller;
import com.njcit.dao.user.UserDao;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.TemplateModelService;
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
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class TemplateModelController {
    @Autowired
    @Qualifier("templateModelService")
    TemplateModelService templateModelService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/getConcern.action", method = RequestMethod.POST)
    @ResponseBody
    public String getTemplateModelByUserIdAndTemplateId(String userId, String templateId) {

        return templateModelService.getTemplateModelByUserIdAndTemplateId(userId,templateId);

    }
    @RequestMapping(value = "/insertNewModel.action", method = RequestMethod.POST)
    @ResponseBody
    public String insertNewTemplateModel(HttpServletRequest request) throws IOException {

        TemplateModelBean templateModelBean =((TemplateModelBean) GsonUtil.JsonToObject(getRequestPostStr(request),TemplateModelBean.class));
        return templateModelService.insertNewTemplateModel(templateModelBean);
    }

    @RequestMapping(value = "/deleteConcern.action", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTemplateModel(String userId, String templateId) {
        return templateModelService.deleteTemplateModel(userId,templateId);
    }
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
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


