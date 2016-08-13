package groovy

class Account {

	private balance
	
	Account(balance) {
		this.balance = balance
	}
	
	def void deposit(amount) {
		this.balance += amount
	}
	
	def void withdraw(amount) {
		
		if(amount > balance)
			throw new InsuficientFundsException()
			
		this.balance -= amount
	}
	
	def void accrueInterest() {
		
		def service = new InterestRateService()
		def rate = service.getInterestRate()
		
		def accruedInterest = balance * rate
		deposit(accruedInterest)
	}
	
}
