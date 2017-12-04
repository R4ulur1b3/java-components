/**
 * 
 */
package mx.com.plinntec.mail.components.threads;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import mx.com.plinntec.mail.model.EmailVo;

/**
 * @author {user}
 *
 */
public class AsynchronousTask implements Runnable {

	private static Logger LOG = LoggerFactory.getLogger(AsynchronousTask.class);

	private EmailVo emailVo;
	
	private JavaMailSender mailSender;
	
	private VelocityEngine velocityEngine;
	
	private HashMap<String, byte[]> multiplesAdjuntos;

	/**
	 * 
	 */
	public AsynchronousTask() {
		super();
	}

	/**
	 * 
	 * @param emailVo
	 * @param mailSender
	 * @param velocityEngine
	 */
	public AsynchronousTask(EmailVo emailVo, JavaMailSender mailSender, VelocityEngine velocityEngine) {
		super();
		this.emailVo = emailVo;
		this.mailSender = mailSender;
		this.velocityEngine = velocityEngine;
	}
	
	/**
	 * 
	 * @param emailVo
	 * @param mailSender
	 * @param velocityEngine
	 * @param multiplesAdjuntos
	 */
	public AsynchronousTask(EmailVo emailVo, JavaMailSender mailSender, VelocityEngine velocityEngine, HashMap<String, byte[]> multiplesAdjuntos) {
		super();
		this.emailVo = emailVo;
		this.mailSender = mailSender;
		this.velocityEngine = velocityEngine;
		this.multiplesAdjuntos = multiplesAdjuntos;
	}

	public void run() {
		LOG.info("-> run Asynchronous");
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(emailVo.getTo());
			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailVo.getTemplateName(), emailVo.getProps());

			helper.setText(body, true);
			Properties props = emailVo.getProps();
			/*
			 * Agrega una imagen en la propiedad id001
			 */
			helper.addInline("id001", new File(props.get("id001").toString()));

			helper.setSubject(emailVo.getSubject());
			
			if( multiplesAdjuntos != null ){
				if( multiplesAdjuntos.size() > 0){
					Set<String> fileName = multiplesAdjuntos.keySet();
					for (String key : fileName) {
						byte[] bs = multiplesAdjuntos.get(key);
						helper.addAttachment(key, new ByteArrayResource(bs));
					}
				}else{
					throw new Exception("NO HAY ARCHIVOS ADJUNTOS...!");
				}
			}
			
			mailSender.send(message);
			
		} catch (Exception ex) {
			System.err.println("ERROR AL ENVIAR EL CORREO ELECTRONICO sendEmail():" + ex);
		}
		LOG.info("<- run Asynchronous");
	}

}
