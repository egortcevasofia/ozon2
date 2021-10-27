package com.example.ozon2.queue;

import com.example.ozon2.dto.ShopRequestDto;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
@Component
public class AmqReceiver {

    @Value("${response.from.ozon2}")
    private String outboundQueue;

    private JmsTemplate jmsTemplate;

    File xmlFile = new File("C:\\Users\\segortseva\\IdeaProjects\\ozon2\\src\\main\\resources\\goods.xml");

    @Autowired
    public AmqReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "${request.for.ozon2}")
    public void getMessageFromOzon(String message) {

        try {

            log.info(message);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            var reader = new StringReader(message);
            Document doc = builder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();
            Node node = doc.getElementsByTagName("shop_request").item(0);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                var element = (Element) node;

                var shopRequest = ShopRequestDto.builder()
                        .shopId(Long.parseLong(element.getElementsByTagName("shop_id").item(0).getTextContent()))
                        .numberOfOrder(Long.parseLong(element.getElementsByTagName("numberOfOrder").item(0).getTextContent()))
                        .build();
                log.info("get message {} вот такой", shopRequest.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String xmlMessage = new String(
                    Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);
            jmsTemplate.convertAndSend(this.outboundQueue, xmlMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
