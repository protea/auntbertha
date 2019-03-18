package cc.protea.auntbertha;

import cc.protea.util.http.Response;

public class AuntBerthaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public Response response = null;
	public String errorMessage;
	public String errorCode;

	public AuntBerthaException() {
		super();
	}

	public AuntBerthaException(final Exception e) {
		super(e);
	}

	public AuntBerthaException(final Exception e, final Response response) {
		super(e);
		this.response = response;
	}

	public AuntBerthaException(final Exception e, final String errorCode, final String errorMessage) {
		super(e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
