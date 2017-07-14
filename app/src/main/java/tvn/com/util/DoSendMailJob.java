package tvn.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvn.com.entity.Server;
import tvn.com.service.ServerService;

@Service
public class DoSendMailJob implements Job {

	//@Autowired

	
	public static boolean sendMail(String to, String subject, String text) {
		Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.tma.com.vn");
		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tvngoan@tma.com.vn", "m");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress("tvngoan@tma.com.vn"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		// TODO Auto-generated method stub
		ServerService serverService = new ServerService();
		List<Server> list = (List<Server>) serverService.findAll();
		System.out.println("======This is a" + list);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Server server : list) {

			if (server.getUser() != null) {
				Date curDate = null, endDate, d3 = null;
				String curS = sdf.format(new Date());
				String endDateS = server.getEndDate();
				try {
					curDate = sdf.parse(curS);
					endDate = sdf.parse(endDateS);
					d3 = new Date(endDate.getTime() + TimeUnit.DAYS.toMillis(1));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (d3.equals(curDate)) {
					sendMail("tvngoan@tma.com.vn", "Ok", "s");
					System.out.println("OK");
				}
			}
		}
	}
	
//	public void aa() {
//		List<Server> list = (List<Server>) serverService.findAll();
//		System.out.println("======This is a" + list);
//	}
//	
}
