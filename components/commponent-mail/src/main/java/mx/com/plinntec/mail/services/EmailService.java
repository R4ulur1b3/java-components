/**
 * 
 */
package mx.com.plinntec.mail.services;

import java.util.HashMap;

import mx.com.plinntec.mail.model.EmailVo;

/**
 * @author {autor}
 *
 */
public interface EmailService {
	/**
	 * Envío de correo electronico simple
	 * @param emailVo
	 */
	void sendEmail(EmailVo emailVo) throws Exception;
	
	/**
	 * 
	 * @param emailVo
	 * @throws Exception
	 */
	void sendEmailAsynchronous(EmailVo emailVo) throws Exception;
	
	/**
	 * 
	 * @param emailVo
	 * @param multiplesAdjuntos <Name, Content>
	 * @throws Exception
	 */
	void sendEmail(EmailVo emailVo, HashMap<String ,byte[]> multiplesAdjuntos ) throws Exception;
	
	/**
	 * 
	 * @param emailVo
	 * @param multiplesAdjuntos <Name, Content>
	 * @throws Exception
	 */
	void sendEmailAsynchronous(EmailVo emailVo, HashMap<String ,byte[]> multiplesAdjuntos ) throws Exception;
}
