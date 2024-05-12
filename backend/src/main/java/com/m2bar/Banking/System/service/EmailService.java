package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.EmailRequest;
import com.m2bar.Banking.System.models.StringResponse;

public interface EmailService {

    StringResponse sendSimpleMessage(EmailRequest emailRequest);
}
