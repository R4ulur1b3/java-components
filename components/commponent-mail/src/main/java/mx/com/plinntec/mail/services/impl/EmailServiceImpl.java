/**
 * 
 */
package mx.com.plinntec.mail.services.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import mx.com.plinntec.mail.components.threads.AsynchronousTask;
import mx.com.plinntec.mail.model.EmailVo;
import mx.com.plinntec.mail.services.EmailService;

/**
 * @author {autor}
 *
 */
@Service(value = "emailService")
public class EmailServiceImpl implements EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Resource(name = "messagesEmail")
	private Properties messagesEmail;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.plinntec.mail.services.EmailService#sendEmail(mx.com.plinntec.mail
	 * .model.EmailVo)
	 */
	@Override
	public void sendEmail(EmailVo emailVo) throws Exception {
		LOG.info("-> sendEmail");
		try {
			final MimeMessage message = mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(emailVo.getTo());
			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailVo.getTemplateName(),
					emailVo.getProps());

			helper.setText(body, true);
			Properties props = emailVo.getProps();
			/*
			 * Agrega una imagen en la propiedad id001
			 */
			helper.addInline("id001", new File(props.get("id001").toString()));

			helper.setSubject(emailVo.getSubject());

			mailSender.send(message);
		} catch (Exception ex) {
			LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO sendEmail():", ex);
			throw new Exception(messagesEmail.getProperty("errorSendFile"));
		}
		LOG.info("<- sendEmail");
	}

	@Override
	public void sendEmail(EmailVo emailVo, HashMap<String, byte[]> multiplesAdjuntos) throws Exception {
		LOG.info("-> SendEmail with Attach");
		if (multiplesAdjuntos == null || multiplesAdjuntos.isEmpty()) {
			throw new Exception("Debe incluir por lo menos un archivo adjunto!");
		}
		try {
			final MimeMessage message = mailSender.createMimeMessage();
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(emailVo.getTo());
			String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailVo.getTemplateName(),
					emailVo.getProps());

			helper.setText(body, true);
			Properties props = emailVo.getProps();
			/*
			 * Agrega una imagen en la propiedad id001
			 */
			helper.addInline("id001", new File(props.get("id001").toString()));

			helper.setSubject(emailVo.getSubject());

			Set<String> fileName = multiplesAdjuntos.keySet();
			for (String key : fileName) {
				byte[] bs = multiplesAdjuntos.get(key);
				helper.addAttachment(key, new ByteArrayResource(bs));
			}

			mailSender.send(message);
		} catch (Exception ex) {
			LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO sendEmail():", ex);
			throw new Exception(messagesEmail.getProperty("errorSendFile"));
		}

		LOG.info("<- SendEmail with Attach");
	}

	@Override
	public void sendEmailAsynchronous(EmailVo emailVo) throws Exception {
		LOG.info("-> sendEmail Asynchronous");
		try {
			Runnable asynchronousTask = new AsynchronousTask(emailVo, mailSender, velocityEngine);

			new Thread(asynchronousTask).start();
			
		} catch (Exception ex) {
			LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO sendEmail():", ex);
			throw new Exception(messagesEmail.getProperty("errorSendFile"));
		}
		LOG.info("<- sendEmail Asynchronous");
	}

	@Override
	public void sendEmailAsynchronous(EmailVo emailVo, HashMap<String, byte[]> multiplesAdjuntos) throws Exception {

		LOG.info("-> sendEmail Asynchronous with Attachment");
		try {
			Runnable asynchronousTask = new AsynchronousTask(emailVo, mailSender, velocityEngine, multiplesAdjuntos);

			new Thread(asynchronousTask).start();
			
		} catch (Exception ex) {
			LOG.error("ERROR AL ENVIAR EL CORREO ELECTRONICO sendEmail():", ex);
			throw new Exception(messagesEmail.getProperty("errorSendFile"));
		}
		LOG.info("<- sendEmail Asynchronous with Attachment");
	

	}

}
