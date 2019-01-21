package com.web.wx.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.web.wx.dto.WxMsgEventDto;
import com.web.wx.dto.WxMsgTextDto;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */
public class WxMessageUtil {

    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_EVENT = "event";

    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    public static final String EVENT_TYPE_CLICK = "CLICK";

    public static final String EVENT_TYPE_VIEW = "VIEW";



    public static Map<String,String> parseXml(HttpServletRequest request){

        Map<String,String> messageMap = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            //读取request Stream信息
            inputStream = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        Element root=document.getRootElement();
        List<Element> elementsList=root.elements();

        for(Element e:elementsList){
            messageMap.put(e.getName(),e.getText());
        }
        try {
            inputStream.close();
            inputStream=null;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return messageMap;
    }

    public static String textMessageToXml(WxMsgEventDto msg) {
        xstream.alias("xml", msg.getClass());
        return xstream.toXML(msg);
    }

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = false;
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {

                    if (!name.equals("xml")) {
                        char[] arr = name.toCharArray();
                        if (arr[0] >= 'a' && arr[0] <= 'z') {
                            // arr[0] -= 'a' - 'A';
                            arr[0] = (char) ((int) arr[0] - 32);
                        }
                        name = new String(arr);
                    }

                    super.startNode(name, clazz);

                }

                @Override
                public void setValue(String text) {

                    if (text != null && !"".equals(text)) {
                        Pattern patternInt = Pattern
                            .compile("[0-9]*(\\.?)[0-9]*");
                        Pattern patternFloat = Pattern.compile("[0-9]+");
                        if (patternInt.matcher(text).matches()
                            || patternFloat.matcher(text).matches()) {
                            cdata = false;
                        } else {
                            cdata = true;
                        }
                    }
                    super.setValue(text);
                }

                protected void writeText(QuickWriter writer, String text) {

                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }

    });

    public static String resSubscribeMsg() {
        StringBuffer sb = new StringBuffer();
        sb.append("\ue418你终于来了，等你很久咯～\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("这里有：\n");
        sb.append("\ue118领不完的福利红包；\n");
        sb.append("\ue112最火爆的优惠活动；\n");
        sb.append("\ue40a还有很爱你的小美；\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("点击下方【每日福利】\ue310 菜单看一下小美给你准备的福利吧～\n");
        return sb.toString();
    }

    public static String resMsgXml(Map<String, String> message, String content) {
        WxMsgEventDto msgDto = new WxMsgEventDto();
        msgDto.setToUserName(message.get("FromUserName"));
        msgDto.setFromUserName(message.get("ToUserName"));
        msgDto.setMsgType(MESSAGE_TEXT);
        msgDto.setContent(content);
        msgDto.setCreateTime(System.currentTimeMillis());
        if(message.get("MsgId") != null) {
            msgDto.setMsgId(message.get("MsgId"));
        }
        return textMessageToXml(msgDto);
    }


}
