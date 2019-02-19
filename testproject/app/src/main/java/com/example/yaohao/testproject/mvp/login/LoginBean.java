package com.example.yaohao.testproject.mvp.login;

/**
 * discription:
 * Created by Guo WenHui on 2018/3/15.
 * phone:13552163255
 */

public class LoginBean {

	/**
	 * status : 1
	 * data : {"uid":"53","name":"闫钧淏","headaddress":"/resources/mrtx/1.jpg","headaddress_sl":"","groupid":"1","sqid":"201","xj":"0","groupName":"B端销售","updateID":null}
	 * errMsg :
	 */

	private int status;
	private DataBean data;
	private String errMsg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public static class DataBean {
		/**
		 * uid : 53
		 * name : 闫钧淏
		 * headaddress : /resources/mrtx/1.jpg
		 * headaddress_sl :
		 * groupid : 1
		 * sqid : 201
		 * xj : 0
		 * groupName : B端销售
		 * updateID : null
		 */

		private String uid;
		private String name;
		private String headaddress;
		private String headaddress_sl;
		private String groupid;
		private String sqid;
		private String xj;
		private String groupName;
		private String updateID;

		public String getD_g() {
			return d_g;
		}

		public void setD_g(String d_g) {
			this.d_g = d_g;
		}

		private String d_g;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getHeadaddress() {
			return headaddress;
		}

		public void setHeadaddress(String headaddress) {
			this.headaddress = headaddress;
		}

		public String getHeadaddress_sl() {
			return headaddress_sl;
		}

		public void setHeadaddress_sl(String headaddress_sl) {
			this.headaddress_sl = headaddress_sl;
		}

		public String getGroupid() {
			return groupid;
		}

		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}

		public String getSqid() {
			return sqid;
		}

		public void setSqid(String sqid) {
			this.sqid = sqid;
		}

		public String getXj() {
			return xj;
		}

		public void setXj(String xj) {
			this.xj = xj;
		}

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public String getUpdateID() {
			return updateID;
		}

		public void setUpdateID(String updateID) {
			this.updateID = updateID;
		}
	}
}
