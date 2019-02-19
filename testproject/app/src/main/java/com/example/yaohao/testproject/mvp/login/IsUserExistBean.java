package com.example.yaohao.testproject.mvp.login;

/**
 *
 * @ClassName:IsUserExistBean

 * @PackageName:eiyudensetsu.ginga.youxin.com.yinheyingxiong.mvp.login

 * @Create On 2018/12/13   10:48

 * @author:gongchenghao

 * @Copyrights 2018/12/13 宫成浩 All rights reserved.
 */


public class IsUserExistBean {

	/**
	 * status : 1
	 * data : 1
	 * errMsg :
	 */

	private int status;
	private int data;
	private String errMsg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
