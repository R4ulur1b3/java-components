/**
 * 
 */
package mx.com.plinntec.mail.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.com.plinntec.mail.model.EmailVo;
import mx.com.plinntec.mail.services.EmailService;

/**
 * @author user-admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/applicationContext-mail-components.xml",
		"classpath:/spring/applicationContext-mail-resources.xml",
		"classpath:/spring/applicationContext-mail-config.xml",
		"classpath:/spring/applicationContext-mail-services.xml",
		"classpath:/spring/applicationContext-mail-tasks.xml"})
public class EmailServiceTest {
	
	@Autowired
	private EmailService emailService;
	
	@Resource(name = "configEmail")
	private Properties configEmail;
	
	private String to;
	
	@Test
	public void deberiaEnviarCorreoBienvenida(){
		EmailVo emailVo = null;
		
		Properties props = new Properties();
		props.put("id001", configEmail.getProperty("pathImageHead"));
		String subject = configEmail.getProperty("wellcome.subject");
		String templateName = configEmail.getProperty("wellcome.template");
		
		to = "email@gmail.com";
		
		try {
			emailVo = new EmailVo();
			emailVo.setProps(props);
			emailVo.setSubject(subject);
			emailVo.setTemplateName(templateName);
			emailVo.setTo(to);
			
			emailService.sendEmail(emailVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deberiaEnviarCorreoAsincronoBienvenida(){
		EmailVo emailVo = null;
		
		Properties props = new Properties();
		props.put("id001", configEmail.getProperty("pathImageHead"));
		String subject = configEmail.getProperty("wellcome.subject");
		String templateName = configEmail.getProperty("wellcome.template");
		
		to = "email@gmail.com";
		
		try {
			emailVo = new EmailVo();
			emailVo.setProps(props);
			emailVo.setSubject(subject);
			emailVo.setTemplateName(templateName);
			emailVo.setTo(to);
			
			emailService.sendEmailAsynchronous(emailVo);
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void deberiaEnviarCorreoAsincronoConAttachmentBienvenida() throws Exception{
		EmailVo emailVo = null;
		
		Properties props = new Properties();
		props.put("id001", configEmail.getProperty("pathImageHead"));
		String subject = configEmail.getProperty("wellcome.subject");
		String templateName = configEmail.getProperty("wellcome.template");
		
		to = "email@gmail.com";
		HashMap<String, byte[]> adjuntos = new HashMap<String, byte[]>();
		
		File file = new File("C:/temp/mapping.xml");
		FileInputStream stream = new FileInputStream(file);
		byte[] data = new byte[stream.available()];
		stream.read(data);
		stream.close();
		
//		adjuntos.put("Archivo 1.xml", data );
		
		try {
			emailVo = new EmailVo();
			emailVo.setProps(props);
			emailVo.setSubject(subject);
			emailVo.setTemplateName(templateName);
			emailVo.setTo(to);
			
			emailService.sendEmailAsynchronous(emailVo, adjuntos);
			
			Thread.sleep(100000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
