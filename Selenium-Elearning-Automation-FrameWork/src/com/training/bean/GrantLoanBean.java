package com.training.bean;

public class GrantLoanBean {

		private String memberlogin;
		private String amount;
		private String loan_decsription;
		
		public GrantLoanBean()
		{
			
		}
		
		public GrantLoanBean(String memberlogin,String amount,String loan_description)
		{
			super();
			this.memberlogin = memberlogin;
			this.amount = amount;
			this.loan_decsription = loan_description;
		}

		public String getMemberlogin() {
			return memberlogin;
		}

		public void setMemberlogin(String memberlogin) {
			this.memberlogin = memberlogin;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getLoan_decsription() {
			return loan_decsription;
		}

		public void setLoan_decsription(String loan_decsription) {
			this.loan_decsription = loan_decsription;
		}
		
		
		

}
