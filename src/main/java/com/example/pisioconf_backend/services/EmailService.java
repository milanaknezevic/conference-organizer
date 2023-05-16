package com.example.pisioconf_backend.services;

public interface EmailService {
    void sendMailApproved(String recipient) throws Exception;

    void sendSimpleMailNotApproved(String recipient) throws Exception;
    void sendSimpleMailBlocked(String recipient) throws Exception;

}
