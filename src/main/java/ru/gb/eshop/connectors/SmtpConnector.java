package ru.gb.eshop.connectors;

import org.springframework.stereotype.Component;

@Component
public class SmtpConnector {

    public void sendEmail() {
        //В качестве примера
        /*try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("172.16.5.5");
            email.setSmtpPort(5555);
            email.setFrom("eremin@geekbrains.ru");
            email.setSubject("Доставка");
            email.setMsg("Товар уже в доставке, ожидайте курьера");
            email.send();
        } catch (EmailException e) {
            System.out.println(e.getLocalizedMessage());
        }*/
    }

}
