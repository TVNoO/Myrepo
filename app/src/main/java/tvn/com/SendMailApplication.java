package tvn.com;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import tvn.com.entity.Server;
import tvn.com.service.ServerService;

@Component
public class SendMailApplication extends TimerTask implements ApplicationRunner {

	@Autowired
	private ServerService serverService;

	Timer timer;
	public SendMailApplication() {
		// TODO Auto-generated constructor stub
	}

//	public SendMailApplication(int seconds) {
//		timer = new Timer();
//		timer.schedule(new SendMailApplication(), seconds * 1000,seconds * 1000 );
//	
//	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// TODO Auto-generated method stub
//		new SendMailApplication(5);
		//timer = new Timer();
		//timer.schedule(new SendMailApplication(), 5 * 1000,5 * 1000 );
		while(true){
			run();
			Thread.sleep(20000);
		}
	}

	public static boolean sendMail(String to, String subject, String text) {
		Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.tma.com.vn");
		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tvngoan@tma.com.vn", "Happy95!98");
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

//	class ToDoTask extends TimerTask {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("====== OK ========");
			List<Server> list = (List<Server>) serverService.findAll();
			System.out.println("======This is a" + list);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Server server : list) {

				if (server.getUser() != null) {
					Date curDate = null, endDate = null, d3 = null;
					String curS = sdf.format(new Date());
					String endDateS = server.getEndDate();
					try {
						curDate = sdf.parse(curS);
						endDate = sdf.parse(endDateS);
						d3 = new Date(curDate.getTime() + TimeUnit.DAYS.toMillis(1));
						
						System.out.println("curDate :" + curDate);
						System.out.println("endDate :" + endDate);
						System.out.println("d3 :" + d3);
						
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (d3.equals(endDate)) {
						sendMail("tvngoan@tma.com.vn", "Ok", "s");
						System.out.println("OK");
					}
				}
			}
		}
	//}

}
