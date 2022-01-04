package com.mydemo.demo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.Iterator;

/**
 *
 */
public class TestForXmlParse {

        public static void main(String[] args) throws DocumentException {
        String str = "<xml><ToUserName><![CDATA[ww1c69883085fe5181]]></ToUserName><FromUserName><![CDATA[sys]]></FromUserName><CreateTime>1639019037</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[sys_approval_change]]></Event><AgentID>3010040</AgentID><ApprovalInfo><SpNo>202112090001</SpNo><SpName><![CDATA[日报审批测试lmj]]></SpName><SpStatus>1</SpStatus><TemplateId><![CDATA[C4NvsS595Xw2gDgeNjijMacojRUphbpwiTof6qQT1]]></TemplateId><ApplyTime>1639019037</ApplyTime><Applyer><UserId><![CDATA[mingjielong]]></UserId><Party><![CDATA[1]]></Party></Applyer><SpRecord><SpStatus>1</SpStatus><ApproverAttr>1</ApproverAttr><Details><Approver><UserId><![CDATA[mingjielong]]></UserId></Approver><Speech><![CDATA[]]></Speech><SpStatus>1</SpStatus><SpTime>0</SpTime></Details></SpRecord><StatuChangeEvent>1</StatuChangeEvent></ApprovalInfo></xml>";
        Document document = DocumentHelper.parseText(str);
        Element element = document.getRootElement();
        System.out.println();
        Iterator<Element> elements = element.elementIterator();
        while (elements.hasNext()) {
            Element element1 = elements.next();
            System.out.println(element1.getName());
            element.element("ToUserName");
        }
    }
    
    
}
