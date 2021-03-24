package in.macor.core.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3160031352280657986L;

	private String field;
	
	private String messageKey;

	private String[] arguments;
	
	public ServiceException(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public ServiceException(String field, String messageKey) {
		this.messageKey = messageKey;
		this.field = field;
	}
	
	public ServiceException(String messageKey, String[] arguments) {
		this.messageKey = messageKey;
		this.arguments = arguments;
	}

	public String getMessageKey() {
		return messageKey;
	}
	
	public String getField() {
		return field;
	}
	
	public String[] getArguments() {
		return arguments;
	}

	public String getFormattedArguments() {
		String formattedArguments = null;
		if (arguments != null) {
			StringBuilder builder = new StringBuilder();
			boolean first = true;
			for (String argument : arguments) {
				if (!first) {
					builder.append(", ");
				}
				builder.append(argument);
				first = false;
			}
			formattedArguments = builder.toString();
		}
		return formattedArguments;
	}
	
}
