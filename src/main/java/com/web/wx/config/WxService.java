package com.web.wx.config;

import com.web.wx.dto.*;
import com.web.wx.util.WxMessageUtil;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/18
 */

public class WxService {

    public static String doService(ServletRequest request) {
        //定义要返回的XML
        String xml = "";

        Map<String, String> message = WxMessageUtil.parseXml(request);

        String event = message.get("Event");
        String msgType = message.get("MsgType");
        String content = message.get("Content");
        String MsgId = message.get("MsgId");

        //推送事件
        if(msgType.equals(WxMessageUtil.MESSAGE_EVENT)) {
            //首次关注
            if(event.equals(WxMessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                String msg = WxMessageUtil.resSubscribeMsg();
                MsgTextDto msgDto = new MsgTextDto();
                msgDto.setContent(msg);
                xml = WxMessageUtil.resTextXml(message, msgDto);
            }else{

            }
        }else{
            if(content.matches("饿")) {
                MsgNewsDto msgNewsListDto = new MsgNewsDto();
                //组件图文
                List<NewsDto> newsList = new ArrayList<>();
                NewsDto item = new NewsDto();
                item.setTitle("欢迎来到美团外卖");
                item.setDescription("美团外卖，送啥都快");
                item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548219837725&di=64fddeba26902bd1098f93e63422215f&imgtype=0&src=http%3A%2F%2Fpic24.photophoto.cn%2F20120822%2F0007019808270625_b.jpg");
                item.setUrl("http://i.meituan.com/awp/h5/hotel/search/search.html");
                newsList.add(item);

                msgNewsListDto.setArticles(newsList);
                msgNewsListDto.setArticleCount(1);
                xml = WxMessageUtil.resNewsXml(message, msgNewsListDto);

            }else if(content.matches("困")) {
                MsgImageDto msgImageDto = new MsgImageDto();
                ImageDto imageDto = new ImageDto();
                imageDto.setMediaId("lKc6zZvr5mxs7Vak_dUKuQIlhB9ENhSgixbmBC5qj4BM8dlBSbH2qFEofgBwcBdg");
                msgImageDto.setImage(imageDto);
                xml = WxMessageUtil.resImageXml(message, msgImageDto);

            }else if(content.matches("看")) {

                MsgMusicDto MsgMusicDto = new MsgMusicDto();
                MusicDto musicDto = new MusicDto();
                musicDto.setTitle("测试音乐");
                musicDto.setDescription("一首好歌");
                musicDto.setMusicUrl("http://www.ytmp3.cn/down/57410.mp3");
                musicDto.setHQMusicUrl("http://www.ytmp3.cn/down/57410.mp3");
                musicDto.setThumbMediaId("lKc6zZvr5mxs7Vak_dUKuQIlhB9ENhSgixbmBC5qj4BM8dlBSbH2qFEofgBwcBdg");
                MsgMusicDto.setMusic(musicDto);
                xml = WxMessageUtil.resMusicXml(message, MsgMusicDto);

            }else if(content.matches("游")) {

            }else if(content.matches("唱")) {

            }else if(content.matches("美")) {

            }else if(content.matches("买")) {

            }else if(content.matches("玩")) {

            }else if(content.matches("脚")) {

            }else if(content.matches("车")) {

            }else if(content.matches("新")) {

            }else{
                String msg = WxMessageUtil.resTemplateMsg();
                MsgTextDto msgDto = new MsgTextDto();
                msgDto.setContent(msg);
                msgDto.setMsgId(MsgId);
                xml = WxMessageUtil.resTextXml(message, msgDto);
            }

        }
        System.out.println(xml);
        return xml;

    }

}
