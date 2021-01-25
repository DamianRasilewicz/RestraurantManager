package pl.rasilewicz.restaurant_manager.services;

public interface MailService {

        void sendEmail(String to, String subject, String content);
    }
