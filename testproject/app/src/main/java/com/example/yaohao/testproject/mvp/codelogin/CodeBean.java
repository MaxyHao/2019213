package com.example.yaohao.testproject.mvp.codelogin;

/**
 * discription:
 * Created by Guo WenHui on 2018/3/15.
 * phone:13552163255
 */

public class CodeBean {

	/**
	 * status : 0
	 * data : 无效的token
	 * errMsg : 无效的token
	 */

	private int status;
	private String data;
	private String errMsg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
