/**
 * 
 */
package mx.com.plinntec.mail.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author user-admin
 *
 */
public class EmailVo implements Serializable {

	/**
	 * UID por default
	 */
	private static final long serialVersionUID = 1L;
	
	private String to;
	private String subject;
	
	private Properties props;
	private String templateName;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
