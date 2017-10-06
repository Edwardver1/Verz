package com.verz.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.verz.domain.Order;
import com.verz.domain.User;



@Component
public class MailConstructor {

	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation # "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("incverz@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
	
	
	public MimeMessagePreparator constructNotificationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderEmailNotificationTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo("admverz@gmail.com");
				email.setSubject("Order Notification # "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("incverz@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
}
