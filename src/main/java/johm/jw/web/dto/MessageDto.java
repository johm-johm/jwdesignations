/**
 * 
 */
package johm.jw.web.dto;

/**
 * @author johm
 *
 */
public class MessageDto {
	
	private final MessageType type;
	private final String message;

	public MessageDto(final MessageType type, final String message) {
		this.type = type;
		this.message = message;
	}

	public enum MessageType {
		INFO, WARN, ERROR, SUCCESS
	}

	public MessageType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

}
